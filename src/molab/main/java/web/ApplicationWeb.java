package molab.main.java.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import molab.main.java.entity.T_Application;
import molab.main.java.util.Apptry;
import molab.main.java.util.fileupload.FileUploadListener;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/application")
public class ApplicationWeb implements ServletContextAware {

	@RequestMapping(value = "/{id}")
	public ModelAndView emulator(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("application");
		String url = Apptry.getDatabaseHost().concat("/application/" + id);
		T_Application application = new Gson().fromJson(Apptry.ajaxGet(url), T_Application.class);
		mav.addObject("application", application);
		return mav;
	}
	
	@RequestMapping(value = "/create")
	public String createPage() {
		return "create/application";
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{name}/{packageName}/{startActivity}")
	public String create(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{name}/{packageName}/{startActivity}")
	public String update(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload")
	public String upload(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
			// force set percentDone to 100%
			FileUploadListener listener = (FileUploadListener) session.getAttribute("uploadProgressListener");
			if(listener != null) {
				listener.setPercentDone(100);
			}
			// create application in database and return applicationId.
			T_Application application = Apptry.parseApplication(file);
			String url = Apptry.getDatabaseHost().concat("/application/create/");
			NameValuePair[] params = {
					new NameValuePair("md5", application.getMd5()),
					new NameValuePair("name", application.getName()),
					new NameValuePair("aliasName", application.getAliasName()),
					new NameValuePair("size", String.valueOf(application.getSize())),
					new NameValuePair("packageName", application.getPackageName()),
					new NameValuePair("version", application.getVersion()),
					new NameValuePair("os", application.getOs()),
					new NameValuePair("startActivity", application.getStartActivity())
			};
			String result = Apptry.ajaxPost(url, params);
			int applicationId = Integer.parseInt(result);
			if (applicationId != -1) {
				session.setAttribute("applicationId", applicationId);
				session.setAttribute("applicationAliasName", application.getAliasName());
			}
			return String.valueOf(applicationId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/percentDone", method = RequestMethod.GET)
	public String loadPercent(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "-2";
		}
		FileUploadListener listener = (FileUploadListener) session.getAttribute("uploadProgressListener");
		if(listener == null) {
			return "-1";
		}
		double percent = listener.getPercentDone();
		return String.valueOf(percent);
	}
	
	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
