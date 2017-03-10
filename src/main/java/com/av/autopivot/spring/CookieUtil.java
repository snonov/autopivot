/*
 * (C) Quartet FS 2016
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of Quartet Financial Systems Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.av.autopivot.spring;

import javax.servlet.SessionCookieConfig;

import com.qfs.server.cfg.impl.JwtConfig;
import com.qfs.util.impl.QfsProperties;

/**
 * Utility class to configure the cookies &#x1f36a;.
 * <p>
 * This class is used, in particular, to align the lifetime of the cookie with that of the JWT token
 *
 * @author Quartet FS
 */
public class CookieUtil {

	/**
	 * Configures the cookies &#x1f36a;
	 *
	 * @param config cookie configuration
	 * @param cookieName the name of the cookies
	 * @see SessionCookieConfig#setName(String)
	 * @see SessionCookieConfig#setMaxAge(int)
	 */
	public static void configure(SessionCookieConfig config, String cookieName) {

		// Change the name of the cookie
		config.setName(cookieName);

		// Change the lifetime of the cookie session: it should not be greater than the lifetime of the tokens
		String expiration = QfsProperties.loadProperties("jwt.properties").getProperty(JwtConfig.EXPIRATION_PROPERTY);

		int maxAge = null != expiration ? Integer.parseInt(expiration): JwtConfig.DEFAULT_EXPIRATION;
		config.setMaxAge(maxAge);
	}

}