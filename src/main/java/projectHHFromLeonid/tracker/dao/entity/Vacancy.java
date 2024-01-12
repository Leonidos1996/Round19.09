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
//TODO убрать лишние строки
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "address_id"))
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "salary_id"))
    private Salary salary;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "area_id"))
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "contact_id"))
    private Contacts contacts;

    @ManyToMany (cascade = CascadeType.PERSIST)
    //TODO скорее всего здесь должно быть Many-To-Many (у ваканскии может быть много ролей, у проф роли может быть много вакансий)
    private List<ProfessionalRole>  professionalRole = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "schedule_id"))
    //TODO: опечатка должно быть: schedule
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "type_id"))
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "employer_id"))
    private Employer employer;

    private String naturalId;

}