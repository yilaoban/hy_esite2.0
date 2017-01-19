package com.huiyee.bdmap.dto;

import java.util.List;

public class BDLBSDto {
private String hykey;
private String district;
private String distance;
public String getDistance() {
	return distance;
}
public void setDistance(String distance) {
	this.distance = distance;
}
private List<Double> location;
public List<Double> getLocation() {
	return location;
}
public void setLocation(List<Double> location) {
	this.location = location;
}
public String getHykey() {
	return hykey;
}
public void setHykey(String hykey) {
	this.hykey = hykey;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
}
