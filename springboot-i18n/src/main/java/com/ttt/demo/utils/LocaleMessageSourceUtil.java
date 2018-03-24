package com.ttt.demo.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by tutingting on 18-1-10.
 */
@Component
public class LocaleMessageSourceUtil {
    @Resource
    private MessageSource messageSource;

    /**
     * 国际化
     * @param code properties里的key
     * @return
     */
    public String getMessage(String code){
        return this.getMessage(code,new Object[]{});
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param defaultMessage 不存在时默认输出
     * @return
     */
    public String getMessage(String code,String defaultMessage){
        return this.getMessage(code,null,defaultMessage);
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param defaultMessage 不存在时默认输出
     * @param locale 环境
     * @return
     */
    public String getMessage(String code,String defaultMessage,Locale locale){
        return this.getMessage(code,null,defaultMessage,locale);
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param locale 环境
     * @return
     */
    public String getMessage(String code,Locale locale){
        return this.getMessage(code,null,"",locale);
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @return
     */
    public String getMessage(String code,Object[] args){
        return this.getMessage(code,args,"");
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @param locale 环境
     * @return
     */
    public String getMessage(String code,Object[] args,Locale locale){
        return this.getMessage(code,args,"",locale);
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @param defaultMessage 不存在时默认输出
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(code,args, defaultMessage,locale);
    }

    /**
     * 国际化
     * @param code properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @param defaultMessage 不存在时默认输出
     * @param locale 环境
     * @return
     */
    public String getMessage(String code,Object[]args,String defaultMessage,Locale locale){
        return messageSource.getMessage(code,args, defaultMessage,locale);
    }
}
