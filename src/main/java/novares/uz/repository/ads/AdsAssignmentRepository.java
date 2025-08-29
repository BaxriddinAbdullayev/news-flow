package novares.uz.repository.ads;

import novares.uz.domain.ads.AdsAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdsAssignmentRepository extends JpaRepository<AdsAssignment, Long>, JpaSpecificationExecutor<AdsAssignment> {

    Optional<AdsAssignment> findByIdAndDeletedFalse(Long id);

}