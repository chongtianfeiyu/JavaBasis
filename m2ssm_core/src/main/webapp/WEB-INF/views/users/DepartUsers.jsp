<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门用户管理</title>
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

<!-- zTree相关 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ztree/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ztree/js/jquery.ztree.core-3.5.min.js"></script>

<!-- 自定义脚本 -->
<script type="text/javascript">
	var zTree;
	var userGrid;
	var setting = {
		//获取json数据  
        async : {    
            enable : true,
            type: "get",
            url : "getAllDepart/2"
        },
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : 0
			},
			key: {
				name: "departName"
			}
		},
		callback:{
			onClick:zTreeOnClick
		}
	};
	$(document).ready(function() {
		loadZtree();
	});
	//构建部门树
	function loadZtree(){
		zTree = $.fn.zTree.init($("#departTree"), setting);
	}
	//节点单击事件
	function zTreeOnClick(event, treeId, treeNode){
		var url = "getUserPage/" + treeNode.id;
		var title = "[" + treeNode.departName + "]用户列表";
		initDataGri(url,title);
	}
	//构建部门用户列表
	function initDataGri(url,title){
		userGrid = $("#userGrid").datagrid({
	        url : url,
	        title:title,
	        idField:'id',
	        singleSelect : true, 	/*是否选中一行*/
	        pagination : true,		/*是否显示下面的分页菜单*/
	        pageNumber: 1,
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50],
	        border:false,
	        fit:true,				/*表格自适应*/
	        rownumbers:true,
	        fitColumns:true,		/*自适应列宽*/
	        nowrap:true,
	        toolbar: '#toolbar',
	        columns:[[  
	            {field:'id',title: '主键编号',align: 'center',hidden:true},    
	            {field:'loginName',title: '用户账号',align: 'center',width: 150},                                                         
	            {field:'nickName',title: '用户昵称',align: 'center',width: 150},                                                         
	            {field:'createTime',title: '创建时间',align: 'center',width: 200},                                                     
	            {field:'userEmail',title: '电子邮件',align: 'center',width: 300},                                                     
	            {field:'isEnable',title: '是否可用',align: 'center',width: 100},                                                     
	        ]] 
	    });
	}
	function doSearch(){
		var loginName = $('#loginName').searchbox('getValue');
		userGrid.datagrid('load',{
			loginName:loginName
		});
	}
	function Add() {
        var win = parent.OpenWin('添加用户', '800', '600', '${pageContext.request.contextPath}/userDepart/user/add');
        win.window('open');
    }
	function Delete() {
		var rows = userGrid.datagrid('getSelections');
        if (rows.length == 0) {
            $.messager.alert("提示", "请选择要删除的数据!", 'info');
        } else {
            var url = "${pageContext.request.contextPath}/userDepart/deleteUser/" + rows[0].id;
            //alert(url);
            $.get(url, function(result){
            	$.messager.alert("提示", result.msg, 'info');
            	userGrid.datagrid('reload');
            });
        }
	}
</script>
</head>
<body>
<body class="easyui-layout" style="margin: 3px; background-color: #eee" fit="true">
	<div region="west" split="true" title="部门管理" iconCls="icon-company"
		style="width: 250px;">
		<div align="center" style="padding: 5px;">
			<ul id="departTree" class="ztree"></ul>
		</div>
	</div>
	<div region="center" title="用户管理" iconCls="icon-emp"
		style="padding: 5px;background-color: #eee">
		 <table id="userGrid" style="height:auto;width:auto" ></table>
		 
		 <!-- 工具栏 -->
		 <div id="toolbar" style="padding:2px 0;">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="text-align:left;padding-right:2px">
						<input id="loginName" class="easyui-searchbox"  
	            				searcher="doSearch" prompt="请输入用户账号" name="loginName"></input>
					</td>
					<td style="text-align:right;padding-right:2px">
						<a href="#" id="btnAdd" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="Add();">添加用户</a> 
	            		<a href="#" id="btnEdit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="Edit();">编辑用户</a> 
                		<a href="#" id="btnDelete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="Delete();">删除用户</a> 
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>