package com.echo.serialization;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.restexpress.Format;
import org.restexpress.contenttype.MediaRange;
import org.restexpress.contenttype.MediaTypeParser;
import org.restexpress.serialization.AliasingSerializationProcessor;

import com.thoughtworks.xstream.XStream;

public class MySerialization implements AliasingSerializationProcessor
{

	XStream xStream = new XStream();

	public MySerialization()
	{
		xStream.aliasSystemAttribute(null, "class");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(String string, Class<T> type)
	{
		xStream.processAnnotations(type);
		return (T) xStream.fromXML(string);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(ChannelBuffer buffer, Class<T> type)
	{
		if (buffer.readableBytes() > 0)
		{
			xStream.processAnnotations(type);
			xStream.alias(type.getSimpleName(), type);
			return (T) xStream.fromXML(new ChannelBufferInputStream(buffer));
		}
		return null;
	}

	@Override
	public ByteBuffer serialize(Object object)
	{
		Class<? extends Object> clazz = object.getClass();
		xStream.processAnnotations(clazz);
		xStream.alias(clazz.getSimpleName(), clazz);
		return ByteBuffer.wrap(xStream.toXML(object).getBytes());
	}

	@Override
	public List<MediaRange> getSupportedMediaRanges()
	{
		return MediaTypeParser.parse("application/xml");
	}

	@Override
	public List<String> getSupportedFormats()
	{
		return Arrays.asList(Format.XML);
	}

	@Override
	public void alias(String name, Class<?> theClass)
	{

	}

}
