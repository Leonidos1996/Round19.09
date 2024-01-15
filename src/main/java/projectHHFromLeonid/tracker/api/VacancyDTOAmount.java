package projectHHFromLeonid.tracker.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VacancyDTOAmount {

    public VacancyDTOAmount() {
        this.salary_amount = salary_amount;
        this.city = city;
    }

    public Integer getSalary_amount() {
        return salary_amount;
    }

    public void setSalary_amount(Integer salary_amount) {
        this.salary_amount = salary_amount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyDTOAmount that = (VacancyDTOAmount) o;
        return Objects.equals(salary_amount, that.salary_amount) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary_amount, city);
    }

    @JsonProperty("salary_amount")
    private Integer salary_amount;

    @JsonProperty("city")
    private String city;

    }

