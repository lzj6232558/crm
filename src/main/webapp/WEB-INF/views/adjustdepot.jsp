<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/js/adjustdepot.js"></script>
</head>
<body>
    <table id="adjustdepot_datagrid"></table>

    <div id="adjustdepot_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-cmd="add">新增调拨</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-cmd="delete">删除调拨信息</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

        <input class="easyui-textbox" name="keyword" id="keyword" prompt="输入商品名称、编码">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    </div>

    <div id="adjustdepot_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

    <div id="orderbill_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-allot" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-quxiao" data-cmd="cancel"></a>
    </div>


    <!-- 商品列表按钮-->
    <div id="product_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <input id="productKeyword" class="easyui-textbox" name="keyword">
        <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="searchProduct">搜索</a>
    </div>

    <div id="orderbill_dialog">
        <form id="orderbill_form" method="post">
            <input type="hidden" name="id">
            <table align="center" style="margin-top: 15px">
                <tr>
                    <td>调出仓库:</td>
                    <td>
                        <input type="text" name="outdepot.id"  class="easyui-combobox"
                               data-options="url:'/depot/selectAll.do',
                    textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                    <td>调入仓库:</td>
                    <td>
                        <input type="text" name="indepot.id"  class="easyui-combobox"
                               data-options="url:'/depot/selectAll.do',
                    textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr>
                    <!--商品列表 -->
                    <table id="product_table" align="center"></table>
                </tr>
                <tr>
                    <td><font color="red" size="4">调拨数量:</font></td>
                    <td><input type="text" name="differ"  class="easyui-textbox"/></td>
                </tr>
                <br/>
                <tr>
                    <td><font color="red" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</font></td>
                    <td><input type="text" name="remark"  class="easyui-textbox"/></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
