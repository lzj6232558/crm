$(function () {
    //抽取变量
    var gift_datagrid = $("#gift_datagrid");
    var gift_dialog = $("#gift_dialog");
    var gift_form = $("#gift_form");
    var methodObj = {
        //新增按钮
        add: function () {
            //清空表单数据
            gift_form.form("clear");

            //设置标题
            gift_dialog.dialog("setTitle", "新增礼品");
            //打开弹出框
            gift_dialog.dialog("open");
        },

//编辑按钮
        edit: function () {
            //判断用户是否选中数据
            var row = gift_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            //清空表单数据
            gift_form.form("clear");


            //回显到表单中(根据同名匹配的原则来回显)
            gift_form.form("load", row);

            //设置标题
            gift_dialog.dialog("setTitle", "编辑礼品");
            //打开弹出框
            gift_dialog.dialog("open");
        },

//删除按钮
        delete: function () {
            //判断用户是否选中数据
            var row = gift_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要操作这条数据吗？', function (r) {
                if (r) {
                    $.get("/gift/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功!", "info", function () {
                                //重新加载数据表格
                                gift_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                        }
                    }, "json")
                }
            });
        },

//刷新按钮
        reload: function () {
            gift_datagrid.datagrid("load");
        },

//提交表单
        save: function () {
            //使用easyui的form表单做提交(ajax)
            gift_form.form('submit', {
                url: "/gift/saveOrUpdate.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //关闭弹出框
                        gift_dialog.dialog("close");
                        //弹出提示信息
                        $.messager.alert("温馨提示", "操作成功!", "info", function () {
                            //重新加载数据表格
                            gift_datagrid.datagrid("reload");
                        });
                    } else {
                        //弹出提示信息
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            })
        },

//取消按钮
        cancel: function () {
            gift_dialog.dialog("close");
        },

//高级查询
        searchAll: function () {
            var keyword = $("#keyword").textbox("getValue");
            gift_datagrid.datagrid("load", {
                keyword: keyword
            });
        }
}
    //页面加载完成,统一绑定事件
    $("[data-cmd]").click(function () {
        //获取有点击事件的按钮的方法名称
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    });

    //初始化数据表格框
    gift_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/gift/query.do',
        singleSelect: true,
        columns: [[
            {field: 'sn', title: '礼品编号', width: 100},
            {field: 'name', title: '礼品名称', width: 100},
            {field: 'point', title: '所需积分', width: 100},
            {field: 'currentNum', title: '剩余数量', width: 100},
            {field: 'totalNum', title: '总数量', width: 100},
            {field: 'remark', title: '备注', width: 100},
            {title:'礼品图片',field:'imagepath',width:100,align:'center',
                formatter:function(value,row,index){return '<a><img width="100px" src="'+row.imagepath+'" /></a>';}
            }
        ]],
        toolbar: '#gift_toolbar',
        striped: true,
        pagination: true,
        rownumbers: true
    })


    //初始化弹出框
    gift_dialog.dialog({
        width: 320,
        height: 250,
        buttons: '#gift_buttons',
        closed: true,
    })


    //初始化搜索框
    $("#keyword").searchbox({
        prompt:"输入礼品名称或编号",
        searcher: function (value, name) {
            //高级查询
            gift_datagrid.datagrid("load", {
                "keyword": value
            })
        }
    });
})









