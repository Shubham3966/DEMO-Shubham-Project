package MCM_Project.MCM.controller;

import com.google.api.services.drive.model.File;

import MCM_Project.MCM.Google_API.GoogleDriveAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/drive")
public class GoogleDriveController {

    @Autowired
    private GoogleDriveAPI googleDriveAPI;

    @GetMapping("/files")
    public List<File> getFiles() throws IOException {
        return googleDriveAPI.listFiles();
    }

    @GetMapping("/download")
    public void downloadFile() throws IOException {
        googleDriveAPI.downloadFile();
    }
}
