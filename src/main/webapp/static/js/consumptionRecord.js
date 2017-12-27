$(function () {
    $("#_easyui_textbox_input1").blur(function () {
        if($("#_easyui_textbox_input1").next().val()){
            if(!$("#_easyui_textbox_input2").next().val()){
                $("#timeFormat").combobox("setValue",'DAY');
            }
        }else{
            $("#timeFormat").combobox("setValue",'');
        }
    })

    $("#_easyui_textbox_input2").blur(function () {
        if($("#_easyui_textbox_input2").next().val() || $("#_easyui_textbox_input2").next().val()==""){
            if(!$("#_easyui_textbox_input1").next().val()){
                $("#formatNum").textbox("setValue","1");
            }else if($("#_easyui_textbox_input2").next().val() ==""){
                $("#formatNum").textbox("setValue","");
            }
        }
    })


    var consumptionRecord_table = $("#consumptionRecord_table");
    var objMethod = {

        search22: function () {
            var timeFormat = $("#timeFormat").textbox("getValue")
            var formatNum = $("#formatNum").textbox("getValue")
            consumptionRecord_table.datagrid("load",{
                timeFormat:timeFormat,
                formatNum:formatNum
            })
        }

    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    consumptionRecord_table.datagrid({
        url: "query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'vipnumber', title: '会员卡号', width: 100},
            {field: 'vipname', title: '会员名称', width: 100},
            {field: 'amountmoney', title: '消费金额', width: 100}
        ]],
         toolbar: "#consumptionRecord_toolbar",
        striped: true,
        rownumbers: true,
        onLoadSuccess:function () {
            var rows = consumptionRecord_table.datagrid("getRows");
            var names = [];
            var moneys = [];
            for(var i =0;i<rows.length;i++){
                names[i] = rows[i].vipname;
                moneys[i] = rows[i].amountmoney;
            }
            // 路径配置
            require.config({
                paths: {
                    echarts:'/static/plugins/echarts/build/dist'
                }
            });

            // 使用
            require(
                [
                    'echarts',
                    'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('main'));

                    var option = {
                        tooltip: {
                            show: true
                        },
                        legend: {
                            data:['消费金额']
                        },
                        xAxis : [
                            {
                                type : 'category',
                                data : names
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:"消费金额",
                                type:"bar",
                                data:moneys
                            }
                        ]
                    };

                    // 为echarts对象加载数据
                    myChart.setOption(option);
                }
            );
        }

    })

})

