<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网问卷</title>
</head>
<body>

<script type="text/javascript" src="/js/surveys.js"></script>
<div id="survey-list-html-widget"></div>

<script type="text/javascript">
SOP.setProperties({
  app_id  : "${app_id}",
  app_mid : "${app_mid}",
  sig     : "${sig}",
  time    : "${time}"
});
SOP.runHTMLWidget({
  el_id   : "survey-list-html-widget",
  title   : " ",
  width   : "700",
  theme   : "type-4"
});
</script>
</body>
</html>