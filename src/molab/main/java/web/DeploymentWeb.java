package molab.main.java.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import molab.main.java.util.Apptry;
import molab.main.java.util.Status;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

@Controller
@RequestMapping(value = "/deployment")
public class DeploymentWeb implements ServletContextAware {
	
	@ResponseBody
	@RequestMapping(value = "/install")
	public String install(HttpServletRequest request) throws IOException {
		String[] device2s = request.getParameterValues("device2s");
		HttpSession session = request.getSession(false);
		int applicationId = Integer.parseInt(session.getAttribute("applicationId").toString());
		String applicationAliasName = session.getAttribute("applicationAliasName").toString();
		String filePath = Apptry.getApkDirectory().concat(applicationAliasName);
		for(String emulatorString : device2s) {
			String[] emulatorInfo = emulatorString.split("/");
			int emulatorId = Integer.parseInt(emulatorInfo[0]);
			String serialNumber = emulatorInfo[2];
			String url = "http://".concat(emulatorInfo[1]).concat("/apptryADB/install");
			String result = Apptry.ajaxMultipart(url, new String[]{filePath}, new String[]{serialNumber});
			switch(Integer.parseInt(result)) {
				case Status.SUCCESS:
					// write deployment to database
					String url2 = Apptry.getDatabaseHost().concat("/deployment/create/" + emulatorId + "/" + applicationId);
					Apptry.ajaxGet(url2);
				default:
					break;
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{emulatorId}/{applicationId}")
	public String create(HttpServletRequest request) {
		String url = Apptry.getDatabaseHost().concat(request.getServletPath());
		return Apptry.ajaxGet(url);
	}
	
	public void setServletContext(ServletContext sc) {
		Apptry.setServletContext(sc);
	}
}
