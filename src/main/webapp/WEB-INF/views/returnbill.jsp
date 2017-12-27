<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <script type="text/javascript" src="/static/js/returnbill.js"></script>

</head>
<body>
    <table id="returnbill_datagrid"></table>

    <div id="returnbill_toolbar">
        <a id="detail_btn" class="easyui-linkbutton" plain="true" iconCls="icon-details" data-cmd="details">详情</a>
        <a id="returnbill_btn" class="easyui-linkbutton" plain="true" iconCls="icon-returnbill" data-cmd="returnbill">退货</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="load">刷新</a>

        <input class="easyui-textbox" name="keyword" id="keyword" prompt="输入供应商名称/业务单号">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchForm">搜索</a>
    </div>

    <div id="returnbill_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save"></a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel"></a>
    </div>

    <!-- 商品列表按钮-->
    <div id="product_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <input id="keyword2" class="easyui-textbox" name="keyword">
        <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search22">搜索</a>
    </div>

    <div id="product_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>

<div id="returnbill_dialog">
    <form id="returnbill_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>订单编号:</td>
                <td><input type="text" name="sn" class="easyui-textbox"></td>
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
                <td>业务时间:</td>
                <td><input type="text" name="vdate"  class="easyui-datebox"/></td>
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
                <!--商品列表 -->
                <table id="product_table" align="center"></table>
            </tr>
            <tr>
                <td><font color="red" size="10">请选择商品数量:</font></td>
                <td><input type="text" name="totalnumber"  class="easyui-textbox"/></td>
            </tr>
        </table>
    </form>
</div>

    <div id="returnbill_dialog_details">
        <form id="returnbill_form_details" method="post">
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
