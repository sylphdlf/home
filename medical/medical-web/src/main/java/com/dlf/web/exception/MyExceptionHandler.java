package com.dlf.web.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.dlf.model.dto.enums.user.UserResultEnum;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    private static String CODE = "code";
    private static String MSG = "msg";

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (ex instanceof UnauthenticatedException) {
            attributes.put(CODE, UserResultEnum.TOKEN_ERROR.getCode());
            attributes.put(MSG, UserResultEnum.TOKEN_ERROR.getMsg());
        } else if (ex instanceof UnauthorizedException) {
            attributes.put(CODE, UserResultEnum.PERMISSION_DENIED.getCode());
            attributes.put(MSG, UserResultEnum.PERMISSION_DENIED.getMsg());
        } else if (ex instanceof IncorrectCredentialsException){
            attributes.put(CODE, UserResultEnum.USERNAME_OR_PASSWORD_ERROR.getCode());
            attributes.put(MSG, UserResultEnum.USERNAME_OR_PASSWORD_ERROR.getMsg());
        } else {
            ex.printStackTrace();
        }
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}