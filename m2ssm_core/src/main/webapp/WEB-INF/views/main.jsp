<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理首页</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/easyui/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/locale/easyui-lang-zh_CN.js"></script>
	
<!-- 自定义脚本 -->
<script type="text/javascript">
	//打开一个模态窗口
	var win;
	function OpenWin(title,width,height,url)
	{
	    if(win ==undefined){
	        win =$('<div id=\"winOpen\"></div>');
	        win.window({
	        title:title,
	        height:height,
	        width:width,
	        collapsible:false,
	        minimizable:false,
	        maximizable:false,
	        closed:true,
	        modal:true,
	        resizable:true,
	        draggable:true,
	        loadingMessage: '正在加载数据，请稍等片刻......',
	        content:'<iframe src="'+url+'" frameborder="0" style="boder:0;width:100%;height:99%;"></iframe>',
	        onClose:function(){  
	           $(this).window('destroy');
	           win=undefined;
	        } 
	    });
	    }    
	     return win;
	}
	//关闭模态窗口
	function CloseWin() {
        win.window('close');
    }
	//打开一个tabs选项卡窗口
	function CenterContent(title,url){
	    var t = $('#layout_center_tabs');
	    if (t.tabs('exists', title)) {
		    t.tabs('select', title);
	    } else {
		    t.tabs('add', {title:title,content:'<iframe src="'+url+'" frameborder="0" style="boder:0;width:100%;height:100%;"></iframe>',closable:true,fit:true});
	    }
	}
	//初始化
	$(function(){
		$('#layout_center_tabs').tabs({
			fit : true,
			border : false
		});
	});
</script>
</head>
<body class="easyui-layout" style="margin: 3px; background-color: #eee">
	<div region="north" split="false" collapsible="false"  title="页头">
	</div>
	<div region="south" split="false" collapsible="false" title="页脚">
	</div>
	<div region="west" split="true" title="系统菜单" style="width: 250px;">
	</div>
	<div region="center" style="padding: 3px;background-color: #eee">
		<div id="layout_center_tabs" class="easyui-tabs" style="overflow: hidden;" fit="true">
            <div title="用户部门管理">
                <iframe frameborder="0" src="${pageContext.request.contextPath }/userDepart/index" 
                style="border: 0; width: 100%; height: 99%"></iframe>
            </div>
        </div>
	</div>
</body>
</html>