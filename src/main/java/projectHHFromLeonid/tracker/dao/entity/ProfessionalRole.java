
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
@Table(name = "PROFESSIONALROLE")
//TODO убрать лишние строки
public class ProfessionalRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naturalId;

    @Column
    private String name;

    //Переделать на Many-To-Many
    @ManyToMany(mappedBy = "professionalRole", fetch = FetchType.LAZY)
    private List<Vacancy> vacancies = new ArrayList<>();

}