package MCM_Project.MCM.service;

import MCM_Project.MCM.Entities.McmClass;
import MCM_Project.MCM.Repository.McmClassRepository;
import MCM_Project.MCM.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McmClassService {

    @Autowired
    private McmClassRepository mcmClassRepository;

    public List<McmClass> getAllMcmClasses() {
        return mcmClassRepository.findAll();
    }

    public McmClass getMcmClassById(Long id) {
        return mcmClassRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("McmClass not found"));
    }

    public McmClass createMcmClass(McmClass mcmClass) {
        return mcmClassRepository.save(mcmClass);
    }

    public void deleteMcmClass(Long id) {
        if (!mcmClassRepository.existsById(id)) {
            throw new ResourceNotFoundException("McmClass with ID " + id + " not found");
        }
        mcmClassRepository.deleteById(id);
    }
}
