/**
 * 
 */
package com.wangexp.zuul.filters;

/**
 * @author wangshuguang
 * 
 *         (1) PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。<br>
 *         (2) ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache
 *         HttpClient或Netfilx Ribbon请求微服务。<br>
 *         (3) POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP
 *         Header、收集统计信息和指标、将响应从微服务发送给客户端等。<br>
 *         (4) ERROR：在其他阶段发生错误时执行该过滤器。
 */
public enum ZuulFilterType {
	PRE("pre"), ROUTING("routing"), POST("post"), ERROR("error");

	private String name;

	ZuulFilterType(String name) {

		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
