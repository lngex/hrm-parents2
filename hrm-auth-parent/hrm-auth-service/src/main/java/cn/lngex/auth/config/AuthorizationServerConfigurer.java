package cn.lngex.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 服务安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /* 开启/oauth/token_key端口所有人可以访问 */
        security//.tokenKeyAccess("permitAll()")
                /* 开启check_Token端口需要认证才能访问 */
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }


    /**
     * 客户端服务详细配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 使用JdbcClientDetailsService客户端详情服务
        clients.withClientDetails(clientDetailsService());
    }

    /**
     *授权服务端口开放配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //1.密码授权模式需要
                .authenticationManager(authenticationManager)
                //2.授权码模式服务
                .authorizationCodeServices(authorizationCodeServices())
                //3.配置令牌管理服务
                .tokenServices(tokenService())
                //允许post方式请求
                .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET);

    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    //令牌的管理服务
    public AuthorizationServerTokenServices tokenService(){
        //创建默认的令牌服务
        DefaultTokenServices services = new DefaultTokenServices();
        //指定客户端详情配置
        services.setClientDetailsService(clientDetailsService());
        //支持产生刷新token
        services.setSupportRefreshToken(true);
        //token存储方式
        services.setTokenStore(tokenStore());

        //设置token增强 - 设置token转换器
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(getJwtAccessTokenConverter()));
        services.setTokenEnhancer(tokenEnhancerChain);  //jwtAccessTokenConverter()

        return services;
    }

    @Bean
    public TokenStore tokenStore(){
        //基于内存的Token存储
        /*return new InMemoryTokenStore();*/
        /* 基于JWTtoken */
        return new JwtTokenStore(getJwtAccessTokenConverter());
    }

    @Bean
    public ClientDetailsService clientDetailsService(){
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        //数据库的secret秘钥是密文
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }


    /**
     * JWT令牌校验工具
     * @return
     */
    static String key = "lngex";
    @Bean
    public JwtAccessTokenConverter getJwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        /* 校验密钥 */
        jwtAccessTokenConverter.setSigningKey(key);
        return jwtAccessTokenConverter;
    }

}
