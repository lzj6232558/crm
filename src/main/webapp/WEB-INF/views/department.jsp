<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/department.js"></script>
    <title>Title</title>
</head>
<body>
<table id="dept_table" align="center"></table>

<div id="dept_toolbar">
    <shiro:hasPermission name="employee:saveOrUpdate">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="department:changeState">
        <a class="easyui-linkbutton" id="change" iconCls="icon-remove" plain="true" data-cmd="change">撤销</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <input id="keyword" class="easyui-textbox" name="keyword">
    <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search22">搜索</a>
</div>

<div id="dept_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>


<div id="dialog">
    <form id="dept_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>部门编码:</td>
                <td><input type="text" name="sn" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>部门名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
