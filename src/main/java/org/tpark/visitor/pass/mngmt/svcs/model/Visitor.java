package org.tpark.visitor.pass.mngmt.svcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Visitor implements Serializable {

	private String firstName;

	private String purpose;

	private String mobile;

	private int accompanyCount;

	private String accompanyName;

	private String company;

	private String building;

	private String vechno;

	private String photoId;

	private String photoIdType;

	private Date issuedDate;

	private int validity;

	private String imageencodestr;

	public Visitor() {

	}

	public Visitor(String firstName, String purpose, String company,
			String building) {
		super();
		this.firstName = firstName;
		this.purpose = purpose;
		this.company = company;
		this.building = building;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVechno() {
		return vechno;
	}

	public void setVechno(String vechno) {
		this.vechno = vechno;
	}

	public String getImageencodestr() {
		return imageencodestr;
	}

	public void setImageencodestr(String imageencodestr) {
		this.imageencodestr = imageencodestr;
	}

	public int getAccompanyCount() {
		return accompanyCount;
	}

	public void setAccompanyCount(int accompanyCount) {
		this.accompanyCount = accompanyCount;
	}

	public String getAccompanyName() {
		return accompanyName;
	}

	public void setAccompanyName(String accompanyName) {
		this.accompanyName = accompanyName;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotoIdType() {
		return photoIdType;
	}

	public void setPhotoIdType(String photoIdType) {
		this.photoIdType = photoIdType;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

}
