package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.IdSeq;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdSeqRepository extends CrudRepository<IdSeq, Integer> {

    @Query(value = "select * from id_seq where year = ? and type =?", nativeQuery = true)
    IdSeq findByYearAndType(int year, String type);

    @Query(value = "select * from id_seq where year = ? and flag = ?", nativeQuery = true)
    IdSeq findByYearAndFlag(int year, int flag);
}
