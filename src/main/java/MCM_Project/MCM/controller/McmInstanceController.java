package MCM_Project.MCM.controller;


import MCM_Project.MCM.Entities.McmInstance;
import MCM_Project.MCM.service.McmInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mcm-instances")
public class McmInstanceController {

    @Autowired
    private McmInstanceService mcmInstanceService;

    @GetMapping
    public List<McmInstance> getAllMcmInstances() {
        return mcmInstanceService.getAllMcmInstances();
    }

    @GetMapping("/{id}")
    public Optional<McmInstance> getMcmInstanceById(@PathVariable Long id) {
        return mcmInstanceService.getMcmInstanceById(id);
    }

    @PostMapping
    public McmInstance createMcmInstance(@RequestBody McmInstance mcmInstance) {
        return mcmInstanceService.createMcmInstance(mcmInstance);
    }

    @DeleteMapping("/{id}")
    public void deleteMcmInstance(@PathVariable Long id) {
        mcmInstanceService.deleteMcmInstance(id);
    }
}
