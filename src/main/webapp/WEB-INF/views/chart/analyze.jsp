<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/plugins/echarts/build/dist/echarts.js"></script>
    <script type="text/javascript" src="/static/js/chartAnalyze.js"></script>
    <title>销售</title>
</head>
<body>

<div id="p" class="easyui-panel"
     style="width:auto;height:350px;padding:10px;background:#fafafa;"
     data-options="border:false">
    <table id="analyze_table" align="center"></table>
</div>


<div id="analyze_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-little" plain="true" data-cmd="groupBy">分组类型</a>
    <input  id="tb" data-options="readonly:true" style="width:120px">
</div>


<div id="analyze_dialog" class="easyui-dialog" title="分组选择" style="width:328px;height:100px;"
     data-options="closed:true,top:100,resizable:true">
    <form id="analyze_form" method="post">
        <select id="cc" class="easyui-combobox" name="groupBy" style="width:200px;">
            <option value="user.username">业务员</option>
            <option value="p.name">消费商品</option>
            <option value="vc.name">消费对象</option>
        </select>
        <tr>
            <td>
                <a class="easyui-linkbutton" data-cmd="analyzeConfirm" iconCls="icon-confirm"
                   style="align-content:center"
                   plain="true">确定</a>
            </td>
        </tr>
    </form>
</div>


<div id="m" class="easyui-panel"
     style="width:auto;height:350px;padding:10px;background:#fafafa;"
     data-options="border:false">
    <div id="main" style="height:400px"></div>
</div>

</body>
</html>
