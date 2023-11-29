package projectHHFromLeonid.tracker.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
//TODO: добавить все поля которые приходят от HH. id в том числе
//TODO: добавить many-to-many Metro
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String building;

    @Column
    private String city;

    @Column
    private String description;

    private String naturalId;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //TODO: убрать, посскольку у нас двунаправленная свзяь join column здесь не нужен
    private List<Vacancy> vacancies = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "address_metro",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "metro_id"))
    private Set<Metro> metroStations = new HashSet<>();
}