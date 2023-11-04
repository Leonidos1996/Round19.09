
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
@Table (name = "SALARY")
//TODO убрать закомментированный код
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String summ;

    @Column
    private String stringFrom;

    @Column
    private boolean gross;

    @Column
    private String stringTo;


/*    currency:
    type: integer
    from:
    type: integer
    gross:
    type: boolean
    to:
    type: object
    //hello2*/

    @OneToMany(mappedBy = "salary", fetch = FetchType.LAZY)
    //TODO: убрать, поскольку у нас двунаправленная свзяь join column здесь не нужен
    @JoinColumn(name = "fk_vacancies")
    private List<Vacancy> vacancies = new ArrayList<>();


}
