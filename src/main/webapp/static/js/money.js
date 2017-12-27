$(function () {
    $("[data-itemid]").click(function () {
        var id= $(this).data("itemid")
        $("#productId").datagrid({
            url:"/shoppingCar/groupProduct.do",
            queryParams: {
                id: id,
            },
            fit: true,
            fitColumns: true,
            pagination: true,
            singleSelect:true,
            columns: [[
                {title:'商品图片',field:'img',width:100, formatter:function(value,row,index){
                    return '<a><img width="130px" height="100px" src="'+row.img+'" /></a>';
                }
                },
                {field: 'name', title: '商品名称', width: 100},
                {field: 'price', title: '售价', width: 100},
                {field: 'number', title: '库存', width: 100}
            ]],
            onClickRow:function (index,row) {
                $.messager.confirm('温馨提示','确认购买此商品么',function (f) {
                    if(f){
                        var name=row.name;
                        var img=row.img;
                        var number=row.number;
                        var price=row.price;
                        var pId=row.pid;
                        var depotId=row.depotId;
                        var vipId=1;
                        $.post("/shoppingCar/add.do",{name:name,img:img,number:number,price:price,pId:pId,depotId:depotId,vipId:vipId});
                        $("#lifeTable").datagrid({
                            /*fit: true,*/
                            fitColumns: true,
                            url:"/shoppingCar/selectProduct.do",
                            columns: [[
                                {field: 'name', title: '商品名称', width: 160},
                                {field: 'price', title: '售价', width: 80},
                                {field: 'shoppingnumber', title: '购买数量', width: 80},
                                {field: 'tocalPrice', title: '总价格', width: 80},
                                {field: 'number', title: '库存数量', width: 80}
                            ]],
                        })
                    }
                })
            }
        })
    })

    //挂单
    $("#selectmenoy").click(function () {
        $.messager.confirm('温馨提示', '确定挂单么', function (f) {
            if (f) {
                $.get("/shoppingCar/selectVipNumber.do", function (data) {
                    if (data.success) {
                        $.get("/shoppingCar/selectmenoy.do", function (data) {
                            if (data.success) {
                                $.messager.alert('温馨提示', "挂单成功!")
                                $("#lifeTable").datagrid("reload");
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'error');
                            }
                        })

                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }

                })
            }
        })
    })
    //取单
    $("#getselectmenoy").click(function () {
        $.get("/shoppingCar/selectVipNumber.do", function (data) {
            if (data.success) {
                //如果选择了会员。改变状态
                $.get("/shoppingCar/getselectmenoy.do", function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', "取单成功!")
                        $("#lifeTable").datagrid("reload");
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                })

            } else {
                $.messager.alert('温馨提示', data.msg, 'error');
            }
        })
    })

    $("#alltype").click(function () {
        $("#productId").datagrid({
            url: "/shoppingCar/allProduct.do",
            fit: true,
            fitColumns: true,
            pagination: true,
            singleSelect: true,
            columns: [[
                {
                    title: '商品图片', field: 'img', width: 100, formatter: function (value, row, index) {
                    return '<a><img width="130px" height="100px" src="' + row.img + '" /></a>';
                }
                },
                {field: 'name', title: '商品名称', width: 100},
                {field: 'price', title: '售价', width: 100},
                {field: 'number', title: '库存', width: 100}
            ]],
            onClickRow: function (index, row) {
                $.messager.confirm('温馨提示', '确认购买此商品么', function (f) {
                    if (f) {
                        var name = row.name;
                        var img = row.img;
                        var number = row.number;
                        var price = row.price;
                        var pId = row.pid;
                        var depotId = row.depotId;
                        var vipId=1
                        $.post("/shoppingCar/add.do", {name: name, img: img, number: number, price: price, pId: pId, depotId: depotId,vipId:vipId});
                        $("#lifeTable").datagrid({
                            /*fit: true,*/
                            fitColumns: true,
                            url: "/shoppingCar/selectProduct.do",
                            columns: [[
                                {field: 'name', title: '商品名称', width: 160},
                                {field: 'price', title: '售价', width: 80},
                                {field: 'shoppingnumber', title: '购买数量', width: 80},
                                {field: 'tocalPrice', title: '总价格', width: 80},
                                {field: 'number', title: '库存数量', width: 80}
                            ]],
                        })
                    }
                })
            }
        })
    })

    $("#vipDig").dialog({
        width: 700,
        height: 550,
        title:'会员选择',
        toolbar:'#vipbtn',
        closed: true,
    })

    $("#productId").datagrid({
        url:"/shoppingCar/allProduct.do",
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect:true,
        columns: [[
            {title:'商品图片',field:'img',width:100, formatter:function(value,row,index){
                return '<a><img width="130px" height="100px" src="'+row.img+'" /></a>';
            }
            },
            {field: 'name', title: '商品名称', width: 100},
            {field: 'price', title: '售价', width: 100},
            {field: 'number', title: '库存', width: 100}
        ]],
        onClickRow:function (index,row) {
            $.messager.confirm('温馨提示','确认购买此商品么',function (f) {
                if(f){
                    var name=row.name;
                    var img=row.img;
                    var number=row.number;
                    var price=row.price;
                    var pId=row.pid;
                    var depotId=row.depotId;
                    var vipId=1;
                    $.post("/shoppingCar/add.do",{name:name,img:img,number:number,price:price,pId:pId,depotId:depotId,vipId:vipId});
                    $("#lifeTable").datagrid({
                        /*fit: true,*/
                        fitColumns: true,
                        url:"/shoppingCar/selectProduct.do",
                        columns: [[
                            {field: 'name', title: '商品名称', width: 160},
                            {field: 'price', title: '售价', width: 80},
                            {field: 'shoppingnumber', title: '购买数量', width: 80},
                            {field: 'tocalPrice', title: '总价格', width: 80},
                            {field: 'number', title: '库存数量', width: 80}
                        ]],
                    })
                }
            })
        }
    })

    //删除一条商品
    $("#delete").click(function () {
        var row = $("#lifeTable").datagrid("getSelected");
        if(!row) {
            $.messager.alert('温馨提示',"请选择一条数据",'warning');
            return;
        }
        $.messager.confirm('确认','您确认删除这个商品吗?',function(r) {
            if(r) {
                //发送请求修改状态
                $.get("/shoppingCar/delete.do",{name:row.name},function(data) {
                    if(data.success) {
                        $.messager.alert('温馨提示',"操作成功!")
                        $("#lifeTable").datagrid("reload");
                    }else {
                        $.messager.alert('温馨提示',data.msg, 'error');
                    }
                },"json")
            }
        });
    })
    var tr={}

    $("#chencked").click(function () {
        $("#vipDig").dialog("open")
        $("#viptable").datagrid({
            url: "/vip/query.do",
            fit: true,
            fitColumns: true,
            singleSelect:true,
            toolbar: "#vipben",
            columns: [[
                {field: 'vipNumber', title: '会员号码', width: 100},
                {field: 'vipName', title: '会员姓名', width: 100},
                {field: 'vipPhone', title: '会员电话', width: 100},
                {field: 'currentMoney', title: '当前金额', width: 100},
                {field: 'currentpoint', title: '当前积分', width: 100},
            ]],
            onClickRow:function (index,row) {
                $.messager.confirm('温馨提示','选择该会员？',function (f) {
                    if(f){
                        $("#vipDig").dialog("close")
                        $.get("/shoppingCar/vipNumber.do",{vipNumber:row.vipNumber})
                        $.get("/shoppingCar/selectVipProduct.do",{vipNumber:row.vipNumber},function (data) {
                            if (data.success) {
                                $("#lifeTable").datagrid("reload")
                            }else {
                                $.messager.confirm('温馨提示','您之前还挂了单，结账不？',function (f) {

                                    if(!f){
                                        $.get("/shoppingCar/deleteselect.do",{vipNumber:row.vipNumber},function (data) {
                                            $.messager.alert('温馨提示', "已经清空清空!")
                                        })
                                    }else {
                                        $("#lifeTable").datagrid("reload")
                                    }

                                })
                            }
                        })
                        //加一个弹窗，表示之前还有未结账需要一起结账还是重新选择商品
                        tr=row;
                    }
                })
            }
        })
    })

    $("#password").dialog({
        width: 350,
        height: 220,
        title:'输入密码',
        closed:true,
        buttons: "#password_btn"
    })
    $("#getmoney").click(function () {
        $("#passwordForm").form("clear");
        $("#password").dialog("open")
    })

    $("#btns").click(function () {
            $.get("/shoppingCar/selectAllStatis.do", function (data) {
                if (data.success) {
                    $("#passwordForm").form("submit", {
                        url: '/vip/checkPassword.do',
                        onSubmit: function (param) {
                            param["vId"] = tr.id;
                        },
                        success: function (data) {
                            data = $.parseJSON(data)
                            if (data.success) {
                                $.get("/collectMoney/getMoney.do", {vipNumber: tr.vipNumber}, function (data) {
                                    if (data.success) {
                                        $.messager.alert('温馨提示', "操作成功!")
                                        $("#password").dialog("close")
                                         window.location.reload();
                                    } else {
                                        $.messager.alert('温馨提示', data.msg, 'error');

                                    }
                                })
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'error');
                            }
                        }
                    })

                } else {
                    $.messager.alert('温馨提示', data.msg, 'error');
                }

            })

        }
    )

})
