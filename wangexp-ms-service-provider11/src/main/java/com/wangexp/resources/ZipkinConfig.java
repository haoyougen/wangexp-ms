/**
 * 
 */
package com.wangexp.resources;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.Brave.Builder;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.SpanCollectorMetricsHandler;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector.Config;
import com.github.kristofa.brave.httpclient.BraveHttpRequestInterceptor;
import com.github.kristofa.brave.httpclient.BraveHttpResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;

/**
 * @author wangshuguang
 *
 */
@Configuration
public class ZipkinConfig {
	private static final String SERVICE_URL = "http://localhost:7555/api/p3";

	/**
	 * span（一次请求信息或者一次链路调用）信息收集器
	 */
	@Bean
	public SpanCollector spanCollector() {
		Config config = HttpSpanCollector.Config.builder().compressionEnabled(false).connectTimeout(5000)
				.flushInterval(1).readTimeout(6000).build();
		SpanCollectorMetricsHandler metricsHandler = new EmptySpanCollectorMetricsHandler();
		return HttpSpanCollector.create(SERVICE_URL, config, metricsHandler);
	}

	/**
	 * 作为各调用链路， 负责将指定格式的数据发送给zipkin
	 */
	@Bean
	public Brave brave(SpanCollector spanCollector) {
		// 指定serviceName
		Builder builder = new Builder("provider3");
		builder.spanCollector(spanCollector);
		builder.traceSampler(Sampler.create(1));
		return builder.build();
	}

	/**
	 * 设置server的（服务端收到请求和服务端完成处理，并将结果发送给客户端）过滤器
	 */
	@Bean
	public BraveServletFilter braveServletFilter(Brave brave) {
		BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
				brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
		return filter;
	}

	/**
	 * 设置client的（发起请求和获取到服务端返回信息）拦截器
	 */
	@Bean
	public CloseableHttpClient okHttpClient(Brave brave) {
		CloseableHttpClient httpclient = HttpClients.custom()
				.addInterceptorFirst(new BraveHttpRequestInterceptor(brave.clientRequestInterceptor(),
						new DefaultSpanNameProvider()))
				.addInterceptorFirst(new BraveHttpResponseInterceptor(brave.clientResponseInterceptor())).build();
		return httpclient;

	}
}
