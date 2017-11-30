package molab.main.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Emulator;
import molab.main.java.util.Apptry;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/emulator")
public class EmulatorWeb implements ServletContextAware {

	@RequestMapping(value = "/{id}")
	public ModelAndView load(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("emulator");
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		T_Emulator emulator = new Gson().fromJson(Apptry.ajaxGet(url), T_Emulator.class);
		mav.addObject("emulator", emulator);
		return mav;
	}
	
	@RequestMapping(value = "/loadAllOs")
	public ModelAndView loadAllOs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("snippet/emulator_all_os");
		// parse os list from json object
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		String result = Apptry.ajaxGet(url);
//		String[] oss = result.split(",");
//		List<String> list = new ArrayList<String>();
//		for(String os : oss) {
//			list.add(os);
//		}
		List<String> list = new Gson().fromJson(result, new TypeToken<List<String>>(){}.getType());
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value = "/loadAllDpi")
	public ModelAndView loadAllDpi(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("snippet/emulator_all_dpi");
		// parse dpi list from json object
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		String result = Apptry.ajaxGet(url);
		List<String> list = new Gson().fromJson(result, new TypeToken<List<String>>(){}.getType());
		mav.addObject("list", list);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadEmulatorsDynatree")
	public String loadEmulatorsDynatree(HttpServletRequest request) throws ClientProtocolException, IOException {
		String os = request.getParameter("os");
		String dpi = request.getParameter("dpi");
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		NameValuePair[] params = {
				new NameValuePair("os", os),
				new NameValuePair("dpi", dpi)
		};
		return Apptry.ajaxPost(url, params);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAllServer")
	public String loadAllServer(HttpServletRequest request) {
		return null;
	}
	
	
	@RequestMapping(value = "/create")
	public String createPage() {
		return "create/emulator";
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{serverId}/{serialNumber}/{manufacturer}/{model}/{os}/{width}/{height}")
	public String create(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{serialNumber}/{manufacturer}/{model}/{os}/{width}/{height}")
	public String update(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}
	
	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
