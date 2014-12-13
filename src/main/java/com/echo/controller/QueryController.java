package com.echo.controller;

import com.echo.controller.response.Personal;
import com.echo.controller.response.QueryResult;
import com.echo.handler.Herders;

public class QueryController
{
	public QueryResult query(QueryResult req) throws Exception
	{
		String userid = Herders.getHeader("userid");
		System.out.println(userid + "|" + req);
		QueryResult result = new QueryResult();
		if (req != null)
		{
			result.setId(req.getId());
		}
		result.setName(userid);
		Personal p = new Personal();
		p.setAddress("test");
		result.setPersonal(p);
		System.out.println(result);
		if ("111".equals(userid))
		{
			throw new Exception("xxxxx");
		}
		return result;
	}

	public QueryResult nothing() throws Exception
	{
		String userid = Herders.getHeader("userid");
		System.out.println(userid);
		QueryResult result = new QueryResult();
		result.setName(userid);
		Personal p = new Personal();
		p.setAddress("test");
		result.setPersonal(p);
		System.out.println(result);
		if ("111".equals(userid))
		{
			throw new Exception("xxxxx");
		}
		return result;
	}
}
