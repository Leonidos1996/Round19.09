package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectHHFromLeonid.tracker.dao.entity.Area;

@Repository
public interface AreaRepo extends CrudRepository<Area, Integer> {

    Area findFirstByNaturalId(String naturalId);
}
