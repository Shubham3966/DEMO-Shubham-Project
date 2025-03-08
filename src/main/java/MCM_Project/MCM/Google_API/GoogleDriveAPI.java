package MCM_Project.MCM.Google_API;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveAPI {
    private static final String APPLICATION_NAME = "Google Drive API Java Spring Boot";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_READONLY);
    private static final String LOCAL_FOLDER_PATH = "C:\\Users\\I518454\\Downloads\\GoogleDriveFiles\\";

    private Drive driveService;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        this.driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = new ClassPathResource("credentials.json").getInputStream();
        if (in == null) {
            throw new FileNotFoundException("Resource not found: credentials.json");
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8181).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public List<File> listFiles() throws IOException {
        FileList result = driveService.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name , mimeType)")
                .setQ("mimeType != 'application/vnd.google-apps.folder'") // Exclude folders
                .execute();
        return result.getFiles();
    }

    public void downloadFile() throws IOException {
        List<File> files = listFiles();
    
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
            return;
        }
    
        for (File file : files) {
            System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
    
            // Define the local file path
            java.io.File localFile = new java.io.File(LOCAL_FOLDER_PATH + file.getName());
    
            // Ensure the directory exists
            if (!localFile.getParentFile().exists()) {
                localFile.getParentFile().mkdirs();
            }
    
            OutputStream outputStream = new FileOutputStream(localFile);
    
            // Get the MIME type safely
            String mimeType = file.getMimeType();
            if (mimeType == null) {
                System.out.println("Skipping file (no MIME type): " + file.getName());
                continue; // Skip files with no MIME type
            }
    
            try {
                // Check if the file is a Google Docs/Sheets/Slides file
                if ("application/vnd.google-apps.document".equals(mimeType)) {
                    driveService.files().export(file.getId(), "application/pdf")
                            .executeMediaAndDownloadTo(outputStream);
                } else if ("application/vnd.google-apps.spreadsheet".equals(mimeType)) {
                    driveService.files().export(file.getId(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                            .executeMediaAndDownloadTo(outputStream);
                } else if ("application/vnd.google-apps.presentation".equals(mimeType)) {
                    driveService.files().export(file.getId(), "application/pdf")
                            .executeMediaAndDownloadTo(outputStream);
                } else {
                    // Direct download for binary files (e.g., PDFs, images)
                    driveService.files().get(file.getId())
                            .executeMediaAndDownloadTo(outputStream);
                }
    
                outputStream.flush();
                outputStream.close();
                System.out.println("Downloaded: " + localFile.getAbsolutePath());
    
            } catch (com.google.api.client.http.HttpResponseException e) {
                System.err.println("Failed to download file: " + file.getName() + " - " + e.getMessage());
            }
        }
    }    
}
