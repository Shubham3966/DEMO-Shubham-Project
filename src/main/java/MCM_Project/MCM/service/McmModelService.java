package MCM_Project.MCM.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MCM_Project.MCM.Entities.McmModel;
import MCM_Project.MCM.Repository.McmModelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class McmModelService {

    @Autowired
    private McmModelRepository mcmModelRepository;

    public List<McmModel> getAllMcmModels() {
        return mcmModelRepository.findAll();
    }

    public Optional<McmModel> getMcmModelById(Long id) {
        return mcmModelRepository.findById(id);
    }

    public McmModel createMcmModel(McmModel mcmModel) {
        return mcmModelRepository.save(mcmModel);
    }

    public void deleteMcmModel(Long id) {
        mcmModelRepository.deleteById(id);
    }
}
