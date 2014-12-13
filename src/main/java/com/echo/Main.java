package com.echo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;
import org.restexpress.util.Environment;

import com.echo.controller.QueryController;
import com.echo.handler.ProxyFactory;
import com.echo.serialization.SerializationProvider;

/**
 * The main entry-point into the RestExpress Echo example services.
 * 
 * @author toddf
 * @since Aug 31, 2009
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		RestExpress.setSerializationProvider(new SerializationProvider());
		Configuration config = loadEnvironment(args);
		RestExpress server = new RestExpress().setName(config.getName()).setPort(config.getPort())
				.setBaseUrl("http://localhost:8080");

		defineRoutes(server, config);

		if (config.getWorkerCount() > 0)
		{
			server.setIoThreadCount(config.getWorkerCount());
		}

		if (config.getExecutorThreadCount() > 0)
		{
			server.setExecutorThreadCount(config.getExecutorThreadCount());
		}

		mapExceptions(server);
		initBeans();
		server.bind();
		server.awaitShutdown();
	}

	private static void initBeans()
	{
		ProxyFactory.getInstance().register(new QueryController());
	}

	/**
	 * @param server
	 * @param config
	 */
	private static void defineRoutes(RestExpress server, Configuration config)
	{
		// This route supports GET, POST, PUT, DELETE echoing the 'echo' query-string parameter in the response.
		// GET and DELETE are also supported but require an 'echo' header or query-string parameter.

		// Waits the delay_ms number of milliseconds and responds with a 200.
		// Supports GET, PUT, POST, DELETE methods.

		// Waits the delay_ms number of milliseconds and responds with the
		// specified HTTP response code.
		// Supports GET, PUT, POST, DELETE methods.
		// server.uri("/query/{userid}", new QueryController()).format(Format.XML);
		server.uri("/{intf}/{method}", ProxyFactory.getInstance()).action("invoke", HttpMethod.GET)
				.action("invoke", HttpMethod.POST).action("invoke", HttpMethod.DELETE)
				.action("invoke", HttpMethod.HEAD).action("invoke", HttpMethod.OPTIONS)
				.action("invoke", HttpMethod.PUT);
	}

	/**
	 * @param server
	 */
	private static void mapExceptions(RestExpress server)
	{
		// server
		// .mapException(ItemNotFoundException.class, NotFoundException.class)
		// .mapException(DuplicateItemException.class, ConflictException.class)
		// .mapException(ValidationException.class, BadRequestException.class);
	}

	private static Configuration loadEnvironment(String[] args) throws FileNotFoundException, IOException
	{
		if (args.length > 0)
		{
			return Environment.from(args[0], Configuration.class);
		}

		return Environment.fromDefault(Configuration.class);
	}
}
