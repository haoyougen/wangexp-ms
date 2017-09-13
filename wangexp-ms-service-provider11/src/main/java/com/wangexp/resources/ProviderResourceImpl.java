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

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.wangexp.services.MyService;

/**
 * @author wangshuguang
 *
 */
@RestController
public class ProviderResourceImpl implements ProviderResources {
	private static Logger log = LoggerFactory.getLogger(ProviderResourceImpl.class);
	@Autowired
	private MyService service;
	@Autowired
	private CloseableHttpClient httpclient ;
 
	
	private String provider3Address = "http://localhost:7775/api/p3";

	public String home() {
		log.info("You are accessing provider 11");
		return "I'm the service provider 11";
	}

	public List<String> getAllServices() {
		return service.all();
	}

	@Override
	public String hc() {
		log.info("start calling hc");

	//	CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet get = new HttpGet(provider3Address);
		String responsestr = null;
		try {
//			Brave.Builder builder = new Brave.Builder("provider1");
//			builder.spanCollector(spanCollector());
//			builder.traceSampler(Sampler.ALWAYS_SAMPLE);
//			Brave brave = builder.build();
			HttpResponse response = httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			responsestr = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "P1 -> P3 with httpclient ===>" + responsestr;
	}

	 

}
