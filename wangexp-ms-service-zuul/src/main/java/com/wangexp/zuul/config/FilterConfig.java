/**
 * 
 */
package com.wangexp.zuul.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wangexp.zuul.filters.AccessFilter;

/**
 * @author wangshuguang
 *
 */
 
public class FilterConfig {
	private static Logger log = LoggerFactory.getLogger(FilterConfig.class);

	@Bean
	public AccessFilter accessFilter() {
		log.info("AccessFilter initialized");
		return new AccessFilter();
	}
}
