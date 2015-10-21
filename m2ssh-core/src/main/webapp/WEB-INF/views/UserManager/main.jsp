<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body id="main-body" class="easyui-layout"
	style="width: 100%; height: 100%;" data-options="fit:true">
	
	<div data-options="region:'center',border:false,title:''">
		<div id="layout_center_tabs" class="easyui-tabs" style="overflow: hidden;">
	        <div title="百度" fit="true"><iframe frameborder="0" scrolling="no" width="100%" src="http://www.baidu.com" onload="SetCwinHeight(this)"></iframe></div>
        </div>
	</div>
	
	<div id="west" data-options="region:'west',split:true"  style="width:200px;overflow: hidden;">
		<div id="west-panel" class="easyui-panel" data-options="title:'部门管理',fit:true" style="width:200px;overflow: hidden;" border="false">
           <%-- <div id="accordion" class="easyui-accordion" data-options="border:false" >
            <%=sb %>
            </div>--%>
        </div>
	</div>
</body>
</html>