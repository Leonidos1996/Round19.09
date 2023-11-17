package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfessionalRoleRepo extends CrudRepository<projectHHFromLeonid.tracker.dao.entity.ProfessionalRole, Integer> {


    projectHHFromLeonid.tracker.dao.entity.ProfessionalRole findFirstByNaturalId(String naturalId);

}