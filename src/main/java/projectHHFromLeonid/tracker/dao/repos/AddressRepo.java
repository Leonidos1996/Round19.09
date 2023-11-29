package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectHHFromLeonid.tracker.dao.entity.Address;


@Repository
public interface AddressRepo extends CrudRepository<Address, Integer> {

    Address findFirstByNaturalId(String naturalId);
}
