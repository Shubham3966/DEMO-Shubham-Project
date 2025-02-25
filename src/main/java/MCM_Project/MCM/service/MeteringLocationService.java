package MCM_Project.MCM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MCM_Project.MCM.Entities.MeteringLocation;
import MCM_Project.MCM.Repository.MeteringLocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeteringLocationService {

    @Autowired
    private MeteringLocationRepository meteringLocationRepository;

    public List<MeteringLocation> getAllMeteringLocations() {
        return meteringLocationRepository.findAll();
    }

    public Optional<MeteringLocation> getMeteringLocationById(Long id) {
        return meteringLocationRepository.findById(id);
    }

    public MeteringLocation createMeteringLocation(MeteringLocation meteringLocation) {
        return meteringLocationRepository.save(meteringLocation);
    }

    public void deleteMeteringLocation(Long id) {
        meteringLocationRepository.deleteById(id);
    }
}
