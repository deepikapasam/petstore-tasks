package Deepika_API.Deepika_API;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Runner {
	JSONObject data;
	@Test(priority=0)
	public void create_Pet() throws IOException, ParseException {


        File file = new File("..\\Deepika_API\\datafiles\\addpet.json");
        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
             data = (JSONObject) parser.parse(
                    new FileReader(file.getAbsolutePath()));//path to the JSON file.
            System.out.println(data.toJSONString());

            String paramValue = "param\\with\\backslash";
            String yourURLStr = "http://host.com?param=" + java.net.URLEncoder.encode(paramValue, "UTF-8");

            URL url2 = new URL("https://petstore.swagger.io/v2/pet");
            HttpsURLConnection conn = (HttpsURLConnection) url2.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            outStreamWriter.write(data.toJSONString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();
            String response = null;

            System.out.println(conn.getResponseCode());
            System.out.println(conn.getResponseMessage());
            response=conn.getResponseMessage();

            DataInputStream input = null;
            input = new DataInputStream (conn.getInputStream());
           
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
 

	@Test(priority=1)
	public void get() {

		Response response = RestAssured.get("https://petstore.swagger.io/v2/pet/9");
		System.out.println(response.statusCode());
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.statusLine());

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		given().get("https://petstore.swagger.io/v2/pet/9").then().statusCode(200).body("id", equalTo(9));


	}


	@Test(priority=2)

	public void update() {

		data.put("name", "Daburman");

		 try {
	            JSONParser parser = new JSONParser();
	            //Use JSONObject for simple JSON and JSONArray for array of JSON.
	           
	            System.out.println(data.toJSONString());

	            String paramValue = "param\\with\\backslash";
	            String yourURLStr = "http://host.com?param=" + java.net.URLEncoder.encode(paramValue, "UTF-8");

	            URL url2 = new URL("https://petstore.swagger.io/v2/pet");
	            HttpsURLConnection conn = (HttpsURLConnection) url2.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setRequestProperty("Accept", "application/json");

	            conn.setDoOutput(true);
	            OutputStream outStream = conn.getOutputStream();
	            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
	            outStreamWriter.write(data.toJSONString());
	            outStreamWriter.flush();
	            outStreamWriter.close();
	            outStream.close();
	            String response = null;

	            System.out.println(conn.getResponseCode());
	            System.out.println(conn.getResponseMessage());
	            response=conn.getResponseMessage();
	            System.out.println("mine"+response);

	            DataInputStream input = null;
	            input = new DataInputStream (conn.getInputStream());
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	@Test(priority=3)
	public void verifyupdated() {

		Response response = RestAssured.get("https://petstore.swagger.io/v2/pet/9");
		System.out.println(response.statusCode());
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.statusLine());

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		given().get("https://petstore.swagger.io/v2/pet/9").then().statusCode(200).body("name", equalTo("Daburman"));
	}

	
	

	}	

