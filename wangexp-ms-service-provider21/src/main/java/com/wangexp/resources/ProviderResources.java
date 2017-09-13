/**
 * 
 */
package com.wangexp.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author wangshuguang
 *
 */
@RestController
public interface ProviderResources {
 

	@RequestMapping("/api/p2")
	public String home();
	@RequestMapping("/api/p2/hc")
	public String hc();
	@RequestMapping("/api/p2/p3")
	public String callp3();
	@RequestMapping("/api/p2/all")
	public List<String> getAllServices();
 

}
