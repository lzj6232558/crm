<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/disburse.js"></script>
    <title>支出</title>
</head>
<body>

<table id="disburse_table" align="center"></table>


<div id="disburse_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
    <a class="easyui-linkbutton" iconCls="icon-download" plain="true" data-cmd="download">导出</a>
    <a class="easyui-linkbutton" iconCls="icon-delete" plain="true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-big" plain="true" data-cmd="big">添加大分类</a>
    <a class="easyui-linkbutton" iconCls="icon-little" plain="true" data-cmd="little">添加小分类</a>
</div>

<div id="bigClassify_dialog" class="easyui-dialog" title="大分类" style="width:328px;height:100px;"
     data-options="closed:true,top:100,resizable:true">
    <form id="bigClassify_form" method="post">
        <tr>
            <input type="text" class="easyui-textbox"
                   data-options="prompt:'分类信息'" required= true  name="classify" style="width:300px">
        </tr>
        <tr>
            <td>
                <a class="easyui-linkbutton" data-cmd="bigConfirm" iconCls="icon-confirm" style="align-content:center"
                   plain="true">确定</a>
            </td>
        </tr>
    </form>
</div>

<div id="littleClassify_dialog" class="easyui-dialog" title="小分类" style="width:328px;height:130px;"
     data-options="closed:true,top:100,resizable:true">
    <form id="littleClassify_form" method="post">
        <tr>
            <td>所属分类 :<input class="easyui-combobox" name="bigClassify.id"
                            data-options="valueField:'id',textField:'classify',url:'/disburseBigClassify/query.do'"/>
            </td>
        </tr>
        <tr>
            <input type="text" class="easyui-textbox"
                   data-options="prompt:'分类信息'" required= true  name="classify" style="width:300px">
        </tr>
        <tr>
            <td>
                <a class="easyui-linkbutton" data-cmd="littleConfirm" iconCls="icon-confirm" style="align-content:center"
                   plain="true">确定</a>
            </td>
        </tr>
    </form>
</div>


<div id="disburse_dialog" class="easyui-dialog" title="添加明细" style="width:328px;height:413px;"
     data-options="closed:true,top:100,resizable:true">
    <form id="disburse_form" method="post">
        <table>
            <tr>

                <td>支出时间:<input class="easyui-datebox" id="disburseTime" name="disburseTime"></td>
            </tr>
            <tr>
                <td>大分类 :<input id="bigClassify" class="easyui-combobox" name="bigClassify.id"
                                data-options="valueField:'id',textField:'classify',url:'/disburseBigClassify/query.do'"/>
                </td>
            </tr>
            <tr>
                <td>小分类 :<input id="littleClassify" class="easyui-combobox" name="littleClassify.id"
                                data-options="valueField:'id',limitToList:true,textField:'classify'"/></td>
            </tr>
            <tr>
                <input type="text" class="easyui-textbox"
                       data-options="prompt:'支出详细信息'" name="detail" style="width:300px">
            </tr>
            <tr>
                <input type="text" class="easyui-textbox"
                       data-options="prompt:'金额'" name="money" style="width:300px">
            </tr>
            <tr>
                <td>支出人 :<input class="easyui-combobox" name="disburseUser.id"
                                data-options="valueField:'id',textField:'username',url:'/employee/selectDisburseAll.do'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="easyui-linkbutton" data-cmd="confirm" iconCls="icon-confirm" style="align-content:center"
                       plain="true">确定</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
