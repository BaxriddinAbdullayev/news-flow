package novares.uz.repository.file;

import novares.uz.domain.file.ResourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceFileRepository extends JpaRepository<ResourceFile, Long>, JpaSpecificationExecutor<ResourceFile> {

    Optional<ResourceFile> findByResourceHashAndDeletedFalse(String resourceHash);

    void deleteByResourceHashAndDeletedFalse(String resourceHash);
}
