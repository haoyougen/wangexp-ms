/**
 * 
 */
package com.wangexp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author wangshuguang
 *
 */
@Component
public class RemoteService2HytrixImpl implements RemoteServiceP2 {

	@Override
	public String home() {
		return "Failed p2";
	}

	@Override
	public List<String> getAllServices() {
		List<String> list = new ArrayList<String>();
		list.add("Failed p2 all service");
		return list;
	}

	@Override
	public String hc() {
		return "Failed of p2 ====>p3 with httpclient";

	}

	/* (non-Javadoc)
	 * @see com.wangexp.services.RemoteServiceP2#callp3()
	 */
	@Override
	public String callp3() {
		 
		return "Failed of p2 ====>p3 by feign";
	}

}
