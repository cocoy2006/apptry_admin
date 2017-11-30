package molab.main.java.web;

import javax.servlet.ServletContext;

import molab.main.java.entity.T_Developer;
import molab.main.java.util.Apptry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/developer")
public class DeveloperWeb implements ServletContextAware {

	@RequestMapping(value = "/{username}/")
	public ModelAndView server(@PathVariable String username) {
		ModelAndView mav = new ModelAndView("snippet/recharge_developer_detail");
		String url = Apptry.getDatabaseHost().concat("/user/" + username);
		T_Developer developer = new Gson().fromJson(Apptry.ajaxGet(url), T_Developer.class);
		mav.addObject("developer", developer);
		return mav;
	}
	
	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
