<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript"
            src="/js/jquery/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript"
            src="/js/jquery/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<script type="text/javascript">
    $(function() {
        $(".searchproduct").click(function() {
            //获取到当前放大镜所在的行:closest
            var trObject = $(this).closest("tr");
            $.dialog.open("/product/selectProductList.do",{
                id:'ajaxList',
                title:'商品选择列表',
                width:650,
                height:460,
                background:'#000000',
                close:function() {
                    //当子窗口关闭的时候来执行该回调函数
                    var json = $.dialog.data("json");
                    if(json) {
                        trObject.find("[tag=name").val(json.name);
                        trObject.find("[tag=pid]").val(json.id);
                        trObject.find("[tag=brand]").text(json.brandName);
                        trObject.find("[tag=costPrice]").val(json.costPrice);
                    }
                }
            });
        });


        //为价格和数量的输入框绑定失去焦点事件
        $("[tag=costPrice],[tag=number]").blur(function() {
            var tr = $(this).closest("tr");
            var costPrice = parseFloat(tr.find("[tag=costPrice]").val()) || 0;
            var number = parseFloat(tr.find("[tag=number]").val()) || 0;
            var amount = (costPrice * number).toFixed(2);
            tr.find("[tag=amount]").text(amount);
        });

        //添加明细
        $(".appendRow").click(function() {
            //克隆表格的第一行tr
            var newTr = $("#edit_table_body tr:first").clone(true);
            newTr.find("[tag=name]").val("");
            newTr.find("[tag=pid]").val("");
            newTr.find("[tag=brand]").text("");
            newTr.find("[tag=costPrice]").val("");
            newTr.find("[tag=number]").val("");
            newTr.find("[tag=amount]").text("");
            newTr.find("[tag=remark]").val("");
            newTr.appendTo($("#edit_table_body"));
        });

        //在提交表单的时候,修改每一行元素name属性的值
        //name=item[0].id
        $("#editForm").submit(function() {
            $.each($("#edit_table_body tr"),function(index,item) {
                $(item).find("[tag=pid]").
                prop("name","items[" + index +"].product.id");
                $(item).find("[tag=costPrice]").
                prop("name","items[" + index +"].costPrice");
                $(item).find("[tag=number]").
                    prop("name","items[" + index +"].number");
                $(item).find("[tag=remark]").
                prop("name","items["+ index +"].remark");
            })
        })

        $(".removeItem").click(function() {
            //如果表格中只有一行的时候,就不要再删除,
            // 而是请求清空当前行中的数据
            if($("#edit_table_body tr").size() > 1) {
                $(this.closest("tr")).remove();
            }else {
                var firstTr = $("#edit_table_body tr:first");
                firstTr.find("[tag=name]").val("");
                firstTr.find("[tag=pid]").val("");
                firstTr.find("[tag=brand]").text("");
                firstTr.find("[tag=costPrice]").val("");
                firstTr.find("[tag=number]").val("");
                firstTr.find("[tag=amount]").text("");
                firstTr.find("[tag=remark]").val("");
            }
        })
    })

</script>
<body>
<form name="editForm" action="/orderBill/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${orderBill.id }">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <input name="sn" class="ui_input_txt02"
                         value="${orderBill.sn }"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <select name="supplier.id" class="ui_select03">
                            <c:forEach items="${suppliers}" var="supplier">
                                <option value="${supplier.id}"
                                ${orderBill.supplier.id==supplier.id?"selected='selected'":""}>${supplier.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <fmt:formatDate value="${orderBill.vdate}" pattern="yyyy-MM-dd" var="billDate"></fmt:formatDate>
                    <td class="ui_text_lt">
                        <input class="ui_input_txt02 Wdate" name="vDate" readonly onclick="WdatePicker();"
                        value="${billDate}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <c:if test="${empty orderBill}">
                            <tr>
                                <td></td>
                                <td>
                                    <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                    <img src="/images/common/search.png" class="searchproduct"/>
                                    <input type="hidden" tag="pid"/>
                                </td>
                                <td><span tag="brand"></span></td>
                                <td><input tag="costPrice"
                                           class="ui_input_txt00"/></td>
                                <td><input tag="number"
                                           class="ui_input_txt00"/></td>
                                <td><span tag="amount"></span></td>
                                <td><input tag="remark"
                                           class="ui_input_txt02"/></td>
                                <td>
                                    <a href="javascript:;" class="removeItem">删除明细</a>
                                </td>
                            </tr>
                            </c:if>
                            <c:if test="${not empty orderBill}">
                                <c:forEach items="${orderBill.items}" var="item">
                                <tr>
                                    <td></td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"
                                        value="${item.product.name}"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" tag="pid" value="${item.product.id}"/>
                                    </td>
                                    <td><span tag="brand">${item.product.brandName}</span></td>
                                    <td><input tag="costPrice"
                                               class="ui_input_txt00" value="${item.costPrice}"/></td>
                                    <td><input tag="number"
                                               class="ui_input_txt00" value="${item.number}"/></td>
                                    <td><span tag="amount">${item.amount}"</span></td>
                                    <td><input tag="remark"
                                               class="ui_input_txt02" value="${item.remark}"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </form>
</body>
</html>