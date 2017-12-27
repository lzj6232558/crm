<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/pointexchange.js"></script>
    <meta charset="UTF-8">
    <title>积分兑换记录</title>

</head>
<body>
    <table id="pointexchange_datagrid"></table>

<div id="pointexchange_toolbar">
    <input type="text" id="keyword" name="keyword" class="easyui-textbox"/>
    <a class="easyui-linkbutton" plain=true data-cmd="searchAll">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-redo" plain=true data-cmd="exportXls">导出Excel</a>
</div>

<div id="pointexchange_buttons">
    <a class="easyui-linkbutton" iconCls="icon-add" plain=true data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain=true data-cmd="cancel">取消</a>
</div>



</body>
</html>