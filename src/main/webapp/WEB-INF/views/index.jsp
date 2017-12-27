<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/js/common.js"></script>
</head>
<body class="easyui-layout">

<div data-options="region:'north',split:true" style="height:110px;">
    <h1 align="center">XXX管理系统</h1>
    <h4 align="right">当前用户:<shiro:principal property="username"/>
                    <a href="/loginOut">注销</a></h4>
</div>
<div data-options="region:'south',split:true" style="height:50px;">
    <h5 align="center">@版权声明 请联系 陈经理:18974014610</h5>
</div>
<div data-options="region:'west',split:true," style="width:250px;">
    <div class="easyui-accordion" data-option="fit:true">
        <div title="菜单列表" style="overflow:auto;padding: 10px;">
            <ul id="myTree">
                <%--  <li><span>系统模块</span></li>
                  <li>业务模块</li>
                  <li>报表模块'</li>--%>
            </ul>
        </div>
        <div title="代办事项" style="overflow:auto;padding: 10px;">这是代办事项</div>
        <div title="其他" style="overflow:auto;padding: 10px;">其他的什么杂七杂八的东东</div>
    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div id="myTabs" class="easyui-tabs" fit="true">
        <div title="主页">

            <div id="portal">
                <div class="easyui-panel" data-options="border:false,width:400">
                <iframe allowtransparency="true" frameborder="0" width="385" height="96" scrolling="no"
                        src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=3&bd=0&k=000000&f=&ltf=009944&htf=cc0000&q=1&e=1&c=54511&w=385&h=96&align=right"></iframe>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
