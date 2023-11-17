package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectHHFromLeonid.tracker.dao.entity.Vacancy;

@Repository
public interface VacancyRepo extends CrudRepository<Vacancy, Integer> {

    Vacancy findFirstByNaturalId(String naturalId);
}
