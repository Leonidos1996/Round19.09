package projectHHFromLeonid.tracker.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectHHFromLeonid.tracker.dao.entity.Metro;

@Repository
public interface MetroRepo extends CrudRepository<Metro, Integer> {

    /**
     * Как ты знаешь, спринг за нас может составлять SQL запросы. Для того чтобы этот функционал работал нужно правильно
     * написать имя метода. Например findFirstByNaturalId(String naturalId) найдет первый попавшийся Metro по naturalId.
     * Синтаксис составления правильно имени метода, можно посмотреть https://www.baeldung.com/spring-data-derived-queries
     * Идея тоже помогает с этими запросами и при написании подсказывает какие имена подставлять. Попробуй увидишь,
     * что я имею в виду
     * @param naturalId
     * @return
     */
    Metro findFirstByNaturalId(String naturalId);
}
