package molab.main.java.service;

import molab.main.java.entity.T_Product;
import molab.main.java.util.Apptry;

import com.google.gson.Gson;

public class ProductService {

	public static T_Product load(int id) {
		String url = Apptry.getDatabaseHost().concat("/product/").concat(String.valueOf(id)).concat("/");
		String json = Apptry.ajaxGet(url);
		if(json != null && json.length() > 0) {
			T_Product product = new Gson().fromJson(json, T_Product.class);
			return product;
		}
		return null;
	}
}
