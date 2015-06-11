<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Expression Language语法</title>
</head>
<body>
	JSP2.0中最重要的特性之一是Expression Language(EL),开发者可以使用它访问应用程序数据，
	受到ECMAScript和XPath表达式语言的启发，EL被设计成能够轻松地编写【无脚本】或【不包含Java代码的】JSP页面，
	即不使用JSP声明，表达式或者Scripelet的页面。
	<hr/>
	<ol>
		<li>
			<strong>EL语法</strong><br/>
			<p>EL表达式是以&#36;&#123;开头，以&#125;结束。一个表达式的构造如下：</p>
			<p>&#36;&#123;expression&#125;</p>
			<p>例如，编写表达式x+y时候，要使用下面的结构:</p>
			<p>&#36;&#123;x+y&#125;</p>
			<p>
				两个表达式相连接，这种也很常见。表达式的运算顺序是【从左到右】，【结果的类型强制为String，然后连在一起】。
				例如a+b等于8，c+d等于10,那么下面这两个表达式的结果将是810:
			</p>
			<p>
				&#36;&#123;a+b&#125;&#36;&#123;c+d&#125;
			</p>
			<p>
				如果&#36;&#123;a+b&#125;and&#36;&#123;c+d&#125;结果将是8and10。
			</p>
			<p>
				如果EL表达式用在某个定制标签的属性值中，那么将会运算这个表达式，并强制结果字符串为该属性想要的类型：
			</p>
			<p>
				&lt;my:tag someAttribute="&#36;&#123;expression#125;"/&gt;
			</p>
			<p>
				字符&#36;&#123;表示某一个EL表达式开始了，如果你想要输出字符&#36;&#123;，则必须转换掉第一个字符，如&#92;&#36;&#123;
			</p>
		</li>
		<li>
			<p><strong>保留字</strong></p>
			下面是一些保留字，不能用作标识符：<br/>
			and eq gt true instanceof<br/>
			or ne le false empty<br/>
			not lt ge null dic mod<br/>
		</li>
		<li>
			<p><strong>[]和.运算符</strong></p>
			<p>
				EL表达式可以返回任意类型。如果某个EL表达式的结果对象中有一个属性，那么我们就可以利用[]或者.运算符来访问该属性。
				[]和.运算符功能相似；[]比较常用，但是.比较简洁
			</p>
			<p>要访问某个对象的属性，可以使用以下任意一种形式：</p>
			<p>&#36;&#123;object["propertyName"]&#125;</p>
			<p>&#36;&#123;object.propertyName&#125;</p>
			<p>但是，如果propertyName不是一个有效(合法)的Java变量名，则只能使用[]运算符，例如，下面的两个EL表达式可以用来访问隐式对象标头中的HTTP标头host:</p>
			<p>&#36;&#123;header["host"]&#125;</p>
			<p>&#36;&#123;header.host&#125;</p>
			<p>但是，要访问accept-language标头，则只能使用[]运算符，因为accept-language不是一个合法的java变量名，如果用.运算符来访问它，将会抛出一个异常。</p>
			<p>如果一个对象的额属性碰巧返回的又是一个带有属性的对象，那么就可以利用[]或者.访问第二个对象的属性。例如:</p>
			<p>&#36;&#123;pageContext["request"]["servletPath"]&#125;</p>
			<p>&#36;&#123;pageContext.request["servletPath"]&#125;</p>
			<p>&#36;&#123;pageContext.request.servletPath&#125;</p>
			<p>&#36;&#123;pageContext["request"].servletPath&#125;</p>
			<p>上面这些表达式是相等的，都将在pageContrext中生成servletPath属性值</p>
		</li>
		<li>
			<p><strong>运算规则</strong></p>
			<p>EL表达式的运算顺序是从左到右的。对于像expr-a[expr-b]这种表达式，其EL表达式的运算规则是：</p>
			<p>1，运算expr-a，得到value-a</p>
			<p>2，如果value-a为null，则返回null。</p>
			<p>3，运算expr-b，得到value-b。</p>
			<p>4，如果value-b为null，则返回null。</p>
			<p>5，如果value-a的类型为java.util.Map，就要查看value-b是否为Map中的一个键。如果是，返回value-a.get(value-b);如果不是，则返回null。</p>
			<p>6，如果value-a的类型为java.util.List，或者是一个数组，那么就要:</p>
			<ol>
				<li>a,强制value-b的类型为int。如果强制失败，将抛出一个异常。(很好理解，角标嘛)</li>
				<li>b,如果value-a.get(value-b)抛出一个IndexOutBoundsException，或者假设Array.get(value-a,value-b)抛出一个ArrayIndexOfBoundsException，则返回null。</li>
				<li>c,否则，如果value-a为List，就返回value-a.get(value-b)，如果value-a为数字，则返回Array.get(value-a,value-b)。</li>
			</ol>
			<p>7，如果value-a不是Map，List或数组，value-a就必须是一个JavaBean。在这种情况下，就要强制value-b为String。如果value-b是value-a的一个可读属性，那么将调用该属性的getter方法，并返回来自getter方法的值，如果getter方法抛出异常，则该表达式无效。</p>
			<p><strong>访问JavaBean就可以利用这些规则进行访问。</strong></p>
		</li>
		<li>
			<p><strong>EL隐式对象</strong></p>
			<p>
				我们知道，在JSP页面中，可以利用JSP脚本访问JSP隐式对象。但是在一个无脚本的JSP页面中，则不可能访问这些隐式对象。
				EL通过提供一组它自己的隐式对象，可以帮助我们访问各种对象。EL隐式对象如下表。
			</p>
			<table style="boder:1px solid #eee">
				<tr><td style="text-align:center">对象</td><td style="text-align:center">描述</td></tr>
				<tr><td style="text-align:center">pageContext</td><td style="text-align:center">当前JSP页面的javax.servlet.jsp.PageContext</td></tr>
				<tr><td style="text-align:center">initParam</td><td style="text-align:center">包含所有context初始化参数并以参数名称作为键值的Map</td></tr>
				<tr><td style="text-align:center">param</td><td style="text-align:center">包含所有请求参数并以参数名作为键的Map。要想获取所有同名的参数的值，则要使用params对象</td></tr>
				<tr><td style="text-align:center">paramValues</td><td style="text-align:center">包含所有请求参数兵并以参数名称作为键的Map</td></tr>
				<tr><td style="text-align:center">header</td><td style="text-align:center">包含所有请求标头并以标头名称作为键的Map。想要获得多值标头，则要使用headerValues对象</td></tr>
				<tr><td style="text-align:center">headerValues</td><td style="text-align:center">包含所有请求标头并以标头名称作为键的Map。</td></tr>
				<tr><td style="text-align:center">cookie</td><td style="text-align:center">包含当前请求对象中所有Cookie对象的Map</td></tr>
				<tr><td style="text-align:center">applicationScope</td><td style="text-align:center">包含ServletContext对象中所有属性并以属性名作为键的Map</td></tr>
				<tr><td style="text-align:center">sessionScope</td><td style="text-align:center">包含HttpSession对象中所有属性并以属性名称作为键的Map</td></tr>
			</table>
			<ol>
				<li>
					<p><strong>pageContext</strong></p>
					<p>pageContext对象表示当前JSP页面的javax.servlet.jsp.PageContext。它包含所有其他的JSP隐式对象，request，response，session，out，application，config，pageContext，page，exception。</p>
					<p>例如，可以利用以下任意一种表达式获得当前的ServletRequest对象</p>
					<p>&#36;&#123;pageContext.request&#125;</p>
					<p>&#36;&#123;pageContext["request"]&#125;</p>
				</li>
				<li>
					<p><strong>initParam</strong></p>
					<p>隐式对象initParam用于获取一个context参数值得。如，获取一个context参数password</p>
					<p>&#36;&#123;initParam.password&#125;</p>
					<p>&#36;&#123;initParam["password"]&#125;</p>
				</li>
				<li>
					<p><strong>paramValues</strong></p>
					<p>
						利用隐式对象paramValues可以获取一个请求参数的多个值。这个对象表示一个包含所有请求参数并以参数名称作为键的值。
						每个键的值都是一个字符串数组，其中包含指定参数名称的所有制。如果该参数只有一个值，也依然返回只有一个元素的一个数组。
						例如，要获得selectOptions参数的第一个和第二个值，可以使用如下表达式：
					</p>
					<p>&#36;&#123;paramValues.selectOptions[0]&#125;</p>
					<p>&#36;&#123;paramValues.selectOptions[1]&#125;</p>
				</li>
				<li>
					<p><strong>cookie,header,headerValues的用法都差不多</strong></p>
				</li>
				<li>
					<p><strong>applicationScope,sessionScope,requestScope和pageScope</strong></p>
					<p>利用隐式对象applicationScope获得一个application范围的变量值的。比如我们有一个application范围的变量myValue，就可以利用下面的表达式来访问该属性:</p>
					<p>&#36;&#123;applicationScope.myValue&#125;</p>
					<p>
						注意，在Servlet/JSP编程中，有作用范围的对象是值放在以下这些对象中作为属性的对象，
						PageContext，ServletRequest，HttpSession或者ServletContext
					</p>
					<p>
						有作用范围的对象也可以用一个没有指定范围的EL表达式进行访问。在这种情况下，
						JSP容器将会返回在PageContext，ServletRequest，HttpSession或者ServletContext
						第一次识别到的指定对象。搜索顺序从最小(PageContext)开始，到最大范围(ServletContext)。
						例如，下面的表达式将返回任意范围的today所引用的对象：
					</p>
					<p>&#36;&#123;today&#125;</p>
				</li>
				<li>
					<p><strong>使用其他EL运算符</strong></p>
					<p>
						除了.和[]运算符之外，EL还提供个了几个其他的计算服：算术运算符，关系运算符，逻辑运算符，
						条件运算符及empty运算符。使用这些运算符，可以完成各种运算。但是，由于EL的目的是便于设计
						无脚本的JSP页面，因此这些EL运算符中，除了条件运算符之外，其他的几个的用处都十分有效。
					</p>
				</li>
			</ol>
		</li>
	</ol>
</body>
</html>