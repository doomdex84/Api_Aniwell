package com.example.RSW;

import com.example.RSW.interceptor.BeforeActionInterceptor;
import com.example.RSW.interceptor.NeedLoginInterceptor;
import com.example.RSW.interceptor.NeedLogoutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    // BeforeActionInterceptor 불러오기(연결)
    @Autowired
    BeforeActionInterceptor beforeActionInterceptor;

    // NeedLoginInterceptor 불러오기(연결)
    @Autowired
    NeedLoginInterceptor needLoginInterceptor;

    // NeedLogoutInterceptor 불러오기(연결)
    @Autowired
    NeedLogoutInterceptor needLogoutInterceptor;

    // ✅ CORS 설정 추가
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3001")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
    
    

    // 인터셉터 등록(적용)
    public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**")
//				.excludePathPatterns("/error");
//
//		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write")
//				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/modify")
//				.addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete")
//				.addPathPatterns("/usr/member/doLogout");
//
//		registry.addInterceptor(needLogoutInterceptor).addPathPatterns("/usr/member/login")
//				.addPathPatterns("/usr/member/doLogin").addPathPatterns("/usr/member/join")
//				.addPathPatterns("/usr/member/doJoin");

        InterceptorRegistration ir;

        ir = registry.addInterceptor(beforeActionInterceptor);
        ir.addPathPatterns("/**");
        ir.addPathPatterns("/favicon.ico");
        ir.excludePathPatterns("/resource/**");
        ir.excludePathPatterns("/error");

//        ir.addPathPatterns("/usr/member/doLogout");


//      펫 관련
        ir.addPathPatterns("/usr/pet/petPage");
        ir.addPathPatterns("/usr/pet/list");
        ir.addPathPatterns("/usr/pet/join");
        ir.addPathPatterns("/usr/pet/doJoin");
        ir.addPathPatterns("/usr/pet/modify");
        ir.addPathPatterns("/usr/pet/doModify");
        ir.addPathPatterns("/usr/pet/analysis");
        ir.addPathPatterns("/usr/pet/analysis/do");
        ir.addPathPatterns("/usr/pet/delete");
        ir.addPathPatterns("/usr/pet/vaccination/registration");
        ir.addPathPatterns("/usr/pet/vaccination/doRegistration");
        ir.addPathPatterns("/usr/pet/vaccination/modify");
        ir.addPathPatterns("/usr/pet/vaccination/doModify");
        ir.addPathPatterns("/usr/pet/vaccination/detail");
        ir.addPathPatterns("/usr/pet/vaccination/delete");
        ir.addPathPatterns("/usr/pet/daily");
        ir.addPathPatterns("/usr/pet/daily/write");
        ir.addPathPatterns("/usr/pet/daily/domodify");
        ir.addPathPatterns("/usr/pet/daily/delete");
        ir.addPathPatterns("/usr/pet/daily/detail");



//		로그아웃 필요
        ir = registry.addInterceptor(needLogoutInterceptor);
        ir.addPathPatterns("/usr/member/login");
        ir.addPathPatterns("/usr/member/doLogin");
        ir.addPathPatterns("/usr/member/join");
        ir.addPathPatterns("/usr/member/doJoin");
        ir.addPathPatterns("/usr/member/findLoginId");
        ir.addPathPatterns("/usr/member/doFindLoginId");
        ir.addPathPatterns("/usr/member/findLoginPw");
        ir.addPathPatterns("/usr/member/doFindLoginPw");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")  // 웹에서 요청할 경로
                .addResourceLocations("file:/Users/e-suul/Desktop/aniwell_uploads/"); // 실제 로컬 폴더
    }
    }
