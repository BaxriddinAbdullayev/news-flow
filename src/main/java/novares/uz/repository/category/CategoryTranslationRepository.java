package novares.uz.repository.category;

import novares.uz.domain.category.CategoryTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryTranslationRepository extends JpaRepository<CategoryTranslation, Long>, JpaSpecificationExecutor<CategoryTranslation> {

    List<CategoryTranslation> findAllByCategoryIdAndDeletedFalse(Long categoryId);

    Optional<CategoryTranslation> findByIdAndDeletedFalse(Long categoryId);

}