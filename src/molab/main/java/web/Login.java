package molab.main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// database handle
		request.getSession().setAttribute("username", username);
		
		modelMap.put("username", username);
		modelMap.put("password", password);
		return new ModelAndView("welcome", modelMap);
	}
}
