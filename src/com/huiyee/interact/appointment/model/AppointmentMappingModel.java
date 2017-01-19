package com.huiyee.interact.appointment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMappingModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7131144111250860990L;
	private List<OrderMappingModel> list =new ArrayList<OrderMappingModel>();
	
	private List<Long> id;
	private List<Long> aptid;;
	private List<String> name;
	private List<String> mapping;
	private List<String> coltype;
	private List<String> stype;
    private List<String> defaultvalue;
	private List<String> show;
	private List<Integer> row;
	private List<String> req;
	
	public AppointmentMappingModel(){
		OrderMappingModel om1=new OrderMappingModel();
		om1.setColtype("S");
		om1.setDefaultvalue("");
		om1.setMapping("name");
		om1.setName("姓名");
		om1.setReq("N");
		om1.setRow(0);
		om1.setIsshow("Y");
		om1.setStype("T");
		om1.setTag("Y");
		list.add(om1);
		OrderMappingModel om2=new OrderMappingModel();
		om2.setColtype("I");
		om2.setDefaultvalue("");
		om2.setMapping("age");
		om2.setName("年龄");
		om2.setReq("N");
		om2.setRow(0);
		om2.setIsshow("Y");
		om2.setStype("T");
		om2.setTag("Y");
		list.add(om2);
		OrderMappingModel om3=new OrderMappingModel();
		om3.setColtype("S");
		om3.setDefaultvalue("男,女");
		om3.setMapping("gender");
		om3.setName("性别");
		om3.setReq("N");
		om3.setRow(0);
		om3.setIsshow("Y");
		om3.setStype("R");
		om3.setTag("Y");
		list.add(om3);
		OrderMappingModel om4=new OrderMappingModel();
		om4.setColtype("D");
		om4.setDefaultvalue("");
		om4.setMapping("birthday");
		om4.setName("生日");
		om4.setReq("N");
		om4.setRow(0);
		om4.setIsshow("Y");
		om4.setStype("D");
		om4.setTag("Y");
		list.add(om4);
		//省市区
		OrderMappingModel om16=new OrderMappingModel();
		om16.setColtype("S");
		om16.setDefaultvalue("");
		om16.setMapping("province");
		om16.setName("省份");
		om16.setReq("N");
		om16.setRow(0);
		om16.setIsshow("Y");
		om16.setStype("T");
		om16.setTag("Y");
		list.add(om16);
		OrderMappingModel om17=new OrderMappingModel();
		om17.setColtype("S");
		om17.setDefaultvalue("");
		om17.setMapping("city");
		om17.setName("城市");
		om17.setReq("N");
		om17.setRow(0);
		om17.setIsshow("Y");
		om17.setStype("T");
		om17.setTag("Y");
		list.add(om17);
		OrderMappingModel om18=new OrderMappingModel();
		om18.setColtype("S");
		om18.setDefaultvalue("");
		om18.setMapping("district");
		om18.setName("区域");
		om18.setReq("N");
		om18.setRow(0);
		om18.setIsshow("Y");
		om18.setStype("T");
		om18.setTag("Y");
		list.add(om18);
		OrderMappingModel om5=new OrderMappingModel();
		om5.setColtype("S");
		om5.setDefaultvalue("");
		om5.setMapping("address");
		om5.setName("地区");
		om5.setReq("N");
		om5.setRow(0);
		om5.setIsshow("Y");
		om5.setStype("T");
		om5.setTag("Y");
		list.add(om5);
		OrderMappingModel om6=new OrderMappingModel();
		om6.setColtype("S");
		om6.setDefaultvalue("");
		om6.setMapping("idcard");
		om6.setName("身份证");
		om6.setReq("N");
		om6.setRow(0);
		om6.setIsshow("Y");
		om6.setStype("T");
		om6.setTag("Y");
		list.add(om6);
		OrderMappingModel om7=new OrderMappingModel();
		om7.setColtype("S");
		om7.setDefaultvalue("");
		om7.setMapping("email");
		om7.setName("邮箱");
		om7.setReq("N");
		om7.setRow(0);
		om7.setIsshow("Y");
		om7.setStype("T");
		om7.setTag("Y");
		list.add(om7);
		OrderMappingModel om8=new OrderMappingModel();
		om8.setColtype("S");
		om8.setDefaultvalue("");
		om8.setMapping("phone");
		om8.setName("手机");
		om8.setReq("N");
		om8.setRow(0);
		om8.setIsshow("Y");
		om8.setStype("T");
		om8.setTag("Y");
		list.add(om8);
		OrderMappingModel om9=new OrderMappingModel();
		om9.setColtype("D");
		om9.setDefaultvalue("");
		om9.setMapping("date");
		om9.setName("预约日期");
		om9.setReq("N");
		om9.setRow(0);
		om9.setIsshow("Y");
		om9.setStype("D");
		om9.setTag("Y");
		list.add(om9);
		OrderMappingModel om10=new OrderMappingModel();
		om10.setColtype("D");
		om10.setDefaultvalue("");
		om10.setMapping("time");
		om10.setName("预约时间");
		om10.setReq("N");
		om10.setRow(0);
		om10.setIsshow("Y");
		om10.setStype("D");
		om10.setTag("Y");
		list.add(om10);
		OrderMappingModel om11=new OrderMappingModel();
		om11.setColtype("S");
		om11.setDefaultvalue("");
		om11.setMapping("f1");
		om11.setName("");
		om11.setReq("N");
		om11.setRow(0);
		om11.setIsshow("Y");
		om11.setStype("T");
		om11.setTag("N");
		list.add(om11);
		OrderMappingModel om12=new OrderMappingModel();
		om12.setColtype("S");
		om12.setDefaultvalue("");
		om12.setMapping("f2");
		om12.setName("");
		om12.setReq("N");
		om12.setRow(0);
		om12.setIsshow("Y");
		om12.setStype("S");
		om12.setTag("N");
		list.add(om12);
		OrderMappingModel om13=new OrderMappingModel();
		om13.setColtype("S");
		om13.setDefaultvalue("");
		om13.setMapping("f3");
		om13.setName("");
		om13.setReq("N");
		om13.setRow(0);
		om13.setIsshow("Y");
		om13.setStype("R");
		om13.setTag("N");
		list.add(om13);
		OrderMappingModel om14=new OrderMappingModel();
		om14.setColtype("S");
		om14.setDefaultvalue("");
		om14.setMapping("f4");
		om14.setName("");
		om14.setReq("N");
		om14.setRow(0);
		om14.setIsshow("Y");
		om14.setStype("C");
		om14.setTag("N");
		list.add(om14);
		OrderMappingModel om15=new OrderMappingModel();
		om15.setColtype("S");
		om15.setDefaultvalue("");
		om15.setMapping("f5");
		om15.setName("");
		om15.setReq("N");
		om15.setRow(0);
		om15.setIsshow("Y");
		om15.setStype("A");
		om15.setTag("N");
		list.add(om15);
	}
	
	
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public List<String> getMapping() {
		return mapping;
	}
	public void setMapping(List<String> mapping) {
		this.mapping = mapping;
	}
	
	public List<String> getColtype() {
		return coltype;
	}
	public void setColtype(List<String> coltype) {
		this.coltype = coltype;
	}
	public List<String> getStype() {
		return stype;
	}
	public void setStype(List<String> stype) {
		this.stype = stype;
	}
	public List<String> getShow() {
		return show;
	}
	public void setShow(List<String> show) {
		this.show = show;
	}
	
	public List<Integer> getRow() {
		return row;
	}
	public void setRow(List<Integer> row) {
		this.row = row;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public List<String> getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(List<String> defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public List<Long> getAptid() {
		return aptid;
	}
	public void setAptid(List<Long> aptid) {
		this.aptid = aptid;
	}
	public List<Long> getId() {
		return id;
	}
	public void setId(List<Long> id) {
		this.id = id;
	}
	public List<String> getReq() {
		return req;
	}
	public void setReq(List<String> req) {
		this.req = req;
	}


	public List<OrderMappingModel> getList()
	{
		return list;
	}


	public void setList(List<OrderMappingModel> list)
	{
		this.list = list;
	}
	
}
