<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/js/vip.js"></script>
    <title>Title</title>
</head>
<body>

<table id="vip_table" align="center"></table>

<div id="vip_toolbar">
    <shiro:hasPermission name="vip:saveOrUpdate">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="vip:changeState">
    <a class="easyui-linkbutton" id="change" iconCls="icon-remove" plain="true" data-cmd="change">挂失会员卡</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <!--高级查询 : 客户关键字-->
    <input id="keyword" class="easyui-textbox" name="keyword" data-options="prompt:'会员姓名/手机/会员号码',width:150">
    <!--高级查询 : 客户来源-->
    <input id="seachSource" class="easyui-combobox" name="source"
           data-options="valueField:'name',panelHeight:'auto',textField:'name',prompt:'客户来源',url:'/vip/source.do?id=4'" />
    <!--高级查询 : 开卡员工-->
    <input id="employeeId" class="easyui-combobox" name="employeeId"
           data-options="prompt:'开卡员工',valueField:'id',textField:'username',panelHeight:'auto',url:'/employee/selectAll.do'" />
    最近:
    <input id="formatNum"  class="easyui-textbox" name="formatNum" data-options="width:50">
    <input id="timeFormat" class="easyui-combobox" name="timeFormat"
           data-options="width:50,valueField:'intro',panelHeight:'auto',textField:'name',url:'/vip/source.do?id=5'" />

    <input id="beginTime" class="easyui-datebox" name="beginTime">
    <input id="endTime" class="easyui-datebox" name="endTime">
    <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search22">搜索</a>
</div>

<div id="vip_btns">
    <a class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>
<div id="imlportbtn">
    <a class="easyui-linkbutton" iconCls="icon-undo" data-cmd="imlportbtn">提交</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>
<div id="passwordbtn">
    <a class="easyui-linkbutton" iconCls="icon-undo" data-cmd="passwordbtn">提交</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
</div>

<div id="imlport">
    <form id="imlportForm" method="post" enctype="multipart/form-data">
        <input class="easyui-filebox" data-options="buttonText:'点击上传',multiple:true" style="width: 200px" name="file"/>
    </form>
 </div>


<div id="myMenu" class="easyui-menu" style="width:120px;">
    <div data-cmd="editpassword" data-options="iconCls:'icon-edit'">修改密码</div>
    <div data-cmd="initPassword" data-options="iconCls:'icon-remove'">重置密码</div>
    <div class="menu-sep"></div>
    <div data-cmd="reload" data-options="iconCls:'icon-reload'">刷新</div>
</div>

<div id="passwordDialog">
    <form id="password_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>原密码</td>
                <td><input type="text" name="oldPassword" class="easyui-passwordbox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>新密码</td>
                <td>
                    <input id="newPassword" name="newPassword" validType="length[3,11]" class="easyui-passwordbox" required="true" type="password"/>
                </td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td>
                    <input type="password" id="repassword" required="true" class="easyui-passwordbox"  validType="equalTo['#newPassword']" invalidMessage="两次输入密码不匹配"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="password">
    <form id="passwordForm">
        密码： <input class="easyui-passwordbox" data-options="" style="width:150px" height="100px" name="vipPassword">
    </form>
</div>
<div id="password_btn">
    <a id="btns"  class="easyui-linkbutton" data-options="width:90,height:35">确认密码</a>
</div>




<div id="dialog">
    <form id="vip_form" method="post">
        <input type="hidden" name="id">
        <table id="table" align="center">
            <tr>
                <td>会员姓名:</td>
                <td><input type="text" name="vipName" class="easyui-textbox" data-options="required:true,validType:'length[2,11]'"></td>
            </tr>
            <tr id="pwd">
                <td>会员密码:</td>
                <td><input type="text" name="vipPassword" class="easyui-passwordbox" data-options="required:true,
                    validType:'length[3,11]'"></td>
            </tr>
            <tr>
                <td>会员电话:</td>
                <td><input type="text" name="vipPhone" id="phone" class="easyui-numberbox" data-options="required:true,">
                    <span id="phoneMsg"></span></td>
            </tr>
            <tr>
                <td>称谓:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="gender" data-options="panelHeight:'auto',width:143">
                    <option value="1">男士</option>
                    <option value="0">女士</option>
                </select>
            </tr>
            <tr id="amountMoney">
                <td>充值总金额:</td>
                <td><input type="text" name="amountMoney" class="easyui-textbox"></td>
                <input type="hidden" name="currentMoney">
            </tr>

            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>录入员工:</td>
                <td><input id="emp" class="easyui-combobox" name="employee.id"
                           data-options="valueField:'id',textField:'username',panelHeight:'auto',url:'/employee/selectAll.do'" /></td>
            </tr>
            <tr>
                <td>会员来源:</td>
                <td><input id="source" class="easyui-combobox" name="source"
                           data-options="valueField:'name',panelHeight:'auto',textField:'name',url:'/vip/source.do?id=4'" />
                </td>
            </tr>
            <tr >
                <td>会员卡状态:</td>
                <td>
                    <select id="vipCardState" class="easyui-combobox" name="vipCardState" data-options="panelHeight:'auto',width:143">
                        <option value="1" >开启会员卡</option>
                        <option value="0">暂不使用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
