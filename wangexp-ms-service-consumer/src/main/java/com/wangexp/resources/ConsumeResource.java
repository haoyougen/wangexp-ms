/**
 * 
 */
package com.wangexp.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangshuguang
 *
 */

public interface ConsumeResource {
	@RequestMapping("/api/p1")
	public String p1();

	@RequestMapping("/api/p1/hc/p3")
	public String p1hcp3();
	
	@RequestMapping("/api/p1/all")
	public List<String> getAllServicesP1();
	
	@RequestMapping("/api/p2")
	public String p2();

	@RequestMapping("/api/p2/all")
	public List<String> getAllServicesP2();
	
	@RequestMapping("/api/p2/hc/p3")
	public String p2hc();
	@RequestMapping("/api/p2/p3")
	public String p22p3();
	@RequestMapping("/api/p3")
	public String p3();
	

}
