<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<link href="${pageContext.request.contextPath }/styles/style.css"
	rel="stylesheet" type="text/css" />
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
</head>
<body style="margin: 3px;">
	<div id="main">
		<div class="top">添加用户</div>
		<div class="content">
			<form>
				<div class="contentNew">
					<div class="con_1 clearfix">
						<div class="con_1_l">用户账号:</div>
						<div class="con_1_r">
							<div>   
						        <label for="name">Name:</label>   
						        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
						    </div>  
						</div>
					</div>
					<div class="con_2 clearfix">
						<div class="con_2_l">是否完成：</div>
						<div class="con_2_r">
							<select>
								<option>完成</option>
								<option>未完成</option>
							</select>
						</div>
					</div>
					<div class="con_3 clearfix">
						<div class="con_3_l">录入时间：</div>
						<div class="con_3_r">
							<input type="text" name="" value="" />
						</div>
					</div>
					<div class="con_4 clearfix">
						<div class="con_4_l">完成情况反馈：</div>
						<div class="con_4_r">
							<textarea cols="30" rows="8"></textarea>
						</div>
					</div>
					<div class="con_5">
						<input type="submit" name="" value="添加" /><input type="submit"
							name="" value="修改" /><input type="submit" name="" value="删除" />
					</div>
				</div>
			</form>
		</div>
		<div class="footer"></div>
	</div>
</body>
</html>