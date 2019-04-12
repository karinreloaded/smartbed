package at.fhv.uct.adaptaal.smartbed.persistence;

import at.fhv.uct.adaptaal.smartbed.domain.Technology;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends CrudRepository <Technology, Integer> {
}
