package projectHHFromLeonid.tracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceJDBC {
    public final VacancyRepositoryForRequiest vacancyRepositoryForRequiest;
    public final VacancyDTOAmount vacancyDTOAmount;


    @Autowired
    public ServiceJDBC(VacancyRepositoryForRequiest vacancyRepositoryForRequiest, VacancyDTOAmount vacancyDTOAmount) {
        this.vacancyRepositoryForRequiest = vacancyRepositoryForRequiest;
        this.vacancyDTOAmount = vacancyDTOAmount;
    }

    public List<VacancyDTOForJdbc> convertFromRepo() {

        return vacancyRepositoryForRequiest.getVacanciesGroupedByCity();
    }

//    public List<VacancyDTOForJdbc> convertFromSum() {
//
//        return vacancyRepositoryForRequiest.getVacanciesGroupedBySalaryAndSity();
//    }

    public List<VacancyDTOAmount> convertFromSum(){

        return vacancyRepositoryForRequiest.getVacanciesGroupByAmount();
    }

}