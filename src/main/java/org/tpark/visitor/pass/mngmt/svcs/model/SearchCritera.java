package org.tpark.visitor.pass.mngmt.svcs.model;

import java.util.Date;

public class SearchCritera {
	
	public String fromDate;
	
	public String toDate;
	
	public String showimg;
	
	public String passNum;
	
	public String building;
	
	public String company;

	

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getShowimg() {
		return showimg;
	}

	public void setShowimg(String showimg) {
		this.showimg = showimg;
	}

	public String getPassNum() {
		return passNum;
	}

	public void setPassNum(String passNum) {
		this.passNum = passNum;
	}

	public String getBuilding() {
		return building.trim();
	}

	public void setBuilding(String building) {
		this.building = building.trim();
	}

	public String getCompany() {
		return company.trim();
	}

	public void setCompany(String company) {
		this.company = company.trim();
	}

	
	

}
