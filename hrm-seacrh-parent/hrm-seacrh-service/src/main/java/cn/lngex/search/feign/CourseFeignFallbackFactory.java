package cn.lngex.search.feign;

import cn.lngex.course.dto.CoursePageQuery;
import cn.lngex.search.doc.CourseDoc;
import cn.lngex.utils.AjaxResult;
import feign.hystrix.FallbackFactory;

public class CourseFeignFallbackFactory implements FallbackFactory<CourseFeign> {
    @Override
    public CourseFeign create(Throwable throwable) {
        return new CourseFeign() {
            @Override
            public AjaxResult save(CourseDoc courseDoc) {
                return AjaxResult.me().setSuccess(false).setMessage("系统繁忙");
            }

            @Override
            public AjaxResult del(Long id) {
                return AjaxResult.me().setSuccess(false).setMessage("系统繁忙");
            }

            @Override
            public AjaxResult queryCourses(CoursePageQuery coursePageQuery) {
                return  AjaxResult.me().setSuccess(false).setMessage("系统繁忙");
            }
        };
    }
}
