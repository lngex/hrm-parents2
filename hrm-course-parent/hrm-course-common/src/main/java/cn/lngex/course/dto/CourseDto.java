package cn.lngex.course.dto;

import cn.lngex.course.domain.Course;
import cn.lngex.course.domain.CourseDetail;
import cn.lngex.course.domain.CourseMarket;
import cn.lngex.course.domain.CourseType;

import javax.validation.Valid;

public class CourseDto {
    @Valid
    private Course course;

    @Valid
    private CourseMarket courseMarket;

    public CourseDetail getCourseDetail() {
        return courseDetail;
    }

    public CourseDto setCourseDetail(CourseDetail courseDetail) {
        this.courseDetail = courseDetail;
        return this;
    }

    @Valid
    private CourseDetail courseDetail;

    public Course getCourse() {
        return course;
    }

    public CourseDto setCourse(Course course) {
        this.course = course;
        return this;
    }

    public CourseMarket getCourseMarket() {
        return courseMarket;
    }

    public CourseDto setCourseMarket(CourseMarket courseMarket) {
        this.courseMarket = courseMarket;
        return this;
    }
}
