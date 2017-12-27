<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/plugins/jquery-easyui/base.js"></script>
    <meta charset="utf-8"/>
    <title>CRM</title>
    <!-- basic styles -->
    <link href="/static/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/static/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/static/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="/static/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="/static/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="/static/assets/css/ace-ie.min.css"/>
    <script src="/static/js/index.js"></script>
    <script type="text/javascript" src="/static/plugins/echarts/build/dist/echarts.js"></script>
    <style>
        .ss {
            display: block;
            position: relative;
            color: #616161;
            padding: 7px 0 9px 37px;
            margin: 0;
            border-top: 1px dotted #e4e4e4;
        }

    </style>
    <script type="text/javascript">
        $(function () {

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
                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'));

                var option = {
                    title : {
                        text: '会员开卡排行',
                        subtext: '你开心就好',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        data:${NameList}
                    },
                    calculable : true,
                    series : [
                        {
                            name:'会员开卡排行',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:${Jsonlist}
                        }
                    ]
                };
                // 为echarts对象加载数据
                myChart.setOption(option);
            }
        );

        // 使用
        require(
            [
                'echarts',
                'echarts/chart/pie',// 使用柱状图就加载bar模块，按需加载
                'echarts/chart/funnel' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('saleChartsByBar'));

                var option  = {
                    title : {
                        text: '销售报表',
                        subtext: 'ooxxooxx',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        data: ${groupByName}
                    },
                    calculable : true,
                    series : [
                        {
                            name:'访问来源',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data: ${JsonMap}
                        }
                    ]
                };


                // 为echarts对象加载数据
                myChart.setOption(option);
            }
        );
    })
    </script>
</head>

<body background="/static/assets/avatars/main.png">
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="/collectMoney/myView.do" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    CRM后台管理系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="_easyui_textbox_input20-container"  id="main-container">
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>
            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <a data-toggle="dropdown" href="/collectMoney/myView.do" class="dropdown-toggle">
                    <img class="nav-user-photo" src="static/assets/avatars/user.jpg" alt="Jason's Photo"/>
                    <span class="menu-text"> 收银界面 </span>
                </a>
            </div>

            <ul class="nav nav-list">

                <li class="dropdown-toggle">
                    <a>
                        <i class="icon-dashboard"></i>

                        <span class="menu-text"> 首页 </span>
                    </a>
                </li>

                <li class="dropdown-toggle" data-prentid="1">
                    <a onclick="system()">
                        <i class="icon-desktop"></i>
                        <b class="arrow icon-angle-down"></b>
                        <span class="menu-text"> 系统模块 </span>
                    </a>
                    <c:forEach items="${system}" var="menu">
                <li class="system">
                    <a data-url="${menu.url}" data-text=" ${menu.text}"
                    >
                        <i class="icon-leaf"></i>
                            ${menu.text}
                    </a>
                </li>
                </c:forEach>
                <li class="dropdown-toggle" data-prentid="25">
                    <a onclick="vip()">
                        <i class="icon-edit"></i>
                        <span class="menu-text"> 会员管理 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <c:forEach items="${vip}" var="menu">
                <li class="vip focus">
                    <a data-url="${menu.url}" data-text=" ${menu.text}"
                    >
                        <i class="icon-leaf"></i>
                            ${menu.text}
                    </a>
                </li>
                </c:forEach>

                <li>
                    <a class="dropdown-toggle" onclick="product()">
                        <i class="icon-leaf"></i>
                        <span class="menu-text"> 商品管理 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <c:forEach items="${product}" var="menu">
                <li class="product">
                    <a data-url="${menu.url}" data-text=" ${menu.text}"
                       id="uuuu">
                        <i class="icon-pencil"></i>
                            ${menu.text}
                    </a>
                </li>
                </c:forEach>
                <li>
                    <a class="dropdown-toggle" onclick="depot()">
                        <i class="icon-desktop"></i>
                        <span class="menu-text"> 库存管理 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <c:forEach items="${depot}" var="menu">
                <li class="depot">
                    <a data-url="${menu.url}" data-text=" ${menu.text}"
                    >
                        <i class="icon-leaf"></i>
                            ${menu.text}
                    </a>
                </li>
                </c:forEach>

                <li>
                    <a class="dropdown-toggle" onclick="chart()">
                        <i class="icon-tag"></i>
                        <span class="menu-text"> 销售分析 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <c:forEach items="${chart}" var="menu">
                <li class="chart">
                    <a data-url="${menu.url}" data-text=" ${menu.text}"
                    >
                        <i class="icon-leaf"></i>
                            ${menu.text}
                    </a>
                </li>
                </c:forEach>

                <li class="dropdown-toggle">
                    <a class="dropdown-toggle" onclick="pointex()">
                        <i class="icon-list-alt"></i>
                        <span class="menu-text"> 礼品模块 </span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <c:forEach items="${pointex}" var="menu">
                <li class="pointex">
                    <a data-url="${menu.url}" data-text=" ${menu.text}">
                        <i class="icon-leaf"></i>
                            ${menu.text}
                    </a>
                </li>
                </c:forEach>

                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
                </div>
            </ul>
        </div>
    </div>
    <div id="tt" class="easyui-tabs" style="width:auto;height:600px;">
        <div title="首页" style="padding:20px;display:none;" data-options="fit:true">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north'" style="height:120px;">
                   <%-- <div class="easyui-panel" data-options="border:false,width:430">--%>
                        <iframe allowtransparency="true" frameborder="0" width="385" height="75" scrolling="no"
                                src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=3&bd=0&k=000000&f=&ltf=009944&htf=cc0000&q=1&e=1&c=54511&w=385&h=96&align=right"></iframe>
                   <%-- </div>--%>
                </div>
                <div data-options="region:'west'"  style="width:220px;">

                    <!--模板1-->
                    <div class="shareleftbox"  style="display: block;">
                        <div class="leftprinttemplate">
                            <div class="head">
                                <div class="welcome">欢迎使用本系统!</div>
                                <hr>
                            </div>
                            <div class="printbox table-responsive">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>商品名称</th>
                                        <th>单价</th>
                                        <th>数量</th>
                                        <th>价格</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>苹果</td>
                                        <td>9000.0</td>
                                        <td>2</td>
                                        <td>9000.0</td>
                                    </tr>
                                    <tr>
                                        <td>华为</td>
                                        <td>2000.0</td>
                                        <td>2</td>
                                        <td>4000.0</td>
                                    </tr>
                                    <tr>
                                        <td>锤子</td>
                                        <td>2500.0</td>
                                        <td>3</td>
                                        <td>7500.0</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="sharetypebox">商品总数 : 4</div>
                                <div class="sharetypebox">金额合计 : ¥33500</div>
                                <hr />
                                <div class="sharetypebox text-center">谢谢惠顾，欢迎下次光临！</div>
                                <div class="decerp text-center">由<i class="_g_agents_text">超哥系统管理软件</i>提供</div>
                                <div class="decerp text-center _g_agents_hide">www.chaoge.cn</div>
                            </div>
                            <div class="printfooter">

                            </div>
                        </div>
                    </div>
                    <!--模板1-->
                </div>
                <div data-options="region:'center'" border="false">
                    <div id="main" style="height:380px"></div>
                </div>
                <div data-options="region:'east'" border="false" style="width:600px;">
                    <div id="saleChartsByBar" style="height:380px"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

