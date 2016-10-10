package com.att.model;

import java.util.Date;

public class Product {

	private int productid;
	private String productname;
	private String productdesc;
	private String relatedproducts;
	
	

	public int getProductid() {
		return productid;
	}



	public void setProductid(int productid) {
		this.productid = productid;
	}



	public String getProductname() {
		return productname;
	}



	public void setProductname(String productname) {
		this.productname = productname;
	}



	public String getProductdesc() {
		return productdesc;
	}



	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}



	public String getRelatedproducts() {
		return relatedproducts;
	}



	public void setRelatedproducts(String relatedproducts) {
		this.relatedproducts = relatedproducts;
	}



	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname
				+ ", productdesc=" + productdesc + ", relatedproducts="
				+ relatedproducts + "]";
	}
	
	
}
