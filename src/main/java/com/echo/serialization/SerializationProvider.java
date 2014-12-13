package com.echo.serialization;

import org.restexpress.response.ErrorResponseWrapper;
import org.restexpress.response.ResponseWrapper;
import org.restexpress.serialization.AbstractSerializationProvider;
import org.restexpress.serialization.SerializationProcessor;
import org.restexpress.serialization.json.JacksonJsonProcessor;

public class SerializationProvider extends AbstractSerializationProvider
{
	// SECTION: CONSTANTS

	private static final SerializationProcessor JSON_SERIALIZER = new JacksonJsonProcessor();
	private static final SerializationProcessor XML_SERIALIZER = new MySerialization();
	private static final ResponseWrapper RESPONSE_WRAPPER = new ErrorResponseWrapper();

	public SerializationProvider()
	{
		super();
		add(JSON_SERIALIZER, RESPONSE_WRAPPER);
		// add(XML_SERIALIZER, RESPONSE_WRAPPER);
		add(new MySerialization(), new ExceptionResponse(), true);
	}

	public static SerializationProcessor json()
	{
		return JSON_SERIALIZER;
	}

	public static SerializationProcessor xml()
	{
		return XML_SERIALIZER;
	}
}
