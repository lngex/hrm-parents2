/*
package cn.lngex.course.service.impl;

import cn.lngex.common.constant.CourseTypeConstant;
import cn.lngex.course.domain.CourseType;
import cn.lngex.course.mapper.CourseTypeMapper;
import cn.lngex.course.service.ICourseTypeService;
import cn.lngex.utils.AjaxResult;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

*/
/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 *//*

@Service
public class CourseTypeServiceImpl_bak extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public AjaxResult treeTypeData() {

        List<CourseType> list = courseTypeMapper.treeTypeData(0L);
        return AjaxResult.me().setResultObj(list);
    }

    */
/**
     *
     * 查询所有在装
     * @param wrapper
     * @return List
     *//*

    @Override
    @Cacheable(cacheNames = CourseTypeConstant.COURSE_TYPE,key = "'KEY'")
    public List<CourseType> selectList(Wrapper<CourseType> wrapper) {
        */
/*Object o = redisTemplate.opsForValue().get(CourseTypeConstant.COURSE_TYPE);
        if(o != null){
            System.out.println("================================>从redis中获取");
            return (List<CourseType>) o;
        }*//*

        */
/* 查询所有 *//*

        List<CourseType> courseTypes = super.selectList(wrapper);
        HashMap<Long, CourseType> map = new HashMap<>();
        List<CourseType> arrays = new ArrayList<>();
        courseTypes.forEach(courseType -> map.put(courseType.getId(),courseType));
        courseTypes.forEach(courseType -> {
            Long pid = courseType.getPid();
            if(pid != 0L){
                map.get(pid)
                        .getChildren()
                        .add(courseType);
            }else {
                arrays.add(courseType);
            }
        });
//        redisTemplate.opsForValue().set(CourseTypeConstant.COURSE_TYPE,arrays);
        System.out.println("======================>从数据库中获取");
        return arrays;
    }

    @Override
    */
/* 清空缓存 *//*

    @CacheEvict(cacheNames = CourseTypeConstant.COURSE_TYPE,key = "'KEY'")
    public boolean updateById(CourseType entity) {
        return super.updateById(entity);
    }

    @Override
    */
/* 清空缓存 *//*

    @CacheEvict(cacheNames = CourseTypeConstant.COURSE_TYPE,key = "'KEY'")
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    */
/* 清空缓存 *//*

    @CacheEvict(cacheNames = CourseTypeConstant.COURSE_TYPE,key = "'KEY'")
    public boolean insert(CourseType entity) {
        return super.insert(entity);
    }
}
*/
