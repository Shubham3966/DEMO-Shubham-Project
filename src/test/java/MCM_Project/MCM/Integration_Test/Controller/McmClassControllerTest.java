package MCM_Project.MCM.Integration_Test.Controller;

import MCM_Project.MCM.Entities.McmClass;
import MCM_Project.MCM.controller.McmClassController;
import MCM_Project.MCM.service.McmClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(McmClassController.class)
class McmClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private McmClassService mcmClassService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllMcmClasses() throws Exception {
        McmClass mcmClass = new McmClass();
        mcmClass.setId(1L);
        mcmClass.setName("Standard Consumption");

        when(mcmClassService.getAllMcmClasses()).thenReturn(Collections.singletonList(mcmClass));

        mockMvc.perform(get("/api/mcm-class"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Standard Consumption_ss"));
    }

    @Test
    void testGetMcmClassById_Success() throws Exception {
        McmClass mcmClass = new McmClass();
        mcmClass.setId(1L);
        mcmClass.setName("Standard Consumption");

        when(mcmClassService.getMcmClassById(1L)).thenReturn(mcmClass);

        mockMvc.perform(get("/api/mcm-class/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Standard Consumption"));
    }

    @Test
    void testGetMcmClassById_NotFound() throws Exception {
        when(mcmClassService.getMcmClassById(1L)).thenThrow(new RuntimeException("McmClass not found"));
    
        mockMvc.perform(get("/api/mcm-class/1"))
                .andExpect(status().isNotFound())  // Expect 404 Not Found
                .andExpect(jsonPath("$.error").value("McmClass not found")); // Check error message
    }

    @Test
    void testCreateMcmClass() throws Exception {
        McmClass mcmClass = new McmClass();
        mcmClass.setId(1L);
        mcmClass.setName("Standard Consumption");

        when(mcmClassService.createMcmClass(any(McmClass.class))).thenReturn(mcmClass);

        mockMvc.perform(post("/api/mcm-class")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mcmClass)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Standard Consumption"));
    }

    @Test
    void testDeleteMcmClass() throws Exception {
        doNothing().when(mcmClassService).deleteMcmClass(1L);

        mockMvc.perform(delete("/api/mcm-class/1"))
                .andExpect(status().isOk());
    }
}