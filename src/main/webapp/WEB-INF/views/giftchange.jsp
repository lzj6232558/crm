<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <title>积分变动</title>
</head>

<body class="easyui-layout">


<div id="gift_toolbar">
    <input id="gift" class="easyui-textbox" name="keyword" data-options="prompt:'请输入礼品编号或礼品名称',width:150">
    <a class="easyui-linkbutton" plain=true iconCls="icon-search" data-cmd="searchGift">搜索</a>
</div>
<div id="tb">
    <a class="easyui-linkbutton" plain=true iconCls="icon-remove" data-cmd="deleteGift">删除</a>
</div>





<div data-options="region:'south',title:'兑换记录',split:true" style="height:350px;">
    <table id="pointexchange_datagrid"></table>
</div>
<div data-options="region:'west',title:'会员信息'" style="width:450px;height:230px;background:#eee;">
    <form id="vip_form" method="post">
        <input type="hidden" name="id">
        <div id="p" class="easyui-panel"
             style="width:auto;height:260px;background:#fafafa;">
            <table id="table" style="padding-left: 40px">
                <tr >
                    <td>会员卡号:<input type="text" name="vipNumber" class="easyui-textbox" readonly="readonly"></td>
                    <td>会员姓名:<input type="text" name="vipName" class="easyui-textbox" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>会员等级:<input type="text" id="cc" name="vipcard" class="easyui-textbox" readonly="readonly"/></td>
                    <td>会员电话:<input type="text" name="vipPhone" class="easyui-textbox" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>当前余额:<input type="text" name="currentMoney" class="easyui-textbox" readonly="readonly"></td>
                    <td>当前积分:<input type="text" name="currentpoint" class="easyui-textbox" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>累计充值:<input class="easyui-textbox" name="amountMoney" readonly="readonly"/></td>
                    <td>积分总额:<input class="easyui-textbox" name="totalpoint" readonly="readonly"/></td>
                </tr>
            </table>
        </div>
    </form>
</div>

    <div id="vip_toolbar">
        <!--高级查询 : 客户关键字-->
        <input id="keyword" class="easyui-textbox" name="keyword" data-options="prompt:'请输入手机号或会员卡号',width:180">
        <a class="easyui-linkbutton" plain="true" data-cmd="searchAll">搜索</a>
    </div>

    <div data-options="region:'center',title:'会员查询'" class="easyui-tabs" style="width:330px;height:300px;">
        <table id="vip_table"></table>
    </div>

    <div data-options="region:'east',title:'礼品兑换'" class="easyui-tabs" style="width:410px;height:150px;">
            <form id="change_form" method="post" style="padding:5px">
                <input type="hidden" name="str" id="str_json">
                <table id="choose_datagrid" class="easyui-datagrid" style="width:400px;height:190px"
                       data-options="url:'#',fitColumns:true,toolbar:'#tb',singleSelect:true">
                    <thead>
                    <tr>
                        <th data-options="field:'sn',width:60">礼品编号</th>
                        <th data-options="field:'name',width:60">礼品名称</th>
                        <th data-options="field:'point',width:60,align:'right'">所需积分</th>
                        <th data-options="field:'currentNum',width:60">剩余数量</th>
                        <th data-options="field:'number',width:60,editor:'numberbox'">数&emsp;&emsp;量</th>
                        <th data-options="field:'requiredPoints',width:60,editor:'numberbox',readonly:true">合计积分</th>
                    </tr>
                    兑换礼品所需积分:<span id="span" style="color: red">0</span>
                    </thead>
                </table>
                <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="choose_gift">选择礼品</a>
                <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="exchange">马上兑换</a>

            </form>
    </div>
<!--弹出礼品列表-->
<div id="giftdialog">
    <table id="gifttable"></table>
</div>


<script type="text/javascript" src="/static/js/giftchange.js"></script>
</body>
</html>













