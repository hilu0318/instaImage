package com.hilu0318.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstagramImgDownloadController {

	private final String hintStr = "\"display_url\":\"";
	private final String preInstaUrl = "https://www.instagram.com/p/";
	
	@GetMapping("/dn_str/{id}")
	public @ResponseBody Map<String, String> getImgUrl(@PathVariable("id") String id) {
		Map<String, String> m = new HashMap<>();
		m.put("url", getParseUrlPString(preInstaUrl + id));
		return m;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImgFile(@PathVariable("id") String id) {
		System.out.println("** InstagramImgDownloadController.getImgFile() In");
		System.out.println("   - ID : " + id);
		if(id.equals("favicon.ico")) {
			System.out.println("   - favicon.ico In. return null!!");
			return null;
		}
		
		String sd = getParseUrlPString(preInstaUrl + id);
		
		URL imgUrl;
		BufferedImage bi;
		ByteArrayOutputStream baos = null;
		
		try {
			imgUrl = new URL(sd);
			bi = ImageIO.read(imgUrl);
			
			baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return baos.toByteArray();
	}
	
	private String getParseUrlPString(String parUrl) {
		URL url;
		String resultUrl = "";
		BufferedReader br = null;
		try {
			url = new URL(parUrl);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
			while((resultUrl = br.readLine()) != null) {
				if(resultUrl.indexOf(hintStr) > 0) {
					resultUrl = resultUrl.substring(resultUrl.indexOf(hintStr) + hintStr.length());
					resultUrl = resultUrl.substring(0, resultUrl.indexOf("\""));
					resultUrl = resultUrl.replace("\\u0026", "&");
					break;
				}
			}
			if(br != null) br.close();
		} catch (IOException e) {
			System.out.println("IO Exception!!!!");
			e.printStackTrace();
		}
		 
		return resultUrl;
	}
}
