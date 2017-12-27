$(function () {
    var editIndex = undefined;
    var totalcount = 0;
    function endEdit() {//该方法用于关闭上一个焦点的editing状态
        if (editIndex == undefined) {
            return true;
        }
        if ($('#choose_datagrid').datagrid('validateRow', editIndex)) {
            $('#choose_datagrid').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    //实时计算积分
    function calculate() {
        totalcount = 0;
        var rows = $('#choose_datagrid').datagrid("getRows");
        if(!rows){
            totalcount = 0;
        }
        for(var i=0;i<rows.length;i++) {

            totalcount += rows[i]['requiredPoints'];
        }
        $("#span").html(totalcount);
    }


    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        objMethod[cmd]();
    })
    var objMethod = {
        //高级查询(会员信息)
        searchAll: function () {
            var keyword = $("#keyword").textbox("getValue");
            $("#vip_table").datagrid("load", {
                keyword: keyword
            })
        },
        //高级查询(礼品信息)
        searchGift: function () {
            var keyword = $("#gift").textbox("getValue");
            $("#gifttable").datagrid("load", {
                keyword: keyword
            })
        },

        //选择礼品
        choose_gift: function () {
            //清空表单数据
            $("#change_form").form("clear");
            //设置标题
            $('#giftdialog').dialog("setTitle", "选择礼品");
            //打开弹出框
            $('#giftdialog').dialog("open");
        },

        //删除选中礼品
        deleteGift:function () {
            var row = $('#choose_datagrid').datagrid('getSelected');
            var rowIndex = $('#choose_datagrid').datagrid('getRowIndex', row);
            if(row){
                $('#choose_datagrid').datagrid('deleteRow',rowIndex);
            }
            calculate();
        },

        //兑换礼品,扣除积分
        exchange: function () {
            var totalcount = 0;
            var rows = $('#choose_datagrid').datagrid("getRows");
               /*  var rows = "";
                    for(i = 0;i < rows.length;i++)
                    {
                        rows = rows  + JSON.stringify(rows[i]);
                    }*/
            $("#change_form").form('submit', {
                url: "/giftChange/renew.do",
                onSubmit: function(param){
                   var row = $("#vip_table").datagrid("getSelected");
                   if(!row){
                       $.messager.alert("温馨提示", "请在会员列表中选中一个会员","error");
                       return;
                   }
                   if(!rows){
                       $.messager.alert("温馨提示", "请选择礼品","error");
                       return;
                   }
                   //计算会员选择的礼品的积分
                    for(var i=0;i<rows.length;i++){
                        totalcount += rows[i]['requiredPoints'];
                    }
                   //封装选中的会员的id传到后台
                   /* console.log(row.id);
                    console.log(totalcount);*/
                    param["vipId"] = row.id;//会员id
                    param["totalcount"] = totalcount;//所选礼物总积分
                    param["rows"] = JSON.stringify(rows);
                    //console.log(rows);
                },
          //      data: {'rows': rows},
               dataType:"json",
               success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //清空数据表格
                        $('#choose_datagrid').datagrid({
                            data:[
                                {total: 0, rows: []}
                            ]
                        });
                        //弹出提示信息
                        $.messager.alert("温馨提示", "操作成功!", "info", function () {
                        });
                    } else {
                        //弹出提示信息
                        $.messager.alert("温馨提示", "操作失败", "error");
                    }
                }
            })
        },
    }

    //会员列表
    $("#vip_table").datagrid({
        url: "/vip/query.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        columns: [[
            {field: 'vipNumber', title: '会员卡号', width: 100},
            {field: 'vipName', title: '会员姓名', width: 100},
            {field: 'vipPhone', title: '会员电话', width: 100},
            {field: 'currentMoney', title: '当前金额', width: 100},
            {field: 'amountMoney', title: '充值总金额', width: 100},
            {
                field: 'vipcard', title: '会员卡类型', width: 100, formatter: function (value, row, index) {
                return value.name;
            }
            },
            {
                field: 'employee', title: '录入员工', width: 100, formatter: function (value, row, index) {
                return row.employee.username;
            }
            }
        ]],
        toolbar: "#vip_toolbar",
        striped: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            row["vipcard"] = row.vipcard.name;
            row["employee"] = row.employee.username;
            $("#vip_form").form("load", row);
        }
    })


    //弹出会话框中的礼品列表
    $("#gifttable").datagrid({
        fit: true,
        fitColumns: true,
        url: '/gift/query.do',
        toolbar: '#gift_toolbar',
        striped: true,
        rownumbers: true,
        singleSelect:true,
        columns: [[
            {field: 'sn', title: '礼品编号', width: 100},
            {field: 'id', title: '', width: 100},
            {field: 'name', title: '礼品名称', width: 100},
            {field: 'point', title: '所需积分', width: 100},
            {field: 'currentNum', title: '剩余数量', width: 100},
            {field: 'number', title: '数量', width: 100, hidden: true},
            {title: '礼品图片', field: 'imagepath', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    return '<a><img width="50px" src="' + row.imagepath + '" /></a>';
                }
            }
        ]],
        onClickRow: function (index, row) {
            var row = $('#gifttable').datagrid('getSelected');
            $('#choose_datagrid').datagrid('appendRow', row);
            if (row) {
                $('#choose_datagrid').datagrid('updateRow',{
                    //初始值设置为1
                    index: $('#choose_datagrid').datagrid('getRowIndex', row),
                    row: {
                        number:1,
                        requiredPoints:row["point"]
                    }
                });
                $('#choose_datagrid').datagrid('refreshRow', index);
                    //计算总积分
                $("#span").html(0);
                totalcount = 0;
                var rows = $('#choose_datagrid').datagrid("getRows");
                if(!rows){
                    totalcount = 0;
                }
                for(var i=0;i<rows.length;i++) {

                    totalcount += rows[i]['requiredPoints'];
                }
                $("#span").html(totalcount);
            }


                $("#gifttable").datagrid('deleteRow', index);

            $('#choose_datagrid').datagrid({
                onClickCell: function(index,field,value){
                    if (endEdit()) {
                        if(field=="number"){
                            $(this).datagrid('beginEdit', index);
                            var ed = $(this).datagrid('getEditor', {index:index,field:field});
                            $(ed.target).focus();
                        }
                        editIndex = index;
                    }
                },
                onBeforeEdit:function(index,row){
                    row.editing = true;
                    $('#choose_datagrid').datagrid('refreshRow', index);
                },
               onAfterEdit:function(index, row, changes) {
                   $('#choose_datagrid').datagrid('updateRow',{
                       //实时计算合计积分
                       index:index,
                       row: {
                           number:row['number'],
                           requiredPoints:row['point']*row['number']
                       }
                   });
                   $('#choose_datagrid').datagrid('refreshRow', index);
                   //计算总积分
                   calculate();
               }
            });

        },

    })


    //兑换记录
    $("#pointexchange_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        url: '/pointexchange/query.do',
        singleSelect: true,
        columns: [[
            {
                field: 'vip.vipNumber', title: '会员卡号', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.vipNumber;
            }
            },
            {
                field: 'vip.vipName', title: '会员名称', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.vipName;
            }
            },
            {
                field: 'gift.name', title: '礼品名称', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.gift.name;
            }
            },
            {field: 'number', title: '兑换数量', width: 100},

         /*   {
                field: 'vip.totalpoint', title: '积分总额', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : row.vip.totalpoint;
            }
            },*/
            {
                field: 'gift.point', title: '消费积分', width: 100, formatter: function (value, row, index) {
                return row == null ? "" : (row.gift.point) * (row.number);
            }
            },
            {field: 'inputTime', title: '兑换日期', width: 100}
        ]],
        toolbar: '#pointexchange_toolbar',
        striped: true,
        pagination: true,
        rownumbers: true
    })


    //初始化表单
    $("#gift_form").form({})
    //初始化搜索框
    $("#keyword").searchbox({
        searcher: function (value, name) {
            //高级查询
            $("#vip_table").datagrid("load", {
                "keyword": value
            })
        }
    });
    //初始化礼品列表弹出框
    $('#giftdialog').dialog({
        width: 600,
        height: 450,
        closed: true,
        cache: false
    });


})


