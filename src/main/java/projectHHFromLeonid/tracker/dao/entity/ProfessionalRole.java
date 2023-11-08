
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
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    /*@Column
    private String email;

    @Column
    private Integer published;

    @Column
    private String relation;

    @Column
    private boolean responseLetterRequired;
*/
    //Переделать на Many-To-Many
    @ManyToMany(mappedBy = "professionalRole", fetch = FetchType.LAZY)
    private List<Vacancy> vacancies = new ArrayList<>();

}