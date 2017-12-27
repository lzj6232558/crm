$(function () {
    var roleForm = $("#role_form");
    var dialog = $("#dialog");
    var roleTable = $("#role_table");
    var allPermission = $("#allPermission");
    var selfPermission = $("#selfPermission");
    var objMethod = {
        add: function () {
            roleForm.form("clear");
            selfPermission.datagrid("loadData",[]);
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增角色页面");
        },

        save: function () {
            roleForm.form("submit", {
                url: '/role/saveOrUpdate.do',
                onSubmit:function (param) {
                    var allRows = selfPermission.datagrid("getRows");
                    for(var i =0;i<allRows.length;i++){
                        param["permissions["+i+"].id"] = allRows[i].id;
                    }
                },
                success: function (data) {
                    roleForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg, "warning", function () {
                            roleTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        cancel: function () {
            dialog.dialog("close");
        },

        edit: function () {
            var select = roleTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            //清楚数据
            roleForm.form("clear");
            selfPermission.datagrid("loadData",[]);
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑角色页面");
            roleForm.form("load", select);
            selfPermission.datagrid("load",{id:select.id});

        },

        reload: function () {
            roleTable.datagrid("load");
        },

        change: function () {
            var select = roleTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要修改吗？', function (r) {
                if (r) {
                    $.get("/role/changeState.do", {id: select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg, "warning", function () {
                                roleTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        }
    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    roleTable.datagrid({
        url: "query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'sn', title: '角色编码', width: 100},
            {field: 'name', title: '角色名称', width: 100},
        ]],
        toolbar: "#role_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,

    })
    dialog.dialog({
        width: 450,
        height: 450,
        closed: true,
        buttons: "#role_btns"
    })
    allPermission.datagrid({
        width: 200,
        height: 310,
        fitColumns: true,
        url: '/permission/query.do',
        title: '所有权限',
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: '所有权限', width: 100, algin: 'center'}
        ]],
        onClickRow: function (index, row) {
            var allRows = selfPermission.datagrid("getRows");
            for(var i =0;i<allRows.length;i++){
                if (allRows[i].id == row.id){
                    selfPermission.datagrid("selectRow",i);
                    return;
                }
            }
            selfPermission.datagrid("appendRow",row);

        }


    })
    selfPermission.datagrid({
        width: 200,
        height: 310,
        fitColumns: true,
        url: '/permission/getPermissionbyRoleId.do',
        title: '已有权限',
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: '已有权限', width: 100, algin: 'center'},
        ]]
    })
})

