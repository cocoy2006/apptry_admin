package molab.main.java.web;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Developer;
import molab.main.java.entity.T_Order;
import molab.main.java.entity.T_Product;
import molab.main.java.service.DeveloperService;
import molab.main.java.service.OrderService;
import molab.main.java.service.ProductService;
import molab.main.java.util.Apptry;
import molab.main.java.util.Constants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/order")
public class OrderWeb implements ServletContextAware {

	@RequestMapping(value = "/loadAll/{developerId}/")
	public ModelAndView server(@PathVariable int developerId) {
		ModelAndView mav = new ModelAndView("snippet/recharge_order_detail");
		String url = Apptry.getDatabaseHost().concat("/order/loadAll/" + developerId + "/");
		List<T_Order> list = new Gson().fromJson(Apptry.ajaxGet(url), new TypeToken<List<T_Order>>(){}.getType());
		if(list.size() > 0) {
			Map<String, T_Order> orders = new TreeMap<String, T_Order>();
			for(T_Order order : list) {
				long time = order.getTime();
				orders.put(Apptry.getLocaleTimestamp(time), order);
			}
			mav.addObject("orders", orders);
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/")
	public String create(HttpServletRequest request) {
		// build order and return order_id
		T_Order order = OrderService.create(request);
		if(order != null) {
			int productId = order.getProduct_id();
			T_Product product = ProductService.load(productId);
			int developerId = order.getDeveloper_id();
			T_Developer user = DeveloperService.load(developerId);
			user.setLeftClicks(user.getLeftClicks() + product.getClicks());
			DeveloperService.update(user);
			return new Gson().toJson(Constants.OK);
		}
		return "";
	}
	
	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
