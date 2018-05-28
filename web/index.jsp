<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>语音播放</title>
  </head>
  <script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
      function play(){
          var text = $("#text").val();
          if(text){
              $.ajax({
                  type: "POST",
                  url: '<%=basePath%>voice/compound',
                  data: {
                      text:text
                  },
                  dataType:'json',
                  cache: false
              });
          }else {
              alert("请输入内容");
          }
      }
  </script>
  <body>
  <div style="width: 100%;margin: 0 auto;">
    <h1 style="text-align: center">语音播放</h1>
  </div>
  <div style="width: 800px;height: 500px;margin: 0 auto;">
    <textarea id="text" style="width: 100%;height: 100%"></textarea>
  </div>
  <div align="center">
    <input style="" type="button" onclick="play()" value="提交语音并播放" />
  </div>
  </body>
</html>
