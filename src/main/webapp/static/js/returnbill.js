$(function() {
    //1.变量抽取
    var returnbill_form = $("#returnbill_form");
    var returnbill_datagrid = $("#returnbill_datagrid");
    var returnbill_dialog = $("#returnbill_dialog");
    var productTable = $("#product_table");
    var returnbill_form_details = $("#returnbill_form_details");
    var returnbill_dialog_details = $("#returnbill_dialog_details");
    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {

        load: function (){
                returnbill_datagrid.datagrid("load");
         },

        cancel: function () {
                //关闭弹出框
                returnbill_dialog.dialog("close");
            },

        searchForm: function () {
                //获取关键字input
                var keyword = $("#keyword").textbox("getValue");

                //会让数据表格重新加载,并且带上查询的参数
                //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
                returnbill_datagrid.datagrid("load",{
                    keyword:keyword
                });
            },
        details:function() {
            var row = returnbill_datagrid.datagrid("getSelected");
            if(!row) {
                $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                return;
            }

            //清空表单
            returnbill_form_details.form("clear");

            row["supplier.id"] = row.supplier.id;
            row["depot.id"] = row.depot.id;
            row["inputuser"] = row.inputuser.username;
            row["auditor"] = row.auditor.username;
            console.log(row);
            //回显数据
            returnbill_form_details.form("load",row);

            //设置标题
            returnbill_dialog_details.dialog("setTitle","编辑采购清单");
            //打开弹出框
            returnbill_dialog_details.dialog("open");

        },
        returnbill:function() {
            var row = returnbill_datagrid.datagrid("getSelected");
            if(!row) {
                $.messager.alert('温馨提示',"请选择要退货的订单!",'warning');
                return;
            }

            $.messager.confirm('订单退货','您确认要退货该订单吗',function(r) {
                if(r) {
                    //发送请求修改状态
                    $.get("/orderbill/returnbill.do",{id:row.id},function(data) {
                        if(data.success) {
                            $.messager.alert('温馨提示',"退货成功!",'info',function() {
                                //重新加载表格数据
                                returnbill_datagrid.datagrid("reload");
                            });
                        }else {
                            $.messager.alert('温馨提示',data.msg, 'error');
                        }
                    },"json")
                }
            });


        }


    }



    //3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function(){
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })


    returnbill_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#returnbill_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/orderbill/querydepotbill.do',
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'sn', title: '单据编号', width: 100},
            {field:'supplier',title:'供应商',width:100,formatter:function(value,row,index) {
                return value?value.name : "";
            }},
            {field:'product',title:'商品名称',width:100,formatter:function(value,row,index) {
                return value?value.name : "";
            }},
            {field:'product2',title:'单价',width:100,formatter:function(value,row,index) {
                return row.product?row.product.saleprice : "";
            }},
            {field: 'totalnumber', title: '数量', width: 100},
            {field: 'totalamount', title: '合计', width: 100},
            {field:'depot',title:'所在仓库',width:100,formatter:function(value,row,index) {
                return value?value.name : "";
            }},
            {field: 'auditor', title: '审核人', width: 100,formatter:function(value,row,index) {
                return value?value.username:"";
            }},
            {field: 'status', title: '入库状态', width: 100,formatter:function(value,row,index) {
                return value?"<font color='green'>已入库</font>":"<font color='red'>普通</font>"
            }},
        ]],
        onClickRow:function(index,row) {
            if(row.status) {
                //修改按钮
                $("#audit_btn").linkbutton({
                    text:'已审核'
                });
            } else {
                //修改按钮
                $("#audit_btn").linkbutton({
                    text:"审核"
                });

                //启用编辑按钮
                $("#edit_btn").linkbutton("enable");
                //启用删除按钮
                $("#delete_btn").linkbutton("enable");
                //禁用详情按钮
                $("#detail_btn").linkbutton("disable");
            }

            if(row.status) {
                //禁用编辑按钮
                $("#edit_btn").linkbutton("disable");
                //禁用审核按钮
                $("#audit_btn").linkbutton("disable");
                //禁用删除按钮
                $("#delete_btn").linkbutton("disable");
                //启用详情按钮
                $("#detail_btn").linkbutton("enable");
            }

        }

    });

    returnbill_dialog.dialog({
        width:400,
        height:300,
        buttons:'#returnbill_btns',
        closed:true
    });

    returnbill_dialog_details.dialog({
        width:400,
        height:300,
        closed:true
    });

    productTable.datagrid({
        url: "/product/query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field:'id',width:100,checkbox:true},
            {field:'productId',title:'商品id',width:100,formatter:function(value, row, index) {
                return row.id;
            }},
            {field: 'name', title: '商品名称', width: 100},
            {field: 'parent', title: '一级类别', width: 100,formatter: function (value, row, index) {
                return value ? value.name : "";
            }
            },
            {field: 'child', title: '二级类别', width: 100,formatter: function (value, row, index) {
                return value ? value.name : "";
            }
            },
            {field: 'sn', title: '商品编码', width: 100},
            {field: 'costprice', title: '进货价', width: 100},
            {field: 'saleprice', title: '零售价', width: 100},
            {field: 'storenumber', title: '库存量', width: 100},
            {field: 'inputtime', title: '入库时间', width: 100},
            {field: 'state', title: '状态', width: 100},
        ]],
        toolbar: "#product_toolbar",
        striped: true,
        rownumbers: true,
        height:150,
        singleSelect:true,
    })

})









