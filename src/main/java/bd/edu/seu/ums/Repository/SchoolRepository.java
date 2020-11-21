package bd.edu.seu.ums.Repository;


import bd.edu.seu.ums.Entity.School;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query(value = "update school set campus_id = null where campus_id = ?;",nativeQuery = true)
    int setCampusNull(int campus_id);
}
