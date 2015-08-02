package org.tpark.visitor.pass.mngmt.svcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VISITOR_INFO")
public class VisitorEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME")
	private String firstName;
	
	@Column(name = "PURPOSE")
	private String purpose;
	
	@Column(name = "MOBILE")
	private String mobile;	
	
	@Column(name = "ACCMPND_CNT")
	private int accompanyCount;
	
	@Column(name = "ACCMPNY_NAME")
	private String accompanyName;
	
	@Column(name = "COMPANY")
	private String company;
	
	@Column(name = "BUILDING")
	private String building;
	

	@Column(name = "VECH_NO")
	private String vechno;
	
	@Column(name = "PHOTOID")
	private String photoId;

	@Column(name = "PHOTOID_TYPE")
	private String photoIdType;

	
	@Column(name = "ISSUE_DATE")
	private Date issuedDate;
	
	@Column(name = "VALID_FOR_HOURS")
	private int validity;
	
	@Transient 
	private String imageencodestr;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return photoId;
	}

	public void setEmail(String email) {
		this.photoId = email;
	}

	public String getVechno() {
		return vechno;
	}

	public void setVechno(String vechno) {
		this.vechno = vechno;
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

	public String getImageencodestr() {
		return imageencodestr;
	}

	public void setImageencodestr(String imageencodestr) {
		this.imageencodestr = imageencodestr;
	}

	 


}
