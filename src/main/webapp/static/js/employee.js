$(function () {
    var empForm = $("#emp_form");
    var dialog = $("#dialog");
    var empTable = $("#emp_table");
    var objMethod = {
        add: function () {
            empForm.form("clear");
            dialog.dialog("open");
            $("#pwd").show();
            dialog.dialog("setTitle", "新增员工页面");
        },

        save: function () {
            empForm.form("submit", {
                url: '/employee/saveOrUpdate.do',
                onSubmit:function (param) {
                    var roles = $("#role_combo").combobox("getValues");
                    for(var i=0;i<roles.length;i++){
                        param["roles["+i+"].id"]=roles[i];
                    }
                },
                success: function (data) {
                    empForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            empTable.datagrid("reload");
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
            var select = empTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $("#pwd").hide();
            empForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑员工页面");
            select['dept.id'] = select.dept.id;
            empForm.form("load", select);

            $.get("/employee/getRoleIdByEmpId.do",{id:select.id},function (data) {
                $("#role_combo").combobox("setValues",data);
            })


        },

        reload: function () {
            empTable.datagrid("load");
        },

        change: function () {
            var select = empTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要修改吗？', function (r) {
                if (r) {
                    $.get("/employee/changeState.do", {id: select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg,"warning", function () {
                                empTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },

        search22: function () {
            var keyword = $("#keyword").textbox("getValue");
            empTable.datagrid("load", {
                keyword: keyword,
                inputtime:inputtime
            });
        },
        exportXls:function () {
            window.location.href="/employee/export.do";
        },
        imlportXls:function () {
            $("#imlport").dialog("setTitle","文件上传")
            $("#imlport").dialog("open");
        },
        imlportbtn:function () {
            $("#imlportForm").form("submit",{
                url:'/employee/imlport.do',
                success:function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#imlport").dialog("close");
                            empTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        }
    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    empTable.datagrid({
        url: "query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'username', title: '员工姓名', width: 100},
            {field: 'realname', title: '真实姓名', width: 100},
            {field: 'password', title: '密码', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {
                field: 'dept', title: '部门', width: 100, formatter: function (value, row, index) {
                return value ? value.name : "";
            }
            },
            {field: 'inputtime', title: '入职时间', width: 100},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return value ? "在职" : "离职";
            }
            },
            {
                field: 'admin', title: '是否为管理员', width: 100, formatter: function (value, row, index) {
                return value ? "是" : "否";
            }
            }
        ]],
        toolbar: "#emp_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.state) {
                $('#change').linkbutton({
                    text: '离职'
                });
            } else {
                $('#change').linkbutton({
                    text: '复职'
                });
            }
        }
    })

    dialog.dialog({
        width: 400,
        height: 450,
        closed: true,
        buttons: "#emp_btns"
    })

    $("#imlport").dialog({
        width: 300,
        height: 250,
        closed: true,
        buttons:'#imlportbtn'
    })

})

