$(function() {
    //1.变量抽取
    var depot_form = $("#depot_form");
    var depot_datagrid = $("#depot_datagrid");
    var depot_dialog = $("#depot_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {
                //显示密码框
                $("#password_tr").show();

                //清空表单
                depot_form.form("clear");

                //设置标题
                depot_dialog.dialog("setTitle","新增员工");
                //打开弹出框
                depot_dialog.dialog("open");
            },

        edit: function () {
                var row = depot_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                    return;
                }


                //清空表单
                depot_form.form("clear");

                //回显数据
                depot_form.form("load",row);

                //设置标题
                depot_dialog.dialog("setTitle","编辑员工");
                //打开弹出框
                depot_dialog.dialog("open");
            },

        changeState: function () {
                var row = depot_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据",'warning');
                    return;
                }

                $.messager.confirm('确认','您确认想要执行该操作吗?',function(r) {
                    if(r) {
                        //发送请求修改状态
                        $.get("/depot/changeState.do",{id:row.id},function(data) {
                            if(data.success) {
                                $.messager.alert('温馨提示',"操作成功!",'info',function() {
                                    //重新加载表格数据
                                    depot_datagrid.datagrid("reload");
                                });
                            }else {
                                $.messager.alert('温馨提示',data.msg, 'error');
                            }
                        },"json")
                    }
                });
            },
        load: function (){
                depot_datagrid.datagrid("load");
         },
        save: function (){
                depot_form.form("submit",{
                    url:'/depot/saveOrUpdate.do',
                    success:function(data) {
                        data = $.parseJSON(data);
                        if(data.success) {
                            //提示信息
                            $.messager.alert('温馨提示','操作成功','info',function() {
                                //关闭弹出框
                                depot_dialog.dialog("close");
                                //重新加载表格数据
                                depot_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示',data.msg,'error');
                        }
                    }
                })
            },

        cancel: function () {
                //关闭弹出框
                depot_dialog.dialog("close");
            },

        searchForm: function () {
                //获取关键字input
                var keyword = $("#keyword").textbox("getValue");

                //会让数据表格重新加载,并且带上查询的参数
                //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
                depot_datagrid.datagrid("load",{
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


    depot_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#depot_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/depot/query.do',
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'name', title: '仓库名称', width: 100},
            {field: 'sn', title: '仓库编码', width: 100},
            {field: 'linkman', title: '联系人', width: 100},
            {field: 'tel', title: '联系电话', width: 100},
            {field: 'location', title: '仓库地址', width: 100},
            {field: 'inputtime', title: '添加时间', width: 100},
            {
                field: 'state', title: '仓库状态', width: 100, formatter: function (value, row, index) {
                return value ? "<font color='green'>正常</font>" : "<font color='red'>停用</font>";
            }
            }
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $('#changState_btn').linkbutton({
                    text: '停用'
                });
            } else {
                $('#changState_btn').linkbutton({
                    text: '启用'
                });
            }

            if(!row.state) {
                //禁用编辑按钮
                $("#edit_btn").linkbutton("disable");
            } else {
                //启用按钮
                $("#edit_btn").linkbutton("enable");
            }
        }

    });

    depot_dialog.dialog({
        width:300,
        height:300,
        buttons:'#depot_btns',
        closed:true
    })


})









