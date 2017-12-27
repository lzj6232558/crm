<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/role.js"></script>
    <title>Title</title>
</head>
<body>
<table id="role_table" align="center"></table>

<div id="role_toolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
</div>

<div id="role_btns">
    <a  class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
    <a  class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>


<div id="dialog">
    <form id="role_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>角色编码:<input type="text" name="sn" class="easyui-textbox"></td>
                <td>角色名称:<input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td><table id="allPermission"></table></td>
                <td><table id="selfPermission"></table></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
