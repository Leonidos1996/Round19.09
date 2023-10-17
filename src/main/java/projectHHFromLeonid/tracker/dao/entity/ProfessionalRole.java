
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROFESSIONALROLE")
public class ProfessionalRole {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Integer published;

    @Column
    private String relation;

    @Column
    private boolean responseLetterRequired;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vacancies")
    private List<Vacancy> vacancies = new ArrayList<>();

}