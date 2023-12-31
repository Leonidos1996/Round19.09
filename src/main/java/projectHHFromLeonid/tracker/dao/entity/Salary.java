
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String currency;

    @Column
    private String stringFrom;

    @Column
    private boolean gross;

    @Column
    private String stringTo;

    private String naturalId;

    @OneToMany(mappedBy = "salary", fetch = FetchType.LAZY)
    //TODO: убрать, поскольку у нас двунаправленная свзяь join column здесь не нужен
    private List<Vacancy> vacancies = new ArrayList<>();
}