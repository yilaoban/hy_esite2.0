package com.huiyee.esite.dto;

import java.util.List;
import com.huiyee.esite.model.TemplateCard;
import com.huiyee.esite.model.TemplateCategory;
public class TemplateSiteDto implements IDto{

	private List<TemplateCategory> categoryList;
	private List<TemplateCard> cardlist;
	private int total;
	public List<TemplateCategory> getCategoryList()
	{
		return categoryList;
	}
	public void setCategoryList(List<TemplateCategory> categoryList)
	{
		this.categoryList = categoryList;
	}
	public List<TemplateCard> getCardlist()
	{
		return cardlist;
	}
	public void setCardlist(List<TemplateCard> cardlist)
	{
		this.cardlist = cardlist;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
}
