$(function () {
    //字典
    var dictionaryForm = $("#dictionary_form");
    var dialog = $("#dialog");
    var dictionaryTable = $("#dictionary_table");

    //明细
    var dictionaryItemForm = $("#dictionaryItem_form");
    var dialog2 = $("#dialog2");
    var dictionaryItemTable = $("#dictionaryItem_table");

    var objMethod = {
        dictionary_add: function () {
            dictionaryForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "新增字典页面");
        },

        dictionary_save: function () {
            dictionaryForm.form("submit", {
                url: '/systemDictionary/saveOrUpdate.do',
                success: function (data) {
                    dictionaryForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg, function () {
                            dictionaryTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        dictionary_cancel: function () {
            dialog.dialog("close");
        },

        dictionary_edit: function () {
            var select = dictionaryTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            dictionaryForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑字典页面");
            dictionaryForm.form("load",select);

        },

        dictionary_reload: function () {
            dictionaryTable.datagrid("load");
        },

        dictionary_delete: function () {
            var select = dictionaryTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要删除吗？', function (r) {
                if (r) {
                    $.get("/systemDictionary/delete.do", {id:select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg, function () {
                                dictionaryTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },
        dictionaryItem_add: function () {
            dictionaryItemForm.form("clear");
            dialog2.dialog("open");
            dialog2.dialog("setTitle", "新增字典明细页面");
        },

        dictionaryItem_save: function () {
            dictionaryItemForm.form("submit", {
                url: '/systemDictionaryItem/saveOrUpdate.do',
                success: function (data) {
                    dictionaryItemForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog2.dialog("close");
                        $.messager.alert('温馨提示', data.msg, function () {
                            dictionaryItemTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        dictionaryItem_cancel: function () {
            dialog2.dialog("close");
        },

        dictionaryItem_edit: function () {
            var select = dictionaryItemTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            dictionaryItemForm.form("clear");
            dialog2.dialog("open");
            dialog2.dialog("setTitle", "编辑字典页面");
            select['systemDictionary.id'] = select.systemDictionary.id;
            dictionaryItemForm.form("load",select);

        },

        dictionaryItem_reload: function () {
            dictionaryItemTable.datagrid("load");
        },

        dictionaryItem_delete: function () {
            var select = dictionaryItemTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $.messager.confirm('确认', '您确认想要删除吗？', function (r) {
                if (r) {
                    $.get("/systemDictionaryItem/delete.do", {id:select.id}, function (data) {
                        if (!data.success) {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        } else {
                            $.messager.alert('温馨提示', data.msg, function () {
                                dictionaryItemTable.datagrid("reload");
                            });
                        }
                    }, 'json')
                }
            });
        },

    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    dictionaryTable.datagrid({
        url: "query.do",
        width:600,
        height:400,
        fit: true,
        fitColumns: true,

        pagination: true,
        columns: [[
            {field: 'sn', title: '目录编号', width: 100},
            {field: 'name', title: '目录名称', width: 100},
            {field: 'intro', title: '目录简介', width: 100 },
        ]],
        toolbar: "#dictionary_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onSelect:function (index, row) {
            dictionaryItemTable.datagrid("load",{id:row.id})
        }
    })
    dialog.dialog({
        width: 400,
        height: 450,
        closed: true,
        buttons: "#dictionary_btns"
    })

    dictionaryItemTable.datagrid({
        url: "/systemDictionaryItem/queryById.do",
        fit: true,
        fitColumns: true,
        width:600,
        height:400,
        pagination: true,
        columns: [[
            {field: 'name', title: '目录名称', width: 100},
            {field: 'intro', title: '目录简介', width: 100 },
            {field: 'systemDictionary', title: '上级字典', width: 100, formatter: function (value, row, index) {
                return value ? value.name : "";
            }},
        ]],
        toolbar: "#dictionaryItem_toolbar",
        striped: true,
        rownumbers: true,
        singleselect: true,
    })

    dialog2.dialog({
        width: 400,
        height: 450,
        closed: true,
        buttons: "#dictionaryItem_btns"
    })
})


