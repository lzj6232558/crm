$(function() {
    //1.变量抽取
    var checkproduct_form = $("#checkproduct_form");
    var checkproduct_datagrid = $("#checkproduct_datagrid");
    var checkproduct_dialog = $("#checkproduct_dialog");
    var checkrecord_datagrid = $("#checkrecord_datagrid");
    var checkrecord_dialog = $("#checkrecord_dialog");
    var checkrecord_form = $("#checkrecord_form");
    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        check: function () {
            var row = checkproduct_datagrid.datagrid("getSelected");
            if(!row) {
                $.messager.alert('温馨提示',"请选择需要确认的产品条目!",'warning');
                return;
            }

            $.messager.confirm('数量确认','您要确认该商品数量吗吗?',function(r) {
                if(r) {
                    //发送请求修改状态
                    $.get("/checkproduct/check.do",{id:row.id},function(data) {
                        if(data.success) {
                            $.messager.alert('温馨提示',"数量确认成功!",'info',function() {
                                //重新加载表格数据
                                checkproduct_datagrid.datagrid("reload");
                            });
                        }else {
                            $.messager.alert('温馨提示',data.msg, 'error');
                        }
                    },"json")
                }
            });

        },

        adjust: function () {
                var row = checkproduct_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择要盘点的条目!",'warning');
                    return;
                }


                //清空表单
                checkproduct_form.form("clear");

                row["product"] = row.product.name;
                row["inputuser"] = row.inputuser.username;
                //回显数据
                checkproduct_form.form("load",row);

                //设置标题
                checkproduct_dialog.dialog("setTitle","数量调整");
                //打开弹出框
                checkproduct_dialog.dialog("open");
            },

        record: function () {
            //清空表单
            checkrecord_form.form("clear");

            //设置标题
            checkrecord_dialog.dialog("setTitle","盘点记录");
            //打开弹出框
            checkrecord_dialog.dialog("open");
        },

        load: function (){
                checkproduct_datagrid.datagrid("load");
         },
        loadrecord: function (){
            checkrecord_datagrid.datagrid("load");
        },
        save: function (){
                checkproduct_form.form("submit",{
                    url:'/checkproduct/adjust.do',
                    success:function(data) {
                        data = $.parseJSON(data);
                        if(data.success) {
                            //提示信息
                            $.messager.alert('温馨提示','调整成功','info',function() {
                                //关闭弹出框
                                checkproduct_dialog.dialog("close");
                                //重新加载表格数据
                                checkproduct_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示',data.msg,'error');
                        }
                    }
                })
            },

        cancel: function () {
                //关闭弹出框
                checkproduct_dialog.dialog("close");
            },

        searchForm: function () {
                //获取关键字input
                var keyword = $("#keyword").textbox("getValue");

                //会让数据表格重新加载,并且带上查询的参数
                //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
                checkproduct_datagrid.datagrid("load",{
                    keyword:keyword
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


    checkproduct_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#checkproduct_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/checkproduct/checkproduct.do',
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'product', title: '商品编码', width: 100,formatter:function(value, row, index) {
                return value?value.sn:"";
            }},
            {field: 'productName', title: '商品名称', width: 100,formatter:function(value, row, index) {
                return row.product?row.product.name:"";
            }},
            {field: 'productSalePrice', title: '商品售价', width: 100,formatter:function(value, row, index) {
                return row.product?row.product.saleprice:"";
            }},
            {field: 'storeNumber', title: '商品总库存量', width: 100},
            {field: 'checkTime', title: '上次盘点时间', width: 100,formatter:function(value, row, index) {
                return row.checkTime?"<font color='red'>" + row.checkTime + "</font>" : "";
            }},
            {field: 'inputuser', title: '上次操作人员', width: 100,formatter:function(value, row, index) {
                return value?"<font color='red'>" + value.username + "</font>":"";
            }}

        ]],

    });

    checkrecord_datagrid.datagrid({
        //fit:true,
        //fitColumns:true,
        striped:true, //是否显示斑马线效果。
        url:'/checkproduct/record.do',
        height:450,
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'storeNumber', title: '原有库存', width: 80},
            {field: 'newNumber', title: '修改数量', width: 80},
            {field: 'inputter', title: '操作人员', width: 140,formatter:function(value, row, index) {
                return value?value.username:"";
            }},
            {field: 'inputtime', title: '盘点时间', width: 170},
            {field: 'remark', title: '备注', width: 200}

        ]],
        toolbar:$("#checkrecord_toolbar")


    });

    checkproduct_dialog.dialog({
        width:300,
        height:300,
        buttons:'#checkproduct_btns',
        closed:true
    })


    checkrecord_dialog.dialog({
        width:700,
        height:500,
        //buttons:'#checkproduct_btns',
        closed:true
    })


})









