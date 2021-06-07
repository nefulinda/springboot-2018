package com.nefu.myspringboot.interceptor;


import com.nefu.myspringboot.common.MyException;
import com.nefu.myspringboot.common.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TeacherInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Integer.parseInt(request.getAttribute("role").toString()) < Role.TEACHER) {
            throw new MyException(403, "无权限");
        }
        return true;
    }
}
