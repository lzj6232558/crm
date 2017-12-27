$(function() {
    //1.变量抽取
    var adjustdepot_form = $("#adjustdepot_form");
    var adjustdepot_datagrid = $("#adjustdepot_datagrid");
    var adjustdepot_dialog = $("#adjustdepot_dialog");
    var productTable = $("#product_table");
    var orderbill_form = $("#orderbill_form");
    var orderbill_dialog = $("#orderbill_dialog");
    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {
            //清空表单
            orderbill_form.form("clear");

            //设置标题
            orderbill_dialog.dialog("setTitle","商品调拨");
            //打开弹出框
            orderbill_dialog.dialog("open");
            },

        edit: function () {
                var row = adjustdepot_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                    return;
                }

                //隐藏密码框
                $("#password_tr").hide();
                //清空表单
                adjustdepot_form.form("clear");

                //回显数据
                adjustdepot_form.form("load",row);

                //设置标题
                adjustdepot_dialog.dialog("setTitle","编辑员工");
                //打开弹出框
                adjustdepot_dialog.dialog("open");
            },

        delete: function () {
                var row = adjustdepot_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据",'warning');
                    return;
                }

                $.messager.confirm('库存调拨信息删除','您确认要删除该信息吗?',function(r) {
                    if(r) {
                        //发送请求修改状态
                        $.get("/adjustdepot/delete.do",{id:row.id},function(data) {
                            if(data.success) {
                                $.messager.alert('温馨提示',"删除成功!",'info',function() {
                                    //重新加载表格数据
                                    adjustdepot_datagrid.datagrid("reload");
                                });
                            }else {
                                $.messager.alert('温馨提示',data.msg, 'error');
                            }
                        },"json")
                    }
                });
            },
        load: function (){
                adjustdepot_datagrid.datagrid("load");
         },
        save: function (){
            orderbill_form.form("submit",{
                url:'/adjustdepot/saveOrUpdate.do',
                onSubmit:function(param) {
                    //获取选中的行
                    var row = productTable.datagrid("getSelected");
                    param["outproduct.id"] = row.id;
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
                adjustdepot_datagrid.datagrid("load",{
                    keyword:keyword
                });
            },
        reload: function () {
            productTable.datagrid("load");
        },
        searchProduct: function () {
            var keyword = $("#productKeyword").textbox("getValue");
            productTable.datagrid("load", {
                keyword: keyword
            });
        },


    }



    //3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function(){
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })


    adjustdepot_datagrid.datagrid({
        fit:true,
        //fitColumns:true,
        toolbar:'#adjustdepot_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/adjustdepot/query.do',
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns:[[ //DataGrid列配置对象，详见列属性说明中更多的细节。
            {field:'inproduct',title:'调入商品编码',width:80,formatter:function(value,row,index) {
                return value?value.sn:"";
            }},
            {field:'inproductName',title:'调入商品名',width:100,formatter:function(value,row,index) {
                return row.inproduct?row.inproduct.name:"";
            }},
            {field:'outproduct',title:'调出商品编码',width:80,formatter:function(value,row,index) {
                return value?value.sn:"";
            }},
            {field:'outproductName',title:'调出商品名称',width:100,formatter:function(value,row,index) {
                return row.inproduct?row.inproduct.name:"";
            }},
            {field:'differ',title:'调拨数量',width:55},
            {field:'outdepot',title:'调出仓库名',width:65,formatter:function(value,row,index) {
                return value?value.name:"";
            }},
            {field:'oldoutnumber',title:'调出仓库原数量',width:95},
            {field:'newoutnumber',title:'调出仓库现数量',width:95},
            {field:'indepot',title:'调入仓库名',width:65,formatter:function(value,row,index) {
                return value?value.name:"";
            }},
            {field:'oldinnumber',title:'调入仓库原数量',width:100},
            {field:'newinnumber',title:'调入仓库现数量',width:100},
            {field:'inputuser',title:'操作人',width:50,formatter:function(value,row,index) {
                return value?value.username:"";
            }},
            {field:'inputtime',title:'操作时间',width:130},
            {field:'remark',title:'备注',width:150},
        ]],
        onClickRow:function(index,row) {
            if(row.state) {
                //修改按钮
                $("#changState_btn").linkbutton({
                    text:'停用'
                })
            } else {
                //修改按钮
                $("#changState_btn").linkbutton({
                    text:"正常"
                })
            }

            if(!row.state) {
                //禁用离职按钮
                $("#edit_btn").linkbutton("disable");
            } else {
                //启用按钮
                $("#edit_btn").linkbutton("enable");
            }
        }

    });

    adjustdepot_dialog.dialog({
        width:300,
        height:350,
        buttons:'#adjustdepot_btns',
        closed:true
    })


    orderbill_dialog.dialog({
        width:800,
        height:500,
        buttons:'#orderbill_btns',
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









