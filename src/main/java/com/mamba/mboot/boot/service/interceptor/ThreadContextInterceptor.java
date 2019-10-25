package com.mamba.mboot.boot.service.interceptor;

import com.mamba.mboot.boot.common.ThreadContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ThreadContextInterceptor extends HandlerInterceptorAdapter {
    public ThreadContextInterceptor() {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadContext.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appId = request.getHeader("appId");
        ThreadContext.put("appId", appId);
        return super.preHandle(request, response, handler);
    }
}

