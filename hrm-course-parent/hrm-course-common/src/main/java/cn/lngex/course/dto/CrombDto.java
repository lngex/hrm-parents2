package cn.lngex.course.dto;

import cn.lngex.course.domain.CourseType;

import java.util.List;

public class CrombDto {
    private CourseType own;
    private List<CourseType> bros;

    @Override
    public String toString() {
        return "CrombDto{" +
                "own=" + own +
                ", bros=" + bros +
                '}';
    }

    public CourseType getOwn() {
        return own;
    }

    public CrombDto setOwn(CourseType own) {
        this.own = own;
        return this;
    }

    public List<CourseType> getBros() {
        return bros;
    }

    public CrombDto setBros(List<CourseType> bros) {
        this.bros = bros;
        return this;
    }

    public CrombDto() {
    }

    public CrombDto(CourseType own, List<CourseType> bros) {
        this.own = own;
        this.bros = bros;
    }
}
