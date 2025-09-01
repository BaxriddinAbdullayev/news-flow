package novares.uz.service.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.collection.CategoryCollection;
import novares.uz.criteria.category.CategoryCriteria;
import novares.uz.domain.category.Category;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.dto.category.CategoryCrudDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.dto.category.CategoryTranslationCrudDto;
import novares.uz.dto.category.CategoryTranslationDto;
import novares.uz.mapper.category.CategoryMapper;
import novares.uz.mapper.category.CategoryTranslationMapper;
import novares.uz.repository.category.CategoryRepository;
import novares.uz.repository.category.CategoryTranslationRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements GenericCrudService<Category, CategoryCrudDto, CategoryCriteria> {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final CategoryTranslationRepository categoryTranslationRepository;
    private final CategoryTranslationMapper categoryTranslationMapper;

    @Override
    @Transactional(readOnly = true)
    public Category get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public CategoryDto getCategoryWithTranslation(Long id) {
        Category category = getCategory(id);
        CategoryDto dto = mapper.toDto(category);

        List<CategoryTranslation> allByParentId = categoryTranslationRepository.findAllByCategoryIdAndDeletedFalse(category.getId());

        List<CategoryTranslationDto> categoryTranslationDtoList = new ArrayList<>();
        for (CategoryTranslation categoryTranslation : allByParentId) {
            categoryTranslationDtoList.add(categoryTranslationMapper.toDto(categoryTranslation));
        }

        dto.setTranslation(categoryTranslationDtoList);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> list(CategoryCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Cacheable(value = "categories", key = "#criteria.lang ?: 'default'")
    public Page<CategoryDto> listCategoryWithTranslation(CategoryCriteria criteria) {

        Pageable pageable = PageRequest.of(
                criteria.getPage(),
                criteria.getSize(),
                Sort.by(criteria.getDirection(), criteria.getSort())
        );

        Page<Category> categoryPage = repository.findAllByDeletedFalse(pageable);

        List<CategoryDto> categoryDtos = categoryPage.getContent().stream().map(category -> {
            CategoryDto dto = mapper.toDto(category);

            List<CategoryTranslationDto> translations = categoryTranslationRepository.findAllByCategoryIdAndDeletedFalse(category.getId())
                    .stream()
                    .map(categoryTranslationMapper::toDto)
                    .toList();

            dto.setTranslation(translations);
            return dto;
        }).toList();

        return new PageImpl<>(categoryDtos, pageable, categoryPage.getTotalElements());
    }

    @Override
    @Transactional
    public Category create(CategoryCrudDto dto) {

        Category category = repository.save(mapper.fromCreateDto(dto));

        List<CategoryTranslationDto> translationDtoList = dto.getTranslation();

        for (CategoryTranslationDto translationDto : translationDtoList) {

            CategoryTranslationCrudDto categoryTranslationCrudDto = new CategoryTranslationCrudDto();
            categoryTranslationCrudDto.setCategory(mapper.toDto(category));
            categoryTranslationCrudDto.setLang(translationDto.getLang());
            categoryTranslationCrudDto.setTitle(translationDto.getTitle());
            categoryTranslationCrudDto.setSlug(translationDto.getSlug());
            categoryTranslationCrudDto.setDescription(translationDto.getDescription());

            categoryTranslationRepository.save(categoryTranslationMapper.fromCreateDto(categoryTranslationCrudDto));
        }

        return category;
    }

    @Override
    @Transactional
    public Category update(Long id, CategoryCrudDto dto) {

        Category category = getCategory(id);
        category = repository.save(mapper.fromUpdate(dto, category));

        List<CategoryTranslationDto> categoryTranslationDtoList = dto.getTranslation();
        for (CategoryTranslationDto categoryTranslationDto : categoryTranslationDtoList) {

            CategoryTranslationCrudDto categoryTranslationCrudDto = new CategoryTranslationCrudDto();
            categoryTranslationCrudDto.setCategory(mapper.toDto(category));
            categoryTranslationCrudDto.setLang(categoryTranslationDto.getLang());
            categoryTranslationCrudDto.setTitle(categoryTranslationDto.getTitle());
            categoryTranslationCrudDto.setSlug(categoryTranslationDto.getSlug());
            categoryTranslationCrudDto.setDescription(categoryTranslationDto.getDescription());

            CategoryTranslation categoryTranslation = categoryTranslationRepository.findByIdAndDeletedFalse(categoryTranslationDto.getId()).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
            categoryTranslationMapper.fromUpdate(categoryTranslationCrudDto, categoryTranslation);
        }

        return category;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = getCategory(id);
        category.setDeleted(true);
        repository.save(category);

        List<CategoryTranslation> allByParentId = categoryTranslationRepository.findAllByCategoryIdAndDeletedFalse(category.getId());

        for (CategoryTranslation categoryTranslation : allByParentId) {
            categoryTranslation.setDeleted(true);
            categoryTranslationRepository.save(categoryTranslation);
        }
    }

    private Category getCategory(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }


    @Cacheable(value = "publicCategories", key = "#criteria.lang ?: 'default'")
    public Page<CategoryCollection> getNewsBySlugAndLang(CategoryCriteria criteria) {
        return repository.getCategoryFilteredForPublic(
                criteria.getLang(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort()))
        );
    }
}
