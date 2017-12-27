$(function () {
    var disburseTable = $("#disburse_table");
    var methodObj = {
        //添加分类
        big:function () {
            $('#bigClassify_form').form('clear');
            $("#bigClassify_dialog").dialog("open");
        },
        little:function () {
            $('#littleClassify_form').form('clear');
            $("#littleClassify_dialog").dialog("open");
        },
        //导出
        download: function () {
            window.location.href = "/disburse/download.do"
        },
        //添加
        add:function () {
            $('#disburse_form').form('clear');
            $("#disburse_dialog").dialog("open");
        },
        //保存支出信息
        confirm:function () {
            $("#disburse_form").form("submit", {
                url: '/disburse/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data)
                    if(!data.success){
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }else {
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#disburse_dialog").dialog("close");
                            disburseTable.datagrid("reload");
                        });
                    }
                },
            });
        },
        //保存大分类
        bigConfirm:function () {
            $("#bigClassify_form").form("submit", {
                url: '/disburseBigClassify/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data)
                    if(!data.success){
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }else {
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#bigClassify_dialog").dialog("close");
                            disburseTable.datagrid("reload");
                        });
                    }
                },
            });
        },
        //保存小分类
        littleConfirm:function () {
            $("#littleClassify_form").form("submit", {
                url: '/littleClassify/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data)
                    if(!data.success){
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }else {
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#littleClassify_dialog").dialog("close");
                            disburseTable.datagrid("reload");
                        });
                    }
                },
            });
        },
        remove: function () {
            var select = disburseTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要修改吗？', function (r) {
                if (r) {
                    $.get("/disburse/delete.do", {id: select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg,"warning", function () {
                                disburseTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },
    }
    //数据表格
    disburseTable.datagrid({
        url: "/disburse/query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect:true,
        columns: [[
            {field: 'bigClassify', title: '支出分类', width: 100,formatter:function (value, row, index) {
                return value? value.classify:'';
            }},
            {field: 'money', title: '支出金额', width: 100},
            {field: 'disburseTime', title: '支出时间', width: 100},
            {field: 'disburseUser', title: '支出人员', width: 100,formatter:function (value, row, index) {
                return value? value.username:'';
            }},
            {field: 'detail', title: '支出备注', width: 100},
        ]],
        toolbar: "#disburse_toolbar",
    });

//3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })

    //当大分类发生改变搜索对应的小分类
    $("#bigClassify").combobox({
        onHidePanel: function (newValue, oldValue) {
            console.log($("#bigClassify").combobox("getText"))
            var bigName = $("#bigClassify").combobox("getText")
            $.post("/littleClassify/getLittleByBigName.do", {bigName: bigName}, function (data) {
                $("#littleClassify").combobox("loadData", data);
            });
        },
    });
});