<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <title>积分变动</title>
</head>

<body class="easyui-layout">
<div data-options="region:'south',title:'积分变动明细',split:true" style="height:300px;">
    <table id="pointItem_table"></table>

</div>
<div data-options="region:'west',title:'会员信息'" style="width:420px;padding:10px;height:250px;background:#eee;">
    <form id="vip_form" method="post">
        <input id="vip_id" type="hidden" name="vip_id">
        <div id="p" class="easyui-panel"
             style="width:auto;height:210px;background:#fafafa;">
            <table id="table" style="padding-left: 3px">
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
                    <td>充值人员:<input class="easyui-textbox" name="employee" readonly="readonly"/></td>
            </table>
        </div>
    </form>
</div>


<div data-options="region:'center',title:'会员查询'" class="easyui-tabs" style="width:380px;height:300px;">
    <table id="vip_table"></table>
</div>

    <div data-options="region:'east',title:'积分充值'" class="easyui-tabs" style="width:350px;height:400px;">
            <form id="recharge_form" method="post" style="padding:25px">
                选择类型:&nbsp;&nbsp;&nbsp;
                <label><input  name="choose" type="radio" value="1" />充值 </label>
                <label><input  name="choose" type="radio" value="0" />扣除 </label>
                <h3>变动数额 :<input class="easyui-numberbox" id="changeNum" name="changeNum"></h3>
                <a class="easyui-linkbutton" iconCls="icon-add"  data-cmd="recharge">确定</a>
                <a class="easyui-linkbutton" iconCls="icon-remove"  data-cmd="clear">积分清零</a>
            </form>
    </div>

<div id="vip_toolbar">
    <!--高级查询 : 客户关键字-->
    <input id="keyword" class="easyui-textbox" name="keyword" data-options="prompt:'请输入手机号或会员编号',width:150">
    <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="searchAll">搜索</a>
</div>
<script type="text/javascript" src="/static/js/pointItem.js"></script>
</body>
</html>













