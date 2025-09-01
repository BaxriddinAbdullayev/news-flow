package novares.uz.service.news;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.collection.NewsCollection;
import novares.uz.criteria.news.NewsCriteria;
import novares.uz.domain.auth.Role;
import novares.uz.domain.auth.User;
import novares.uz.domain.category.Category;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.domain.news.News;
import novares.uz.domain.news.NewsTranslation;
import novares.uz.domain.tag.Tag;
import novares.uz.dto.category.CategoryTranslationCrudDto;
import novares.uz.dto.category.CategoryTranslationDto;
import novares.uz.dto.news.NewsCrudDto;
import novares.uz.dto.news.NewsTranslationCrudDto;
import novares.uz.dto.news.NewsTranslationDto;
import novares.uz.dto.tag.TagDto;
import novares.uz.enums.NewsStatus;
import novares.uz.mapper.auth.UserMapper;
import novares.uz.mapper.category.CategoryTranslationMapper;
import novares.uz.mapper.news.NewsMapper;
import novares.uz.mapper.news.NewsTranslationMapper;
import novares.uz.repository.auth.RoleRepository;
import novares.uz.repository.auth.UserRepository;
import novares.uz.repository.category.CategoryRepository;
import novares.uz.repository.category.CategoryTranslationRepository;
import novares.uz.repository.news.NewsRepository;
import novares.uz.repository.news.NewsTranslationRepository;
import novares.uz.repository.tag.TagRepository;
import novares.uz.service.GenericCrudService;
import novares.uz.util.SpringSecurityUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NewsService implements GenericCrudService<News, NewsCrudDto, NewsCriteria> {

    private final NewsRepository repository;
    private final NewsMapper mapper;
    private final NewsTranslationRepository newsTranslationRepository;
    private final NewsTranslationMapper newsTranslationMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public News get(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<News> list(NewsCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    public Page<NewsCollection> getNewsFiltered(NewsCriteria criteria) {
        return repository.getNewsFilteredForAdmin(
                criteria.getStatus(),
                criteria.getAuthorId(),
                criteria.getCategoryId(),
                criteria.getTag(),
                criteria.getLang(),
                criteria.getFrom(),
                criteria.getTo(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort()))
        );
    }



    @Override
    @Transactional
    public News create(NewsCrudDto dto) {

        User currentUser = SpringSecurityUtil.getCurrentUser();
        Set<Role> roleList = roleRepository.findRoleListByUserId(currentUser.getId());
        currentUser.setRoles(roleList);
        dto.setAuthor(userMapper.toDto(currentUser));

        News news = repository.save(mapper.fromCreateDto(dto));

        List<NewsTranslationDto> newsTranslationDtoList = dto.getTranslations();
        for (NewsTranslationDto newsTranslationDto : newsTranslationDtoList) {

            NewsTranslationCrudDto newsTranslationCrudDto = new NewsTranslationCrudDto();
            newsTranslationCrudDto.setNews(mapper.toDto(news));
            newsTranslationCrudDto.setLang(newsTranslationDto.getLang());
            newsTranslationCrudDto.setSlug(newsTranslationDto.getSlug());
            newsTranslationCrudDto.setTitle(newsTranslationDto.getTitle());
            newsTranslationCrudDto.setSummary(newsTranslationDto.getSummary());
            newsTranslationCrudDto.setContent(newsTranslationDto.getContent());
            newsTranslationCrudDto.setMetaTitle(newsTranslationDto.getMetaTitle());
            newsTranslationCrudDto.setMetaDescription(newsTranslationDto.getMetaDescription());

            newsTranslationRepository.save(newsTranslationMapper.fromCreateDto(newsTranslationCrudDto));
        }

        return news;
    }

    @Override
    @Transactional
    public News update(Long id, NewsCrudDto dto) {
        News entity = get(id);

        if(dto.getAuthor() != null) {
            User user = userRepository.findByIdAndDeletedFalse(dto.getAuthor().getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
            entity.setAuthor(user);
        }

        if(dto.getCategory() != null) {
            Category category = categoryRepository.findByIdAndDeletedFalse(dto.getCategory().getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(dto.getCategory().getId())));
            entity.setCategory(category);
        }

        if (!dto.getTags().isEmpty()) {
            tagRepository.deleteByNewsId(entity.getId());
            for (TagDto tag : dto.getTags()) {
                tagRepository.insertNewsTag(entity.getId(), tag.getId());
            }
        }

        List<NewsTranslationDto> newsTranslationDtoList = dto.getTranslations();
        for (NewsTranslationDto newsTranslationDto : newsTranslationDtoList) {
            NewsTranslationCrudDto newsTranslationCrudDto = new NewsTranslationCrudDto();
            newsTranslationCrudDto.setNews(mapper.toDto(entity));
            newsTranslationCrudDto.setLang(newsTranslationDto.getLang());
            newsTranslationCrudDto.setSlug(newsTranslationDto.getSlug());
            newsTranslationCrudDto.setTitle(newsTranslationDto.getTitle());
            newsTranslationCrudDto.setSummary(newsTranslationDto.getSummary());
            newsTranslationCrudDto.setContent(newsTranslationDto.getContent());
            newsTranslationCrudDto.setMetaTitle(newsTranslationDto.getMetaTitle());
            newsTranslationCrudDto.setMetaDescription(newsTranslationDto.getMetaDescription());

            NewsTranslation newsTranslation = newsTranslationRepository.findByIdAndDeletedFalse(newsTranslationDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(newsTranslationDto.getId())));
            newsTranslationMapper.fromUpdate(newsTranslationCrudDto, newsTranslation);
        }

        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        News entity = get(id);
        entity.setDeleted(true);
        repository.save(entity);

        List<NewsTranslation> allTranslation = newsTranslationRepository.findByNewsIdAndDeletedFalse(id);
        for (NewsTranslation newsTranslation : allTranslation) {
            newsTranslation.setDeleted(true);
            newsTranslationRepository.save(newsTranslation);
        }
    }

    public void restore(Long id) {
        News news = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        news.setDeleted(false);

        List<NewsTranslation> allTranslation = newsTranslationRepository.findByNewsIdAndDeletedTrue(id);
        for (NewsTranslation newsTranslation : allTranslation) {
            newsTranslation.setDeleted(false);
            newsTranslationRepository.save(newsTranslation);
        }
    }

    public void hardDelete(Long id) {
        News news = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        List<NewsTranslation> allTranslation = newsTranslationRepository.findByNewsId(id);
        for (NewsTranslation newsTranslation : allTranslation) {
            newsTranslationRepository.delete(newsTranslation);
        }

        repository.delete(news);
    }

    @Cacheable(value = "publicNewsDetail", key = "#slug + '_' + (#criteria.lang ?: 'default')")
    public Page<NewsCollection> getNewsBySlugAndLang(String slug, NewsCriteria criteria) {
        return repository.getNewsBySlugAndLang(
                slug,
                criteria.getLang(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort()))
        );
    }

    @Cacheable(value = "publicNews", key = "#criteria.lang ?: 'default'")
    public Page<NewsCollection> getNewsFilteredForPublic(NewsCriteria criteria) {
        return repository.getNewsFilteredForPublic(
                criteria.getCategoryId(),
                criteria.getTag(),
                criteria.getLang(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort()))
        );
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateNewsStatuses() {
        ZonedDateTime now = ZonedDateTime.now();

        // publish_at <= now && status = DRAFT
        List<News> toPublish = repository
                .findAllByPublishAtLessThanEqualAndStatus(now, NewsStatus.DRAFT);

        for (News news : toPublish) {
            news.setStatus(NewsStatus.PUBLISHED);
        }

        // unpublish_at <= now && status = PUBLISHED
        List<News> toUnpublish = repository
                .findAllByUnpublishAtLessThanEqualAndStatus(now, NewsStatus.PUBLISHED);

        for (News news : toUnpublish) {
            news.setStatus(NewsStatus.UNPUBLISHED);
        }

        // Barchasini saqlaymiz
        repository.saveAll(toPublish);
        repository.saveAll(toUnpublish);
    }
}
