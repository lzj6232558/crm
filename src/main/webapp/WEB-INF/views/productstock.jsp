<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/static/js/productstock.js"></script>
</head>
<body>
<table id="productstock_datagrid"></table>

<div id="productstock_toolbar">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

    商品名称或编号:<input class="easyui-textbox" name="keyword" id="keyword" prompt="输入商品名、编号">
    所在仓库:<input class="easyui-combobox" name="depotId" id="depotId"
                data-options="url:'/depot/selectAll.do',
			   textField:'name',valueField:'id',panelHeight:'auto'">
    库存阈值:<input class="easyui-textbox" name="maxNumber" id="maxNumber" prompt="请输入库存量">

    <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-myout" data-cmd="myout">导出库存信息</a>
</div>

<div id="productstock_btns">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
</div>

<div id="productstock_dialog">
    <form id="productstock_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>库存名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>库存编码:</td>
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
                <td>库存地址:</td>
                <td><input type="text" name="location" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
