package cn.lngex.search.feign;

import cn.lngex.course.dto.CoursePageQuery;
import cn.lngex.search.doc.CourseDoc;
import cn.lngex.utils.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "SEARCH-SERVER",fallbackFactory = CourseFeignFallbackFactory.class)
public interface CourseFeign {

    @PostMapping("/coursesearch/save")
    AjaxResult save(@RequestBody CourseDoc courseDoc);

    @PostMapping("/coursesearch/del")
    AjaxResult del(@RequestBody Long id);

    @PostMapping("/coursesearch/queryCourses")
    AjaxResult queryCourses(@RequestBody CoursePageQuery coursePageQuery);
}
