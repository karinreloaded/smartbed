package at.fhv.uct.adaptaal.smartbed.controllers;

import at.fhv.uct.adaptaal.smartbed.domain.Feature;
import at.fhv.uct.adaptaal.smartbed.domain.TechnologyFeature;
import at.fhv.uct.adaptaal.smartbed.persistence.FeatureRepository;
import at.fhv.uct.adaptaal.smartbed.persistence.TechnologyFeatureRepository;

import at.fhv.uct.adaptaal.smartbed.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/feature")
public class FeatureController {

    private final FeatureRepository featureRepository;
    private final TechnologyFeatureRepository technologyFeatureRepository;

    @Autowired
    public FeatureController(FeatureRepository featureRepository, TechnologyFeatureRepository technologyFeatureRepository) {
        this.featureRepository = featureRepository;
        this.technologyFeatureRepository = technologyFeatureRepository;
    }

    @GetMapping (path = "/by/id")
    public @ResponseBody Optional<Feature> getById(@RequestParam("id") Integer id) {
        return featureRepository.findById(id);
    }

    @GetMapping (path = "/by/level")
    public @ResponseBody Iterable<Feature> getByLevel(@RequestParam("level") Integer level) {
        return featureRepository.findByLevel(level);
    }

    @GetMapping (path = "/by/upperlevel")
    public @ResponseBody Iterable<Feature> getByUpperLevelFeature(@RequestParam("upperlevel") Integer upperlevel) {
        return featureRepository.findByUpperLevelFeature(upperlevel);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Feature> getAll() {
        return featureRepository.findAll();
    }

    @GetMapping(path = "/by/technology")
    public @ResponseBody Iterable<Serializable> getAllByTechnologyId(@RequestParam("id") Integer technologyId) {
        return technologyFeatureRepository.findByTechnologyId(technologyId).stream()
                .map(x -> PersistenceUtil.getProxyId(x.getFeature()))
                .distinct().sorted().collect(Collectors.toList());
    }

    @PostMapping(path = "/put")
    public @ResponseBody void createOrUpdate(@RequestBody Feature feature) {
        featureRepository.save(feature);
    }

    @GetMapping(path = "/put/technology")
    public @ResponseBody void addRelationToTechnology(@RequestBody TechnologyFeature technologyFeature) {
        technologyFeatureRepository.save(technologyFeature);
    }

}
