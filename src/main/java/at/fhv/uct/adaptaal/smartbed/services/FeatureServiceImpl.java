package at.fhv.uct.adaptaal.smartbed.services;

import at.fhv.uct.adaptaal.smartbed.domain.Feature;
import at.fhv.uct.adaptaal.smartbed.persistence.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("featureService")
public class FeatureServiceImpl implements FeatureService{

    private FeatureRepository featureRepository;
    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Iterable<Feature> findAll() {
        return featureRepository.findAll();
    }
}
