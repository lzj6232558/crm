$(function () {
    //抽取变量
    var pointexchange_datagrid = $("#pointexchange_datagrid");
    var pointexchange_dialog = $("#pointexchange_dialog");
    var pointexchange_form = $("#pointexchange_form");
    var methodObj = {

        //取消按钮
        cancel: function () {
            pointexchange_dialog.dialog("close");
        },

//高级查询
        searchAll: function () {
            var keyword = $("#keyword").textbox("getValue");
            pointexchange_datagrid.datagrid("load", {
                keyword: keyword
            });
        },
//导出按钮
        exportXls: function () {
            window.location.href = "/pointexchange/export.do";
        },

    }


        //页面加载完成,统一绑定事件
        $("[data-cmd]").click(function () {
        //获取有点击事件的按钮的方法名称
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    });


    //初始化数据表格框
    pointexchange_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/pointexchange/query.do',
        singleSelect: true,
        columns: [[
            {field: 'vip.vipNumber', title: '会员卡号', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.vipNumber;
            }
            },
            {field: 'vip.vipName', title: '会员名称', width: 100, formatter: function (value, row, index) {
                return row == null ? "" :row.vip.vipName;
            }
            },
            { field: 'gift.name', title: '礼品名称', width: 100, formatter: function (value, row, index) {
                return row == null ? "" :row.gift.name;
            }
            },
            {field: 'number', title: '兑换数量', width: 100},

         /*   {field: 'vip.currentpoint', title: '当前积分', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.currentpoint;
            }
            }*/
            {
                field: 'gift.point', title: '消费积分', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : (row.gift.point)*(row.number);
            }
            },
            {field: 'inputTime', title: '兑换日期', width: 100}
        ]],
        toolbar: '#pointexchange_toolbar',
        striped: true,
        pagination: true,
        rownumbers: true
    })

    //初始化搜索框
    $("#keyword").searchbox({
        prompt: "输入会员名或卡号",
        searcher: function (value, name) {
            //高级查询
            pointexchange_datagrid.datagrid("load", {
                "keyword": value
            })
        }
    });
})









