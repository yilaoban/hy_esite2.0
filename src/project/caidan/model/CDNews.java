
package project.caidan.model;

import com.huiyee.esite.model.ContentNew;

public class CDNews
{

	private long id;
	private int type;
	private long newsid;
	private long owner;
	private int idx;
	private ContentNew news;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public long getNewsid()
	{
		return newsid;
	}

	public void setNewsid(long newsid)
	{
		this.newsid = newsid;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

	public ContentNew getNews()
	{
		return news;
	}

	public void setNews(ContentNew news)
	{
		this.news = news;
	}
}
