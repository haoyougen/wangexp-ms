/**
 * 
 */
package com.wangexp.services;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

public final class SignUtils {

	/**
	 * to sign the parameters
	 * 
	 * @param params
	 *            the parameters map
	 * @param secret
	 *            the secret
	 * @return the sign string
	 */
	public static String sign(final TreeMap<String, String> params, final String secret) {
		final StringBuilder buf = new StringBuilder();
		// add the secret
		buf.append(secret);
		// add the parameters
		for (final String key : params.keySet()) {
			final String value = params.get(key);
			if (value != null && value.trim().length() > 0) {
				buf.append(key).append(value);
			}
		}
		// add the secret
		buf.append(secret);
		// MD5
		return md5(buf.toString());
	}

	/**
	 * MD5
	 * 
	 * @param str
	 *            the input string
	 * @return the md5 string
	 */
	public static String md5(final String str) {
		if (str != null) {
			try {
				return DigestUtils.md5Hex(str.getBytes("UTF-8")).toUpperCase();
			} catch (UnsupportedEncodingException e) {
				//
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(md5("6d25f8dad92b35a8e93e531c6ee96d7ec1d52efaeb2e3e97a24c6ad129411f7c".toLowerCase()));
		System.out.println(md5(md5("90475BG").toLowerCase()));
	}
}
