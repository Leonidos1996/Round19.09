package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends CrudRepository<projectHHFromLeonid.tracker.dao.entity.Schedule, Integer> {
    projectHHFromLeonid.tracker.dao.entity.Schedule findFirstByNaturalId(String naturalId);
}
