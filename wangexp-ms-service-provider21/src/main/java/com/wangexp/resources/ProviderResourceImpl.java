/**
 * 
 */
package com.wangexp.resources;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.wangexp.services.MyService;
import com.wangexp.services.RemoteServiceP3;

/**
 * @author wangshuguang
 *
 */
@RestController
public class ProviderResourceImpl implements ProviderResources {
	private static Logger log = LoggerFactory.getLogger(ProviderResourceImpl.class);
	private String provider3Address = "http://localhost:7775/api/p3";
	@Autowired
	private MyService service;
	@Autowired
	private RemoteServiceP3 p3service;

	public String home() {
		log.info("You are accessing provider 21 ");
		return "I'm the service provider 21";
	}

	public List<String> getAllServices() {
		return service.all();
	}

	@Override
	public String hc() {
		log.info("start calling hc");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet get = new HttpGet(provider3Address);
		String responsestr = null;
		try {
			HttpResponse response = httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			responsestr = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "P2 -> P3 with httpclient ===>" + responsestr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangexp.resources.ProviderResources#callp3()
	 */
	@Override
	public String callp3() {
		log.info("start calling callp3");
		String result = "";
		try {
			result = p3service.home();
		} catch (Exception e) {
			 e.printStackTrace();
		}

		return "P2 -> P3 with feign ===>" + result;
	}

}
