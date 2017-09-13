/**
 * 
 */
package com.wangexp.service.helloworld;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author wangshuguang
 *
 */
public class HytrixFaultTolerance implements HelloFaultTolerance {
	private Logger log = LoggerFactory.getLogger(HytrixFaultTolerance.class);
	private int executeTimeoutInMilliseconds = 6000;
	private Map<String, HystrixCommand.Setter> commandSetterMap = new ConcurrentHashMap<>();

	@Override
	public <T> T executeWithFaultTolerance(String servicename, BizService service) {

		HystrixCommand<T> command = new HystrixCommand<T>(buildSetter(servicename)) {
			@Override
			protected T run() throws Exception {
				log.info("Start running biz ");
				// 调用業務方法 service.***
				return null;
			}

			@Override
			protected T getFallback() {
				log.info("Start running biz exception handle ");
				return null;
			}

		};
		return command.execute();
	}

	private HystrixCommand.Setter buildSetter(String servicename) {
		HystrixCommand.Setter setter = commandSetterMap.get(servicename);
		if (setter != null) {
			return setter;
		}
		HystrixCommandProperties.Setter commonPropertiesSetter = HystrixCommandProperties.Setter();
		commonPropertiesSetter.withExecutionTimeoutInMilliseconds(executeTimeoutInMilliseconds);
		setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(servicename))
				.andCommandKey(HystrixCommandKey.Factory.asKey(servicename))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(servicename))
				.andCommandPropertiesDefaults(commonPropertiesSetter);
		commandSetterMap.putIfAbsent(servicename, setter);
		return setter;

	}

}
