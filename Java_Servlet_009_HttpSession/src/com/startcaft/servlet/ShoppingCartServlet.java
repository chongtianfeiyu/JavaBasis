package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.startcaft.model.Product;
import com.startcaft.model.ShoppingItem;

/**
 * 在所有的Session追踪技术中，HttpSession对象是最强大的，也是功能最多的。
 * 用户可以没有或者有一个HttpSession，并且只能访问属于自己的HttpSession。
 * 
 * HttpSession是当一个用户第一次访问网站时自动创建的，通过request.getSeesion()方法
 * 可以获取用户专属的HttpSession对象。getSession方法有两个重载 1,HttpSession
 * getSession()------返回当前用户的HttpSession,如果没有则创建一个并返回。
 * 2,HttpSession.getSession(boolean
 * create)------返回当前HttpSession，若false且HttpSession对象没有，则返回null。
 * 若参数true且没有HttpSession对象，则创建一个并返回。
 * 
 * HttpSession的setAttribute方法将一个值放在HttpSession对象中，其方法签名如下： void
 * setAttribute(String name,Object value);
 * 
 * 注意：HttpSession的值是保存在内存中的，与URL重写，隐藏域和Cookie不同。
 * 因此，尽可能的将小的对象存放在HttpSession中。虽然现在的Servlet容器可以在内存将满时
 * 把HttpSession中的对象转移到辅助存储设备中，但这样会影响性能。
 * 
 * 添加到HttpSession对象中的值可以是任意Java对象，只要它的类实现了java.io.Serializable接口即可。
 * 为什么要实现Serializable接口呢？ 因为Servlet容器认为在有必要的时候，保存的对象可以序列化成一个硬盘文件或者保存到数据库中。
 * 所以必须实现Serializable接口，这个接口只是java中的标示接口，表示实现该接口的对象可以被序列化。
 * 如果不实现这个接口，Servlet容器在执行对象序列化时会抛出异常。
 * 
 * 通过HttpSession对象的getAttribute，传递一个属性名称，可以获取保存的对象，其方法签名如下： Object
 * getAttribute(String name);
 * 
 * 还有一个获取所有属性集合的方法getAttributeNams,它返回一个Enumeration，其方法签名如下：
 * java.util.Enumeration<String> getAttributeNames();
 * 
 * 
 * 注意：HttpSession中保存的值不能发送到客户端，Servlet容器会为每个用户的HttpSession
 * 生成一个唯一的标识符，并将这个标识符作为一个token发送给浏览器，一般是作为一个名为
 * JSESSIONID的cookie，或者作为一个jsessionid参数添加到URL后面。
 * 在用户后续的请求中，浏览器会将这个token发送会服务器，使得服务器能够知道是哪个用户在发出请求。
 * 无论Servlet容器选择哪一种方式传输session标识符，该动作都是在后台自动完成的，不需要我们做额外的处理工作。
 * 通过在HttpSession中调用getId方法，可以获取HttpSession的标识符： String getId();
 * 
 * 
 * HttpSession中还定义了一个invalidate方法，强制Session过期，并将绑定到该HttpSession
 * 的所有对象进行解除绑定，在默认情况下，HttpSession是在用户静默一定时间之后过期。
 * 可以在web.xml的session-timeout元素中设置Session的期限。如果没有配置该元素，这个期限由
 * Servlet容器决定【该期限是从用户的最后一次访问开始计算的】
 * 
 * 可以调用getMaxInactiveInterval方法，来查看一个HttpSession在用户最后一次访问 之后还可以维持多久(描述)。
 * setMaxInactiveInterval方法可以设置HttpSession的期限值。如果传递0，那么
 * HttpSession将永远不会过期【不要这样做，因为HttpSession占用的堆空间将永远不会释放，知道应用程序卸载或容器关闭】
 * 
 * @author wow
 *
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = { "/products",
		"/viewProductDetails", "/addToCart", "/viewCart" })
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CART_ATTRIBUTE_STRING = "cart";

	private List<Product> products = new ArrayList<Product>();
	private NumberFormat currencyFormat = NumberFormat
			.getCurrencyInstance(Locale.CHINA);

	@Override
	public void init() throws ServletException {
		products.add(new Product(1, "iphone 6 plus",
				"苹果公司出品的最新版本的手机，IOS系统为8.2", 359.95F));
		products.add(new Product(2, "iphone AV适配器", "苹果公司配套的iphone电源适配器",
				30.95F));
		products.add(new Product(3, "iphone 钢化膜", "手机屏幕膜", 159.95F));
		products.add(new Product(4, "iphone 耳机线", "苹果公司配套的iphone耳机线", 159.95F));
	}

	public ShoppingCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		if (uri.endsWith("/products")) {
			sendProductList(response);
		} else if (uri.endsWith("/viewProductDetails")) {
			sendProductDetails(request, response);
		} else if (uri.endsWith("/viewCart")) {
			showCart(request, response);
		}
	}

	private void showCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		//设置响应的编码格式utf-8。
		response.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>Shopping Cart</title>"
				+ "</head>");
		writer.println("<body><a href='products'>" + 
				"Product List</a>");
		//获取Session，取出当前用户的购物车对象
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<ShoppingItem> cart = (List<ShoppingItem>)session.getAttribute(CART_ATTRIBUTE_STRING);
		
		if (cart != null) {
			writer.println("<table>");
			writer.println("<tr><td style='width:150px'>Quantity"
					+ "</td>"
					+ "<td style='width:150px'>Product</td>"
					+ "<td>Amount</td></tr>");
			double total = 0.0;
			for (ShoppingItem shoppingItem : cart) {
				Product product = shoppingItem.getProduct();
				int quantity = shoppingItem.getQuantity();
				if (quantity != 0) {
					float price = product.getPrice();
					writer.println("<tr>");
					writer.println("<td>" +quantity + "</td>");
					writer.println("<td>" + product.getName()
							+ "</td>");
					writer.println("<td>" + currencyFormat.format(price)
							+ "</td>");
					double subTotal = price * quantity;
					writer.println("<td>"
							+ currencyFormat.format(subTotal)
							+ "</td>");
					total += subTotal;
					writer.println("</tr>");
				}
			}
			writer.println("<tr><td colspan='4' "
					+ "style='text-align:right'>"
					+ "Total:"
					+ currencyFormat.format(total)
					+ "</td></tr>");
			writer.println("</table>");
		}
		writer.println("</table></body></html>");
	}

	private void sendProductDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		//设置响应的编码格式utf-8。
		response.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		int productId = 0;
		try {
			productId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		Product currentProduct = getProduct(productId);
		
		if (currentProduct != null) {
			writer.println("<html><head>"
					+ "<title>Product Details</title></head>"
					+ "<body><h2>Product Details</2>"
					+ "<form method='post' action='addToCart'>");
			writer.println("<input type='hidden' name='id' "
					+ "value='"+ productId +"'/>");
			writer.println("<table>");
			writer.println("<tr><td>Name:</td><td>"
					+ currentProduct.getName() + "</td></tr>");
			writer.println("<tr><td>Description:</td><td>"
					+ currentProduct.getDescription() + "</td></tr>");
			writer.println("<tr>" + "<tr>"
					+ "<td><input name='quantity'/></td>"
					+ "<td><input type='submit' value='Buy'/>"
					+ "</td>"
					+ "</tr>");
			writer.println("<tr><td colspan='2'>"
					+ "<a href='products'>Product List</a>"
					+ "</td></tr>");
			writer.println("</table>");
			writer.println("</form></body></html>");
		}
		else {
			writer.println("No product found");
		}
	}

	private Product getProduct(int productId) {
		for (Product product : products) {
			if (product.getId() == productId) {
				return product;
			}
		}
		return null;
	}

	private void sendProductList(HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		//设置响应的编码格式utf-8。
		response.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>Products</title>"
				+ "</head><body><h2>Products</h2>");
		writer.println("<ul>");
		for (Product product : products) {
			writer.println("<li>" + product.getName() + "("
					+ currencyFormat.format(product.getPrice())
					+ ") (" + "<a href='viewProductDetails?id=" + product.getId()
					+ "'>Details</a>)");
		}
		writer.println("</ul>");
		writer.println("<a href='viewCart'>View Cart</a>");
		writer.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//add to cart
		int productId = 0;
		int quantity = 0;
		try {
			productId = Integer.parseInt(request.getParameter("id"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (NumberFormatException e) {

		}
		
		Product currentProduct = getProduct(productId);
		if (currentProduct != null && quantity >= 0) {
			ShoppingItem item = new ShoppingItem(currentProduct, quantity);
			
			//获取HttpSession对象
			HttpSession session = request.getSession();
			//看看有没有List<ShoppingItem>集合，如果没有则创建一个并绑定到HttpSession中，并把当前购买的Product和quantity添加到集合中。
			@SuppressWarnings("unchecked")
			List<ShoppingItem> cart = (List<ShoppingItem>)session.getAttribute(CART_ATTRIBUTE_STRING);
			if (cart == null) {
				cart = new ArrayList<ShoppingItem>();
				session.setAttribute(CART_ATTRIBUTE_STRING, cart);
			}
			cart.add(item);
		}
		sendProductList(response);
	}
}
