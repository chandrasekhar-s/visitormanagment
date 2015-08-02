package org.tpark.visitor.pass.mngmt.svcs.model;

import java.util.List;

public class VisitorJsonObject {
	 
    int iTotalRecords;
 
    int iTotalDisplayRecords;
 
    String sEcho;
 
    String sColumns;
 
    List<Visitor> aaData;
 
    public int getiTotalRecords() {
	return iTotalRecords;
    }
 
    public void setiTotalRecords(int iTotalRecords) {
	this.iTotalRecords = iTotalRecords;
    }
 
    public int getiTotalDisplayRecords() {
	return iTotalDisplayRecords;
    }
 
    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
	this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
 
    public String getsEcho() {
	return sEcho;
    }
 
    public void setsEcho(String sEcho) {
	this.sEcho = sEcho;
    }
 
    public String getsColumns() {
	return sColumns;
    }
 
    public void setsColumns(String sColumns) {
	this.sColumns = sColumns;
    }
 
    public List<Visitor> getAaData() {
        return aaData;
    }
 
    public void setAaData(List<Visitor> aaData) {
        this.aaData = aaData;
    }
 
    
}