package cn.lngex.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * 权限服务配置中心
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll() // 放行路径
                .antMatchers("/loginUser/login").permitAll() // 放行路径
                .anyRequest().authenticated() // 其他路径需要认证
                .and().formLogin() // 允许表单登录
                .successForwardUrl("/loginUser/loginsuccess") // 登陆成功也
                .loginProcessingUrl("/login") //登陆处理地址
                .and().logout().permitAll() // 开启
                .and().csrf().disable(); // 关闭跨站
    }

    /**
     * 加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Resource
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //JSON格式序列化
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //key的序列化
        redisTemplate.setKeySerializer(genericJackson2JsonRedisSerializer);
        //value的序列化
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        //hash结构key的虚拟化

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash结构value的虚拟化
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
