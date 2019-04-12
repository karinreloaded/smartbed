package at.fhv.uct.adaptaal.smartbed.persistence;

import at.fhv.uct.adaptaal.smartbed.domain.TechnologyFeature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyFeatureRepository extends CrudRepository<TechnologyFeature, Integer> {
    List<TechnologyFeature> findByTechnologyId (@Param("technology_id") Integer technologyId);
    List<TechnologyFeature> findByFeatureId (@Param("feature_id") Integer featureId);
}
