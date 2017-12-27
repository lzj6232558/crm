$(function () {
    $(".system").hide();
})
function system() {
    if($(".system").is(":hidden")){
        $(".system").show();
    }else {
        $(".system").hide();     //如果元素为显现,则将其隐藏
    }
}

$(function () {
    $(".vip").hide();
})
function vip() {
    if($(".vip").is(":hidden")){
        $(".vip").show();
    }else {
        $(".vip").hide();     //如果元素为显现,则将其隐藏
    }
}
$(function () {
    $(".product").hide();
})
function product() {
    if($(".product").is(":hidden")){
        $(".product").show();
    }else {
        $(".product").hide();     //如果元素为显现,则将其隐藏
    }
}

$(function () {
    $(".pointex").hide();
})
function pointex() {
    if($(".pointex").is(":hidden")){
        $(".pointex").show();
    }else {
        $(".pointex").hide();     //如果元素为显现,则将其隐藏
    }
}

$(function () {
    $(".depot").hide();
})
function depot() {
    if($(".depot").is(":hidden")){
        $(".depot").show();
    }else {
        $(".depot").hide();     //如果元素为显现,则将其隐藏
    }
}

$(function () {
    $(".chart").hide();
})
function chart() {
    if($(".chart").is(":hidden")){
        $(".chart").show();
    }else {
        $(".chart").hide();     //如果元素为显现,则将其隐藏
    }
}

$(function () {
    $("[data-url]").click(function () {
        var url=$(this).data("url");
        var text=$(this).data("text");
        var ss =$("#tt").tabs("exists",text);

        if(ss){//如果存在就选择此页面
            $("#tt").tabs("select",text);
        }else {
            $("#tt").tabs("add",{
                fit:true,
                title:text,
                content:'<iframe src=http://localhost:80/'+url+' width=100% height=100% frameborder=0></iframe>',
                closable:true
            })
        }
    })
})


