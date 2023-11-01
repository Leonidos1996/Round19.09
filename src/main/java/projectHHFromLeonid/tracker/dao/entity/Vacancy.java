
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
@Table(name = "VACANCY")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private Salary salary;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private Area area;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private Contacts contacts;

    @ManyToMany (cascade = CascadeType.PERSIST)
    private List<Metro> metroName = new ArrayList<>();

    @ManyToOne (cascade = CascadeType.PERSIST)
    private ProfessionalRole professionalRole;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private Shedule shedule;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private Type type;



}
