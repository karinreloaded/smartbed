package at.fhv.uct.adaptaal.smartbed.services;

import at.fhv.uct.adaptaal.smartbed.domain.Feature;

public interface FeatureService {
    public Iterable<Feature> findAll();
}
