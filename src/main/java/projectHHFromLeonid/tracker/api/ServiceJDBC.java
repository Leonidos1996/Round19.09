package projectHHFromLeonid.tracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceJDBC {
    public final VacancyRepositoryForRequiest vacancyRepositoryForRequiest;

    @Autowired
    public ServiceJDBC(VacancyRepositoryForRequiest vacancyRepositoryForRequiest) {
        this.vacancyRepositoryForRequiest = vacancyRepositoryForRequiest;
    }

    public List<VacancyDTOForJdbc> convertFromRepo() {

        return vacancyRepositoryForRequiest.getVacanciesGroupedByCity();
    }


}