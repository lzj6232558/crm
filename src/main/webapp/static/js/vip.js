$(function () {
    $.extend($.fn.validatebox.defaults.rules, {
        /*必须和某个字段相等*/
        equalTo: {
            validator:function(value,param){
                return $(param[0]).val() == value;
            },
            message:'字段不匹配'
        }

    });

    $("#btns").bind("click",function () {
        $("#passwordForm").form("submit",{
            url:'/vip/checkPassword.do',
            onSubmit:function (param) {
                param["vId"]=vipId;
            },
            success:function (data) {
                data=$.parseJSON(data);
                if(data.success){
                    $.post("/vip/changeState.do",{id:vipId},function (data2) {
                        if(data2.success){
                            password.dialog("close");
                            $.messager.alert('温馨提示', data2.msg,"warning", function () {
                                vipTable.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示', '操作失败')
                        }
                    })
                }
            }
        })
    })
    var textbox2 = $("#_easyui_textbox_input2");
    var textbox11 = $("#_easyui_textbox_input11");
    var textbox19 = $("#_easyui_textbox_input19");
    var textbox20 = $("#_easyui_textbox_input20");
    textbox2.blur(function () {
        if(textbox2.next().val()){
            if(!textbox11.next().val()){
                $("#timeFormat").combobox("setValue",'DAY');
                textbox19.parent().hide();
                textbox20.parent().hide();
            }
        }else{
            $("#timeFormat").combobox("setValue",'');
            textbox19.parent().show();
            textbox20.parent().show();
        }
    })
    textbox11.blur(function () {
        if(textbox11.next().val() || textbox11.next().val()==""){
            if(!textbox2.next().val()){
                 $("#formatNum").textbox("setValue","1");
                textbox19.parent().hide();
                textbox20.parent().hide();
                textbox11.next().prop("readOnly",true);
                textbox11.prop("readOnly",true);
            }
        }else if(textbox11.next().val() ==""){
            $("#formatNum").textbox("setValue","");
            textbox19.parent().show();
            textbox20.parent().show();
        }
    })



    var vipForm = $("#vip_form");
    var dialog = $("#dialog");
    var vipTable = $("#vip_table");
    var password = $("#password");
    var objMethod = {
        add: function () {
            vipForm.form("clear");
            dialog.dialog("open");
            $("#pwd").show();
            dialog.dialog("setTitle", "新增会员页面");
            $("#vipCardState").combobox("setValue",1);
            $("#source").combobox("setValue",'送上门来的');
            $("#cc").combobox("setValue",1);
            /*$("#vipCardState").show();*/
            $("#amountMoney").show();
        },

        save: function () {
            vipForm.form("submit", {
                url: '/vip/saveOrUpdate.do',
                success: function (data) {
                    vipForm.form("clear");
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            vipTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        cancel: function () {
            dialog.dialog("close");
            $("#passwordDialog").dialog("close");
        },

        edit: function () {
            var select = vipTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            $("#pwd").hide();
            $("#vipCardStateTr").hide();
            $("#amountMoney").hide();
            vipForm.form("clear");
            dialog.dialog("open");
            dialog.dialog("setTitle", "编辑会员页面");
            select['employee.id'] = select.employee.id;
            $("emp").combobox("readonly",true);
            if(select.gender){
                $("cc").combobox("setValue",1);
            }else{
                $("cc").combobox("setValue",0);
            }
            vipForm.form("load", select);
        },

        reload: function () {
            vipTable.datagrid("load");
        },

        change: function () {
            var select = vipTable.datagrid("getSelected");
            if (!select) {
                $.messager.alert("温馨提示", "请选择一条数据", "warning");
                return;
            }
            password.dialog("open");
            window.vipId = select.id;
        },

        search22: function () {
            var keyword = $("#keyword").textbox("getValue");
            var employeeId = $("#employeeId").textbox("getValue")
            var seachSource = $("#seachSource").textbox("getValue")
            var beginTime = $("#beginTime").textbox("getValue")
            var endTime = $("#endTime").textbox("getValue")
            var timeFormat = $("#timeFormat").textbox("getValue")
            var formatNum = $("#formatNum").textbox("getValue")
            vipTable.datagrid("load", {
                keyword: keyword,
                seachSource:seachSource,
                employeeId:employeeId,
                beginTime:beginTime,
                endTime:endTime,
                timeFormat:timeFormat,
                formatNum:formatNum
            });
        },
        imlportbtn:function () {
            $("#imlportForm").form("submit",{
                url:'/vip/imlport.do',
                success:function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#imlport").dialog("close");
                            vipTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', '操作失败')
                    }
                }
            })
        },
        editpassword:function () {
            //这里是修改密码的窗口
            $("#passwordDialog").dialog("open")
        },
        passwordbtn:function () {
            $("#password_form").form("submit",{
                url:"/vip/editVipPassword.do",
                onSubmit:function (param) {
                    param["vId"]=window.thisRow.id;
                },
                success:function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        dialog.dialog("close");
                        $.messager.alert('温馨提示', data.msg,"warning", function () {
                            $("#passwordDialog").dialog("close");
                            vipTable.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg,"error")
                    }
                }
            })
        },
        initPassword:function () {
            $.messager.confirm('确认','您确认想要重置吗？',function(success){
                if (success){
                    $.post("/vip/initPassword.do",{id:window.thisRow.id},function (data) {
                        if(data.success){
                            $.messager.alert('温馨提示', data.msg,"warning")
                        }
                    })
                }
            });

        }
    }

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })

    vipTable.datagrid({
        url: "query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'vipNumber', title: '会员号码', width: 100},
            {field: 'vipName', title: '会员姓名', width: 100},
            {field: 'vipPhone', title: '会员电话', width: 100},
            {field: 'gender', title: '性别', width: 100,formatter:function (value, row, index) {
                return value ? "先生":'女士';
            }},
            {field: 'amountMoney', title: '充值总金额', width: 100},
            {field: 'currentMoney', title: '当前金额', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {field: 'inputTime', title: '加入时间', width: 100},
            {field: 'vipcard', title: '会员卡类型', width: 100,formatter:function (value,row,index){
                return value.name;
            }},
            {field: 'currentpoint', title: '当前积分', width: 100},
            {field: 'totalpoint', title: '总积分', width: 100},
            {field: 'employee', title: '录入员工', width: 100,formatter:function (value, row, index) {
                return value?value.username:"";
            }},
            {field: 'source', title: '会员来源', width: 100},
            {field: 'vipCardState', title: '会员卡状态', width: 100,formatter:function (value, row, index) {
                if(value > 0){
                    return "正常使用";
                }else if(value == 0){
                    return "已挂失";
                }else {
                    return "停用";
                }
            }}
        ]],
        toolbar: "#vip_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.vipCardState > 0) {
                $('#change').linkbutton({
                    text: '挂失会员卡'
                });
            } else {
                $('#change').linkbutton({
                    text: '补办会员卡'
                });
            }
        },
        onRowContextMenu:function (e, index, row) {
            $('#myMenu').menu('show', {
                left:e.pageX,
                top:e.pageY,
            });
            window.thisRow = row;
            e.preventDefault();
        }
    })
    dialog.dialog({
        width: 470,
        height: 450,
        closed: true,
        buttons: "#vip_btns"
    })
    $("#imlport").dialog({
        width: 300,
        height: 250,
        closed: true,
        buttons:'#imlportbtn'
    })
    $("#passwordDialog").dialog({
        width: 300,
        height: 180,
        title: '修改密码',
        closed: true,
        buttons:"#passwordbtn"
    })
    password.dialog({
        title:'请输入密码',
        width:300,
        height:200,
        closed:true,
        buttons:"#btns"
    })
})

$(function () {
    $("#_easyui_textbox_input18").blur(function () {
        var val = $(this).next().val();
        $.get("/vip/checkVipPhone.do",{phone:val},function (data) {
            if(data.success){
                $("#phoneMsg").text(data.msg);
                $("#saveBtn").linkbutton("enable",true);
            }else {
                $("#phoneMsg").text(data.msg);
                $("#saveBtn").linkbutton("disable",true);
            }
        })
    })
})
