
package projectHHFromLeonid.tracker;


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
@Table (name = "SALARY")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String summ;


    @Column
    private String from;

    @Column
    private boolean gross;

    @Column
    private int to;


/*    currency:
    type: integer
    from:
    type: integer
    gross:
    type: boolean
    to:
    type: object
    //hello2*/

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vacancies")
    private List<Vacancies> vacancies = new ArrayList<>();


}
