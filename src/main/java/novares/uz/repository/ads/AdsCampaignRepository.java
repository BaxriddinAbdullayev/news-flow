package novares.uz.repository.ads;

import novares.uz.domain.ads.AdsCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdsCampaignRepository extends JpaRepository<AdsCampaign, Long>, JpaSpecificationExecutor<AdsCampaign> {

    Optional<AdsCampaign> findByIdAndDeletedFalse(Long id);
}