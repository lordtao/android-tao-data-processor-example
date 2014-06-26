package ua.at.tsvetkov.dataprocessor.example;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {

	public String	nm;
	public String	cty;
	public String	hse;
	public String	yrs;

	public Item(JSONObject obj) throws JSONException {
			nm = obj.getString("nm");
			cty = obj.getString("cty");
			hse = obj.getString("hse");
			yrs = obj.getString("yrs");
	}

}
