$(function() {
    //1.变量抽取
    var productstock_form = $("#productstock_form");
    var productstock_datagrid = $("#productstock_datagrid");
    var productstock_dialog = $("#productstock_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {
            //显示密码框
            $("#password_tr").show();

            //清空表单
            productstock_form.form("clear");

            //设置标题
            productstock_dialog.dialog("setTitle","新增员工");
            //打开弹出框
            productstock_dialog.dialog("open");
        },

        load: function (){
            $("#depotId").combobox("clear");
            $("#maxNumber").textbox("clear");
            $("#keyword").textbox("clear");

            productstock_datagrid.datagrid("load");
        },

        cancel: function () {
            //关闭弹出框
            productstock_dialog.dialog("close");
        },

        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");

            //会让数据表格重新加载,并且带上查询的参数
            //(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
            productstock_datagrid.datagrid("load",{
                keyword:keyword,
                maxNumber:$("#maxNumber").textbox("getValue"),
                depotId:$("#depotId").combobox("getValue")
            });
        },
        //导出库存
        myout: function() {
            //不能使用ajax发送请求,因为ajax会把请求保存到data中,
            // 不会弹出文件下载选项,而我们不需要data
            window.location.href="/productstock/export.do?keyword=" + $("#keyword").textbox("getValue");
        }


    }



    //3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function(){
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })


    productstock_datagrid.datagrid({
        fit:true,
        fitColumns:true,
        toolbar:'#productstock_toolbar',
        striped:true, //是否显示斑马线效果。
        url:'/productstock/query.do',
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
        rownumbers:true,//如果为true，则显示一个行号列
        singleSelect:true,//如果为true，则只允许选择一行
        columns: [[
            {field: 'product', title: '商品编码', width: 100,formatter:function(value,row,index) {
                return value?value.sn:"";
            }},
            {field: 'productName', title: '商品名称', width: 100,formatter:function(value,row,index) {
                return row.product?row.product.name:"";
            }},
            {field: 'productCostPrice', title: '商品单价', width: 100,formatter:function(value,row,index) {
                return row.product?row.product.costprice:"";
            }},
            {field: 'depot', title: '所在仓库', width: 100,formatter:function(value,row,index) {
                return value?value.name:"";
            }},
            {field: 'storenumber', title: '库存数量', width: 100},
            {field: 'amount', title: '库存汇总', width: 100},
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

    productstock_dialog.dialog({
        width:300,
        height:300,
        buttons:'#productstock_btns',
        closed:true
    })


})
