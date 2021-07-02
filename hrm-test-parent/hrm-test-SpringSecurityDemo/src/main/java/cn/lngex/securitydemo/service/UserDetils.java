package cn.lngex.securitydemo.service;

import cn.lngex.securitydemo.config.LoginUser;
import cn.lngex.securitydemo.domain.Permission;
import cn.lngex.securitydemo.mapper.LoginUserMapper;
import cn.lngex.securitydemo.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserDetils  implements UserDetailsService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(s);
        loginUser = loginUserMapper.selectOne(loginUser);
        if(loginUser == null){
            throw new RuntimeException("用户或密码错误");
        }
        ArrayList<GrantedAuthority> objects = new ArrayList<>();
        /* 获取权限 */
        List<Permission> permissions = permissionMapper.getUserPermissionById(loginUser.getId());
        permissions.forEach(permission->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.getSn());
            objects.add(simpleGrantedAuthority);
        });
        return new User(loginUser.getUsername(), loginUser.getPassword(),objects);
    }
}
