
package projectHHFromLeonid.tracker.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EMPLOYER")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean accredited_it_employer;

    @Column
    private String name;

    @Column
    private Boolean trusted;

    @Column
    private String url;

    private String naturalId;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    private List<Vacancy> vacancies = new ArrayList<>();
}
