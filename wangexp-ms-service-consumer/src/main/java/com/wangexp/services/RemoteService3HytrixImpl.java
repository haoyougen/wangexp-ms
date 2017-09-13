/**
 * 
 */
package com.wangexp.services;

import org.springframework.stereotype.Component;

/**
 * @author wangshuguang
 *
 */
@Component
public class RemoteService3HytrixImpl implements RemoteServiceP3 {
	@Override
	public String home() {
		return "Failed P3 ";
	}

}
