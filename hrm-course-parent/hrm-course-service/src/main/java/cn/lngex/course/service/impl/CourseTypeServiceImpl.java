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
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /* 防止缓存穿透 */
    List<CourseType> arrays = new ArrayList<>();


    /**
     * 创建锁
     */
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public AjaxResult treeTypeData() {

        List<CourseType> list = courseTypeMapper.treeTypeData(0L);
        return AjaxResult.me().setResultObj(list);
    }

    /**
     *
     * 查询所有在装
     * @param wrapper
     * @return List
     */
    @Override
    public List<CourseType> selectList(Wrapper<CourseType> wrapper) {
        System.out.println("===================================");

        Object o = redisTemplate.opsForValue().get(CourseTypeConstant.COURSE_TYPE);
        System.out.println("===================================");

        if(o != null){
            System.out.println("================================>从redis中获取");
            return (List<CourseType>) o;
        }

        /* 获取锁 */
        if(reentrantLock.tryLock()){
            try {
                /* 查询所有 */
                HashMap<Long, CourseType> map = new HashMap<>();
                List<CourseType> courseTypes = super.selectList(wrapper);
                courseTypes.forEach(courseType -> map.put(courseType.getId(),courseType));
                courseTypes.forEach(courseType -> {
                    if(courseType.getPid() != 0L){
                        map.get(courseType.getPid())
                                .getChildren()
                                .add(courseType);
                    }else {
                        arrays.add(courseType);
                    }
                });
                /* 使用热点数据永不过期防止防止雪崩 */
                int time = ThreadLocalRandom.current().nextInt(100, 200);
                redisTemplate.opsForValue().set(CourseTypeConstant.COURSE_TYPE,arrays,time, TimeUnit.MINUTES);
                System.out.println("======================>从数据库中获取");
                return arrays;
            } finally {
                reentrantLock.unlock();
            }
        }else{
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrays = this.selectList(wrapper);
        }
        return arrays;
    }

    @Override
    /* 清空缓存 */
    public boolean updateById(CourseType entity) {
        return super.updateById(entity);
    }

    @Override
    /* 清空缓存 */
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    /* 清空缓存 */
    public boolean insert(CourseType entity) {
        return super.insert(entity);
    }
}
