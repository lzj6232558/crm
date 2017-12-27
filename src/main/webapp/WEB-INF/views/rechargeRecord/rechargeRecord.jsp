<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/rechargeRecord.js"></script>
    <title>Title</title>
</head>

<body class="easyui-layout">
<div data-options="region:'west',title:'会员信息',collapsible:false" style="width:400px;">
    <table id="vip_table" align="center"></table>
</div>
<div data-options="region:'center',title:'大爷,快来充值了'" style="padding:1px;background:#eee;">
    <form id="vip_form" method="post">
                    <input id="vipNumber" type="hidden" name="vipNumber">
        <div id="p" class="easyui-panel"
             style="width:auto;height:150px;padding:10px;background:#fafafa;">
            <table id="table" align="center">
                <tr >
                    <td>会员姓名:</td>
                    <td><input type="text" name="vipName" class="easyui-textbox" readonly="readonly"></td>
                    <td>会员电话:</td>
                    <td><input type="text" name="vipPhone" class="easyui-textbox" readonly="readonly"></td>
                    <td>总计消费:</td>
                    <td><input type="text" id="cc" class="easyui-textbox" name="consume" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>当前余额:</td>
                    <td><input type="text" id="currentMoney" name="currentMoney" class="easyui-textbox" readonly="readonly"></td>
                    <td>邮箱</td>
                    <td><input type="text" name="email" class="easyui-textbox" readonly="readonly"></td>
                    <td>录入员工:</td>
                    <td><input class="easyui-textbox" name="employee.username" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>会员来源:</td>
                    <td><input  class="easyui-textbox" name="source" readonly="readonly"/></td>
                    <td>会员卡状态:</td>
                    <td><input id="vipCardState" class="easyui-textbox" name="vipCardState" readonly="readonly" /></td>
                    <td>当前积分:</td>
                    <td><input class="easyui-textbox" name="currentpoint" readonly="readonly" /></td>
                </tr>
            </table>
        </div>
    </form>
    <div  class="easyui-tabs" style="width:auto;height:33%">
        <div name="myPage" title="充值页面" style="padding:20px;display:none;">
            <form id="recharge_form" method="post">
                <h4>充值金额 :<input class="easyui-textbox" id="currentInMoney" name="currentInMoney"></h4>
                <h4>备注信息 :<input class="easyui-textbox" name="postscript" data-options="multiline:true,width:125,height:60"></h4>
                <a class="easyui-linkbutton" iconCls="icon-add"  data-cmd="recharge">交了钱就是孙子</a>
            </form>

        </div>
        <div name="myPage" title="退卡页面" style="overflow:auto;padding:20px;display:none;">
            <form id="refund_form" method="post">
                <h3>退卡备注:<input id="refundMsg" class="easyui-textbox" name="postscript" data-options="multiline:true,width:250,height:60"></h3>
                <a class="easyui-linkbutton" iconCls="icon-remove"  data-cmd="refund">退钱还是孙子</a>
            </form>
        </div>
    </div>

    <table id="incomeRecord_table" align="center"></table>
</div>

<div id="vip_toolbar">
    <!--高级查询 : 客户关键字-->
    <input id="keyword" class="easyui-textbox" name="keyword" data-options="prompt:'会员姓名/手机/会员号码',width:150">
    <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search22">搜索</a>
</div>
</body>
</html>
