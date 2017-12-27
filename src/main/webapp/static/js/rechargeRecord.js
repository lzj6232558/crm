$(function () {
    var vipTable = $("#vip_table");
    var vipForm = $("#vip_form");
    var incomeRecord_table = $("#incomeRecord_table");

    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })
    var objMethod = {
        search22 :function () {
            var keyword = $("#keyword").textbox("getValue");
            $("#vip_table").datagrid("load", {
                keyword:keyword
            })
         },
       /* 充值方法*/
        recharge:function () {
            var getSelected = vipTable.datagrid("getSelected");
            if(!getSelected){
                $.messager.alert('温馨提示','请选择会员后在进行充值',"warning");
                return
            }
        
            $("#recharge_form").form("submit",{
                url:'/rechargeRecord/recharge.do',
                onSubmit:function (param) {
                    var getText = $("#currentInMoney").textbox("getText");
                    if(!getText){
                        $.messager.alert('温馨提示','请添加充值金额',"warning");
                        return false;
                    }
    
                    param["vipNumber"]=$("#vipNumber").val();
                },
                success:function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg,"warning", function () {

                            window.location.reload();
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg)
                    }
                }
            })
         },
        refund:function () {
            var getSelected = vipTable.datagrid("getSelected");
            if(!getSelected){
                $.messager.alert('温馨提示','请选择会员后在进行退卡',"warning");
                return
            }
            $.messager.confirm('温馨提示','您确认想要退卡吗？退卡后将清空所有积分',function(success){
                if (success){
                    $("#refund_form").form("submit",{
                        url:'/rechargeRecord/refund.do',
                        onSubmit:function (param) {
                            var getText = $("#refundMsg").textbox("getText");
                            if(!getText){
                                $.messager.alert('温馨提示','请添加退卡详情',"warning");
                                return false;
                            }
                            param["vipNumber"]=$("#vipNumber").val();
                        },
                        success:function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert('温馨提示', data.msg + "应退金额:" + $("#currentMoney").val(),"warning", function () {
                                    vipTable.datagrid("reload");
                                    incomeRecord_table.datagrid("reload");

                                });
                            } else {
                                $("#refundMsg").textbox("clear");
                                $.messager.alert('温馨提示', data.msg)
                            }
                        }
                    })
                }
            });


        }

}
    vipTable.datagrid({
        url: "/vip/query.do",
        fit: true,
        fitColumns: true,
        columns: [[
            {field: 'vipName', title: '会员姓名', width: 100},
            {field: 'vipPhone', title: '会员电话', width: 100},
            {field: 'currentMoney', title: '当前金额', width: 100},
            {field: 'vipcard', title: '会员卡类型', width: 100,formatter:function (value,row,index){
                return value.name;
            }}
        ]],
        toolbar: "#vip_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            vipForm.form("clear");
            row["consume"] = row.amountMoney - row.currentMoney;
            row["employee.username"]= row.employee.username;
            console.log(row.vipCardState)
            if(row.vipCardState == 1 || row.vipCardState == "正常使用"){
                row["vipCardState"] = "正常使用";
            }else if(row.vipCardState==0 || row.vipCardState== "已挂失"){
                row["vipCardState"] = "已挂失";
            }else{
                row["vipCardState"] = "停用中";
            }
            vipForm.form("load",row);

            /*
                充值记录表数据回显
             */
            incomeRecord_table.datagrid("load",{
                number:$("#vipNumber").val()
            })
        }
    })
    vipForm.form({

    })
    incomeRecord_table.datagrid({
        url:'/rechargeRecord/order.do',
        fit: true,
        fitColumns: true,
        columns: [[
            {field: 'vipNumber', title: '会员号码', width: 100},
            {field: 'vipName', title: '会员姓名', width: 100},
            {field: 'currentInMoney', title: '当次充值金额', width: 100},
            {field: 'vipCurrentMoney', title: '充值后余额', width: 100},
            {field: 'vipAmountMoney', title: '充值总金额', width: 100},
            {field: 'inputTime', title: '充值时间', width: 100},
            {field: 'inputUser', title: '充值员工', width: 100,formatter:function (value,row,index){
                return value?value.username:"";
            }},
            {field: 'postscript', title: '备注', width: 100},
        ]]
    })

})
