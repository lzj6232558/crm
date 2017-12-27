<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/static/js/supplier.js"></script>
</head>
<body>
    <table id="supplier_datagrid"></table>

    <div id="supplier_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-cmd="add">新增</a>
        <a id="edit_btn" class="easyui-linkbutton" plain="true" iconCls="icon-edit" data-cmd="edit">编辑</a>
        <a id="changState_btn" class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

        <input class="easyui-textbox" name="keyword" id="keyword" prompt="输入供应商名、联系人、手机号">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    </div>

    <div id="supplier_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

<div id="supplier_dialog">
    <form id="supplier_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>供应商:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>联系人:</td>
                <td><input type="text" name="linkman" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>联系电话:</td>
                <td><input type="text" name="tel" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
