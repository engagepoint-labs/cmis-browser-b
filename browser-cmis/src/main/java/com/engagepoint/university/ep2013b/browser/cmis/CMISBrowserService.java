package com.engagepoint.university.ep2013b.browser.cmis;

import com.engagepoint.university.ep2013b.browser.api.BrowserDocumentContent;
import com.engagepoint.university.ep2013b.browser.api.BrowserItem;
import com.engagepoint.university.ep2013b.browser.api.BrowserService;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

import java.math.BigInteger;
import java.util.*;

public class CMISBrowserService implements BrowserService
{
	// Unique Service Provider name
	private static final String SERVICE_NAME = "CMIS";
	private Session session;


	public CMISBrowserService()
	{
		session = connect();
	}

	// Should return SERVICE_NAME
	@Override
	public String getServiceName()
	{
		return SERVICE_NAME;
	}


	// Non paging methods
	@Override
	public BrowserItem findFolderById(String id)
	{
		Folder current = (Folder) session.getObject(id);
		return findFolder(current, 0, 0);
	}

	@Override
	public BrowserItem findFolderByPath(String path)
	{
		Folder current = (Folder) session.getObjectByPath(path);
		return findFolder(current, 0, 0);
	}


	// Paging methods
	@Override
	public BrowserItem findFolderById(String id, int page, int rowCount)
	{
		Folder current = (Folder) session.getObject(id);
		return findFolder(current, page, rowCount);
	}

	@Override
	public BrowserItem findFolderByPath(String path, int page, int rowCount)
	{
		Folder current = (Folder) session.getObjectByPath(path);
		return findFolder(current, page, rowCount);
	}

	// Search methods
	@Override
	public BrowserItem simpleSearch(String id, String parameter, int pageNum, int rowCounts)
	{
		String queryString = "SELECT * FROM cmis:document WHERE IN_FOLDER(?) and cmis:name LIKE ?";

		QueryStatement query = session.createQueryStatement(queryString);
		query.setString(1, id);
		query.setStringLike(2, "%" + parameter + "%");

		System.out.println("Query = " + query.toString());

		return search(query, pageNum, rowCounts);
	}

	@Override
	public BrowserItem advancedSearch(String id, Object param, int pageNum, int rowCounts)
	{
		AdvSearchParams parameter = (AdvSearchParams) param;

		QueryStatement prepQuery = session.createQueryStatement(parameter.createQueryString());
		QueryStatement query = parameter.setQueryParams(prepQuery);

		System.out.println("Query = " + query.toString());

		return search(query, pageNum, rowCounts);
	}


	// Folder management methods
	@Override
	public BrowserItem createFolder(String id, String name, String type) throws CmisBaseException
	{

		Map<String, String> folderProps = new HashMap<String, String>();

		folderProps.put(PropertyIds.OBJECT_TYPE_ID, type);
		folderProps.put(PropertyIds.NAME, name);
		folderProps.put(PropertyIds.PARENT_ID, id);

		Folder parent = (Folder) session.getObject(id);

		Folder newFolder = null;
		BrowserItem result = new BrowserItem();

		newFolder = parent.createFolder(folderProps);
		result = new BrowserItem(newFolder.getId(), newFolder.getName(), BrowserItem.TYPE.FOLDER);

//
//        try {
//            newFolder = parent.createFolder(folderProps);
//            result = new BrowserItem(newFolder.getId(), newFolder.getName(), BrowserItem.TYPE.FOLDER);
//        } catch (CmisBaseException e) {
//            // TODO: exception handling task
//            e.printStackTrace();
//        }

		return result;
	}

	@Override
	public BrowserItem editFolder(String id, String name, String type) throws CmisBaseException
	{

		Map<String, String> folderProps = new HashMap<String, String>();

		folderProps.put(PropertyIds.OBJECT_TYPE_ID, type);
		folderProps.put(PropertyIds.NAME, name);
		folderProps.put(PropertyIds.PARENT_ID, id);

		Folder current = (Folder) session.getObject(id);

		Folder newFolder = null;
		BrowserItem result = new BrowserItem();

		current.updateProperties(folderProps);
		result = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);

//
//        try {
//
//            current.updateProperties(folderProps);
//            result = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);
//        } catch (CmisBaseException e) {
//            // TODO: exception handling task
//            e.printStackTrace();
//        }

		return result;

	}

	@Override
	public void deleteFolder(String id) throws CmisBaseException
	{

		Folder current = (Folder) session.getObject(id);

		if (current.getId().equals(""))
		{
			return;
		}
		current.delete();

//
//        try {
//            current.delete();
//        } catch (CmisBaseException e) {
//            // TODO: exception handling task
//            e.printStackTrace();
//        }

	}


	// Connect method
	public Session connect()
	{
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// ATOM
//        final String url = "http://localhost:18080/server/atom11";
//        parameter.put(SessionParameter.ATOMPUB_URL, url);
//        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// WSDL
		final String url = "http://localhost:18080/server/services/";
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.WEBSERVICES.value());
		parameter.put(SessionParameter.WEBSERVICES_ACL_SERVICE, url + "ACLService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE, url + "DiscoveryService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE, url + "MultiFilingService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE, url + "NavigationService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_OBJECT_SERVICE, url + "ObjectService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_POLICY_SERVICE, url + "PolicyService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE, url + "RelationshipService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE, url + "RepositoryService?wsdl");
		parameter.put(SessionParameter.WEBSERVICES_VERSIONING_SERVICE, url + "VersioningService?wsdl");

		Repository repository = sessionFactory.getRepositories(parameter).get(0);
		parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());

		return sessionFactory.createSession(parameter);
	}


	// Helper methods
	private List<BrowserItem> findParents(Folder current)
	{
		List<BrowserItem> parents = new ArrayList<BrowserItem>();

		// Create all parents folders
		Folder parent;
		BrowserItem item;

		while (!current.isRootFolder())
		{
			parent = current.getFolderParent();

			item = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);
			parents.add(item);

			current = parent;
		}

		item = new BrowserItem(current.getId(), current.getName(), BrowserItem.TYPE.FOLDER);

		parents.add(item);


		for (int i = 0; i < parents.size() - 1; ++i)
		{
			parents.get(i).setParent(parents.get(i + 1));
		}

		return parents;
	}

	// Subclass uses for calculating pages
	private class Page
	{
		public long skip;
		public int total;

		public Page(long numItems, int pageNum, int rowCounts)
		{
			// count total pages
			long rest = numItems % rowCounts;
			int pages = (int) (numItems - rest) / rowCounts;
			if (rest > 0) pages++;
			total = pages;

			// count skipped records
			skip = (pageNum - 1) * rowCounts;
		}
	}

	private BrowserItem findFolderOld(Folder current, int pageNum, int rowCounts)
	{
		BrowserItem result;
		List<BrowserItem> parents = findParents(current);

		// Fill children of each parent folder
		for (BrowserItem i : parents)
		{
			ItemIterable<CmisObject> children = current.getChildren();

			// if enabled paging (paging only for selected folder, other parents without)
			if (((pageNum != 0) && (rowCounts != 0)) && (i.equals(parents.get(0))))
			{
				Page page = new Page(children.getTotalNumItems(), pageNum, rowCounts);
				i.setTotalPages(page.total);
				children = current.getChildren().skipTo(page.skip).getPage(rowCounts);
			}

			for (CmisObject o : children)
			{
				BrowserItem child, subchild;

				// check if already exists (is one of the parents)
				BrowserItem find = new BrowserItem();
				find.setId(o.getId());
				int index = parents.indexOf(find);

				if (index == -1)
				{
					// create child
					child = new BrowserItem();
					child.setId(o.getId());
					child.setName(o.getName());
					child.setCreated(o.getCreationDate().getTime());
					if (o instanceof Document)
					{
						// ..child is Document
						child.setType(BrowserItem.TYPE.FILE);
						child.setContentType(((Document) o).getContentStreamMimeType());
						child.setSize(BigInteger.valueOf(((Document) o).getContentStreamLength()));
					}
					else
					{
						// ..child is Folder
						child.setType(BrowserItem.TYPE.FOLDER);

						for (CmisObject s : ((Folder) o).getChildren())
						{
							// create subchild
							subchild = new BrowserItem();
							subchild.setId(s.getId());
							subchild.setName(s.getName());
							subchild.setCreated(s.getCreationDate().getTime());
							if (s instanceof Document)
							{
								// ..subchild is Document
								subchild.setType(BrowserItem.TYPE.FILE);
								subchild.setContentType(((Document) s).getContentStreamMimeType());
								subchild.setSize(BigInteger.valueOf(((Document) s).getContentStreamLength()));
							}
							else subchild.setType(BrowserItem.TYPE.FOLDER);

							subchild.setParent(child);
							child.setChild(subchild);
						}
					}

					child.setParent(i);

				} else child = parents.get(index);

				i.setChild(child);
			}

			current = current.getFolderParent();
		}

		result = parents.get(0);
		return result;
	}

	private BrowserItem findFolder(Folder current, int pageNum, int rowCounts)
	{
		String queryString = "SELECT * FROM cmis:document WHERE IN_FOLDER(?)";

		QueryStatement query = session.createQueryStatement(queryString);
		query.setString(1, current.getId());

		System.out.println("Query = " + query.toString());

		return search(query, pageNum, rowCounts);
	}

	private BrowserItem search(QueryStatement query, int pageNum, int rowCounts)
	{
		BrowserItem result = new BrowserItem();
		result.setType(BrowserItem.TYPE.FOLDER);

		ItemIterable<QueryResult> results = query.query(false);

		if (((pageNum != 0) && (rowCounts != 0)))
		{
			Page page = new Page(results.getTotalNumItems(), pageNum, rowCounts);

			result.setTotalPages(page.total);
			results = results.skipTo(page.skip).getPage(rowCounts);
		}

		for (QueryResult hit : results)
		{
			BrowserItem item = new BrowserItem();

			item.setId(hit.getPropertyByQueryName("cmis:objectId").getFirstValue().toString());
			item.setName(hit.getPropertyByQueryName("cmis:name").getFirstValue().toString());

			String t = hit.getPropertyByQueryName("cmis:baseTypeId").getFirstValue().toString();
			item.setType(("cmis:folder".equals(t)) ? BrowserItem.TYPE.FOLDER : BrowserItem.TYPE.FILE);
			item.setCreated(((GregorianCalendar) hit.getPropertyByQueryName("cmis:creationDate").getFirstValue()).getTime());

			if (item.getType() == BrowserItem.TYPE.FILE)
			{
				item.setContentType(hit.getPropertyByQueryName("cmis:contentStreamMimeType").getFirstValue().toString());
				item.setSize((BigInteger) hit.getPropertyByQueryName("cmis:contentStreamLength").getFirstValue());
			}

			result.setChild(item);
		}


		return result;
	}


	@Override
	public Map<String, String> getTypeList(String type)
	{

		Map<String, String> result = new HashMap<String, String>();

		ItemIterable<ObjectType> typeList = session.getTypeChildren(type, true);
		roundTrip(typeList, 1, result);

		return result;

	}

	private String countStr(String template, int count)
	{

		String result = "";

		for (int i = 0; i < count; i++)
		{
			result = template + result;
		}

		return result;
	}


	private void roundTrip(ItemIterable<ObjectType> list, int level, Map<String, String> result)
	{

		String prefix = countStr(" - ", level);

		for (ObjectType tt : list)
		{
			System.out.println("" + level + prefix + tt.getDisplayName() + "  [" + tt.getId() + "]");
			result.put(prefix + tt.getDisplayName(), tt.getId());

			if (tt.getChildren().getTotalNumItems() > 0)
			{
				roundTrip(tt.getChildren(), ++level, result);
			}
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	// Folder management methods
	@Override
	public BrowserItem createDocument(String id, String name)
	{
		Folder current = (Folder) session.getObject(id);
		return createDocument(current, name, null);
	}

//	@Override
	public BrowserItem createDocument(String id, String name, BrowserDocumentContent content)
	{
		Folder current = (Folder) session.getObject(id);

		ContentStream con = new ContentStreamImpl(
				content.getFilename(),
				content.getSize(),
				content.getType(),
				content.getStream()
		);

		return createDocument(current, name, con);
	}

	private BrowserItem createDocument(Folder current, String name, ContentStream content)
	{
		BrowserItem result = new BrowserItem();

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		properties.put(PropertyIds.NAME, name);

		Document document = current.createDocument(properties, content, VersioningState.NONE);

		result.setType(BrowserItem.TYPE.FILE);
		result.setName(name);
		result.setId(document.getId());
		result.setContentType(document.getContentStreamMimeType());
		result.setSize(document.getContentStreamLength());

		return result;
	}

	@Override
	public BrowserItem editDocument(String id, String name)
	{
		Document current = (Document) session.getObject(id);
		return editDocument(current, name);
	}

	private BrowserItem editDocument(Document document, String name)
	{
		BrowserItem result = new BrowserItem();

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, name);

		document.updateProperties(properties);

		result.setName(name);
		result.setId(document.getId());

		return result;
	}

	@Override
	public void deleteDocument(String id)
	{
		Document current = (Document) session.getObject(id);
		deleteDocument(current);
	}

	private void deleteDocument(Document document)
	{
		document.delete();
	}
}
