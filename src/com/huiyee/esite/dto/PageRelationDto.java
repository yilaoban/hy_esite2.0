package com.huiyee.esite.dto;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.model.EdgeBean;
import com.huiyee.esite.model.NodeBean;

public class PageRelationDto {
	private List<EdgeBean> edges = new ArrayList<EdgeBean>();
	private List<NodeBean> nodes = new ArrayList<NodeBean>();
	public List<EdgeBean> getEdges()
	{
		return edges;
	}
	public void setEdges(List<EdgeBean> edges)
	{
		this.edges = edges;
	}
	public List<NodeBean> getNodes()
	{
		return nodes;
	}
	public void setNodes(List<NodeBean> nodes)
	{
		this.nodes = nodes;
	}

	
}
