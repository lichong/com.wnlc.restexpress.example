package com.echo.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Herders
{
	private static Map<String, String> headers = new ConcurrentHashMap<String, String>();

	public static String getHeader(String key)
	{
		return headers.get(key);
	}

	public static void setHeaders(Map<String, String> h)
	{
		Map<String, String> temp = new HashMap<String, String>();
		Iterator<Entry<String, String>> iterator = h.entrySet().iterator();
		while (iterator.hasNext())
		{
			Entry<String, String> entry = iterator.next();
			temp.put(entry.getKey().toLowerCase(Locale.US), entry.getValue());
		}
		headers.clear();
		headers.putAll(temp);
	}

	public static void addHeaders(String key, String value)
	{
		headers.put(key.toLowerCase(Locale.US), value);
	}
}
