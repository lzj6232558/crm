$(function() {
    //1.变量抽取
    var supplier_form = $("#supplier_form");
    var supplier_datagrid = $("#supplier_datagrid");
    var supplier_dialog = $("#supplier_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {
                //显示密码框
                $("#password_tr").show();

                //清空表单
                supplier_form.form("clear");

                //设置标题
                supplier_dialog.dialog("setTitle","新增员工");
                //打开弹出框
                supplier_dialog.dialog("open");
            },

        edit: function () {
                var row = supplier_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                    return;
                }

                //隐藏密码框
                $("#password_tr").hide();
                //清空表单
                supplier_form.form("clear");

                //回显数据
                supplier_form.form("load",row);

                //设置标题
                supplier_dialog.dialog("setTitle","编辑员工");
                //打开弹出框
                supplier_dialog.dialog("open");
            },

        load: function (){
                supplier_datagrid.datagrid("load");
         },
        save: function (){
                supplier_form.form("submit",{
                    url:'/supplier/saveOrUpdate.do',
                    success:function(data) {
                        data = $.parseJSON(data);
                        if(data.success) {
                            //提示信息
                            $.messager.alert('温馨提示','操作成功','info',function() {
                                //关闭弹出框
                                supplier_dialog.dialog("close");
                                //重新加载表格数据
                                supplier_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示',data.msg,'error');
                        }
                    }
                })
            },
        delete: function () {
            var row = supplier_datagrid.datagrid("getSelected");
            if(!row) {
                $.messager.alert('温馨提示',"请选择一条数据",'warning');
                return;
            }

            $.messager.confirm('确认','您确认想要执行该操作吗?',function(r) {
                if(r) {
                    //发送请求修改状态
                    $.get("/supplier/delete.do",{id:row.id},function(data) {
                        if(data.success) {
                            $.messager.alert('温馨提示',"操作成功!",'info',function() {
                                //重新加载表格数据
                                supplier_datagrid.datagrid("reload");
                            });
                        }else {
                            $.messager.alert('温馨提示',data.msg, 'error');
                        }
                    },"json")
                }
            });
        },

        cancel: function () {
                //关闭弹出框
                supplier_dialog.dialog("close");
            },

        searchForm: function () {
                //获取关键字input
                var keyword = $("#keyword").textbox("getValue");

                //会让数据表格重新加载,并且带上查询的参数
                //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
                supplier_datagrid.datagrid("load",{
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


    supplier_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#supplier_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/supplier/query.do',
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'name', title: '供应商', width: 100},
            {field: 'debt', title: '应付欠款', width: 100},
            {field: 'refund', title: '应收退款', width: 100},
            {field: 'linkman', title: '联系人', width: 100},
            {field: 'tel', title: '联系电话', width: 100},
            {field: 'inputtime', title: '添加时间', width: 100},
            /*{field: 'inputUser', title: '操作人', width: 100}*/
        ]],

});

    supplier_dialog.dialog({
        width:300,
        height:300,
        buttons:'#supplier_btns',
        closed:true
    })


})









