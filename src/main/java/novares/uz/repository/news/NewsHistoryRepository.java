package novares.uz.repository.news;

import novares.uz.domain.news.NewsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsHistoryRepository extends JpaRepository<NewsHistory, Long>, JpaSpecificationExecutor<NewsHistory> {

}