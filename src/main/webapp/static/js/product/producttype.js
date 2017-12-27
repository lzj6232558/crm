$(function () {
    //给常用的定义变量
    var parentForm = $("#parent_form");
    var parentDialog = $("#parent_dialog");
    var childForm = $("#child_form");
    var childDialog = $("#child_dialog");

    var objMethod = {
        //回到所有列表
        allType: function () {
            $("#parent_table").datagrid("unselectAll");
            $("#child_table").datagrid({
                url: "/producttype/selectAll.do",
                fit: true,
                fitColumns: true,
                rownumbers:true,
                columns: [[
                    {field: 'id', title: '序号', width: 100},
                    {field: 'name', title: '类别名称', width: 100},
                    {
                        field: 'parentId', title: '一级分类/二级分类', width: 100, formatter: function (value, row, index) {
                        return value ? "二级分类" : "一级分类";
                    }
                    }
                ]],
                striped: true
            })
        },
        //添加一级分类
        addParent: function () {
            parentForm.form("clear");
            parentDialog.dialog("open");
            parentDialog.dialog("setTitle", "新增一级分类页面");
        },
        //编辑一级分类
        editParent: function () {
            var select = $("#parent_table").datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            //清空表单
            parentForm.form("clear");
            //回显数据
            parentForm.form("load", select);
            parentDialog.dialog("open");
            parentDialog.dialog("setTitle", "编辑一级分类页面");
        },
        //取消一级分类
        cancelParent: function () {
            parentDialog.dialog("close");
        },
        //保存一级分类
        saveParent: function () {
            parentForm.form("submit", {
                url: '/producttype/saveOrUpdate.do',
                success: function (data) {
                    parentForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        parentDialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#parent_table").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        //删除一级分类
        deleteParent:function () {
            var row = $("#parent_table").datagrid("getSelected");
            if(!row){
                $.messager.alert("温馨提示","请选择一条数据","warning");
                return;
            }
            $.messager.confirm("确认","您确认想要执行该操作吗?",function (r) {
                if(r){
                    $.get("/producttype/delete.do",{id:row.id},function (data) {
                        if(data.success){
                            $.messager.alert("温馨提示","操作成功!","info",function () {
                                //重新加载数据表格
                                $("#parent_table").datagrid("reload");
                            });
                        }else {
                            $.messager.alert("温馨提示",data.message,"error");
                        }
                    },"json")
                }
            })
        },
        //添加二级分类
        addChild: function () {
            var row = $("#parent_table").datagrid("getSelected");
            if (!row){
                $.messager.alert("温馨提示","请先选择一级类别","warning");
                return;
            }
            childForm.form("clear");
            childDialog.dialog("open");
            childDialog.dialog("setTitle", "新增二级分类页面");
        },
        //编辑二级分类
        editChild: function () {
            var row = $("#parent_table").datagrid("getSelected");
            if (!row){
                $.messager.alert("温馨提示","请先选择一级类别","warning");
                return;
            }
            var select = $("#child_table").datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            //清空表单
            childForm.form("clear");
            //回显数据
            childForm.form("load", select);
            childDialog.dialog("open");
            childDialog.dialog("setTitle", "编辑二级分类页面");
        },
        //取消二级分类
        cancelChild: function () {
            childDialog.dialog("close");
        },
        //保存二级分类
        saveChild: function () {
            //console.log(row);
            childForm.form("submit", {
                url: '/producttype/saveOrUpdate.do',
                onSubmit:function (param) {
                    //得到一级分类选中的那一行
                    var row = $("#parent_table").datagrid("getSelected");
                    param["parentId"]=row.id;
                },
                success: function (data) {
                    childForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        childDialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#child_table").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        //删除二级分类
        deleteChild: function () {
            var row = $("#parent_table").datagrid("getSelected");
            if (!row){
                $.messager.alert("温馨提示","请先选择一级类别","warning");
                return;
            }
            var row = $("#child_table").datagrid("getSelected");
            if(!row){
                $.messager.alert("温馨提示","请选择一条数据","warning");
                return;
            }
            $.messager.confirm("确认","您确认想要执行该操作吗?",function (r) {
                if(r){
                    $.get("/producttype/delete.do",{id:row.id},function (data) {
                        if(data.success){
                            $.messager.alert("温馨提示","操作成功!","info",function () {
                                //重新加载数据表格
                                $("#child_table").datagrid("reload");
                            });
                        }else {
                            $.messager.alert("温馨提示",data.message,"error");
                        }
                    },"json")
                }
            })
        }
    }
    //统一绑定按钮点击事件
    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })
    //一级分类
    $("#parent_table").datagrid({
        fit:true,
        fitColumns:true,
        url:"/producttype/selectParentClassify.do",
        rownumbers:true,
        columns:[[
            {field:'name',title:"一级分类",width:170}
        ]],
        toolbar:"#parent_toolbar",
        singleSelect:true,
        onClickRow:function (index,row) {
            //选中一级列表的行,会切换二级列表的页面(二级联动)
            $("#child_table").datagrid({
                url:"/producttype/selectChildClassify.do?id="+row.id,
                toolbar:"#child_toolbar",
                columns:[[
                    {field:'name',title:"二级分类",width:170}
                ]],
                singleSelect:true
            })
        }
    })
    //二级分类列表初始化是查所有一级二级分类
    $("#child_table").datagrid({
        url: "/producttype/selectAll.do",
        fit: true,
        fitColumns: true,
        rownumbers:true,
        columns: [[
            {field: 'id', title: '序号', width: 100},
            {field: 'name', title: '类别名称', width: 100},
            {
                field: 'parentId', title: '一级分类/二级分类', width: 100, formatter: function (value, row, index) {
                return value ? "二级分类" : "一级分类";
            }
            }
        ]],
        striped: true
    })

    //一级分类dialog初始化
    parentDialog.dialog({
        width:300,
        height:150,
        buttons:"#parent_btns",
        closed:true
    })
    //二级分类dialog初始化
    childDialog.dialog({
        width:300,
        height:150,
        buttons:"#child_btns",
        closed:true
    })
})

