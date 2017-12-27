$(function () {
    var analyzeTable = $("#analyze_table");
    var methodObj = {
        //分组
        groupBy: function () {
            $("#analyze_dialog").dialog("open");
        },
        //分组类型选择
        analyzeConfirm: function () {
           var  type = $(".easyui-combobox").combobox("getValue");
            $.post("/chart/salesAnalyze.do", {type: type}, function (data) {
                $("#analyze_dialog").dialog("close");
                analyzeTable.datagrid("loadData", data);
            });
        },
    }
    //分析数据表格
    analyzeTable.datagrid({
        url: "/chart/salesAnalyze.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect: true,
        columns: [[
            {field: 'groupByName', title: '分组类型', width: 100},
            {field: 'saleAmount', title: '销售总金额', width: 100},
            {field: 'subtotal', title: '利润小计', width: 100},
        ]],
        toolbar: "#analyze_toolbar",
        striped: true,
        rownumbers: true,
        onLoadSuccess: function () {
            var rows = analyzeTable.datagrid("getRows");
            var names = [];
            var moneys = [];
            for (var i = 0; i < rows.length; i++) {
                names[i] = rows[i].groupByName;
                moneys[i] = rows[i].saleAmount;
            }
            // 路径配置
            require.config({
                paths: {
                    echarts: '/static/plugins/echarts/build/dist'
                }
            });

            // 使用
            require(
                [
                    'echarts',
                    'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                    'echarts/chart/line' // 使用折线图就加载bar模块，按需加载
                ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('main'));

                    var option = {
                        tooltip: {
                            show: true
                        },
                        legend: {
                            data: ['销售总金额']
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: names
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: "销售总金额",
                                type: "bar",
                                data: moneys
                            }
                        ]
                    };
                    // 为echarts对象加载数据
                    myChart.setOption(option);
                }
            );
            $.post("/chart/sumProfit.do", function (data) {
                $("#tb").val('销售总金额 :'+data)
            });
        }
    });

//3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })
});