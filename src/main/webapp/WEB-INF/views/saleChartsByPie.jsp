<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="/static/js/jquery/jquery.js"></script>
<script src="/static/js/jquery/build/dist/echarts.js"></script>
<script type="text/javascript">
    $(function() {
        // 路径配置
        require.config({
            paths: {
                echarts: '/static/js/jquery/build/dist'
            }
        });

        // 使用
        require(
            [
                'echarts',
                'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/funnel' // 使用漏斗图就加载funnel模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('saleChartsByPie'));

                option = {
                    title : {
                        text: '销售总额',
                        subtext: '${groupByName}',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        data:${groupByNames}
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel'],
                                option: {
                                    funnel: {
                                        x: '25%',
                                        width: '50%',
                                        funnelAlign: 'left',
                                        max: ${max}
                                    }
                                }
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'销售总额',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:${mapList2}
                        }
                    ]
                };

                // 为echarts对象加载数据
                myChart.setOption(option);
            }
        );
    });
</script>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="saleChartsByPie" style="height:400px;width: 600px;"></div>
</body>
</html>
