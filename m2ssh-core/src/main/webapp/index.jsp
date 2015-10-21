<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>框架首页</title>
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
<script type="text/javascript">
	//iframe 自适应高度
	function SetCwinHeight(iframeObj) {
		if (document.getElementById) {
			if (iframeObj && !window.opera) {
				if (iframeObj.contentDocument
						&& iframeObj.contentDocument.body.offsetHeight) {
					iframeObj.height = iframeObj.contentDocument.body.offsetHeight;
				} else if (document.frames[iframeObj.name].document
						&& document.frames[iframeObj.name].document.body.scrollHeight) {
					iframeObj.height = document.frames[iframeObj.name].document.body.scrollHeight;
				}
			}
		}
	}
	//刷新当前的tabs
	function refrshTabs() {
		//获取当前选中正在操作的tabs;
		var tab = $('#layout_center_tabs').tabs('getSelected');
		var iframe = tab.find("iframe");
		var url = iframe.attr("src");
		iframe.attr("src", url);
	}
	//创建一个模态窗口
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
	//关闭一个模态窗口
	function CloseWin(win) {
		if(win != null && win != undefined){
	        win.window('close');
	        refrshTabs();
		}
    }
</script>
</head>
<body id="main-body" class="easyui-layout"
	style="width: 100%; height: 100%;" data-options="fit:true">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px">north
		region</div>
	<div id="west" data-options="region:'west',split:true"
		style="width: 200px; overflow: hidden;">
		<div id="west-panel" class="easyui-panel"
			data-options="title:'XXXXX菜单',fit:true"
			style="width: 200px; overflow: hidden;" border="false">
			<%-- <div id="accordion" class="easyui-accordion" data-options="border:false" >
            <%=sb %>
            </div>--%>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height: 50px; background: #A9FACD; padding: 10px;">south
		region</div>
	<div data-options="region:'center',border:false,title:''">
		<div id="layout_center_tabs" class="easyui-tabs"
			style="overflow: hidden;">
			<div title="百度" fit="true" href="user/index">
				<!--  
				<iframe frameborder="0" scrolling="no" width="100%"
					src="http://www.baidu.com" onload="SetCwinHeight(this)"></iframe>
				-->
			</div>
		</div>
	</div>
</body>
</html>