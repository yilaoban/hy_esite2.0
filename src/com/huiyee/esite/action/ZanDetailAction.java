package com.huiyee.esite.action;
import com.huiyee.esite.dto.ZanDetailDto;
public class ZanDetailAction extends AbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6617910686900215991L;
	private int pageId = 1;//µÚ¼¸Ò³
	private ZanDetailDto dto;
	private long productid;
	@Override
	public String execute() throws Exception{
		dto = (ZanDetailDto) pageCompose.findZamDetailList(productid, pageId);
		return SUCCESS;
	}
	public int getPageId()
	{
		return pageId;
	}
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
	public ZanDetailDto getDto()
	{
		return dto;
	}
	public long getProductid()
	{
		return productid;
	}
	public void setProductid(long productid)
	{
		this.productid = productid;
	}
	

}
