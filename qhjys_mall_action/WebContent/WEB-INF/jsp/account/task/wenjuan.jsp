<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 体验赚钱-飞券-答题赚钱-网赚-游戏赚钱-飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/index2.css" rel="stylesheet" type="text/css" />
<link href="/css/taskdetail.css" rel="stylesheet" type="text/css">

</head>
<body>
<!--问卷调查须知-s-->
<div class="global">
	<div class="top1"><img src="/images/task-wj01.jpg"></div>
    <div class="task-xuzhi">
      <div class="infobox">
        <h1>以下内容与您填写调查问卷成功率息息相关，请仔细查看！</h1>
           ${task.taskDetail }
          
        	<div class="answerbutton"><a target="__blank" href="${task.projectUrl }${userId}">立即答题</a></div>
      </div>
    </div>
    <div class="top1"><img src="/images/task-wj02.jpg"></div>
    <div class="top1"><img src="/images/task-wj03.jpg"></div>   
</div>
</body>
</html>