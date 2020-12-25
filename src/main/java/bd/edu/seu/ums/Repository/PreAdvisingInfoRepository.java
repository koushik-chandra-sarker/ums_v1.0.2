package bd.edu.seu.ums.Repository;


import bd.edu.seu.ums.Entity.PreAdvisingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PreAdvisingInfoRepository extends JpaRepository<PreAdvisingInfo, Integer> {

    Optional<PreAdvisingInfo> findByRunning(boolean running);

}
