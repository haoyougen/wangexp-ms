/**
 * 
 */
package com.wangexp.brave.httpclient;

/**
 * @author wangshuguang
 *
 */
public class ZipkinSetting {
	private String zipkinhost;
	private int connectTime = 6000;
	private int readTimeout = 6000;
	private int flushInterval = 1;
	private boolean compressEnabled = false;

	ZipkinSetting(String host) {
		this.zipkinhost = host;
	}

	/**
	 * @return the zipkinhost
	 */
	public String getZipkinhost() {
		return zipkinhost;
	}

	/**
	 * @param zipkinhost
	 *            the zipkinhost to set
	 */
	public void setZipkinhost(String zipkinhost) {
		this.zipkinhost = zipkinhost;
	}

	/**
	 * @return the connectTime
	 */
	public int getConnectTime() {
		return connectTime;
	}

	/**
	 * @param connectTime
	 *            the connectTime to set
	 */
	public void setConnectTime(int connectTime) {
		this.connectTime = connectTime;
	}

	/**
	 * @return the readTimeout
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * @param readTimeout
	 *            the readTimeout to set
	 */
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * @return the flushInterval
	 */
	public int getFlushInterval() {
		return flushInterval;
	}

	/**
	 * @param flushInterval
	 *            the flushInterval to set
	 */
	public void setFlushInterval(int flushInterval) {
		this.flushInterval = flushInterval;
	}

	/**
	 * @return the compressEnabled
	 */
	public boolean isCompressEnabled() {
		return compressEnabled;
	}

	/**
	 * @param compressEnabled
	 *            the compressEnabled to set
	 */
	public void setCompressEnabled(boolean compressEnabled) {
		this.compressEnabled = compressEnabled;
	}

}
