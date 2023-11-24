package projectHHFromLeonid.tracker.dao.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
//TODO убрать лишние строки
public class Contacts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Integer id;

    @Column
    private String email;

    @Column
    private String name;

    private String naturalId;


    @OneToMany(mappedBy = "contacts", fetch = FetchType.LAZY)
    //TODO: убрать, посскольку у нас двунаправленная свзяь join column здесь не нужен
    private Set<Vacancy> vacancies = new HashSet<>();

    @OneToMany
    @JoinTable(name = "contacts_phonesentity",
            joinColumns = @JoinColumn(name = "acontacts_id"),
            inverseJoinColumns = @JoinColumn(name = "phonesentity_id"))
    private Set<Phone> phones = new HashSet<>() ;


}