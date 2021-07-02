package cn.lngex.securitydemo.config;

import cn.lngex.securitydemo.handler.MyAuthenFailHandler;
import cn.lngex.securitydemo.handler.MyAuthenSucessHandler;
import cn.lngex.securitydemo.handler.MyAuthorSucessHandler;
import cn.lngex.securitydemo.service.UserDetils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled= true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //提供用户信息，这里没有从数据库查询用户信息，在内存中模拟
/*    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager =
                new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("zs").password("123").authorities
                ("admin").build());
        return inMemoryUserDetailsManager;
    }*/

    //密码编码器：加密

    @Autowired
    private DataSource dataSource ;

    @Autowired
    private UserDetils userDetils;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl obj = new JdbcTokenRepositoryImpl();
        obj.setDataSource(dataSource);
        // obj.setCreateTableOnStartup(true);
            //启动创建表 persistent_logs 表，存 token，username 时会用到
        return obj;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //授权规则配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedHandler(new MyAuthorSucessHandler())
                .and()
                .authorizeRequests() //授权配置
                .antMatchers("/login").permitAll() //登录路径放行
                .antMatchers("/login.html").permitAll() //登录路径放行
                .anyRequest().authenticated() //其他路径都要认证之
                .and().formLogin() //允许表单登录
                .successForwardUrl("/loginSuccess") // 设置登陆成功页
                .successHandler(new MyAuthenSucessHandler())
                .failureHandler(new MyAuthenFailHandler())
                .loginPage("/login.html") //登录页面跳转地址
                .loginProcessingUrl("/login") //登录处理地址(必须)
                .and().logout().permitAll() //登出路径放行 /logout
                .and().csrf().disable(); //关闭跨域伪造检查

        http.rememberMe()
                .tokenRepository(persistentTokenRepository()) //持久
                .tokenValiditySeconds(3600) //过期时间
                .userDetailsService(userDetils); //用来加载用户认证信息的
    }
}