<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/product/producttype.js"></script>
    <title>Title</title>
</head>
<body class="easyui-layout">

<!--二级分类列表需要用的toolbar-->
<div id="child_toolbar">
    <shiro:hasPermission name="producttype:saveOrUpdate">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="addChild">增加</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="editChild">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="producttype:delete">
    <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="deleteChild">删除</a>
    </shiro:hasPermission>
</div>

<!--一级分类页面-->
<div data-options="region:'west',split:true" style="width:280px;">
    <div id="parent_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-undo" plain="true" data-cmd="allType">所有分类</a>
        <shiro:hasPermission name="producttype:saveOrUpdate">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="addParent">增加</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="editParent">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="producttype:delete">
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="deleteParent">删除</a>
        </shiro:hasPermission>
    </div>
    <table id="parent_table" align="center"></table>
</div>
<!--二级分类页面-->
<div data-options="region:'center',split:true">
    <table id="child_table" align="center"></table>
</div>
<!--一级分类添加/编辑页面-->
<div id="parent_dialog">
    <form id="parent_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>一级分类名称</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
<!--一级分类保存/取消按钮-->
<div id="parent_btns">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="saveParent">保存</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancelParent">取消</a>
</div>
<!--二级分类添加/编辑页面-->
<div id="child_dialog">
    <form id="child_form" method="post">
        <input type="hidden" name="id">
        <table id="table2" align="center">
            <tr>
                <td>二级分类名称</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
<!--二级分类保存/取消按钮-->
<div id="child_btns">
    <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="saveChild">保存</a>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancelChild">取消</a>
</div>

</body>
</html>

