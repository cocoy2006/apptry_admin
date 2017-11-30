package molab.main.java.service;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Order;
import molab.main.java.util.Apptry;
import molab.main.java.util.Status;

import org.apache.commons.httpclient.NameValuePair;

public class OrderService {

	public static T_Order create(HttpServletRequest request) {
		T_Order order = new T_Order();
		long orderId = Long.parseLong(Apptry.randomOrderId());
		order.setId(orderId);
		order.setProduct_id(Integer.parseInt(request.getParameter("productId")));
		order.setDeveloper_id(Integer.parseInt(request.getParameter("developerId")));
		order.setTime(System.currentTimeMillis());
		order.setState(Status.ORDER_SUCCESS);
		String url = Apptry.getDatabaseHost().concat("/order/create/");
		NameValuePair[] params = {
				new NameValuePair("id", String.valueOf(order.getId())),
				new NameValuePair("productId", String.valueOf(order.getProduct_id())),
				new NameValuePair("developerId", String.valueOf(order.getDeveloper_id())),
				new NameValuePair("time", String.valueOf(order.getTime())),
				new NameValuePair("state", String.valueOf(order.getState()))
		};
		String id = Apptry.ajaxPost(url, params);
		if(orderId == Long.parseLong(id)) {
			return order;
		}
		return null;
	}
}
