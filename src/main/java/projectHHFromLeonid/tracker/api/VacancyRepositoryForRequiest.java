package projectHHFromLeonid.tracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacancyRepositoryForRequiest {

    private final JdbcTemplate jdbcTemplate;
    private projectHHFromLeonid.tracker.api.VacancyDTOAmount VacancyDTOAmount;

    @Autowired
    public VacancyRepositoryForRequiest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }





    public List<VacancyDTOForJdbc> getVacanciesGroupedByCity() {
        String queryrr = "SELECT city, COUNT(*) as count FROM address GROUP BY city;";

        return jdbcTemplate.query(queryrr, (resultSet, i) -> {
            VacancyDTOForJdbc vacancyDTO = new VacancyDTOForJdbc();
            vacancyDTO.setCity(resultSet.getString("city"));
            vacancyDTO.setCount(resultSet.getInt("count"));
            return vacancyDTO;
        });
    }

    public List<VacancyDTOAmount> getVacanciesGroupByAmount(){
        String cityAbgSalary = "select \n" +
                "    avg(cast(string_to as Integer) + cast(string_from as Integer) / 2) as average_sum, \n" +
                "    city\n" +
                "from salary s \n" +
                "    inner join vacancy v on v.salary_id = s.id \n" +
                "    inner join address a on v.address_id = a.id \n" +
                "where string_to is not null \n" +
                "    and string_from is not null \n" +
                "    and city is not null\n" +
                "group by city\n" +
                "order by average_sum desc;";
        return jdbcTemplate.query(cityAbgSalary, (resultSet, i) -> {
            VacancyDTOAmount vacancyDTOAmount = new VacancyDTOAmount();
            vacancyDTOAmount.setCity(resultSet.getString("city"));
            vacancyDTOAmount.setSalary_amount(resultSet.getInt("average_sum"));
            return VacancyDTOAmount;
        });
        /*    public List<VacancyDTOForJdbc> getVacanciesGroupedBySalaryAndSity() {
        String cityAbgSalary = "select \n" +
                "    avg(cast(string_to as Integer) + cast(string_from as Integer) / 2) as average_sum, \n" +
                "    city\n" +
                "from salary s \n" +
                "    inner join vacancy v on v.salary_id = s.id \n" +
                "    inner join address a on v.address_id = a.id \n" +
                "where string_to is not null \n" +
                "    and string_from is not null \n" +
                "    and city is not null\n" +
                "group by city\n" +
                "order by average_sum desc;";
        return jdbcTemplate.query(cityAbgSalary, (resultSet, i) -> {
            VacancyDTOForJdbc vacancyDTO = new VacancyDTOForJdbc();
            vacancyDTO.setCity(resultSet.getString("city"));
            vacancyDTO.setSalary_amount(resultSet.getInt("average_sum"));

            return vacancyDTO;
        });
    }*/
        
    }

}