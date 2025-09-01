package novares.uz.repository.tag;

import novares.uz.domain.tag.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    Optional<Tag> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM news_tag WHERE news_id = :newsId", nativeQuery = true)
    void deleteByNewsId(@Param("newsId") Long newsId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO news_tag (news_id, tag_id) VALUES (:newsId, :tagId)", nativeQuery = true)
    void insertNewsTag(@Param("newsId") Long newsId, @Param("tagId") Long tagId);

    List<Tag> findAllByActiveTrueAndDeletedFalse();


}