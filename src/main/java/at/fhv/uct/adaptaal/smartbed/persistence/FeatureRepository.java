package at.fhv.uct.adaptaal.smartbed.persistence;

import at.fhv.uct.adaptaal.smartbed.domain.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository <Feature, Integer> {
}
