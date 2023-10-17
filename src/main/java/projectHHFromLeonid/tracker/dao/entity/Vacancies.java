
package projectHHFromLeonid.tracker.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VACANCIES")
public class Vacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Salary salary;

    @ManyToOne
    private Area area;

    @ManyToOne
    private Contacts contacts;

    @ManyToMany
    private List<Metro> metroName = new ArrayList<>();

    @ManyToOne
    private ProfessionalRole professionalRole;

    @ManyToOne
    private Shedule shedule;

    @ManyToOne
    private Type type;

    //добавить ссылки на тип, контакты, Ариа


}
