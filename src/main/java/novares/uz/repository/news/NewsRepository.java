package novares.uz.repository.news;

import novares.uz.collection.NewsCollection;
import novares.uz.domain.news.News;
import novares.uz.enums.NewsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    Optional<News> findByIdAndDeletedFalse(Long id);

    @Query(value = """
            SELECT
                n.id as id,
                n.author_id as author_id,
                n.category_id as category_id,
                n.file_id as file_id,
                n.status AS status,
                n.is_featured AS is_featured,
                n.publish_at AS publish_at,
                n.unpublish_at AS unpublish_at,
                n.deleted_at AS deleted_at,
                nt.id AS translation_id,
                nt.news_id AS translation_news_id,
                nt.lang AS translation_lang,
                nt.title AS translation_title,
                nt.slug AS translation_slug,
                nt.summary AS translation_summary,
                nt.content AS translation_content,
                nt.meta_title AS translation_meta_title,
                nt.meta_description AS translation_meta_description
            FROM news n
                     LEFT JOIN news_translation nt ON n.id = nt.news_id AND nt.is_deleted = false
            WHERE n.is_deleted = false
              AND (:status IS NULL OR n.status = :status)
              AND (:lang is null or LOWER(nt.lang) = LOWER(:lang))
              AND (CAST(:from AS timestamptz) IS NULL OR n.publish_at >= CAST(:from AS timestamptz))
              AND (CAST(:to   AS timestamptz) IS NULL OR n.publish_at <= CAST(:to   AS timestamptz))
              and exists(select 1
                         from news_tag nt
                                  left join tag t on nt.tag_id = t.id
                         where t.is_deleted = false
                           AND (:tag IS NULL OR LOWER(t.code) = LOWER(:tag))
                           and nt.news_id = n.id)
              and exists(select 1
                         from auth_users au
                         where au.is_deleted = false
                            and au.id = n.author_id
                           AND (:authorId IS NULL OR au.id = :authorId))
              and exists(select 1
                         from category c
                                left join category_translation ct on c.id = ct.category_id and ct.is_deleted = false
                         where c.is_deleted = false
                            and c.id = n.category_id
                            AND (:lang is null or LOWER(ct.lang) = LOWER(:lang))
                            AND (:categoryId IS NULL OR c.id = :categoryId))
            """,
            countQuery = """
                    SELECT count(*)
                    FROM news n
                             LEFT JOIN news_translation nt ON n.id = nt.news_id AND nt.is_deleted = false
                    WHERE n.is_deleted = false
                      AND (:status IS NULL OR n.status = :status)
                      AND (:lang is null or LOWER(nt.lang) = LOWER(:lang))
                      AND (CAST(:from AS timestamptz) IS NULL OR n.publish_at >= CAST(:from AS timestamptz))
                      AND (CAST(:to   AS timestamptz) IS NULL OR n.publish_at <= CAST(:to   AS timestamptz))
                      and exists(select 1
                                 from news_tag nt
                                          left join tag t on nt.tag_id = t.id
                                 where t.is_deleted = false
                                   AND (:tag IS NULL OR LOWER(t.code) = LOWER(:tag))
                                   and nt.news_id = n.id)
                      and exists(select 1
                                 from auth_users au
                                 where au.is_deleted = false
                                   and au.id = n.author_id
                                   AND (:authorId IS NULL OR au.id = :authorId))
                      and exists(select 1
                                 from category c
                                          left join category_translation ct on c.id = ct.category_id and ct.is_deleted = false
                                 where c.is_deleted = false
                                   and c.id = n.category_id
                                   AND (:lang is null or LOWER(ct.lang) = LOWER(:lang))
                                   AND (:categoryId IS NULL OR c.id = :categoryId))                                      
                    """,
            nativeQuery = true)
    Page<NewsCollection> getNewsFilteredForAdmin(
            @Param("status") String status,
            @Param("authorId") Long authorId,
            @Param("categoryId") Long categoryId,
            @Param("tag") String tag,
            @Param("lang") String lang,
            @Param("from") ZonedDateTime from,
            @Param("to") ZonedDateTime to,
            Pageable pageable
    );

    @Query(value = """
            SELECT
                n.id as id,
                n.author_id as author_id,
                n.category_id as category_id,
                n.file_id as file_id,
                n.status AS status,
                n.is_featured AS is_featured,
                n.publish_at AS publish_at,
                n.unpublish_at AS unpublish_at,
                n.deleted_at AS deleted_at,
                nt.id AS translation_id,
                nt.news_id AS translation_news_id,
                nt.lang AS translation_lang,
                nt.title AS translation_title,
                nt.slug AS translation_slug,
                nt.summary AS translation_summary,
                nt.content AS translation_content,
                nt.meta_title AS translation_meta_title,
                nt.meta_description AS translation_meta_description
            FROM news n
                     LEFT JOIN news_translation nt ON n.id = nt.news_id AND nt.is_deleted = false
            WHERE n.is_deleted = false
              AND nt.slug = :slug
              AND (:lang is null or LOWER(nt.lang) = LOWER(:lang))
            """,
            countQuery = """
                    SELECT count(*)
                    FROM news n
                             LEFT JOIN news_translation nt ON n.id = nt.news_id AND nt.is_deleted = false
                    WHERE n.is_deleted = false
                      AND nt.slug = :slug
                      AND (:lang is null or LOWER(nt.lang) = LOWER(:lang))                                   
                    """,
            nativeQuery = true)
    Page<NewsCollection> getNewsBySlugAndLang(
            @Param("slug") String slug,
            @Param("lang") String lang,
            Pageable pageable
    );

    @Query(value = """
            SELECT
                n.id as id,
                n.author_id as author_id,
                n.category_id as category_id,
                n.file_id as file_id,
                n.status AS status,
                n.is_featured AS is_featured,
                n.publish_at AS publish_at,
                n.unpublish_at AS unpublish_at,
                n.deleted_at AS deleted_at,
                nt.id AS translation_id,
                nt.news_id AS translation_news_id,
                nt.lang AS translation_lang,
                nt.title AS translation_title,
                nt.slug AS translation_slug,
                nt.summary AS translation_summary,
                nt.content AS translation_content,
                nt.meta_title AS translation_meta_title,
                nt.meta_description AS translation_meta_description
            FROM news n
                     LEFT JOIN news_translation nt ON n.id = nt.news_id AND nt.is_deleted = false
            WHERE n.is_deleted = false
              AND (:lang is null or LOWER(nt.lang) = LOWER(:lang))
              and exists(select 1
                         from news_tag nt
                                  left join tag t on nt.tag_id = t.id and t.is_deleted = false
                         where t.is_deleted = false
                           AND t.is_active = true
                           AND (:tag IS NULL OR LOWER(t.code) = LOWER(:tag))
                           and nt.news_id = n.id)
              and exists(select 1
                         from category c
                                  left join category_translation ct on c.id = ct.category_id and ct.is_deleted = false
                         where c.is_deleted = false
                           AND c.is_active = true
                           AND c.id = n.category_id
                           AND (:lang is null or LOWER(ct.lang) = LOWER(:lang))
                           AND (:categoryId IS NULL OR c.id = :categoryId))
            """,
            countQuery = """
                    select count(*)
                    FROM news n
                             LEFT JOIN news_translation nt ON n.id = nt.news_id AND nt.is_deleted = false
                    WHERE n.is_deleted = false
                      AND (:lang is null or LOWER(nt.lang) = LOWER(:lang))
                      and exists(select 1
                                 from news_tag nt
                                          left join tag t on nt.tag_id = t.id
                                 where t.is_deleted = false
                                   AND (:tag IS NULL OR LOWER(t.code) = LOWER(:tag))
                                   and nt.news_id = n.id)
                      and exists(select 1
                                 from category c
                                          left join category_translation ct on c.id = ct.category_id and ct.is_deleted = false
                                 where c.is_deleted = false
                                   and c.id = n.category_id
                                   AND (:lang is null or LOWER(ct.lang) = LOWER(:lang))
                                   AND (:categoryId IS NULL OR c.id = :categoryId))
                    """,
            nativeQuery = true)
    Page<NewsCollection> getNewsFilteredForPublic(
            @Param("categoryId") Long categoryId,
            @Param("tag") String tag,
            @Param("lang") String lang,
            Pageable pageable
    );

    List<News> findAllByPublishAtLessThanEqualAndStatus(ZonedDateTime now, NewsStatus status);

    List<News> findAllByUnpublishAtLessThanEqualAndStatus(ZonedDateTime now, NewsStatus status);
}