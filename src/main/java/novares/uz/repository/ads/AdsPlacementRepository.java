package novares.uz.repository.ads;

import novares.uz.collection.AdsPlacementCollection;
import novares.uz.collection.NewsCollection;
import novares.uz.domain.ads.AdsPlacement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdsPlacementRepository extends JpaRepository<AdsPlacement, Long>, JpaSpecificationExecutor<AdsPlacement> {

    Optional<AdsPlacement> findByIdAndDeletedFalse(Long id);

    @Query(value = """
            select distinct
                ap.id as placement_id,
                ap.title as plecement_title,
                ap.description as plecement_description,
                ac.name as campaign_name,
                ac.advertiser as campaing_advertiser,
                acr.html_snippet as creative_html_snippet,
                acr.landing_url as creative_landing_url,
                acrt.lang as creative_translation_lang,
                acrt.title as creative_title,
                acrt.alt_text as creative_alt_text
            from ads_placement ap
                left join ads_assignment aa on ap.id = aa.placement_id and aa.is_deleted = false and aa.is_active = true
                left join ads_campaign ac on aa.campaign_id = ac.id and ac.is_deleted = false
                left join ads_creative acr on aa.creative_id = acr.id and acr.is_deleted = false and acr.is_active = true
                left join ads_creative_translation acrt on acr.id = acrt.creative_id and acrt.is_deleted = false
            where ap.is_active = true
                and ap.is_deleted = false
                and ap.code = :placementCode
                AND (:lang is null or LOWER(acrt.lang) = LOWER(:lang))
            """,
            countQuery = """
                    select count(distinct ap.id)
                    from ads_placement ap
                             left join ads_assignment aa on ap.id = aa.placement_id and aa.is_deleted = false and aa.is_active = true
                             left join ads_campaign ac on aa.campaign_id = ac.id and ac.is_deleted = false
                             left join ads_creative acr on aa.creative_id = acr.id and acr.is_deleted = false and acr.is_active = true
                             left join ads_creative_translation acrt on acr.id = acrt.creative_id and acrt.is_deleted = false
                    where ap.is_active = true
                      and ap.is_deleted = false
                      and ap.code = :placementCode
                      AND (:lang is null or LOWER(acrt.lang) = LOWER(:lang))
                    """,
            nativeQuery = true)
    Page<AdsPlacementCollection> getAdsFilteredForPublic(
            @Param("placementCode") String placementCode,
            @Param("categoryId") Long categoryId,
            @Param("lang") String lang,
            Pageable pageable
    );
}