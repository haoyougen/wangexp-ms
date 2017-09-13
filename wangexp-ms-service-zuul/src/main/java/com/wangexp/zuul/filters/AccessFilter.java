/**
 * 
 */
package com.wangexp.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author wangshuguang
 *
 */
@Component
public class AccessFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		log.info("");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requesturl = request.getRequestURI();
		log.info("You are accessing {}", requesturl);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		log.info("zuul fiter type:{}",ZuulFilterType.PRE.getName());
		return ZuulFilterType.PRE.getName();
	}

}
