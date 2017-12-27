package cn.wolfcode.crm.web.filter;

import cn.wolfcode.crm.util.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter extends FormAuthenticationFilter {

    /**
     * 登录成功返回json数据
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult("登录成功")));

        return false;//返回false 结束过滤
    }

    /**
     * 登录失败返回json数据
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse resp = (HttpServletResponse) response;
        JsonResult jsonResult = new JsonResult(false, "登录异常,请联系管理员");
        if (e instanceof UnknownAccountException) {
            jsonResult.setMsg("用户名输入错误/不存在");
        } else if (e instanceof IncorrectCredentialsException) {
            jsonResult.setMsg("密码输入错误");
        }
        try {
            resp.getWriter().print(new ObjectMapper().writeValueAsString(jsonResult));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //输出错误信息
        e.printStackTrace();
        return false;//返回false 结束过滤
    }

    /**
     * 解决重复登录的问题
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(this.isLoginRequest(request,response)){//是否为登录的请求
            Subject subject = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){//如果此用户已经登录了,就登出此用户
                subject.logout();
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
