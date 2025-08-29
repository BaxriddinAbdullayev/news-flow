package novares.uz.repository.ads;

import novares.uz.domain.ads.AdsCreativeTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdsCreativeTranslationRepository extends JpaRepository<AdsCreativeTranslation, Long>, JpaSpecificationExecutor<AdsCreativeTranslation> {

    Optional<AdsCreativeTranslation> findByIdAndDeletedFalse(Long id);

    List<AdsCreativeTranslation> findAllByCreativeIdAndDeletedFalse(Long creativeId);

}