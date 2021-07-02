package cn.lngex.auth.service.impl;

import cn.lngex.auth.domain.EmpTen;
import cn.lngex.auth.domain.LoginUser;
import cn.lngex.auth.domain.Permission;
import cn.lngex.auth.mapper.LoginUserMapper;
import cn.lngex.auth.mapper.PermissionMapper;
import cn.lngex.common.constant.AuthConstant;
import cn.lngex.system.feign.SystemFeign;
import cn.lngex.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 用户认证
 */
@Service
public class UserDetilsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private SystemFeign systemFeign;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(s);
        loginUser = loginUserMapper.selectOne(loginUser);
        if(loginUser == null){
            throw new RuntimeException("用户不存在");
        }
        List<Permission> userPermissionById = permissionMapper.getUserPermissionById(loginUser.getId());
        ArrayList<GrantedAuthority> objects = new ArrayList();
        userPermissionById.forEach(permission -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.getSn());
            objects.add(simpleGrantedAuthority);
        });
        /* 获取用户及其商铺*/
        LinkedHashMap linkedHashMap = (LinkedHashMap)systemFeign.getEmpTen(loginUser.getId()).getResultObj();
        System.out.println("========================>"+loginUser.getId());
        EmpTen empTen = new EmpTen();
        System.out.println(empTen);
        empTen
                .setUserId(Long.valueOf(linkedHashMap.get("userId").toString()))
                .setTenantName(linkedHashMap.get("tenantName").toString())
                .setUserName(linkedHashMap.get("userName").toString())
                .setTenantId(Long.valueOf(linkedHashMap.get("tenantId").toString()));
        /* 存入redis */
        redisTemplate.opsForValue().set(AuthConstant.COURSE_EMPTAN+s,empTen,8, TimeUnit.HOURS);
        System.out.println("Auth============="+AuthConstant.COURSE_EMPTAN + s);
        System.out.println("Auth=============获取到的类型"+empTen);
        return new User(loginUser.getUsername(),loginUser.getPassword(),objects);
    }

}
