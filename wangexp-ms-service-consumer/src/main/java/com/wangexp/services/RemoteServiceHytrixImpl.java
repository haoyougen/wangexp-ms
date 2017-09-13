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
public class RemoteServiceHytrixImpl implements RemoteServiceP1 {

	@Override
	public String home() {
		return "Failed P1";
	}

	@Override
	public List<String> getAllServices() {
		List<String> list = new ArrayList<String>();
		list.add("error");
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangexp.services.RemoteServiceP1#hc()
	 */
	@Override
	public String hc() {
		 
		return "Failed P1->P3(hc) ";
	}

}
