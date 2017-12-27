package cn.wolfcode.crm.util;

import cn.wolfcode.crm.service.ISystemLogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/12/21.
 */
public class SystemLogUtil {

    @Autowired
    ISystemLogService service;

    /**
     * 记录日志
     */
    public void writeLog(JoinPoint joinPoint) {
        //ISystemLogService的日志不需要记录
      /*  if (joinPoint.getTarget() instanceof ISystemLogService) {
            return;
        }
        System.out.println("--------写入日志-------");
        SystemLog log = new SystemLog();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            //设置ip地址
            log.setOpIp(request.getRemoteAddr());
        }
        Object principal = SecurityUtils.getSubject().getPrincipal();
        //设置操作用户
        if (principal != null) {
            log.setOpUser((Employee) principal);
        }


        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        //设置目标执行的方法
        log.setFunction(className + ":" + methodName);
        try {
            //设置请求参数
            log.setParams(new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //保存到数据库
        System.out.println("--------写入日志-------");
        service.insert(log);*/
    }
}
