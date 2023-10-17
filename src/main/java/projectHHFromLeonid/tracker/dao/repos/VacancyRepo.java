package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import projectHHFromLeonid.tracker.dao.entity.Vacancy;

public interface VacancyRepo extends CrudRepository<Vacancy, Integer> {
}
