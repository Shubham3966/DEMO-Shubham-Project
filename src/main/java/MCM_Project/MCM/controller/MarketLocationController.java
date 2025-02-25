package MCM_Project.MCM.controller;

import MCM_Project.MCM.Entities.MarketLocation;
import MCM_Project.MCM.service.MarketLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/market-locations")
public class MarketLocationController {

    @Autowired
    private MarketLocationService marketLocationService;

    @GetMapping
    public List<MarketLocation> getAllMarketLocations() {

        
        return marketLocationService.getAllMarketLocations();
    }

    @GetMapping("/{id}")
    public Optional<MarketLocation> getMarketLocationById(@PathVariable Long id) {
        return marketLocationService.getMarketLocationById(id);
    }

    @PostMapping
    public MarketLocation createMarketLocation(@RequestBody MarketLocation marketLocation) {
        return marketLocationService.createMarketLocation(marketLocation);
    }

    @DeleteMapping("/{id}")
    public void deleteMarketLocation(@PathVariable Long id) {
        marketLocationService.deleteMarketLocation(id);
    }
}
