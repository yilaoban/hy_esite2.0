package com.huiyee.weixin.model.template;

import java.lang.reflect.Field;

import net.sf.json.JSONObject;

public class BaseTemplate {

	public String toData() {
		return toData("#173177");
	}

	public String toData(String color) {
		JSONObject json = new JSONObject();
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);// need access to private field

				String name = field.getName();
				Object value = field.get(this);
				if (value != null) {
					JSONObject obj = new JSONObject();
					obj.put("value", value);
					obj.put("color", color);
					json.put(name, obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

}
