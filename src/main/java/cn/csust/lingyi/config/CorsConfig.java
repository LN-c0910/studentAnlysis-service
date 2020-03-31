package cn.csust.lingyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by Enzo Cotter on 2020/3/15.
 * 解决跨域问题
 */
@Configuration
public class CorsConfig {
        @Bean
        public CorsFilter corsFilter(){

            //初始化cors配置对象
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            //允许跨域的域名
            corsConfiguration.addAllowedOrigin("http://192.168.184.1:8080");
            //是否允许携带cookie
            corsConfiguration.setAllowCredentials(true);
            //请求方法
            corsConfiguration.addAllowedMethod("*");
            //设置头信息
            corsConfiguration.addAllowedHeader("*");

            //初始化cors配置源对象
            UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
            corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

            //返回CorFilter实例，需要参数：cors配置源对象
            return new CorsFilter(corsConfigurationSource);
        }
    }


