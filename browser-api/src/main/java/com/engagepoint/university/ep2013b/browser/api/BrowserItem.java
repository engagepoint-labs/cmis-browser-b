package com.engagepoint.university.ep2013b.browser.api;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserItem implements Serializable
{
	public enum TYPE { FILE, FOLDER	}

	private String id;
	private TYPE type;
	private String name;
	private BrowserItem parent = null;
	private List<BrowserItem> children = new ArrayList<BrowserItem>();
	private int totalPages;
	private Date created;
	private String contentType;
	private BigInteger size;


	public BrowserItem()
	{
	}

	public BrowserItem(String name)
	{
		this.name = name;
	}

	public BrowserItem(String name, TYPE type)
	{
		this.name = name;
		this.type = type;
	}

	public BrowserItem(String name, TYPE type, BrowserItem parent)
	{
		this(name, type);
		this.parent = parent;
	}

	public BrowserItem(String id, String name, TYPE type)
	{
		this(name, type);
		this.id = id;
	}

	public BrowserItem(String name, TYPE type, BrowserItem parent, List<BrowserItem> children)
	{
		this(name, type, parent);
		this.children = children;
	}

	public BrowserItem(String name, TYPE type, BrowserItem parent, List<BrowserItem> children, int totalPages)
	{
		this(name, type, parent, children);
		this.totalPages = totalPages;
	}

	public BrowserItem(String id, String name, TYPE type, BrowserItem parent, List<BrowserItem> children)
	{
		this(name, type, parent);
		this.id = id;
		this.children = children;
	}


	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public TYPE getType()
	{
		return type;
	}

	public void setType(TYPE type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public BrowserItem getParent()
	{
		return parent;
	}

	public void setParent(BrowserItem parent)
	{
		this.parent = parent;
	}

	public List<BrowserItem> getChildren()
	{
		return children;
	}

	public void setChildren(List<BrowserItem> children)
	{
		this.children.addAll(children);
	}

	public void setChild(BrowserItem child)
	{
		children.add(child);
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public BigInteger getSize()
	{
		return size;
	}

	public void setSize(BigInteger size)
	{
		this.size = size;
	}

	public void setSize(long size)
	{
		this.size = BigInteger.valueOf(size);
	}

	// Get current path
	public String getLocation()
	{
		String result = "";
		BrowserItem item = this;

		if (item.getParent() == null) return "/";

		while (item.getParent() != null)
		{
			result = "/" + item.getName() + result;
			item = item.getParent();
		}

		return result;
	}

	// Find Root from any folder
	public BrowserItem getRootFolder()
	{
		BrowserItem item = this;

		while (item.getParent() != null)
		{
			item = item.getParent();
		}

		return item;
	}

	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof BrowserItem)) return false;

		return (this.getId().equals(((BrowserItem) other).getId()));
	}

	@Override
	public String toString()
	{
		String result = "BrowserItem (id:" + getId() +
				", name:" + getName() +
				", date:" + getCreated() +
				", type:" + getType().name() +
				", mime:" + getContentType() +
				", size:" + getSize() +

				", parent:" + ((parent == null) ? "null" : parent.getId());
		result += ", children:";

		if (children.isEmpty()) result += "empty";
		else
		{
			result += "[";
			for (BrowserItem i : children)
			{
				result += i.getId() + ", ";
			}
			result += "]";
		}

		result += ")";

		return result;
	}


}