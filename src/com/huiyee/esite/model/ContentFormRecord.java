package com.huiyee.esite.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.huiyee.interact.appointment.model.AptMapping;

public class ContentFormRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1047735793482656195L;
	private long id;
	private long formid;
	private String title;
	private String detail;
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private String f5;
	private String f6;
	private String f7;
	private String f8;
	private String f9;
	private String f10;
	private String f11;
	private String f12;
	private String f13;
	private String f14;
	private String f15;
	private String f16;
	private String f17;
	private String f18;
	private String f19;
	private String f20;
	private long lbsid;
	private List<String> list;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFormid() {
		return formid;
	}

	public void setFormid(long formid) {
		this.formid = formid;
	}

	private void init() {
		System.out.println(123);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	public String getF5() {
		return f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
	}

	public String getF6() {
		return f6;
	}

	public void setF6(String f6) {
		this.f6 = f6;
	}

	public String getF7() {
		return f7;
	}

	public void setF7(String f7) {
		this.f7 = f7;
	}

	public String getF8() {
		return f8;
	}

	public void setF8(String f8) {
		this.f8 = f8;
	}

	public String getF9() {
		return f9;
	}

	public void setF9(String f9) {
		this.f9 = f9;
	}

	public String getF10() {
		return f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}

	public String getF11() {
		return f11;
	}

	public void setF11(String f11) {
		this.f11 = f11;
	}

	public String getF12() {
		return f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
	}

	public String getF13() {
		return f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
	}

	public String getF14() {
		return f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
	}

	public String getF15() {
		return f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
	}

	public String getF16() {
		return f16;
	}

	public void setF16(String f16) {
		this.f16 = f16;
	}

	public String getF17() {
		return f17;
	}

	public void setF17(String f17) {
		this.f17 = f17;
	}

	public String getF18() {
		return f18;
	}

	public void setF18(String f18) {
		this.f18 = f18;
	}

	public String getF19() {
		return f19;
	}

	public void setF19(String f19) {
		this.f19 = f19;
	}

	public String getF20() {
		return f20;
	}

	public void setF20(String f20) {
		this.f20 = f20;
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public List<String> composeList(List<ContentFormMapping> colum) {
		list = new ArrayList<String>();
		for (ContentFormMapping mapp : colum) {
			try {
				String name = toUpperCaseFirstOne(mapp.getMapping());
				Method m = this.getClass().getMethod("get" + name);
				Object value = m.invoke(this); // 调用getter方法获取属性值
				if (value != null && String.valueOf(value).trim().length() > 0) {
					if ("P".equals(mapp.getStype())) {
						try
						{
							JSONObject jo = JSONObject.fromObject(value);
							list.add(jo.getString("name"));
						} catch (Exception e)
						{
							list.add("");
						}
					} else {
						list.add(value + "");
					}
				} else {
					list.add("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getMapingValue(String mapping) {
		String rs = "";
		try {
			String name = toUpperCaseFirstOne(mapping);
			Method m = this.getClass().getMethod("get" + name);
			Object value = m.invoke(this); // 调用getter方法获取属性值
			if (value != null) {
				rs = value + "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

	public void setMapingValue(String mapping, String mapvalue) {
		try {
			String name = toUpperCaseFirstOne(mapping);
			 Method m = this.getClass().getDeclaredMethod("set"+name,String.class);
			 m.setAccessible(true);//因为写成private 所以这里必须设置
			Object value = m.invoke(this, mapvalue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public long getLbsid()
	{
		return lbsid;
	}

	
	public void setLbsid(long lbsid)
	{
		this.lbsid = lbsid;
	}
}
