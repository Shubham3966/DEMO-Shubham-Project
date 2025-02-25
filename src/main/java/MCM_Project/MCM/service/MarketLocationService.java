package MCM_Project.MCM.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MCM_Project.MCM.Entities.MarketLocation;
import MCM_Project.MCM.Repository.MarketLocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarketLocationService {

    @Autowired
    private MarketLocationRepository marketLocationRepository;

    public List<MarketLocation> getAllMarketLocations() {
        return marketLocationRepository.findAll();
    }

    public Optional<MarketLocation> getMarketLocationById(Long id) {
        return marketLocationRepository.findById(id);
    }

    public MarketLocation createMarketLocation(MarketLocation marketLocation) {
        return marketLocationRepository.save(marketLocation);
    }

    public void deleteMarketLocation(Long id) {
        marketLocationRepository.deleteById(id);
    }
}
