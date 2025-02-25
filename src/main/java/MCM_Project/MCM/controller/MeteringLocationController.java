package MCM_Project.MCM.controller;

import MCM_Project.MCM.Entities.MeteringLocation;
import MCM_Project.MCM.service.MeteringLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metering-locations")
public class MeteringLocationController {

    @Autowired
    private MeteringLocationService meteringLocationService;

    @GetMapping
    public List<MeteringLocation> getAllMeteringLocations() {
        return meteringLocationService.getAllMeteringLocations();
    }

    @GetMapping("/{id}")
    public Optional<MeteringLocation> getMeteringLocationById(@PathVariable Long id) {
        return meteringLocationService.getMeteringLocationById(id);
    }

    @PostMapping
    public MeteringLocation createMeteringLocation(@RequestBody MeteringLocation meteringLocation) {
        return meteringLocationService.createMeteringLocation(meteringLocation);
    }

    @DeleteMapping("/{id}")
    public void deleteMeteringLocation(@PathVariable Long id) {
        meteringLocationService.deleteMeteringLocation(id);
    }
}
