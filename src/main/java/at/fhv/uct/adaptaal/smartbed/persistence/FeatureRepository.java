package at.fhv.uct.adaptaal.smartbed.persistence;

import at.fhv.uct.adaptaal.smartbed.domain.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends CrudRepository <Feature, Integer> {
    List<Feature> findByLevel (@Param("level") Integer level);
    List<Feature> findByUpperLevelFeature (@Param("upper_level_feature_id") Integer upperLevelFeature);
}
