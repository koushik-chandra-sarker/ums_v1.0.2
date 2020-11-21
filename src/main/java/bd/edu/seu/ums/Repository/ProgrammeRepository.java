package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Programme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ProgrammeRepository extends CrudRepository<Programme,String> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query(value = "update programme set school_id = null where school_id = ?;",nativeQuery = true)
    int setSchoolNull(int school_id);
}
