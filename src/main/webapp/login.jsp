<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
  body {
    font-family: '微软雅黑';
  }
</style>

<script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript">
    function login() {
        $.post("/login.do",$("form").serialize(),function (data) {
            if(data.success){
                window.parent.location.href="/index.do";
            }else{
                alert(data.msg);
            }
        },'json')
    }
</script>
<head>

  <title>登录</title>

  <!-- Meta-Tags -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
  <link rel="stylesheet" href="/static/css/css/style.css" type="text/css" media="all">
</head>

<body>

<h1>会员登录</h1>

<div class="container w3layouts agileits" style="height: 200px;width: 250px">

  <div>
    <form  method="post">
      <input type="text" name="username" placeholder="用户名" >
      <input type="password" name="password" placeholder="密码" >
    </form>
    <ul class="tick w3layouts agileits">
      <li>
        <input type="checkbox" id="brand1" value="">
      </li>
    </ul>
    <div class="send-button w3layouts agileits">
      <form>
        <input type="button" value="登 录" onclick="login()">
      </form>
    </div>
    <div class="social-icons w3layouts agileits">
      <p>- 其他方式登录 -</p>
      <ul>
        <li class="qq"><a href="http://w.qq.com/">
          <span class="icons w3layouts agileits"></span>
          <span class="text w3layouts agileits">QQ</span></a></li>
        <li class="weixin w3ls"><a href="https://wx.qq.com/">
          <span class="icons w3layouts"></span>
          <span class="text w3layouts agileits">微信</span></a></li>
        <li class="weibo aits"><a href="https://login.sina.com.cn/signup/signin.php">
          <span class="icons agileits"></span>
          <span class="text w3layouts agileits">微博</span></a></li>
        <div class="clear"> </div>
      </ul>
    </div>
    <div class="clear"></div>
  </div>
</div>