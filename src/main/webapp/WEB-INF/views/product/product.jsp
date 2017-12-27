<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/js/commons.jsp" %>
    <script type="text/javascript" src="/static/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="/static/js/product/product.js"></script>
    <title>Title</title>
</head>
<body>
    <!--页面展示-->
    <table id="product_table" align="center"></table>

    <div id="product_toolbar">
        <shiro:hasPermission name="product:saveOrUpdate">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="product:delete">
        <a class="easyui-linkbutton" id="delete" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="product:changeState">
        <a class="easyui-linkbutton" id="change" iconCls="icon-remove" plain="true" data-cmd="change">下架/上架</a>
        </shiro:hasPermission>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">回到首页</a>
        <a class="easyui-linkbutton" iconCls="icon-redo" plain="true" data-cmd="exportXls">导出</a>
        <a class="easyui-linkbutton" iconCls="icon-undo" plain="true" data-cmd="imlportXls">导入</a>
        <input id="keyword" class="easyui-textbox" name="keyword" prompt="请输入商品名称/编码">
        <input id="cc1" class="easyui-combobox" name="parentId" prompt="请选择一级分类" editable="false" style="width:140px;"
               data-options="valueField:'id',panelHeight:'auto',textField:'name',url:'/producttype/selectParentClassify.do',
                onSelect: function(record){
                    $('#cc2').combobox({
                        url:'/producttype/selectChildClassify.do?id=' + record.id,
                        valueField:'id',
                        textField:'name'
                    });
                }"/>
        <input id="cc2" class="easyui-combobox" style="width:140px;"
                   name="childId" prompt="请先选择一级分类" editable="false" panelHeight="auto"/>
        <a class="easyui-linkbutton" iconCls='icon-search' plain="true" data-cmd="search">搜索</a>
    </div>

    <div id="product_btns">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>

    <div id="imlportbtn">
        <a class="easyui-linkbutton" iconCls="icon-undo" data-cmd="imlportbtn">提交</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">关闭</a>
    </div>

    <div id="imlport">
        <form id="imlportForm" method="post" enctype="multipart/form-data">
            <input class="easyui-filebox" data-options="buttonText:'点击上传',multiple:true" style="width: 200px" name="file"/>
        </form>
    </div>

    <div id="dialog">
        <form id="product_form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id">
            <table id="table" align="center">
                <tr>
                    <td>一级分类<font color="red">*</font></td>
                    <td>
                        <input id="parentClassify" class="easyui-combobox" name="parent.id" required="true" prompt="请选择一级分类" editable="false"
                               data-options="valueField:'id',panelHeight:'auto',textField:'name',url:'/producttype/selectParentClassify.do'"/></td>
                </tr>
                <tr>
                    <td>二级分类<font color="red">*</font></td>
                    <td>
                        <input id="childClassify" class="easyui-combobox"
                               name="child.id" required="true" prompt="请先选择一级分类" editable="false" panelHeight="auto"/></td>
                </tr>
                <tr>
                    <td>商品名称<font color="red">*</font></td>
                    <td><input type="text" name="name" class="easyui-textbox" required="true"></td>
                </tr>
                <tr>
                    <td>商品编码<font color="red">*</font></td>
                    <td><input type="text" name="sn" class="easyui-textbox"  required="true"></td>
                </tr>
                <tr>
                    <td>商品进价<font color="red">*</font></td>
                    <td><input type="text" name="costprice" class="easyui-textbox"  required="true"></td>
                </tr>
                <tr>
                    <td>销售单价<font color="red">*</font></td>
                    <td><input type="text" name="saleprice" class="easyui-textbox"  required="true"></td>
                </tr>
                <tr>
                    <td>会员价</td>
                    <td><input type="text" name="vipprice" class="easyui-textbox"></td>
                </tr>
                <tr>
                    <td>会员折扣</td>
                    <td><input type="text" name="vipdiscount" class="easyui-textbox"></td>
                </tr>
                <tr>
                    <td>商品单位<font color="red">*</font></td>
                    <td>
                        <input id="cc" class="easyui-combobox" name="unit.id" required="true"
                               data-options="valueField:'id', panelHeight:'auto',textField:'name',url:'/unit/query.do'"/></td>
                </tr>
                <tr>
                    <td>备注信息</td>
                    <td><input type="text" name="remark" class="easyui-textbox"></td>
                </tr>
                <tr>
                    <td>入库时间</td>
                    <td><input type="text" name="inputtime" class="easyui-datebox"></td>
                </tr>
                <tr id="#pimg">
                    <td >商品图片:</td>
                    <td>
                        <input name = "pic" class="easyui-filebox" buttonText="添加图片">
                    </td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>
