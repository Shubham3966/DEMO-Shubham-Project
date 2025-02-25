package MCM_Project.MCM.controller;

import MCM_Project.MCM.Entities.McmModel;
import MCM_Project.MCM.service.McmModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mcm-models")
public class McmModelController {

    @Autowired
    private McmModelService mcmModelService;

    @GetMapping
    public List<McmModel> getAllMcmModels() {
        return mcmModelService.getAllMcmModels();
    }

    @GetMapping("/{id}")
    public Optional<McmModel> getMcmModelById(@PathVariable Long id) {
        return mcmModelService.getMcmModelById(id);
    }

    @PostMapping
    public McmModel createMcmModel(@RequestBody McmModel mcmModel) {
        return mcmModelService.createMcmModel(mcmModel);
    }

    @DeleteMapping("/{id}")
    public void deleteMcmModel(@PathVariable Long id) {
        mcmModelService.deleteMcmModel(id);
    }
}
