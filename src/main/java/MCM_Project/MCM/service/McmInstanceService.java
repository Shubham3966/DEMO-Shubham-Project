package MCM_Project.MCM.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MCM_Project.MCM.Entities.McmInstance;
import MCM_Project.MCM.Repository.McmInstanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class McmInstanceService {

    @Autowired
    private McmInstanceRepository mcmInstanceRepository;

    public List<McmInstance> getAllMcmInstances() {
        return mcmInstanceRepository.findAll();
    }

    public Optional<McmInstance> getMcmInstanceById(Long id) {
        return mcmInstanceRepository.findById(id);
    }

    public McmInstance createMcmInstance(McmInstance mcmInstance) {
        return mcmInstanceRepository.save(mcmInstance);
    }

    public void deleteMcmInstance(Long id) {
        mcmInstanceRepository.deleteById(id);
    }
}
