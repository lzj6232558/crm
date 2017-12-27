<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    body {
        font-family: '微软雅黑';
    }
</style>
<head>
    <title>收银界面</title>
    <%@include file="/static/js/commons.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript" src="/static/js/money.js"></script>
</head>

<body id="cc" class="easyui-layout" style="width:1510px;height:700px" data-options="fit:true,fitColumns:true" >
    <div data-options="region:'north',collapsible:false,title:'德客收银'" style="height:100px; background-color: #0b6cbc" >
        <a data-toggle="dropdown" href="/index.do" class="dropdown-toggle">
            <img class="nav-user-photo" src="/static/css/images/cccc.png" width="200px" alt="返回后台"></a>
        </a>
    </div>
    <div data-options="fit:false,width:380,height:600,region:'west',title:'购物车'">
        <div  class="easyui-layout" style="width:600px;height:400px" data-options="fit:true">
            <div  class="easyui-layout" style="width:1510px;height:700px" >
                <table id="lifeTable" class="region:'north" width="500" hidden="400px" >
                    <input class="easyui-textbox" data-options="iconCls:'icon-search',height:35,width:200,prompt:'商品名/商品编码'"  >
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="delete"  class="easyui-linkbutton" data-options="height:35,width:100" >删除一条商品</a>
                </table>
                <table id="emp_btns" class="region:'south">
                    <a  id="chencked" class="easyui-linkbutton " data-options="width:85,height:50"><font size="3px" color="red">选择会员</font></a>
                    <a  id="selectmenoy" class="easyui-linkbutton " data-options="width:85,height:50"><font size="3px" color="red">挂单</font></a>
                    <a  id="getselectmenoy" class="easyui-linkbutton" data-options="width:85,height:50"><font size="3px" color="red">取单</font></a>
                    <a  id="getmoney" class="easyui-linkbutton" data-options="width:85,height:50"><font size="3px" color="red">结算</font></a>
                 </table>
            </div>
        </div>
    </div>

    <div data-options="region:'west',title:'West',collapsible:false" style="width:100px;"></div>
        <div data-options="fit:false,width:1175,height:600,region:'center'" style="padding:5px;background:#eee">
        <div>
            <table >
                <a id="alltype"  class="easyui-linkbutton" data-options="width:125,height:50">所有分类</a>
                <c:forEach var="item" items="${items}">
                    <a class="easyui-linkbutton" data-itemid="${item.id}" data-options="width:125,height:50" id="${item.name}">${item.name}</a>
                </c:forEach>
            </table>
        </div>
          <table  id="productId" >
          </table>
    </div>

    <div id="vipDig" >
        <table id="viptable"></table>
    </div>

    <div id="password">
        <form id="passwordForm">
           密码： <input class="easyui-passwordbox" data-options="" style="width:200px" height="100px" name="vipPassword">
        </form>
    </div>
        <div id="password_btn">
            <a id="btns"  class="easyui-linkbutton" data-options="width:90,height:35">确认密码</a>
        </div>

</body>
</html>

