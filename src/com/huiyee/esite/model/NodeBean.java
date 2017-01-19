package com.huiyee.esite.model;

public class NodeBean {

	private String id;
	private String label = "";
	private double x;
	private double y;
	private String color = "rgb(0,0,0)";
	private double size;

	public NodeBean(){
		
	}
	
	public NodeBean(int red,int green,int blue){
		this.color="rgb("+red+","+green+","+blue+")";
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getColor() {
		return color;
	}
	public void setColor(int red,int green,int blue) {
		this.color="rgb("+red+","+green+","+blue+")";
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
