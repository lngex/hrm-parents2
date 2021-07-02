package cn.lngex.course.service.impl;

import cn.lngex.auth.domain.EmpTen;
import cn.lngex.common.constant.AuthConstant;
import cn.lngex.common.constant.DataDictionary;
import cn.lngex.course.config.RabbitMQConfig;
import cn.lngex.course.dto.CoursePageQuery;
import cn.lngex.course.mapper.CourseTypeMapper;
import cn.lngex.search.doc.CourseDoc;
import cn.lngex.course.domain.Course;
import cn.lngex.course.domain.CourseDetail;
import cn.lngex.course.domain.CourseMarket;
import cn.lngex.course.dto.CourseDto;
import cn.lngex.course.mapper.CourseDetailMapper;
import cn.lngex.course.mapper.CourseMapper;
import cn.lngex.course.mapper.CourseMarketMapper;
import cn.lngex.course.service.ICourseService;
import cn.lngex.search.feign.CourseFeign;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import javafx.scene.effect.Light;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Autowired
    private CourseFeign courseFeign;


    @Autowired
    private CourseDetailMapper courseDetailMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加课程
     *
     * @param courseDto
     * @return
     */
    @Override
    public AjaxResult save(CourseDto courseDto) {
        /* 获取用户及其商户信息 */
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        EmpTen empTen = (EmpTen) redisTemplate.opsForValue().get(AuthConstant.COURSE_EMPTAN + name);
        System.out.println(AuthConstant.COURSE_EMPTAN + name);
        System.out.println("获取到的类型"+empTen);
        /* 获取课程 */
        Course course = courseDto.getCourse();
        /* 判断课程类型 */
        Long courseTypeId = course.getGradeId();
        System.out.println(courseTypeId);
        DataDictionary dataDictionary = DataDictionary.getDataDictionary(courseTypeId);
        System.out.println(dataDictionary.getName());
        Assert.notNull(dataDictionary,"等级非法");

        /* 保存课程 */
        course.setUserId(empTen.getUserId())
                .setUserName(empTen.getUserName())
                .setTenantId(empTen.getTenantId())
                .setTenantName(empTen.getTenantName());
        super.insert(course);
        /* 保存详细 */
        CourseDetail courseDetail = courseDto.getCourseDetail();
        courseDetail.setId(course.getId());
        courseDetailMapper.insert(courseDetail);
        /* 保存市场信息 */
        CourseMarket courseMarket = courseDto.getCourseMarket();
        courseMarket.setId(course.getId());
        courseMarketMapper.insert(courseDto.getCourseMarket());

        return AjaxResult.me();
    }

    /**
     * 修改状态
     *
     * @param ids
     * @return AjaxResult
     */
    @Override
    public AjaxResult onLineCourse(List<Long> ids) {
        for (Long id:ids ) {
            extracted(id);
        }
        return AjaxResult.me();
    }

    /**
     * 批量下线
     *
     * @param ids
     * @return AjaxResult
     */
    @Override
    public AjaxResult offLineCourse(List<Long> ids) {
        for (Long id:ids ) {
            /* 查询course */
            Course course = super.selectById(id);
            Assert.notNull(course,"参数非法");
            course.setStatus(0);
            courseFeign.del(id);
            super.updateById(course);
        }
        return AjaxResult.me();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public AjaxResult batchDel(List<Long> ids) {
        for (Long id:ids ) {
            super.deleteById(id);
            courseFeign.del(id);
        }
        return AjaxResult.me();
    }

    /**
     * 面包屑
     *
     * @param coursePageQuery
     * @return
     */
    @Override
    public AjaxResult queryCourses(CoursePageQuery coursePageQuery) {
        return courseFeign.queryCourses(coursePageQuery);
    }

    private void extracted(Long id) {
        /* 查询course */
        Course course = super.selectById(id);
        Assert.notNull(course,"参数非法");
        /* 查询类型 */
        String name = DataDictionary.getDataDictionary(course.getGradeId()).getName();
        /* 查询市场 */
        CourseMarket courseMarket = courseMarketMapper.selectById(id);
        /* 查询详细 */
        CourseDetail courseDetail = courseDetailMapper.selectById(id);
        CourseDoc courseDoc = this.courseToCourseDoc(course,courseMarket,courseDetail,name);
        course.setStatus(1);
        courseFeign.save(courseDoc);
        super.updateById(course);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME_TOPIC,"message.system",course.getName());
    }


    /**
     * 将课程转换为CourseDoc
     * @param course
     * @param courseMarket
     * @param courseDetail
     * @return CourseDoc
     */
    private CourseDoc courseToCourseDoc(Course course, CourseMarket courseMarket, CourseDetail courseDetail,String gradeName) {
        CourseDoc courseDoc = new CourseDoc();
        BeanUtils.copyProperties(course,courseDoc);
        BeanUtils.copyProperties(courseMarket,courseDoc);
        BeanUtils.copyProperties(courseDetail,courseDoc);
        courseDoc.setGradeName(gradeName);
        courseDoc.setCourseTypeName(courseTypeMapper.selectById(course.getCourseTypeId()).getName());
        return courseDoc;
    }
}
