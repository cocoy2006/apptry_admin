package molab.main.java.web;

import javax.servlet.ServletContext;

import molab.main.java.util.Apptry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

@Controller
public class Index implements ServletContextAware {

	@RequestMapping(value = "/")
	public String main() {
		return "home";
	}
	
	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/recharge")
	public String recharge() {
		return "recharge";
	}
	
	@RequestMapping(value = "/emulator")
	public String emulator() {
		return "emulator";
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value = "/tree")
	public String tree() {
		return "tree";
	}
	
	@ResponseBody
	@RequestMapping(value = "/treeJson")
	public String treeJson() {
		String url = Apptry.getDatabaseHost().concat("/loadDynatree");
		return Apptry.ajaxGet(url);
	}
	
	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
