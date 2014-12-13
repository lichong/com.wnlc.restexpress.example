package com.echo.controller.response;

import java.util.ArrayList;
import java.util.List;

public class QueryResult
{
	private String name;
	private String id;
	private String memo;
	private Personal personal;
	private List<String> friends;

	public QueryResult()
	{
		friends = new ArrayList<String>();
		friends.add("aa");
		friends.add("bb");
		friends.add("cc");
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public Personal getPersonal()
	{
		return personal;
	}

	public void setPersonal(Personal personal)
	{
		this.personal = personal;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("QueryResult [name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", personal=");
		builder.append(personal);
		builder.append(", friends=");
		builder.append(friends);
		builder.append("]");
		return builder.toString();
	}

	public List<String> getFriends()
	{
		return friends;
	}

	public void setFriends(List<String> friends)
	{
		this.friends = friends;
	}
}
