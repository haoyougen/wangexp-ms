/**
 * 
 */
package com.wangexp.services;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangshuguang
 *
 */
@FeignClient(name = "provider1", fallback = RemoteServiceHytrixImpl.class)
public interface RemoteServiceP1 extends LocalService {

	@RequestMapping(method = RequestMethod.GET, value = "/api/p1")
	public String home();

	@RequestMapping(method = RequestMethod.GET, value = "/api/p1/hc/p3")
	public String hc();
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/p1/all")
	public List<String> getAllServices();

}
