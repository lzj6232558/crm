<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/chart.js"></script>
    <title>各种报表</title>
</head>
<body>

<div class="easyui-accordion">
    <div title="筛选" class="easyui-panel" style="height:170px;">
        <form id="chart_form" method="post">
            <table id="table">
                <tr>
                    <td>会员信息:<input id="vipNameSearch" name="vipId"
                                    data-options="valueField:'id',limitToList:true,textField:'vipName'"/></td>
                </tr>
                <tr>
                    <td>商品信息:<input id="proSearch" name="proId"
                                    data-options="valueField:'id',limitToList:true,textField:'name'"/></td>
                </tr>
                <tr>
                    <td>消费对象:<input id="consumeObject" name="consumeObjectId"
                                    data-options="valueField:'id',limitToList:true,textField:'name'"/></td>
                </tr>
                <tr>
                    <td>消费来源:<input class="easyui-textbox" type="text" name="consumeSource"/></td>
                </tr>
            </table>
        </form>
    </div> <div title="" selected style="padding:-1px;"></div>
</div>

<table id="chart_table" align="center"></table>


<div id="chart_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-today" plain="true" data-cmd="today">今天</a>
    <a class="easyui-linkbutton" iconCls="icon-yesterday" plain="true" data-cmd="yesterday">昨天</a>
    <a class="easyui-linkbutton" id="other" iconCls="icon-all" plain="true">其他</a>
    <a class="easyui-linkbutton" iconCls="icon-download" plain="true" data-cmd="download">下载</a>
</div>

<div id="other_dialog" class="easyui-dialog" title="选择时间" style="width:300px;height:150px;"
     data-options="closed:true,top:100,resizable:true">
    <table>
        <tr>
            <td>开始时间:</td>
            <td>
                <input class="easyui-datebox" id="beginDate" name="beginDate">
            </td>
        </tr>
        <tr>
            <td>结束时间:</td>
            <td>
                <input class="easyui-datebox" id="endDate" name="endDate">
            </td>
        </tr>
        <tr>
            <td>
                 <a class="easyui-linkbutton" data-cmd="date_confirm" iconCls="icon-confirm" style="align-content:center" plain="true">确定</a>
            </td>
        </tr>
    </table>

</div>
</body>
</html>
