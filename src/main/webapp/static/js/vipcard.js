$(function () {
    var vipcardForm = $("#vipcard_form");
    var dialog = $("#dialog");
    var vipcardTable = $("#vipcard_table");
    var objMethod = {
        add: function () {
            vipcardForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增会员卡页面");
        },

        save: function () {
            vipcardForm.form("submit", {
                url: '/vipcard/saveOrUpdate.do',
                success: function (data) {
                    vipcardForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg, function () {
                            vipcardTable.datagrid("reload");
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
            var select = vipcardTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            vipcardForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑会员卡页面");
            select['id'] = select.id;
            vipcardForm.form("load", select);
        },

        reload: function () {
            vipcardTable.datagrid("load");
        },

        change: function () {
            var select = vipcardTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要修改吗？', function (r) {
                if (r) {
                    $.get("/vipcard/changeState.do", {id: select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg, function () {
                                vipcardTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },

        search22: function () {
            var keyword = $("#keyword").textbox("getValue");
            vipcardTable.datagrid("load", {
                keyword: keyword
            });
        }
    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    vipcardTable.datagrid({
        url: "query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'name', title: '会员卡名称', width: 100},
            {
                field: 'discount', title: '会员卡折扣', width: 100, formatter: function (value, row, index) {
                return value.toFixed(2);
            }
            },
            {field: 'minimum', title: '会员卡最小区间', width: 100},
            {field: 'maximum', title: '会员卡最大区间', width: 100},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                if (value > 0) {
                    return "正常使用";
                }  else {
                    return "失效";
                }
            }
            }
        ]],
        toolbar: "#vipcard_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.state > 0) {
                $('#change').linkbutton({
                    text: '停用'
                });
            } else {
                $('#change').linkbutton({
                    text: '恢复'
                });
            }
        }
    })
    dialog.dialog({
        width: 300,
        height: 250,
        closed: true,
        buttons: "#vipcard_btns"
    })

})

