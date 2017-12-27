<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/static/js/depot.js"></script>
</head>
<body>
    <table id="depot_datagrid"></table>

    <div id="depot_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-cmd="add">新增</a>
        <a id="edit_btn" class="easyui-linkbutton" plain="true" iconCls="icon-edit" data-cmd="edit">编辑</a>
        <a id="changState_btn" class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-cmd="changeState">停用</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

        <input class="easyui-textbox" name="keyword" id="keyword" prompt="输入仓库名、联系人、手机号">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    </div>

    <div id="depot_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

<div id="depot_dialog">
    <form id="depot_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>仓库名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>仓库编码:</td>
                <td><input type="text" name="sn" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>联系电话:</td>
                <td><input type="text" name="tel" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>管理人员:</td>
                <td><input type="text" name="linkman" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>仓库地址:</td>
                <td><input type="text" name="location" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
