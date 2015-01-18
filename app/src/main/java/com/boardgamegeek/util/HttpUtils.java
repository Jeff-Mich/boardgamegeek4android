package com.boardgamegeek.util;

import android.text.TextUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class HttpUtils {
	private static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;
	private static final String SITE_URL = "https://www.boardgamegeek.com/";

	@SuppressWarnings("FieldCanBeLocal") private static boolean mMockLogin = false;

	public static String encode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Timber.e("What do you mean UTF-8 isn't supported?!", e);
		}
		return s;
	}

	public static CookieStore authenticate(String username, String password) {
		if (mMockLogin) {
			return mockLogin(username);
		}

		String AUTH_URI = HttpUtils.SITE_URL + "login";

		final HttpResponse resp;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));

		final HttpEntity entity;
		try {
			entity = new UrlEncodedFormEntity(params);
		} catch (final UnsupportedEncodingException e) {
			// this should never happen.
			throw new IllegalStateException(e);
		}
		Timber.i("Authenticating to: " + AUTH_URI);
		final HttpPost post = new HttpPost(AUTH_URI);
		post.addHeader(entity.getContentType());
		post.setEntity(entity);
		try {
			final DefaultHttpClient client = (DefaultHttpClient) getHttpClient();
			resp = client.execute(post);
			Timber.w(resp.toString());
			CookieStore cookieStore = null;
			int code = resp.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				List<Cookie> cookies = client.getCookieStore().getCookies();
				if (cookies == null || cookies.isEmpty()) {
					Timber.w("missing cookies");
				} else {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("bggpassword")) {
							cookieStore = client.getCookieStore();
							break;
						}
					}
				}
			} else {
				Timber.w("Bad response code - " + code);
			}
			if (cookieStore != null) {
				Timber.w("Successful authentication");
				return cookieStore;
			} else {
				Timber.w("Error authenticating - " + resp.getStatusLine());
				return null;
			}
		} catch (final IOException e) {
			Timber.w("IOException when getting authtoken", e);
			return null;
		} finally {
			Timber.w("Authenticate complete");
		}
	}

	/**
	 * Configures the httpClient to connect to the URL provided.
	 */
	private static HttpClient getHttpClient() {
		HttpClient httpClient = new DefaultHttpClient();
		final HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
		HttpConnectionParams.setSoTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
		ConnManagerParams.setTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
		return httpClient;
	}

	/**
	 * Mocks a login by setting the cookie store with bogus data.
	 */
	private static CookieStore mockLogin(String username) {
		CookieStore store = new BasicCookieStore();
		store.addCookie(new BasicClientCookie("bggpassword", "password"));
		store.addCookie(new BasicClientCookie("SessionID", "token"));
		return store;
	}

	public static String ensureScheme(String url) {
		if (TextUtils.isEmpty(url)) {
			return url;
		}
		if (url.startsWith("//")) {
			return "https:" + url;
		}
		return url;
	}
}
