/**
 * 
 */
package com.wangexp.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangshuguang
 *
 */
@FeignClient(name = "provider3", fallback = RemoteService3HytrixImpl.class)
public interface RemoteServiceP3 {
	@RequestMapping(method = RequestMethod.GET, value = "/api/p3")
	public String home();

}
