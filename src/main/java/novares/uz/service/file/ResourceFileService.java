package novares.uz.service.file;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.file.ResourceFileCriteria;
import novares.uz.domain.file.ResourceFile;
import novares.uz.repository.file.ResourceFileRepository;
import novares.uz.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResourceFileService implements GenericService<ResourceFile, ResourceFileCriteria> {

    private final ResourceFileRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResourceFile get(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResourceFile> list(ResourceFileCriteria criteria) {
        return repository.findAll(criteria.toSpecification(), PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }
}
