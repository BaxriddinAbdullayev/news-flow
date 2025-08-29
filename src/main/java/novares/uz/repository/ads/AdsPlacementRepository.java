package novares.uz.repository.ads;

import novares.uz.domain.ads.AdsPlacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdsPlacementRepository extends JpaRepository<AdsPlacement, Long>, JpaSpecificationExecutor<AdsPlacement> {

    Optional<AdsPlacement> findByIdAndDeletedFalse(Long id);

}