$(function () {
    var deptForm = $("#dept_form");
    var dialog = $("#dialog");
    var deptTable = $("#dept_table");
    var objMethod = {
        add: function () {
            deptForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增部门页面");
        },

        save: function () {
            deptForm.form("submit", {
                url: '/department/saveOrUpdate.do',
                success: function (data) {
                    deptForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg, function () {
                            deptTable.datagrid("reload");
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
            var select = deptTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            deptForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑部门页面");
            select['dept.id'] = select.dept.id;
            deptForm.form("load", select);

        },

        reload: function () {
            deptTable.datagrid("load");
        },

        change: function () {
            var select = deptTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要修改吗？', function (r) {
                if (r) {
                    $.get("/department/changeState.do", {id: select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg, function () {
                                deptTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },

        search22: function () {
            var keyword = $("#keyword").textbox("getValue");
            deptTable.datagrid("load", {
                keyword: keyword
            });
        }
    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    deptTable.datagrid({
        url: "query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'sn', title: '部门编码', width: 100},
            {field: 'name', title: '部门名称', width: 100},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return value ? "正常" : "撤销";
            }
            }
        ]],
        toolbar: "#dept_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.state) {
                $('#change').linkbutton({
                    text: '撤销'
                });
            } else {
                $('#change').linkbutton({
                    text: '恢复'
                });
            }
        }
    })
    dialog.dialog({
        width: 400,
        height: 450,
        closed: true,
        buttons: "#dept_btns"
    })

})

