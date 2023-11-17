package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PhoneRepo extends CrudRepository<projectHHFromLeonid.tracker.dao.entity.Phone, Integer> {

    projectHHFromLeonid.tracker.dao.entity.Phone findFirstByNaturalId(String naturalId);
}
