/**
 * 
 */
package com.wangexp.service.helloworld;

/**
 * @author wangshuguang
 *
 */
public interface HelloFaultTolerance {
	<T> T executeWithFaultTolerance(String servicename, BizService service);
}
