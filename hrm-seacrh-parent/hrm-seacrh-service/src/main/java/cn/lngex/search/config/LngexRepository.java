package cn.lngex.search.config;

import cn.lngex.search.doc.CourseDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LngexRepository extends ElasticsearchRepository<CourseDoc,Long> {
}
