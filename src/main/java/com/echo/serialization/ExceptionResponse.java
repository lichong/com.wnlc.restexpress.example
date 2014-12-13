package com.echo.serialization;

import java.util.UUID;

import org.restexpress.Response;
import org.restexpress.domain.ErrorResult;
import org.restexpress.exception.ExceptionUtils;
import org.restexpress.exception.ServiceException;
import org.restexpress.response.ResponseWrapper;

public class ExceptionResponse implements ResponseWrapper
{

	@Override
	public Object wrap(Response response)
	{
		if (addsBodyContent(response))
		{
			if (response.hasException())
			{
				Throwable exception = response.getException();
				Throwable rootCause = ExceptionUtils.findRootCause(exception);
				String message = (rootCause != null ? rootCause.getMessage() : exception.getMessage());
				String causeName = (rootCause != null ? rootCause.getClass().getSimpleName() : exception.getClass()
						.getSimpleName());

				if (ServiceException.isAssignableFrom(exception))
				{
					return new ErrorResult(((ServiceException) exception).getId(), response.getResponseStatus()
							.getCode(), message, causeName);
				}

				return new ErrorResult(UUID.randomUUID(), response.getResponseStatus().getCode(), message, causeName);
			}
			return ErrorResult.fromResponse(response);
		}

		return response.getBody();
	}

	@Override
	public boolean addsBodyContent(Response response)
	{
		if (response.hasException())
		{
			return true;
		}

		int code = response.getResponseStatus().getCode();

		if (code >= 400 && code < 600)
		{
			return true;
		}

		return false;
	}

}
