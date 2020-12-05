package bd.edu.seu.ums.Repository;



import bd.edu.seu.ums.Entity.PreAdvisingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreAdvisingInfoRepository extends JpaRepository<PreAdvisingInfo, Integer> {



}
