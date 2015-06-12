<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filter介绍</title>
</head>
<body>
	<ol>
		<li>
			<p>
				过滤器(Filter)是指拦截请求，并对传递给请求资源的ServletRequest或ServletResponse进行处理的一个对象。
				过滤器可以用于登录，加密和解密，会话检查，图片转换等等。过滤器可以配置为拦截一个或多个资源。</p>
			<p>过滤器配置可以通过JPA注解或者Web.xml文件来完成。如果同一个资源或同一组资源中应用了多个过滤器，
				则其调用顺序有时候显得尤为重要，这时候就需要用到web.xml文件来完成了。</p>
		</li>
		<li>
			<p><strong>过滤器的配置/strong></p>
			编写好过滤器类之后，还需要对它进行配置。配置过滤器的目标如下：
			<ol>
				<li>确定过滤器要拦截哪些资源</li>
				<li>要传给过滤器init方法的启动初始值</li>
				<li>给过滤器起个名字。虽然大多数时这个名字似乎没有什么用处，但是有时候还是很有帮助的。例如，有多个过滤器时，可以查看过滤器的调用顺序。</li>
			</ol>
			FilterConfig接口允许通过其getServletContext方法访问ServletContext:
			<p>ServletContex getServletContext()</p>
			如果过滤器有了名字，FilterConfig中的getFilterName方法就会返回它的名称：
			<p>
				String getFilterName()
			</p>
			但是我们最关注的应该是获取初始参数，这是开发者传递给过滤器的初始值。处理初始参数有两个方法，
			其中一个是getInitParameterNames:
			<p>
				java.uti.Enumeration&lt;String&gt; getInitParameterNames();
			</p>
			另一个是getInitParameter:
			<p>
				String getInitParameter(String name)
			</p>
			<hr/>
			配置过滤器有两种方法。一种是李哟个WebFilter注解类型，一种是在web.xml中注册，来配置过滤器。
			使用WebFilter很容器，因为只需要对过滤器进行标注，但是修改的时候需要重新编译。下面是WebFilter的属性：
			<table style="border:1px solid #eee">
				<tr>
					<td style="text-align:center">属性</td>
					<td style="text-align:center">描述</td>
				</tr>
				<tr>
					<td style="text-align:center">asyncSupported</td>
					<td style="text-align:center">指定过滤器是否支持异步操作模式</td>
				</tr>
				<tr>
					<td style="text-align:center">description</td>
					<td style="text-align:center">过滤器的描述</td>
				</tr>
				<tr>
					<td style="text-align:center">dispatcherTypes</td>
					<td style="text-align:center">应用过滤器的dispatcher类型</td>
				</tr>
				<tr>
					<td style="text-align:center">displayName</td>
					<td style="text-align:center">过滤器的显示名称</td>
				</tr>
				<tr>
					<td style="text-align:center">filterName</td>
					<td style="text-align:center">过滤器的名称</td>
				</tr>
				<tr>
					<td style="text-align:center">initParams</td>
					<td style="text-align:center">初始化参数</td>
				</tr>
				<tr>
					<td style="text-align:center">largeIcon</td>
					<td style="text-align:center">过滤器的大图片名称</td>
				</tr>
				<tr>
					<td style="text-align:center">servletNames</td>
					<td style="text-align:center">适用于过滤器的Servlets名称</td>
				</tr>
				<tr>
					<td style="text-align:center">smallIcon</td>
					<td style="text-align:center">过滤器的小图标名称</td>
				</tr>
				<tr>
					<td style="text-align:center">urlPatterns</td>
					<td style="text-align:center">过滤器的应用URL模式</td>
				</tr>
				<tr>
					<td style="text-align:center">value</td>
					<td style="text-align:center">该属性等价于 urlPatterns 属性。但是两者不应该同时使用。</td>
				</tr>
			</table>
		</li>
		<li>
			<p><strong>过滤器的顺序</strong></p>
			<p>如果多个过滤器应用于同一个资源，那么调用顺序就很重要，必须用web.xml来管理先调用哪一个过滤器。
			例如Filter1必须在Filter2之前调用，那么在web.xml文件中，Filter1的声明就要放在Filter2的声明之前。</p>
			<p><strong>如果没有web.xml，是不可能管理过滤器调用顺序的。</strong></p>
		</li>
	</ol>
</body>
</html>