$(function () {
    var chartTable = $("#chart_table");
    var chartForm = $("#chart_form");
    var proSearch = $("#proSearch");
    var vipNameSearch = $("#vipNameSearch");
    var sellIdSearch = $("#sellIdSearch");
    var consumeObject = $("#consumeObject");
    var methodObj = {
        //其他时间
        date_confirm: function () {
            $("#other_dialog").dialog("close");
            chartForm.form("submit", {
                url: '/chart/sale.do',
                onSubmit:function (param) {
                    param["beginDate"] = $("#_easyui_textbox_input2").next().val()
                    param["endDate"] = $("#_easyui_textbox_input3").next().val()
                },
                success: function (data) {
                    chartTable.datagrid("loadData", $.parseJSON(data));
                }
            })
        },
        //今天
        today: function () {
            chartForm.form("submit", {
                url: '/chart/sale.do',
                success: function (data) {
                    chartTable.datagrid("loadData", $.parseJSON(data));
                },
                onSubmit: function (param) {
                    //时间
                    param["dateState"] = "1"
                },
            })
        },
        //昨天
        yesterday: function () {
            chartForm.form("submit", {
                url: '/chart/sale.do',
                success: function (data) {
                    chartTable.datagrid("loadData", $.parseJSON(data));
                },
                onSubmit: function (param) {
                    //时间
                    param["dateState"] = "2"
                },
            })
        },
        //导出
        download: function () {
            window.location.href = "/chart/download.do"
        },
    }
    //搜索商品
    proSearch.combobox({
        url: '/product/selectProByName.do',
        onChange: function (newValue, oldValue) {
            var proName = proSearch.combobox("getText")
            proSearch.combobox("reload", {proName: proName});
        },
    });
    //搜索会员
    vipNameSearch.combobox({
        url: '/vip/selectVipByNameOrPhone.do',
        onChange: function (newValue, oldValue) {
            var nameOrPhone = vipNameSearch.combobox("getText")
            vipNameSearch.combobox("reload", {nameOrPhone: nameOrPhone});
        },
    });

   //搜索单号
    consumeObject.combobox({
        url: '/vipcard/query.do',
        onChange: function (newValue, oldValue) {
            var consumeObject = consumeObject.combobox("getText")
            consumeObject.combobox("reload", {proName: consumeObject});
        },
    });
    chartTable.datagrid({
        url: "/chart/sale.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'proName', title: '商品名', width: 100},
            {field: 'sellObject', title: '消费对象', width: 100},
            {field: 'costPrice', title: '进货价', width: 100},
            {field: 'salePrice', title: '销售价', width: 100},
            {field: 'discount', title: '折扣', width: 100},
            {field: 'buyNunber', title: '数量', width: 100},
            {field: 'saleAmount', title: '销售金额', width: 100},
            {field: 'profit', title: '利润', width: 100},
            {field: 'sellVdate', title: '消费时间', width: 100},
        ]],
        toolbar: "#chart_toolbar",
    });
    $("#other").linkbutton({
        onClick: function () {
            $("#other_dialog").dialog("open");
        }
    })
//3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })
});
