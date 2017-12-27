<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp"%>
    <script type="text/javascript">
        $(function () {
            var obj = {
                remove: function () {
                    var tr = $("#myTable").datagrid("getSelected");
                    if (!tr) {
                        $.messager.alert('提示', '请选择需要删除的权限');
                        return;
                    }
                    $.get("/permission/delete.do", {id: tr.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert('提示', data.massage);
                        } else {
                            $.messager.alert('提示', '删除成功');
                            $("#myTable").datagrid("reload");
                        }
                    })
                },
                reload: function () {
                    $.messager.confirm('确认', '您确认想要刷新权限信息吗？', function (r) {
                        if (r) {
                            $.get("/permission/reload.do", function (data) {
                                if (!data.success) {
                                    $.messager.alert('提示', data.massage);
                                } else {
                                    $.messager.alert('提示', '刷新成功');
                                    $("#myTable").datagrid("reload");
                                }
                            })
                        }
                    });
                },
            }
            $("[data-cmd]").click(function () {
                var cmd = $(this).data("cmd");
                obj[cmd]();
            })

            $("#myTable").datagrid({
                fit: true,
                fitColumns: true,
                url: "/permission/query.do",
                columns: [[
                    {field: 'name', title: '权限名称', width: 100},
                    {field: 'resource', title: '权限表达式', width: 100},
                ]],
                pagination: true,
                singleSelect: true,
                striped: true,
                rownumbers: true,
                toolbar: "#toolbar_btn",
            })

        })
    </script>
</head>
<body>
<table id="myTable"></table>
<div id="toolbar_btn">
    <a class="easyui-linkbutton" id="removeId" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
</div>

<div id="myDialog">
    <form id="myForm" method="post">
        <table>

            <tr>
                <td>权限名称</td>
                <td>
                    <input name="id" type="hidden">
                    <input class="easyui-textbox" name="name"/>
                </td>
            </tr>


            <tr>
                <td>权限表达式</td>
                <td>
                    <input class="easyui-textbox" name="resource"/>
                </td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>
