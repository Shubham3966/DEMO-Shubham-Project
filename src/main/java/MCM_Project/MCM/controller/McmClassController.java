package MCM_Project.MCM.controller;

import MCM_Project.MCM.Entities.McmClass;
import MCM_Project.MCM.service.McmClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/mcm-class")
@Tag(name = "MCM Class", description = "APIs for managing MCM Classes")
public class McmClassController {

    @Autowired
    private McmClassService mcmClassService;

    @GetMapping
    @Operation(summary = "Get all MCM Classes", description = "Retrieve a list of all available MCM Classes")
    public List<McmClass> getAllMcmClasses() {
        return mcmClassService.getAllMcmClasses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get MCM Class by ID", description = "Retrieve a specific MCM Class using its ID")
    public ResponseEntity<?> getMcmClassById(@PathVariable Long id) {

        try {
            McmClass mcmClass = mcmClassService.getMcmClassById(id);
            return ResponseEntity.ok(mcmClass);
        } catch (RuntimeException e) { // Handle the exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));

        }
    }

    @PostMapping
    @Operation(summary = "Create MCM Class", description = "Create a new MCM Class")
    public McmClass createMcmClass(@RequestBody McmClass mcmClass) {
        return mcmClassService.createMcmClass(mcmClass);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete MCM Class", description = "Delete an MCM Class by ID")
    public void deleteMcmClass(@PathVariable Long id) {
        mcmClassService.deleteMcmClass(id);
    }
}
