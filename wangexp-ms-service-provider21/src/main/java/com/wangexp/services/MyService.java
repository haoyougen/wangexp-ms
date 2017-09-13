/**
 * 
 */
package com.wangexp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

/**
 * @author wangshuguang
 *
 */
@Service
public class MyService {
	@Autowired
	private DiscoveryClient discoveryClient;

	public List<String> all() {
		return discoveryClient.getServices();
	}

	public String getServiceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("STORES");
		if (list != null && list.size() > 0) {
			return list.get(0).getUri().toString();
		}
		return null;
	}
}
