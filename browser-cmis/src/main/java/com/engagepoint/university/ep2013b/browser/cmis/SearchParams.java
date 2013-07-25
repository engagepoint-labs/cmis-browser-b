package com.engagepoint.university.ep2013b.browser.cmis;

import org.apache.chemistry.opencmis.client.api.QueryStatement;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vadym
 * Date: 26.07.13
 * Time: 0:33
 * To change this template use File | Settings | File Templates.
 */
public class SearchParams implements Serializable
{
	private String document = null;
	private Date from = null;
	private Date to = null;
	private String content = null;
	private Long size = null;
	private String text = null;

	public SearchParams()
	{
	}

	public SearchParams(String document, Date from, Date to, String content, Long size, String text)
	{
		this.document = document;
		this.from = from;
		this.to = to;
		this.content = content;
		this.size = size;
		this.text = text;
	}

	public SearchParams(String document, Date from, Date to, String content, long size, String text)
	{
		this(document, from, to, content, null, text);
		this.size = size;
	}

	public String getDocument()
	{
		return document;
	}

	public void setDocument(String document)
	{
		this.document = (!"".equals(document)) ? document : null;
	}

	public Date getFrom()
	{
		return from;
	}

	public void setFrom(Date from)
	{
		this.from = from;
	}

	public Date getTo()
	{
		return to;
	}

	public void setTo(Date to)
	{
		this.to = to;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = (!"".equals(content)) ? content : null;
	}

	public Long getSize()
	{
		return size;
	}

	public void setSize(Long size)
	{
		this.size = (size > 0) ? size : null;
	}

	public void setSize(long size)
	{
		this.size = (size > 0) ? size : null;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = (!"".equals(text)) ? text : null;
	}

	// Empty if every document type is not set
	public boolean isEmpty()
	{
		return (document == null);
	}

	public String getQueryString()
	{
		String query = "SELECT * FROM ? WHERE IN_FOLDER(?)";

		if (from != null)		query += " AND cmis:creationDate >= TIMESTAMP ?";
		if (to != null)			query += " AND cmis:creationDate <= TIMESTAMP ?";
		if (content != null)	query += " AND cmis:contentStreamMimeType = ?";
		if (size != null)		query += " AND cmis:contentStreamLength = ?";
		if (text != null)		query += " AND CONTAINS(?)";

		return query;
	}

	public QueryStatement query(String id, QueryStatement query)
	{
		if (!"document".equalsIgnoreCase(document) && !"folder".equalsIgnoreCase(document))
			throw new IllegalArgumentException("Illegal document type '"+document+"'. Only 'document' or 'folder' allowed.");

		if (isEmpty()) throw new NullPointerException("Set document type, before call query()");

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int counter = 1;

		if ("document".equalsIgnoreCase(document))	query.setType(counter++, "cmis:document"); else
		if ("folder".equalsIgnoreCase(document))	query.setType(counter++, "cmis:folder");

		query.setId(counter++, new ObjectIdImpl(id));

		if (from != null)		query.setString(counter++, format.format(from) + "T00:00:00");
		if (to != null)			query.setString(counter++, format.format(to) + "T23:59:59");
		if (content != null)	query.setString(counter++, content);
		if (size != null)		query.setNumber(counter++, size);
		if (text != null)		query.setString(counter++, text);

		return query;
	}

	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder();

		out.append("\nisEmpty  = " + isEmpty());
		out.append("\ndocument = " + getDocument());
		out.append("\nfrom     = " + getFrom());
		out.append("\nto       = " + getTo());
		out.append("\ncontent  = " + getContent());
		out.append("\nsize     = " + getSize());
		out.append("\ntext     = " + getText());

		return out.toString();
	}
}
