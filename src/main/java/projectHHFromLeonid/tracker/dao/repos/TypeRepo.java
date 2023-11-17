package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TypeRepo extends CrudRepository<projectHHFromLeonid.tracker.dao.entity.Type, Integer> {

    projectHHFromLeonid.tracker.dao.entity.Type findFirstByNaturalId(String naturalId);
}
