$(function () {
    var productTable = $("#product_table");
    var productForm = $("#product_form");
    var dialog = $("#dialog");
    var objMethod = {
        add: function () {
            productForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增商品页面");
        },

        save: function () {
            productForm.form("submit", {
                url: '/product/saveOrUpdate.do',
                success: function (data) {
                    productForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            productTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        delete: function () {
            var row = productTable.datagrid("getSelected");
            if (!row){
                $.messager.alert("温馨提示","请选择一条数据","warning");
                return;
            }
            $.messager.confirm("确认消息","您确认要删除此数据吗?",function (r) {
                if (r){
                    $.get("/product/delete.do",{id:row.id},function (data) {
                        if (data.success){
                            $.messager.alert("温馨提示","删除成功","info",function () {
                                productTable.datagrid("reload");
                            })
                        }else {
                            $.messager.alert("温馨提示",data.msg,"warning");
                        }
                    },"json")
                }
            })
        },
        cancel: function () {
            dialog.dialog("close");
        },

        edit: function () {
            var select = productTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            //清空表单
            productForm.form("clear");
            //console.log(select.unit);
            //处理商品单位数据
            select["unit.id"]=select.unit.id;
            //处理分类数据
            select["parent.id"]=select.parent.id;
            select["child.id"]=select.child.id;
            //回显数据
            productForm.form("load", select);
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑商品页面");

        },

        reload: function () {
            $("#keyword").textbox("clear");
            $("#cc1").combobox("clear");
            $("#cc2").combobox("clear");
            productTable.datagrid("load",{
                page:1
            });
        },

        change: function () {
            var select = productTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要修改吗？', function (r) {
                if (r) {
                    $.get("/product/changeState.do", {id: select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', "操作成功!","info", function () {
                                productTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },

        search: function () {
            var keyword = $("#keyword").textbox("getValue");
            var parentId = $("#cc1").combobox("getValue");
            var childId = $("#cc2").combobox("getValue");
            productTable.datagrid("load", {
                keyword: keyword,
                parentId:parentId,
                childId:childId
            });
        },
        exportXls:function () {
            window.location.href="/product/export.do";
        },
        imlportXls:function () {
            $("#imlport").dialog("setTitle","文件上传")
            $("#imlport").dialog("open");
        },
        imlportbtn:function () {
            $("#imlportForm").form("submit",{
                url:'/product/imlport.do',
                success:function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"info", function () {
                            $("#imlport").dialog("close");
                            productTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        }
    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    //初始化fancybox
    $(".fancybox").fancybox();

    productTable.datagrid({
        url: "/product/query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        nowrap: false,
        columns: [[
            {title:'商品图片',field:'imagePath',width:60, formatter:function(value,row,index){
                    return '<a class="fancybox" href="'+row.imagePath+'" data-fancybox-group="gallery" title="'+row.name+'"><img width="50px" src="'+row.imagePath+'" /></a>';
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
            {field: 'productstock', title: '库存量', width: 100, formatter:function (value,row,index) {
                return value ? value.storenumber : "";
            }},
            {field: 'inputtime', title: '入库时间', width: 100},
            {field: 'state', title: '状态', width: 100,formatter: function (value, row, index) {
                return value ? "在售" : "<font color='red'>已下架</font>";
            }},
            {field: 'remark', title: '备注', width: 140}
        ]],
        toolbar: "#product_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.state) {
                $('#change').linkbutton({
                    text: '下架'
                });
            } else {
                $('#change').linkbutton({
                    text: '上架'
                });
            }
        }
    })

    dialog.dialog({
        width:360,
        height:448,
        buttons:"#product_btns",
        closed:true
    })
    //combobox二级联动
    $("#parentClassify").combobox({
        onSelect: function (record) {
            //var pid=record.id;
            /*  $("#childClassify").combobox({
                  url:'/producttype/selectChildClassify.do?parentId=' + record.id
                }).combobox("clear");*/
            /* $.get("/producttype/selectChildClassify.do",{id:record.id},function (data) {
                 $("#childClassify").combobox("loadData",data);
                   console.log(data)
             })*/

            /*$("#childClassify").combobox("reload","/producttype/selectChildClassify.do?id=" + record.id);*/
            $("#childClassify").combobox({
                url:'/producttype/selectChildClassify.do?id=' + record.id,
                valueField:'id',
                textField:'name'
            });

        }
    })

    $("#imlport").dialog({
        width: 300,
        height: 250,
        closed: true,
        buttons:'#imlportbtn'
    })

})
