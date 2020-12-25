package bd.edu.seu.ums.Repository;



import bd.edu.seu.ums.Entity.AdvisingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvisingInfoRepository extends JpaRepository<AdvisingInfo, Integer> {

    Optional<AdvisingInfo> findByRunning(boolean running);

}
