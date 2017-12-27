package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.util.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public void exceptionHandler(HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        ResponseBody annotation = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
        if(annotation!=null){
            //ajax请求
            response.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult(false,"没有操作权限")));
        }else{
            //正常请求
            response.sendRedirect("/nopermission.jsp");
        }
    }

}
