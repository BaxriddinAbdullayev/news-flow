package novares.uz.repository.category;

import novares.uz.collection.CategoryCollection;
import novares.uz.collection.NewsCollection;
import novares.uz.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Optional<Category> findByIdAndDeletedFalse(Long id);

    Page<Category> findAllByDeletedFalse(Pageable pageable);

    @Query(value = """
            select
                c.id as id,
                c.parent_id as parent_id,
                c.is_active as is_active,
                ct.category_id as category_id,
                ct.lang as lang,
                ct.slug as slug,
                ct.title as title,
                ct.description as description
            from category c
                left join category_translation ct on c.id = ct.category_id and ct.is_deleted = false
            where c.is_deleted = false
                AND c.is_active = true
                AND (:lang is null or LOWER(ct.lang) = LOWER(:lang))
            """,
            countQuery = """
                    select
                        count(*)
                    from category c
                             left join category_translation ct on c.id = ct.category_id and ct.is_deleted = false
                    where c.is_deleted = false
                      AND c.is_active = true
                      AND (:lang is null or LOWER(ct.lang) = LOWER(:lang))
                    """,
            nativeQuery = true)
    Page<CategoryCollection> getCategoryFilteredForPublic(@Param("lang") String lang, Pageable pageable);

}