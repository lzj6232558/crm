<%--
  Created by IntelliJ IDEA.
  User: Charles
  Date: 2017/12/17
  Time: 19:47
  To change this tgiftlate use File | Settings | File Tgiftlates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <%@ include file="/static/js/commons.jsp" %>
    <link href="/static/plugins/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/fancybox/jquery.fancybox.pack.js"></script>
    <script type="text/javascript" src="/static/js/gift.js"></script>

</head>
<body>
<table id="gift_datagrid">
</table>

<div id="gift_toolbar">
    <shiro:hasPermission name="gift:saveOrUpdate">
    <a class="easyui-linkbutton" iconCls="icon-add" plain=true
       data-cmd="add">新增</a> <a class="easyui-linkbutton"
                                iconCls="icon-edit" plain=true data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <a id="changeState_btn" class="easyui-linkbutton" iconCls="icon-remove" plain=true data-cmd="delete">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain=true data-cmd="reload">刷新</a>

    <input type="text" id="keyword" name="keyword"/>
    <a class="easyui-linkbutton" plain=true data-cmd="searchAll">查询</a>
</div>

<div id="gift_buttons">
    <a class="easyui-linkbutton" iconCls="icon-add" plain=true data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain=true data-cmd="cancel">取消</a>
</div>



<!-- 表单弹出框 -->
<div id="gift_dialog">
    <form id="gift_form" method="post" enctype="multipart/form-data">
        <table align='center' style="margin-top: 20px">
            <input type="hidden" name="id"/>
            <tbody>
            <tr>
                <td>礼品编号:</td>
                <td><input type="text" name="sn" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>礼品名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>所需积分:</td>
                <td>
                    <input type="text" name="point" class="easyui-textbox"/>
                </td>
            </tr>
                <td>总数量:</td>
                <td><input type="text" name="totalNum" class="easyui-textbox"/></td>
            </tr>
            <tr id="#simg">
                <td >礼品图片:</td>
                <td>
                    <input name = "pic" class="easyui-filebox" buttonText="添加图片">
                </td>
            </tr>
            </tbody>
        </table>

    </form>
</div>


</body>
</html>