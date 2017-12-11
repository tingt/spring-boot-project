package com.ttt.demo.web;

import com.ttt.demo.utils.CommonResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by tutingting on 18-1-10.
 */
@RestController
public class LanguageController {
    @RequestMapping(value="/switchLanguage")
    public CommonResponse switchLanguage(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "lang",required = false,defaultValue = "zh") String lang){
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        //设置语言区域
        if (lang.equals(Locale.SIMPLIFIED_CHINESE.toString())) {
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if(lang.equals(Locale.US.toString())){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        System.out.println(lang);
        //将local放到cookie中，同时设置过期时间
        Cookie cookie=new Cookie("locale",lang);
        cookie.setMaxAge(1000);   //1000秒过期
        response.addCookie(cookie);
        return CommonResponse.successResponse(null);
    }
}
