$(function () {
    var menuForm = $("#menu_form");
    var dialog = $("#dialog");
    var menuTable = $("#menu_table");
    var objMethod = {
        add: function () {
            menuForm.form("clear");
            selfPermission.datagrid("loadData", []);
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增角色页面");
        },

        save: function () {
            menuForm.form("submit", {
                url: '/menu/saveOrUpdate.do',
                onSubmit: function (param) {

                },
                success: function (data) {
                    menuForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg, "warning", function () {
                            menuTable.datagrid("reload");
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
            var select = menuTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            //清除数据
            menuForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑菜单页面");
            menuForm.form("load", select);
        },

        reload: function () {
            menuTable.datagrid("load");
        },

        change: function () {
            var select = menuTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.get("/menu/changeState.do", {id: select.id}, function (data) {
                if (!data.success) {
                    $.messager.alert("温馨提示", data.msg, "warning");
                } else {
                    $.messager.alert('温馨提示', data.msg, "warning", function () {
                        menuTable.datagrid("reload");
                    });
                }
            }, 'json')
        }
    }
})

$("[data-cmd]").click(function () {
    var cmd = $(this).data("cmd");
    objMethod[cmd]();
})

menuTable.datagrid({
    url: "query.do",
    fit: true,
    fitColumns: true,
    pagination: true,
    columns: [[
        {field: 'text', title: '系统模块', width: 100},
        {field: 'url', title: 'url', width: 100},
        {field: 'parent', title: '父菜单', width: 100}
    ]],
    toolbar: "#menu_toolbar",
    striped: true,
    rownumbers: true,
    singleSelect: true,

})
dialog.dialog({
    width: 450,
    height: 450,
    closed: true,
    buttons: "#menu_btns"
}

)

