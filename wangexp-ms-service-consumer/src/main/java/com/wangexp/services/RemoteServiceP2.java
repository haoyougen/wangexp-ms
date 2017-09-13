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
@FeignClient(name = "provider2", fallback = RemoteService2HytrixImpl.class)
public interface RemoteServiceP2 extends LocalService {

	@RequestMapping(method = RequestMethod.GET, value = "/api/p2")
	public String home();

	@RequestMapping(method = RequestMethod.GET, value = "/api/p2/all")
	public List<String> getAllServices();
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/p2/p3")
	public String callp3();
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/p2/hc")
	public String hc();

}
