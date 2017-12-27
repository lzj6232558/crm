<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/vipcard.js"></script>
    <title>Title</title>
</head>
<body>
<table id="vipcard_table" align="center"></table>

<div id="vipcard_toolbar">
    <shiro:hasPermission name="employee:saveOrUpdate">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <%--<shiro:hasPermission name="vipcard:changeState"></shiro:hasPermission>--%>
    <a class="easyui-linkbutton" id="change" iconCls="icon-remove" plain="true" data-cmd="change">停用</a>

    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
</div>

<div id="vipcard_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>


<div id="dialog">
    <form id="vipcard_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>会员卡称谓:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>会员卡折扣:</td>
                <td><input type="text" name="discount" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>会员卡小区间:</td>
                <td><input type="text" name="minimum" class="easyui-textbox">
                </td>
            </tr>
            <tr>
                <td>会员卡大区间:</td>
                <td>
                    <input type="text" name="maximum" class="easyui-textbox">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
