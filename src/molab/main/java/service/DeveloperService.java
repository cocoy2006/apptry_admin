package molab.main.java.service;

import org.apache.commons.httpclient.NameValuePair;

import molab.main.java.entity.T_Developer;
import molab.main.java.util.Apptry;

import com.google.gson.Gson;

public class DeveloperService {
	
	public static T_Developer load(int id) {
		String url = Apptry.getDatabaseHost().concat("/user/").concat(String.valueOf(id)).concat("/");
		String json = Apptry.ajaxGet(url);
		if(json != null && json.length() > 0) {
			T_Developer user = new Gson().fromJson(json, T_Developer.class);
			return user;
		}
		return null;
	}

	public static String update(T_Developer user) {
		String url = Apptry.getDatabaseHost().concat("/user/update/");
		NameValuePair[] params = {
				new NameValuePair("id", String.valueOf(user.getId())),
				new NameValuePair("leftClicks", String.valueOf(user.getLeftClicks()))
		};
		return Apptry.ajaxPost(url, params);
	}
}
