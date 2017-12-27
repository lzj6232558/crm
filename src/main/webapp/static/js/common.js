$(function () {
    $("#myTree").tree({
        url:'/menu/getRootMenu.do',
        onLoadSuccess:function(){
            $("#tree").tree('collapseAll');
        },
        onClick:function (node) {
            if(node.url){
                var tag =$("#myTabs").tabs("exists",node.text);
                if(tag){//如果存在就选择此页面
                    $("#myTabs").tabs("select",node.text);
                }else {
                    $("#myTabs").tabs("add",{
                        fit:true,
                        title:node.text,
                        content:'<iframe src=http://localhost:/'+node.url+' width=100% height=100% frameborder=0></iframe>',
                        closable:true
                    })
                }
            }else{
                $("#myTree").tree("toggle",node.target);
            }
        }
    });
})