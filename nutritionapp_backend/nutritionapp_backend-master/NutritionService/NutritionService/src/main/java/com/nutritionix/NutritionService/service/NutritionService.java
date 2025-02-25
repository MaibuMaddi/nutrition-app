

package com.nutritionix.NutritionService.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritionix.NutritionService.exception.DataFetchingNutritionUrlException;
import com.nutritionix.NutritionService.model.BrandedProduct;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



@Service
public class NutritionService{

    @Value("${nutrition.api.key}")
    private String nutritionApiKey;

    @Value("${nutrition.api.app.id}")
    private String nutritionApiAppId;
	
//	nutritionApiKey="36625e785051944113389c941607bda2";#cts mail
//	nutritionApiAppId="5eb48c38";
//	#	
//	#nutrition.api.key="41a2dbf08c1754cd2853bb6cdef99255";# personal msg
//	nutritionApiAppId="3bfe7fba";
//
//	nutrition.api.key"3f56b92a75a6cf89301e4439ebb1f015";#uggina mail
//	nutritionApiAppId="1082c37d";



   
    public List<BrandedProduct> searchProducts(String query) throws Exception {
    	

		String nutritionApiUrl="https://trackapi.nutritionix.com/v2/search/";
		String nutritionixInstantSearch = nutritionApiUrl+"instant?query="+query;
		System.out.println(nutritionApiAppId+"------"+nutritionApiKey);
		HttpGet getRequest = createHttpGetRequest(nutritionixInstantSearch);

        String responseJson = fetchHttpGet(getRequest);

        JSONObject jsonObject = new JSONObject(responseJson);
        JSONArray branded = jsonObject.getJSONArray("branded");

        return parseBrandedProducts(branded);
    }

  
    public BrandedProduct getBrandedProduct(String nixItemId) throws Exception {
        String itemUrl = "https://trackapi.nutritionix.com/v2/search/item?nix_item_id=" + nixItemId;
        System.out.println(itemUrl);
        HttpGet getRequest = createHttpGetRequest(itemUrl);
        String responseJson = fetchHttpGet(getRequest);
        JSONObject jsonObject = new JSONObject(responseJson);
        JSONArray foods = jsonObject.getJSONArray("foods");
        JSONObject product = foods.getJSONObject(0);

        return createBrandedProductFromJson(product);
    }
    
    
    
    public String fetchHttpGet(HttpGet get) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();

        if (entity == null) {
            throw new DataFetchingNutritionUrlException("No data received from the URL");
        }

        return EntityUtils.toString(entity);
    }

    

    private HttpGet createHttpGetRequest(String url) {
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeader("Content-Type", "application/json");
        getRequest.setHeader("x-app-id", nutritionApiAppId);
        getRequest.setHeader("x-app-key", nutritionApiKey);
        return getRequest;
    }

    private List<BrandedProduct> parseBrandedProducts(JSONArray branded) throws  Exception {
        List<BrandedProduct> products = new ArrayList<>();

//        for (int i = 0; i < branded.length(); i++) {
        for (int i = 0; i < 2; i++) {
            JSONObject product = branded.getJSONObject(i);
            products.add(getBrandedProduct(product.getString("nix_item_id")));
        }

        return products;
    }
    
    

    private BrandedProduct createBrandedProductFromJson(JSONObject product) throws Exception{
        BrandedProduct prod = new BrandedProduct();
        prod.setNix_item_id(product.getString("nix_item_id"));
    	prod.setNix_item_name(product.getString("food_name"));
    	
    	prod.setNix_brand_id(product.getString("nix_brand_id"));
    	prod.setNix_brand_name(product.getString("brand_name"));
    	prod.setServing_qty(product.getInt("serving_qty"));
    	try{prod.setServing_unit(product.getString("serving_unit"));}catch(Exception e) {prod.setServing_unit("null");}
    	try{prod.setServing_weight_grams(product.getInt("serving_weight_grams"));}catch(Exception e) {prod.setServing_weight_grams(0);}
    	try{prod.setNf_metric_qty(product.getInt("nf_metric_qty"));}catch(Exception e) {prod.setNf_metric_qty(0);}
    	try{prod.setNf_metric_uom(product.getString("nf_metric_uom"));}catch(Exception e) {prod.setNf_metric_uom("null");}
    	try{prod.setNf_calories(product.getInt("nf_calories"));}catch(Exception e) {prod.setNf_calories(0);}
    	try{prod.setNf_total_fat(product.getInt("nf_total_fat"));}catch(Exception e) {prod.setNf_total_fat(0);}
    	
    	try{prod.setNf_saturated_fat(product.getInt("nf_saturated_fat"));}catch(Exception e) {prod.setNf_saturated_fat(0);}
    	try{prod.setNf_cholesterol(product.getInt("nf_cholesterol"));}catch(Exception e) {prod.setNf_cholesterol(0);}
    	try{prod.setNf_sodium(product.getInt("nf_sodium"));}catch(Exception e) {prod.setNf_sodium(0);}
    	try{prod.setNf_total_carbohydrate(product.getInt("nf_total_carbohydrate"));}catch(Exception e) {prod.setNf_total_carbohydrate(0);}
    	try{prod.setNf_dietary_fiber(product.getInt("nf_dietary_fiber"));}catch(Exception e) {prod.setNf_dietary_fiber(0);}
    	try{prod.setNf_sugars(product.getInt("nf_sugars"));}catch(Exception e) {prod.setNf_sugars(0);}
    	try{prod.setNf_protein(product.getInt("nf_protein"));}catch(Exception e) {prod.setNf_protein(0);}
    	try{prod.setNf_potassium(product.getInt("nf_potassium"));}catch(Exception e) {prod.setNf_potassium(0);}
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode photoNode = objectMapper.readTree(product.get("photo").toString());
    	prod.setImage_url(photoNode.get("thumb").asText());
    	
    	
        return prod;
    }
}































//






//package com.nutritionix.NutritionSe//ic//service;
//
//import java.i//IOException;
//import java.u//l.ArrayList;
//import j//a.//il.List;
//
//import org.apache.ht//.HttpEntity;
//import org.apache.http//ttpResponse;
//import org.apache.http.clie//.HttpClient;
//import org.apache.http.client.me//ods.HttpGet;
//import org.apache.http.client.met//ds.HttpPost;
//import org.apache.http.entity//tringEntity;
//import org.apache.http.impl.client.Http//ientBuilder;
//import org.apache.http.uti//EntityUtils;
//import org.j//n.JSONArray;
//import org.js//.JSONObject;
//import org.springframework.beans.factory.ann//ation.Value;
//import org.springframework.context.an//tation.Bean;
//import org.springframework.stere//yp//Service;
//
//import com.nutritionix.NutritionService.exception.DataFetchingNutritio//rlException;
//import com.nutritionix.NutritionService.model.B//ndedProduct;
//import com.squareup.o//ttp.Headers;
//import com.squareup.okhttp//kHttpClient;
//import com.squareup.o//ttp.Request;
//import com.squareup.ok//tp//es//nse;
//
//
//@Service
//public class Nutr//io//ervice {
//
////	String nutritionApiKey="36625e785051944113389c941607bd//";//cts mail
////	String nutritionApiApp//="5//48c38";
//	
////	String nutritionApiKey="41a2dbf08c1754cd2853bb6cdef99255";///personal msg
////	String nutritionApiApp//="3//e7fba";
//	
//	String nutritionApiKey="3f56b92a75a6cf89301e4439ebb1f015"///uggina mail
//	String nutritionApiApp//="1//2c//d";
//	
//
//	public List<BrandedProduct> seaechProductsfromNutritionApi(String query) thr//s Exception{
//		 List<BrandedProduct> products=new //rayL//t<>();
//		
//		String nutritionApiUrl="https://trackapi.nutritionix.co//v2/search/";
//		String nutritionixInstantSearch = nutritionApi//l+"i//tant";
//		
//		String jsonPayload = "{\"query\":\"//query+"\"}";
//		System.out.println//sonPayload);
//        HttpClient httpClient = HttpClientBuilder.cre//e().build();
//        HttpPost postRequest = new HttpPost(nutritionixI//tantSearch);
//        postRequest.setHeader("Content-Type", "appli//tion/json");
//        postRequest.setHeader("x-app-id", nutri//onApiAppId);
//        postRequest.setHeader("x-app-key", nut//tionApiKey);
//        postRequest.setEntity(new StringEntity(//on//yload));
//
//        St//ng//tr = "";
//
//        try{
//            HttpResponse response = httpClient.execute//ostRequest);
//            HttpEntity entity = respons//getEntity();
//            
//            str = EntityUtils.toS//ing(entity);
//        }catch //xception e){
//            throw new DataFetchingNutritionUrlException("Cannot fetch da// from Url")//
//        }
//        JSONObject jsonObject = new JS//Object(str);
//        JSONArray common = (JSONArray) jsonObject.//t("common");
//        System.out.println("2222222-----------------------------//--"+common);
//        JSONArray branded = (JSONArray) jsonObject.g//("branded");
//        System.out.println("33333333------------------------------//-"+branded);
////        for (int i = 0; i < branded.le//th(); i++) {
//        for (int i = 0;// < 4; i++) {
//        	JSONObject product = branded.get//ONObject(i);
//        	System.out.println(product.getString("n//_item_id"));
//        	products.add(getBrandedProduct(product.getString("ni//item_id")))//
//        //
//       //
//        
//        re//rn p//duct//
//		///	}///////	
//	
//	
//	public BrandedProduct getBrandedProduct(String nixItemId) thro// Exc//tion {
//		
//		String itemUrl = "https://trackapi.nutritionix.com/v2/search/item?nix_item_i//"+nixItemId;
//		HttpClient httpClient = HttpClientBuilder.cre//e().build();
//        HttpGet getRequest = new Htt//et(itemUrl);
//        getRequest.setHeader("Content-Type", "appli//tion/json");
//        getRequest.setHeader("x-app-id", nutri//onApiAppId);
//        getRequest.setHeader("x-app-key", nut//tionApiKey);
//        //ring str="";
//        try{
//            HttpResponse response = httpClient.execut//getRequest);
//            HttpEntity entity = respons//getEntity();
//            
//            str = EntityUtils.toS//ing(entity);
//        }catch //xception e){
//        	throw new DataFetchingNutritionUrlException("Cannot fetch da// from Url")//
//       //
//      //
//        
//        JSONObject jsonObject = new JS//Object(str);
//        System.out//rintln(str);
//        JSONArray foods = (JSONArray) jsonObject//et("foods");
//        System.out.p//ntln(foods);
//        JSONObject product = foods.get//ONObject(0);
//        BrandedProduct prod = new Bra//edProduct();
//        prod.setNix_item_id(product.getString("n//_item_id"));
//    	prod.setNix_item_name(product.getString(//ood_nam//));
//    	
//    	prod.setNix_brand_id(product.getString("ni//brand_id"));
//    	prod.setNix_brand_name(product.getString("//and_name"));
//    	prod.setServing_qty(product.getInt("s//ving_qty"));
//    	try{prod.setServing_unit(product.getString("serving_unit"));}catch(Exception e) {prod.setServing_//it("null");}
//    	try{prod.setServing_weight_grams(product.getInt("serving_weight_grams"));}catch(Exception e) {prod.setServing_wei//t_grams(0);}
//    	try{prod.setNf_metric_qty(product.getInt("nf_metric_qty"));}catch(Exception e) {prod.setNf_m//ric_qty(0);}
//    	try{prod.setNf_metric_uom(product.getString("nf_metric_uom"));}catch(Exception e) {prod.setNf_metric//om("null");}
//    	try{prod.setNf_calories(product.getInt("nf_calories"));}catch(Exception e) {prod.setNf//alories(0);}
//    	try{prod.setNf_total_fat(product.getInt("nf_total_fat"));}catch(Exception e) {prod.setNf_//tal_fat//);}
//    	
//    	try{prod.setNf_saturated_fat(product.getInt("nf_saturated_fat"));}catch(Exception e) {prod.setNf_satu//ted_fat(0);}
//    	try{prod.setNf_cholesterol(product.getInt("nf_cholesterol"));}catch(Exception e) {prod.setNf_ch//esterol(0);}
//    	try{prod.setNf_sodium(product.getInt("nf_sodium"));}catch(Exception e) {prod.set//_sodium(0);}
//    	try{prod.setNf_total_carbohydrate(product.getInt("nf_total_carbohydrate"));}catch(Exception e) {prod.setNf_total_car//hydrate(0);}
//    	try{prod.setNf_dietary_fiber(product.getInt("nf_dietary_fiber"));}catch(Exception e) {prod.setNf_diet//y_fiber(0);}
//    	try{prod.setNf_sugars(product.getInt("nf_sugars"));}catch(Exception e) {prod.set//_sugars(0);}
//    	try{prod.setNf_protein(product.getInt("nf_protein"));}catch(Exception e) {prod.setN//protein(0);}
//    	try{prod.setNf_potassium(product.getInt("nf_potassium"));}catch(Exception e) {prod.setNf_//tassium//);}
////  	
//    	
//   //return pro//
////    //
///	
//	}
//	
//	
//}
