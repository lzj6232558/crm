<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/systemDictionary.js"></script>
    <title>Title</title>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
    <div data-options="region:'west',title:'字典目录',split:true" style="width:600px;">
        <table id="dictionary_table"></table>
    </div>
    <div data-options="region:'center',title:'字典目录明细'"  style="padding:5px;background:#eee;">
        <table id="dictionaryItem_table"></table>
    </div>
</div>
<%--字典目录按钮--%>
<div id="dictionary_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="dictionary_add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="dictionary_edit">编辑</a>
        <a class="easyui-linkbutton" id="dictionary_delete" iconCls="icon-remove" plain="true" data-cmd="dictionary_delete">删除</a>
        <a class="easyui-linkbutton" id="dictionary_refresh" iconCls="icon-remove" plain="true" data-cmd="dictionary_reload">刷新</a>
</div>
<%--明细目录按钮--%>
<div id="dictionaryItem_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="dictionaryItem_add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="dictionaryItem_edit">编辑</a>
        <a class="easyui-linkbutton" id="delete" iconCls="icon-remove" plain="true" data-cmd="dictionaryItem_delete">删除</a>
        <a class="easyui-linkbutton" id="refresh" iconCls="icon-remove" plain="true" data-cmd="dictionaryItem_reload">刷新</a>
</div>


<%--字典目录提交关闭--%>
<div id="dictionary_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="dictionary_save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="dictionary_cancel">关闭</a>
</div>
<%--明细提交关闭--%>
<div id="dictionaryItem_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="dictionaryItem_save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="dictionaryItem_cancel">关闭</a>
</div>


<%--字典列表--%>
<div id="dialog">
    <form id="dictionary_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>目录编码:</td>
                <td><input type="text" name="sn" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>目录名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>目录简介:</td>
                <td><input type="text" name="intro" class="easyui-textbox"></td>
            </tr>
        </table>
    </form>
</div>
<%--明细列表--%>
<div id="dialog2">
    <form id="dictionaryItem_form" method="post">
        <input type="hidden" name="id">
        <table id="table2" align="center">
            <tr>
                <td>明细名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>明细简介:</td>
                <td><input type="text" name="intro" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>所属目录:</td>
                <td>
                    <input class="easyui-combobox"
                           data-options="url:'/systemDictionary/query.do',valueField:'id',textField:'name',panelHeight:'auto'"
                           name="systemDictionary.id">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
