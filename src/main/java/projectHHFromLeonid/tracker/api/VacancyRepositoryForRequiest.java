package projectHHFromLeonid.tracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacancyRepositoryForRequiest {

    private final JdbcTemplate jdbcTemplate;

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
}