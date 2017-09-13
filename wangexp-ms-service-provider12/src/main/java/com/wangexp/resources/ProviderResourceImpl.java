/**
 * 
 */
package com.wangexp.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	public String home() {
		log.info("You are accessing provider 12");
		return "I'm the service provider 12";
	}

	public List<String> getAllServices() {
		return service.all();
	}
	 
}
