package novares.uz.mapper;

import novares.uz.domain.Auditable;
import novares.uz.dto.GenericCrudDto;
import novares.uz.dto.GenericDto;
import org.mapstruct.*;

import java.util.List;

/**
 * @param <E>  - Entity type parameter
 * @param <D>  - Dto
 * @param <CD> - CrudDTO
 */

public interface BaseCrudMapper<E extends Auditable<Long>, D extends GenericDto, CD extends GenericCrudDto> {

    D toDto(E entity);

    E fromDto(D dto);

    List<D> toDto(List<E> entityList);

    List<E> fromDto(List<D> dtoList);

    @Mapping(ignore = true, target = "deleted")
    E fromCreateDto(CD crudDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E fromUpdate(CD crudDto, @MappingTarget E order);

    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    D onlyId(E entity);

    @Mapping(source = ".", target = "id")
    E onlyEntityId(Long id);
}
