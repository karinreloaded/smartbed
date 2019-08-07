package at.fhv.uct.adaptaal.smartbed.controllers;

import at.fhv.uct.adaptaal.smartbed.domain.TechnologyFeature;
import at.fhv.uct.adaptaal.smartbed.persistence.TechnologyFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/technology_feature")
public class TechnologyFeatureController {

        private final TechnologyFeatureRepository technologyFeatureRepository;

        @Autowired
        public TechnologyFeatureController(TechnologyFeatureRepository technologyFeatureRepository) {
            this.technologyFeatureRepository = technologyFeatureRepository;
        }

    @PostMapping(path = "/put")
    public @ResponseBody
    void createOrUpdate(@RequestBody TechnologyFeature technologyFeature) {
        technologyFeatureRepository.save(technologyFeature);
    }
}
