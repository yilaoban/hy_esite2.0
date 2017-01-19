package com.huiyee.esite.action;


import com.huiyee.esite.dto.LoadPageInteractionDto;

public class LoadPageInteractionAction extends AbstractAction {

	/**
     * 
     */
    private static final long serialVersionUID = -129412189626230703L;
    private LoadPageInteractionDto dto;
	private long featureid;
	private long siteid;
	
	@Override
	public String execute() throws Exception {
		dto = (LoadPageInteractionDto) pageCompose.composeLoadPageInteraction(siteid);
		return SUCCESS;
	}
	
	public String details() throws Exception{
		dto = (LoadPageInteractionDto) pageCompose.composeLoadPageInteractionDetail(featureid, siteid);
		return SUCCESS;
	}
	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}
	
	public long getSiteid() {
        return siteid;
    }

    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }

    public int getPagePosition(){
		return 2;
	}

    public LoadPageInteractionDto getDto() {
        return dto;
    }
	
}
