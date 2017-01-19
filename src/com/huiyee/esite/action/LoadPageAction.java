package com.huiyee.esite.action;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.LoadPageDto;
public class LoadPageAction extends AbstractAction{
	private static final long serialVersionUID = 8841839491295257701L;
	/**
	 * 
	 */
	private String type = IPageConstants.SITE_TYPE_C;
	private long siteid;
	private LoadPageDto dto ;
	
	//page¡–±Ì
	@Override
	public String execute() throws Exception {
		dto = (LoadPageDto) pageCompose.composeLoadPage(siteid, type);
		return SUCCESS;
	}
	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}
    public LoadPageDto getDto() {
        return dto;
    }
    public void setDto(LoadPageDto dto) {
        this.dto = dto;
    }
    public int getPagePosition(){
        return 1;
    }

}
