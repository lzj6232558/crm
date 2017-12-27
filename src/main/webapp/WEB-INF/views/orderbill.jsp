<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/static/js/orderbill.js"></script>

</head>
<body>
    <table id="orderbill_datagrid"></table>

    <div id="orderbill_toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-cmd="add">新增</a>
        <a id="detail_btn" class="easyui-linkbutton" plain="true" iconCls="icon-details" data-cmd="details">详情</a>
        <a id="edit_btn" class="easyui-linkbutton" plain="true" iconCls="icon-edit" data-cmd="edit">编辑</a>
        <a id="audit_btn" class="easyui-linkbutton" plain="true" iconCls="icon-audit" data-cmd="audit">审核</a>
        <a id="delete_btn" class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

        <input class="easyui-textbox" name="keyword" id="keyword" prompt="输入供应商/单据编号/商品名称/仓库名">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    </div>

    <div id="orderbill_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

    <!-- 商品列表按钮-->
    <div id="product_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <input id="productKeyword" class="easyui-textbox" name="keyword">
        <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="searchProduct">搜索</a>
    </div>

    <%--<div id="product_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>--%>

<div id="orderbill_dialog">
    <form id="orderbill_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>编号:</td>
                <td><input type="text" name="sn" class="easyui-textbox"></td>

                <td>供应商:</td>
                <td>
                    <input type="text" name="supplier.id"  class="easyui-combobox"
                           data-options="url:'/supplier/selectAll.do',
                    textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>

                <td>时间:</td>
                <td><input type="text" name="vdate"  class="easyui-datebox"/></td>
                <td>仓库:</td>
                <td>
                    <input type="text" name="depot.id"  class="easyui-combobox"
                           data-options="url:'/depot/selectAll.do',
                    textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <!--商品列表 -->
                <table id="product_table" align="center"></table>
            </tr>
            <tr>
                <td><font color="red" size="4">商品数量:</font></td>
                <td><input type="text" name="totalnumber"  class="easyui-textbox"/></td>
            </tr>
        </table>
    </form>
</div>






    <div id="orderbill_dialog_details">
        <form id="orderbill_form_details" method="post">
            <input type="hidden" name="id">
            <table align="center" style="margin-top: 15px">
                <tr>
                    <td>订单编号:</td>
                    <td><input type="text" name="sn" class="easyui-textbox"></td>
                </tr>
                <tr>
                    <td>业务时间:</td>
                    <td><input type="text" name="vdate"  class="easyui-datebox"/></td>
                </tr>
                <tr>
                    <td>供应商:</td>
                    <td>
                        <input type="text" name="supplier.id"  class="easyui-combobox"
                               data-options="url:'/supplier/selectAll.do',
                    textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr>
                    <td>所在仓库:</td>
                    <td>
                        <input type="text" name="depot.id"  class="easyui-combobox"
                               data-options="url:'/depot/selectAll.do',
                    textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr>
                    <td>总金额:</td>
                    <td><input type="text" name="totalamount"  class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>总数量:</td>
                    <td><input type="text" name="totalnumber"  class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>录入人:</td>
                    <td><input type="text" name="inputuser"  class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>审核人:</td>
                    <td><input type="text" name="auditor"  class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <!--商品列表 -->
                    <table id="product_table_details" align="center"></table>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
