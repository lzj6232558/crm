$(function() {
    //1.变量抽取
    var orderchart_form = $("#orderchart_form");
    var orderchart_datagrid = $("#orderchart_datagrid");
    var orderchart_dialog = $("#orderchart_dialog");
    var pie_panel = $("#pie_panel");


    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {
                //显示密码框
                $("#password_tr").show();

                //清空表单
                orderchart_form.form("clear");

                //设置标题
                orderchart_dialog.dialog("setTitle","新增员工");
                //打开弹出框
                orderchart_dialog.dialog("open");
            },

        edit: function () {
                var row = orderchart_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据!",'warning');
                    return;
                }


                //清空表单
                orderchart_form.form("clear");

                //回显数据
                orderchart_form.form("load",row);

                //设置标题
                orderchart_dialog.dialog("setTitle","编辑员工");
                //打开弹出框
                orderchart_dialog.dialog("open");
            },

        changeState: function () {
                var row = orderchart_datagrid.datagrid("getSelected");
                if(!row) {
                    $.messager.alert('温馨提示',"请选择一条数据",'warning');
                    return;
                }

                $.messager.confirm('确认','您确认想要执行该操作吗?',function(r) {
                    if(r) {
                        //发送请求修改状态
                        $.get("/orderchart/changeState.do",{id:row.id},function(data) {
                            if(data.success) {
                                $.messager.alert('温馨提示',"操作成功!",'info',function() {
                                    //重新加载表格数据
                                    orderchart_datagrid.datagrid("reload");
                                });
                            }else {
                                $.messager.alert('温馨提示',data.msg, 'error');
                            }
                        },"json")
                    }
                });
            },
        load: function (){
                $("#supplierId").combobox("clear");
                $("#productName").textbox("clear");
                $("#beginDate").datebox("clear");
                $("#endDate").datebox("clear");
                $("#groupBy").combobox("clear");
                orderchart_datagrid.datagrid("load");
         },
        save: function (){
                orderchart_form.form("submit",{
                    url:'/orderchart/saveOrUpdate.do',
                    success:function(data) {
                        data = $.parseJSON(data);
                        if(data.success) {
                            //提示信息
                            $.messager.alert('温馨提示','操作成功','info',function() {
                                //关闭弹出框
                                orderchart_dialog.dialog("close");
                                //重新加载表格数据
                                orderchart_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示',data.msg,'error');
                        }
                    }
                })
            },

        cancel: function () {
                //关闭弹出框
                orderchart_dialog.dialog("close");
            },

        searchForm: function () {

            //获取关键字input
            var productName = $("#productName").textbox("getValue");
            var supplierId = $("#supplierId").combobox("getValue");
            var beginDate = $("#beginDate").datebox("getValue");
            var endDate = $("#endDate").datebox("getValue");
            var groupBy = $("#groupBy").combobox("getValue");
                //会让数据表格重新加载,并且带上查询的参数
                //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
                orderchart_datagrid.datagrid("load",{
                    productName:productName,
                    supplierId:supplierId,
                    beginDate:$("#beginDate").datebox("getValue"),
                    endDate:$("#endDate").datebox("getValue"),
                    groupBy:$("#groupBy").combobox("getValue")
                });
            },
        pie:function() {
            //获取关键字input
            var productName = $("#productName").textbox("getValue");
            var supplierId = $("#supplierId").combobox("getValue");
            var beginDate = $("#beginDate").datebox("getValue");
            var endDate = $("#endDate").datebox("getValue");
            var groupBy = $("#groupBy").combobox("getValue");
            //window.parent.location.href="http://localhost:8080/orderchart/pie.do";
            $.dialog.open("/orderchart/pie.do?productName=" + productName + "&supplierId=" + supplierId
                +"&beginDate=" + beginDate + "&endDate=" + endDate + "&groupBy=" + groupBy,{
                id:'ajaxList',
                title:'销售报表饼状图',
                width:650,
                height:450,
                background:'#000000'
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


    orderchart_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#orderchart_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/orderchart/query.do',
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'groupByName', title: '分组类型', width: 100},
            {field: 'totalNumber', title: '采购总数量', width: 100},
            {field: 'totalAmount', title: '采购总金额', width: 100}
        ]],

    });

    orderchart_dialog.dialog({
        width:300,
        height:300,
        buttons:'#orderchart_btns',
        closed:true
    })

    pie_panel.panel({
        width:800,
        height:600,
        //buttons:'#orderchart_btns',
        closed:true,
        content:'<iframe src=http://localhost:8080/orderchart/pie.do width=100% height=100% frameborder=0></iframe>'
    })


})









