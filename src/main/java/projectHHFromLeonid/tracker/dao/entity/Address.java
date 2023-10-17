package projectHHFromLeonid.tracker.dao.entity;

import integration.projectHHFromLeonid.tracker.MetroName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String building;

    @Getter
    @Setter
    @Column
    private String city;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vacancies")
    private List<Vacancies> vacancies = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "fk_vacancies",
            joinColumns = @JoinColumn(name = "fk_vacancies"),
            inverseJoinColumns = @JoinColumn(name = "metro_name"))
    private Set<Metro> metroName;
}