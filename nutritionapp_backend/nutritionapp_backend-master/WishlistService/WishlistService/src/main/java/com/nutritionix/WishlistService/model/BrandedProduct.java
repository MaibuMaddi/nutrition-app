package com.nutritionix.WishlistService.model;



public class BrandedProduct {
	String nix_brand_name;
    String nix_brand_id;
    String nix_item_name;
   
    String nix_item_id;
	
	Integer serving_qty;
	String serving_unit;
	Integer serving_weight_grams;
	Integer nf_metric_qty;
	String nf_metric_uom;
	Integer nf_calories;
	Integer nf_total_fat;
	Integer nf_saturated_fat;
	Integer nf_cholesterol;
	Integer nf_sodium;
	Integer nf_total_carbohydrate;
	Integer nf_dietary_fiber;
	Integer nf_sugars;
	Integer nf_protein;
	Integer nf_potassium;
	String image_url;
	
	
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getNix_brand_name() {
		return nix_brand_name;
	}
	public void setNix_brand_name(String nix_brand_name) {
		this.nix_brand_name = nix_brand_name;
	}
	public String getNix_brand_id() {
		return nix_brand_id;
	}
	public void setNix_brand_id(String nix_brand_id) {
		this.nix_brand_id = nix_brand_id;
	}
	public String getNix_item_name() {
		return nix_item_name;
	}
	public void setNix_item_name(String nix_item_name) {
		this.nix_item_name = nix_item_name;
	}
	public String getNix_item_id() {
		return nix_item_id;
	}
	public void setNix_item_id(String nix_item_id) {
		this.nix_item_id = nix_item_id;
	}
	public Integer getServing_qty() {
		return serving_qty;
	}
	public void setServing_qty(Integer serving_qty) {
		this.serving_qty = serving_qty;
	}
	public String getServing_unit() {
		return serving_unit;
	}
	public void setServing_unit(String serving_unit) {
		this.serving_unit = serving_unit;
	}
	public Integer getServing_weight_grams() {
		return serving_weight_grams;
	}
	public void setServing_weight_grams(Integer serving_weight_grams) {
		this.serving_weight_grams = serving_weight_grams;
	}
	public Integer getNf_metric_qty() {
		return nf_metric_qty;
	}
	public void setNf_metric_qty(Integer nf_metric_qty) {
		this.nf_metric_qty = nf_metric_qty;
	}
	public String getNf_metric_uom() {
		return nf_metric_uom;
	}
	public void setNf_metric_uom(String nf_metric_uom) {
		this.nf_metric_uom = nf_metric_uom;
	}
	public Integer getNf_calories() {
		return nf_calories;
	}
	public void setNf_calories(Integer nf_calories) {
		this.nf_calories = nf_calories;
	}
	public Integer getNf_total_fat() {
		return nf_total_fat;
	}
	public void setNf_total_fat(Integer nf_total_fat) {
		this.nf_total_fat = nf_total_fat;
	}
	public Integer getNf_saturated_fat() {
		return nf_saturated_fat;
	}
	public void setNf_saturated_fat(Integer nf_saturated_fat) {
		this.nf_saturated_fat = nf_saturated_fat;
	}
	public Integer getNf_cholesterol() {
		return nf_cholesterol;
	}
	public void setNf_cholesterol(Integer nf_cholesterol) {
		this.nf_cholesterol = nf_cholesterol;
	}
	public Integer getNf_sodium() {
		return nf_sodium;
	}
	public void setNf_sodium(Integer nf_sodium) {
		this.nf_sodium = nf_sodium;
	}
	public Integer getNf_total_carbohydrate() {
		return nf_total_carbohydrate;
	}
	public void setNf_total_carbohydrate(Integer nf_total_carbohydrate) {
		this.nf_total_carbohydrate = nf_total_carbohydrate;
	}
	public Integer getNf_dietary_fiber() {
		return nf_dietary_fiber;
	}
	public void setNf_dietary_fiber(Integer nf_dietary_fiber) {
		this.nf_dietary_fiber = nf_dietary_fiber;
	}
	public Integer getNf_sugars() {
		return nf_sugars;
	}
	public void setNf_sugars(Integer nf_sugars) {
		this.nf_sugars = nf_sugars;
	}
	public Integer getNf_protein() {
		return nf_protein;
	}
	public void setNf_protein(Integer nf_protein) {
		this.nf_protein = nf_protein;
	}
	public Integer getNf_potassium() {
		return nf_potassium;
	}
	public void setNf_potassium(Integer nf_potassium) {
		this.nf_potassium = nf_potassium;
	}
	@Override
	public String toString() {
		return "BrandedProduct [nix_brand_name=" + nix_brand_name + ", nix_brand_id=" + nix_brand_id
				+ ", nix_item_name=" + nix_item_name + ", nix_item_id=" + nix_item_id + ", serving_qty=" + serving_qty
				+ ", serving_unit=" + serving_unit + ", serving_weight_grams=" + serving_weight_grams
				+ ", nf_metric_qty=" + nf_metric_qty + ", nf_metric_uom=" + nf_metric_uom + ", nf_calories="
				+ nf_calories + ", nf_total_fat=" + nf_total_fat + ", nf_saturated_fat=" + nf_saturated_fat
				+ ", nf_cholesterol=" + nf_cholesterol + ", nf_sodium=" + nf_sodium + ", nf_total_carbohydrate="
				+ nf_total_carbohydrate + ", nf_dietary_fiber=" + nf_dietary_fiber + ", nf_sugars=" + nf_sugars
				+ ", nf_protein=" + nf_protein + ", nf_potassium=" + nf_potassium + "]";
	}

}
