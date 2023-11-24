package projectHHFromLeonid.tracker.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "METRO")
public class Metro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * К задаче исключения дублей: как первый вариант, проверять дубль / не дубль можно по айдишке, которую HH пихает в
     * свой респонс. Нам эту айдишку нужно хранить
     */
    private String naturalId;

    @Column
    private String name;

    @ManyToMany(mappedBy = "metro_stations")
    private List<Address> addressList = new ArrayList<>();
}