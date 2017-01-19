package com.huiyee.esite.model;

import java.util.List;

public class TreeNode
{
	private String name;
	private List<TreeNode> children;
	private String path;
	private boolean isParent;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<TreeNode> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeNode> children)
	{
		this.children = children;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public boolean isParent()
	{
		return isParent;
	}

	public void setParent(boolean isParent)
	{
		this.isParent = isParent;
	}
}
