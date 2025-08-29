package novares.uz.repository.ads;

import novares.uz.domain.ads.AdsCreative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdsCreativeRepository extends JpaRepository<AdsCreative, Long>, JpaSpecificationExecutor<AdsCreative> {

    Optional<AdsCreative> findByIdAndDeletedFalse(Long id);

    Page<AdsCreative> findAllByDeletedFalse(Pageable pageable);
}