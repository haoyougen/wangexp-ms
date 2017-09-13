/**
 * 
 */
package com.wangexp.services;

import org.springframework.beans.factory.FactoryBean;

import com.github.kristofa.brave.Brave;
import com.wangexp.brave.httpclient.ZipkinSetting;

/**
 * @author wangshuguang
 *
 */
public class MonitorFactoryBean implements FactoryBean<Brave> {
	private String serviceName;
	private ZipkinSetting zipkinsetting;

	@Override
	public Brave getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

}
