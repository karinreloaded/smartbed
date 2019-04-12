package at.fhv.uct.adaptaal.smartbed.controllers;

import at.fhv.uct.adaptaal.smartbed.domain.Technology;
import at.fhv.uct.adaptaal.smartbed.domain.TechnologyFeature;
import at.fhv.uct.adaptaal.smartbed.persistence.TechnologyFeatureRepository;
import at.fhv.uct.adaptaal.smartbed.persistence.TechnologyRepository;
import at.fhv.uct.adaptaal.smartbed.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/technology")
public class TechnologyController {

    private final TechnologyRepository technologyRepository;
    private final TechnologyFeatureRepository technologyFeatureRepository;

    @Autowired
    public TechnologyController(TechnologyRepository technologyRepository, TechnologyFeatureRepository technologyFeatureRepository) {
        this.technologyRepository = technologyRepository;
        this.technologyFeatureRepository = technologyFeatureRepository;
    }

    @GetMapping
    public @ResponseBody Optional<Technology> getById(@RequestParam("id") Integer id) {
        return technologyRepository.findById(id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Technology> getAll() {
        return technologyRepository.findAll();
    }

    @GetMapping(path = "/by/feature")
    public @ResponseBody Iterable<Serializable> getAllByFeatureId(@RequestParam("id") Integer featureId) {
        return technologyFeatureRepository.findByFeatureId(featureId).stream()
                .map(x -> PersistenceUtil.getProxyId(x.getTechnology()))
                .distinct().sorted().collect(Collectors.toList());
    }

    @PostMapping(path = "/put")
    public @ResponseBody void createOrUpdate(@RequestBody Technology technology) {
        technologyRepository.save(technology);
    }

    @GetMapping(path = "/put/feature")
    public @ResponseBody void addRelationToFeature(@RequestBody TechnologyFeature technologyFeature) {
        technologyFeatureRepository.save(technologyFeature);
    }
}
