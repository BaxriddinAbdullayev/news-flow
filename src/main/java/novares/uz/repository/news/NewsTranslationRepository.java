package novares.uz.repository.news;

import novares.uz.domain.news.NewsTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsTranslationRepository extends JpaRepository<NewsTranslation, Long>, JpaSpecificationExecutor<NewsTranslation> {

    Optional<NewsTranslation> findByIdAndDeletedFalse(Long id);

    List<NewsTranslation> findByNewsIdAndDeletedFalse(Long newsId);

    List<NewsTranslation> findByNewsIdAndDeletedTrue(Long newsId);

    List<NewsTranslation> findByNewsId(Long newsId);

}