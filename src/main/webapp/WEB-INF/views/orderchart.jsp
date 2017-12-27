<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>

    <script type="text/javascript" src="/static/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript"
            src="/static/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>

    <script type="text/javascript" src="/static/js/orderchart.js"></script>
    <script>
        console.log($.dialog)
    </script>
</head>
<body>
    <table id="orderchart_datagrid"></table>
    <form id="myForm" method="post">
    <div id="orderchart_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>
        业务时间:<input class="easyui-datebox" name="beginDate" id="beginDate">~
        <input class="easyui-datebox" name="endDate" id="endDate">
        商品名:<input class="easyui-textbox" name="productName" id="productName" prompt="iphone9">
        供应商:<input class="easyui-combobox" name="supplierId" id="supplierId" prompt="苹果公司"
                    data-options="url:'/supplier/selectAll.do',
			   textField:'name',valueField:'id',panelHeight:'auto'">
        分组:<input class="easyui-combobox" name="groupBy" id="groupBy" prompt="货品名称"
                   data-options="url:'/orderchart/groupBy.do',
			   textField:'value',valueField:'key',panelHeight:'auto'">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-pie" data-cmd="pie">饼状报表</a>
    </div>
    </form>
    <div id="orderchart_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

<div id="orderchart_dialog">
    <form id="orderchart_form" method="post">
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

    <div id="pie_panel">

    </div>
</body>
</html>
