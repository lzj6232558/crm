<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/employee.js"></script>
    <title>Title</title>
</head>
<body>
<table id="emp_table" align="center"></table>

<div id="emp_toolbar">
    <shiro:hasPermission name="employee:saveOrUpdate">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:changeState">
        <a class="easyui-linkbutton" id="change" iconCls="icon-remove" plain="true" data-cmd="change">离职</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <input id="keyword" class="easyui-textbox" name="keyword" >
    <input id="inputtime" class="easyui-datebox" name="inputtime">
    <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search22">搜索</a>
    <a class="easyui-linkbutton" iconCls="icon-redo" plain="true" data-cmd="exportXls">导出</a>
    <a class="easyui-linkbutton" iconCls="icon-undo" plain="true" data-cmd="imlportXls">导入</a>
</div>

<div id="emp_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>
<div id="imlportbtn">
    <a class="easyui-linkbutton" iconCls="icon-undo" data-cmd="imlportbtn">提交</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>

<div id="imlport">
    <form id="imlportForm" method="post" enctype="multipart/form-data">
        <input class="easyui-filebox" data-options="buttonText:'点击上传',multiple:true" style="width: 200px" name="file"/>
    </form>
</div>

<div id="dialog">
    <form id="emp_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>员工姓名:</td>
                <td><input type="text" name="username" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input type="text" name="realname" class="easyui-textbox"></td>
            </tr>
            <tr id="pwd">
                <td>密码:</td>
                <td><input type="text" name="password" class="easyui-passwordbox"></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="tel" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input id="cc" class="easyui-combobox" name="dept.id"
                           data-options="valueField:'id', panelHeight:'auto',textField:'name',url:'/department/query.do'"/>
                </td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td><input type="text" name="inputtime" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>是否管理员</td>
                <td>
                    <select class="easyui-combobox" name="admin" style="width:143px" data-options="panelHeight:'auto'">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select></td>
            </tr>
            <tr>
                <td>角色:</td>
                <td>
                    <input id="role_combo" class="easyui-combobox"
                           data-options="valueField:'id', panelHeight:'auto',textField:'name',multiple:true,url:'/role/query.do'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
