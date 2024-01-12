package projectHHFromLeonid.tracker.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VacancyDTOForJdbc {
    @JsonProperty("city")
    private String city;
    @JsonProperty("count")
    private Integer count;

    public VacancyDTOForJdbc() {
        this.city = city;
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public Integer getCount() {
        return count;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyDTOForJdbc that = (VacancyDTOForJdbc) o;
        return Objects.equals(city, that.city) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, count);
    }

    @Override
    public String toString() {
        return "VacancyDTOForJdbc{" +
                "city='" + city + '\'' +
                ", count=" + count +
                '}';
    }
}
