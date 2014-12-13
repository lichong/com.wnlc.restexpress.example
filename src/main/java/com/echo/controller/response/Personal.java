package com.echo.controller.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Personal
{
	private String address;
	private List<String> friends;
	private String[] boys;
	private Set<String> girls;
	private Map<String, String> relations;

	public Personal()
	{
		friends = new ArrayList<String>();
		friends.add("aa");
		friends.add("bb");
		friends.add("cc");
		boys = new String[] { "11", "22" };
		girls = new HashSet<String>();
		girls.add("qq");
		girls.add("ww");
		relations = new HashMap<String, String>();
		relations.put("00", "00");
		relations.put("55", "55");
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Personal [address=");
		builder.append(address);
		builder.append(", friends=");
		builder.append(friends);
		builder.append(", boys=");
		builder.append(Arrays.toString(boys));
		builder.append(", girls=");
		builder.append(girls);
		builder.append(", relations=");
		builder.append(relations);
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

	public String[] getBoys()
	{
		return boys;
	}

	public void setBoys(String[] boys)
	{
		this.boys = boys;
	}

	public Set<String> getGirls()
	{
		return girls;
	}

	public void setGirls(Set<String> girls)
	{
		this.girls = girls;
	}

	public Map<String, String> getRelations()
	{
		return relations;
	}

	public void setRelations(Map<String, String> relations)
	{
		this.relations = relations;
	}
}
