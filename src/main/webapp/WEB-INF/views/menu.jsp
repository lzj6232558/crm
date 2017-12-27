<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/menu.js"></script>
    <title>Title</title>
</head>
<body>
<table id="menu_table" align="center"></table>

<div id="menu_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
</div>

<div id="menu_btns">
    <a  class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
    <a  class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>


<div id="dialog">
    <form id="menu_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>角色编码:<input type="text" name="sn" class="easyui-textbox"></td>
                <td>角色名称:<input type="text" name="name" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
