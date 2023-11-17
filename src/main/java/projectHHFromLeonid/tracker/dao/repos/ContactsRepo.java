package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectHHFromLeonid.tracker.dao.entity.Contacts;


@Repository
public interface ContactsRepo extends CrudRepository<Contacts, Integer> {

    Contacts findFirstByNaturalId(String naturalId);
}
