package com.engagepoint.university.ep2013b.browser.api;

import java.io.InputStream;
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: vadym.karko
 * Date: 7/25/13
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */

// Helper class for document content
public class BrowserDocumentContent
{
	private String filename;
	private String type;
	private BigInteger size;
	private InputStream stream;

	public BrowserDocumentContent()
	{
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public BigInteger getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = BigInteger.valueOf(size);
	}

	public void setSize(BigInteger size)
	{
		this.size = size;
	}

	public InputStream getStream()
	{
		return stream;
	}

	public void setStream(InputStream stream)
	{
		this.stream = stream;
	}
}
