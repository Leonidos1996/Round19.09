package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectHHFromLeonid.tracker.dao.entity.Employer;

@Repository
public interface EmployerRepo extends CrudRepository<Employer, Integer> {


    Employer findFirstByNaturalId(String naturalId);

}
