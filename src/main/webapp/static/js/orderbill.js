$(function() {
    //1.变量抽取
    var orderbill_form = $("#orderbill_form");
    var orderbill_datagrid = $("#orderbill_datagrid");
    var orderbill_dialog = $("#orderbill_dialog");
    var productTable = $("#product_table");
    var orderbill_form_details = $("#orderbill_form_details");
    var orderbill_dialog_details = $("#orderbill_dialog_details");
    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {

                //清空表单
                orderbill_form.form("clear");

                //设置标题
                orderbill_dialog.dialog("setTitle","商品列表");
                //打开弹出框
                orderbill_dialog.dialog("open");
            },

        edit: function () {
                var row = orderbill_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                    return;
                }

                //清空表单
                orderbill_form.form("clear");

                row["supplier.id"] = row.supplier.id;
                row["depot.id"] = row.depot.id;
                var index = productTable.datagrid('getRowIndex',row.product.id);
                console.log(index);
                productTable.datagrid("selectRecord",row.product.id);
                //回显数据
                orderbill_form.form("load",row);

                //设置标题
                orderbill_dialog.dialog("setTitle","编辑采购清单");
                //打开弹出框
                orderbill_dialog.dialog("open");
            },

        delete: function () {
                var row = orderbill_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据",'warning');
                    return;
                }

                $.messager.confirm('确认','您确认想要执行该操作吗?',function(r) {
                    if(r) {
                        //发送请求修改状态
                        $.get("/orderbill/delete.do",{id:row.id},function(data) {
                            if(data.success) {
                                $.messager.alert('温馨提示',"操作成功!",'info',function() {
                                    //重新加载表格数据
                                    orderbill_datagrid.datagrid("reload");
                                });
                            }else {
                                $.messager.alert('温馨提示',data.msg, 'error');
                            }
                        },"json")
                    }
                });
            },
        load: function (){
                orderbill_datagrid.datagrid("load");
         },
        reload: function () {
            productTable.datagrid("load");
        },
        save: function (){
                orderbill_form.form("submit",{
                    url:'/orderbill/saveOrUpdate.do',
                    onSubmit:function(param) {
                        //获取选中的行
                        var row = productTable.datagrid("getSelected");
                        param["product.id"] = row.id;
                    },
                    success:function(data) {
                        data = $.parseJSON(data);
                        if(data.success) {
                            //提示信息
                            $.messager.alert('温馨提示','操作成功','info',function() {
                                //关闭弹出框
                                orderbill_dialog.dialog("close");
                                //重新加载表格数据
                                orderbill_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示',data.msg,'error');
                        }
                    }
                })
            },

        cancel: function () {
                //关闭弹出框
                orderbill_dialog.dialog("close");
            },

        searchForm: function () {
                //获取关键字input
                var keyword = $("#keyword").textbox("getValue");

                //会让数据表格重新加载,并且带上查询的参数
                //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
                orderbill_datagrid.datagrid("load",{
                    keyword:keyword
                });
            },
        searchProduct: function () {
            var keyword = $("#productKeyword").textbox("getValue");
            productTable.datagrid("load", {
                keyword: keyword
            });
        },
        details:function() {
            var row = orderbill_datagrid.datagrid("getSelected");
            if(!row) {
                $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                return;
            }

            //清空表单
            orderbill_form_details.form("clear");

            row["supplier.id"] = row.supplier.id;
            row["depot.id"] = row.depot.id;
            row["inputuser"] = row.inputuser.username;
            row["auditor"] = row.auditor.username;
            console.log(row);
            //回显数据
            orderbill_form_details.form("load",row);

            //设置标题
            orderbill_dialog_details.dialog("setTitle","编辑采购清单");
            //打开弹出框
            orderbill_dialog_details.dialog("open");

        },
        audit:function() {
            var row = orderbill_datagrid.datagrid("getSelected");
            if(!row) {
                $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                return;
            }

            $.messager.confirm('采购单审核','您确认要审核该订单吗',function(r) {
                if(r) {
                    //发送请求修改状态
                    $.get("/orderbill/audit.do",{id:row.id},function(data) {
                        if(data.success) {
                            $.messager.alert('温馨提示',"审核成功!",'info',function() {
                                //重新加载表格数据
                                orderbill_datagrid.datagrid("reload");
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


    orderbill_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#orderbill_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/orderbill/query.do',
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
                return value?"<font color='green'>已审核</font>":"<font color='red'>普通</font>"
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

    orderbill_dialog.dialog({
        width:800,
        height:500,
        buttons:'#orderbill_btns',
        closed:true
    });

    orderbill_dialog_details.dialog({
        width:400,
        height:300,
        closed:true
    });

    productTable.datagrid({
        url: "/product/query.do",
        //fit: true,
        //fitColumns: true,
        pagination: true,
        columns: [[
            {field:'ids',width:100,checkbox:true},
            /*{field:'productId',title:'商品id',width:100,formatter:function(value, row, index) {
                return row.id;
            }},*/
            {title:'礼品图片',field:'imagePath',width:100, formatter:function(value,row,index){
                return '<a><img width="100px" src="'+row.imagePath+'" /></a>';
            }
            },
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
            {field: 'state', title: '状态', width: 100,formatter: function (value, row, index) {
                return value ? "在售" : "<font color='red'>已下架</font>";
            }}
        ]],
        toolbar: "#product_toolbar",
        striped: true,
        //Qrownumbers: true,
        height:400,
        singleSelect:true,
    })

})









