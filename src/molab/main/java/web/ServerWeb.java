package molab.main.java.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Server;
import molab.main.java.util.Apptry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/server")
public class ServerWeb implements ServletContextAware {

	@RequestMapping(value = "/{id}")
	public ModelAndView server(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("server");
		String url = Apptry.getDatabaseHost().concat("/server/" + id);
		T_Server server = new Gson().fromJson(Apptry.ajaxGet(url), T_Server.class);
		mav.addObject("server", server);
		return mav;
	}
	
	@RequestMapping(value = "/create")
	public String createPage() {
		return "create/server";
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{ipAddress}/{port}")
	public String create(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{ipAddress}/{port}")
	public String update(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}

	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
