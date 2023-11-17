package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepo extends CrudRepository<projectHHFromLeonid.tracker.dao.entity.Salary, Integer> {

    projectHHFromLeonid.tracker.dao.entity.Salary findFirstByNaturalId(String naturalId);
}
