<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/static/js/checkproduct.js"></script>
</head>
<body>
    <table id="checkproduct_datagrid"></table>

    <div id="checkproduct_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-check" data-cmd="check">数量确认</a>
        <a id="edit_btn" class="easyui-linkbutton" plain="true" iconCls="icon-adjust" data-cmd="adjust">数量调整</a>
        <a id="changState_btn" class="easyui-linkbutton" plain="true" iconCls="icon-record" data-cmd="record">盘点记录</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

        <input class="easyui-textbox" name="keyword" id="keyword" prompt="输入商品名称、编码">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    </div>

    <div id="checkproduct_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

    <div id="checkrecord_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="loadrecord">刷新</a>
    </div>

<div id="checkproduct_dialog">
    <form id="checkproduct_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>商品名称:</td>
                <td><input type="text" name="product" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>原有库存:</td>
                <td><input type="text" name="storeNumber" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>修改数量:</td>
                <td><input type="text" name="newNumber" class="easyui-textbox" prompt="输入修改的数量"></td>
            </tr>
            <tr>
                <td>操作人员人员:</td>
                <td><input type="text" name="inputuser" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>备注信息:</td>
                <td><input type="text" name="remark" class="easyui-textbox" prompt="请输入备注信息"></td>
            </tr>
        </table>
    </form>
</div>

    <div id="checkrecord_dialog">
        <form id="checkrecord_form" method="post">
            <input type="hidden" name="id">
            <table align="center" style="margin-top: 15px">
                <tr>
                    <!-- 盘点记录列表-->
                    <table id="checkrecord_datagrid"></table>
                </tr>
            </table>
        </form>
    </div>


</body>
</html>
