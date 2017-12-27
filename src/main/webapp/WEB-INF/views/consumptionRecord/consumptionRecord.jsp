<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/consumptionRecord.js"></script>
    <script type="text/javascript" src="/static/plugins/echarts/build/dist/echarts.js"></script>
    <title>Title</title>
    <script type="text/javascript">

    </script>
</head>
<body>

<div id="p" class="easyui-panel"
     style="width:auto;height:350px;padding:10px;background:#fafafa;"
     data-options="border:false">
        <table id="consumptionRecord_table" align="center"></table>

</div>


<div id="consumptionRecord_toolbar">
    最近:
    <input id="formatNum"  class="easyui-textbox" name="formatNum" data-options="width:50">
    <input id="timeFormat" class="easyui-combobox" name="timeFormat"
           data-options="width:50,valueField:'intro',panelHeight:'auto',textField:'name',url:'/vip/source.do?id=5'" />
    <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search22">搜索</a>
</div>

<div id="main" style="height:400px"></div>
</body>
</html>
