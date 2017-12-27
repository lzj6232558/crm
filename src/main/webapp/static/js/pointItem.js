$(function () {

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })
    var objMethod = {
        //高级查询(会员信息)
        searchAll: function () {
            var keyword = $("#keyword").textbox("getValue");
            $("#vip_table").datagrid("load", {
                keyword: keyword
            })
        },


        //积分充值
        recharge: function () {
            var row = $("#vip_table").datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '点击选中会员列表进行充值', "warning");
                return;
            }
            $("#recharge_form").form("submit", {
                url: '/pointItem/recharge.do',
                onSubmit: function (param) {
                    var num = $("#changeNum").textbox("getText");
                    if (!num) {
                        $.messager.alert('温馨提示', '请添加变动数额', "warning");
                        return false;
                    }
                    param["vip_id"] = $("#vip_id").val();
                    var flag = $("input[name='choose']").prop("checked");
                    param["id"] = row.id;
                    param["flag"] = flag;
                    param["changeNum"] = num;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, "warning", function () {
                            $("#pointItem_table").datagrid("reload");
                            $("#vip_form").form("reload", row);
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg)
                    }
                }
            })
        },

        //积分清零
        clear: function () {
            //判断用户是否选中数据
            var row = $("#vip_table").datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认要清零该会员全部积分吗？', function (r) {
                if (r) {
                    $.get("/pointItem/clear.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功!", "info", function () {
                                //重新加载数据表格
                                $("#pointItem_table").datagrid("reload");
                                $("#vip_form").form("reload", row);
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                        }
                    }, "json")
                }
            });
        },
    }

    $("#pointItem_table").datagrid({
        url: "/pointItem/query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        toolbar: '#vip_toolbar',
        striped: true,
        pagination: true,
        columns: [[
            {
                field: 'vip.vipNumber', title: '会员卡号', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.vipNumber;
            }
            },
            {
                field: 'vip.vipName', title: '会员姓名', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.vipName;
            }
            },
            {
                field: 'card', title: '会员等级', width: 100, formatter: function (value, row, index) {
                return value == null ? "" : value.name;
            }
            },
            {
                field: 'inputUser', title: '操作人员', width: 100, formatter: function (value, row, index) {
                if (value) {
                    return value.username;
                }
            }
            },
            {
                field: 'op', title: '操作类型', width: 100, formatter: function (value, row, index) {
                if (value > 0) {
                    return "积分充值";
                } else if (value == 0) {
                    return "积分扣除";
                }
            }
            },
            {
                field: 'changeNum', title: '变动金额', width: 100, styler: function (value, row, index) {
                if (value > 0) {
                    return 'color:green;color:red;';
                }
            }
            },
            {field: 'opTime', title: '充值时间', width: 100},
            {field: 'remark', title: '备注', width: 100},
        ]],

    })

    //会员列表
    $("#vip_table").datagrid({
        url: "/vip/query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'vipNumber', title: '会员卡号', width: 100},
            {field: 'vipName', title: '会员姓名', width: 100},
            {field: 'vipPhone', title: '会员电话', width: 100},
            {field: 'currentMoney', title: '当前金额', width: 100},
            {field: 'amountMoney', title: '充值总金额', width: 100},
            {
                field: 'vipcard', title: '会员卡类型', width: 100, formatter: function (value, row, index) {
                return value.name;
            }
            },
            {
                field: 'employee', title: '录入员工', width: 100, formatter: function (value, row, index) {
                return row.employee.username;
            }
            }
        ]],
        toolbar: "#vip_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            row["vipcard"] = row.vipcard.name;
            row["employee"] = row.employee.username;
            $("#vip_form").form("load", row);
        }
    })




    //初始化表单
   // $("#vip_form").form({})
    //初始化搜索框
    $("#keyword").searchbox({
        prompt:"输入会员卡号",
        searcher: function (value, name) {
            //高级查询
            gift_datagrid.datagrid("load", {
                "keyword": value
            })
        }
    });

})
