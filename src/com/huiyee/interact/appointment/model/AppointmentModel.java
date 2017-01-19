
package com.huiyee.interact.appointment.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;

public class AppointmentModel extends SuperHdModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5356508048559589489L;

	private int coded;
	private List<OrderMappingModel> list;

	public AppointmentModel()
	{
		setFeatureid(IInteractConstants.INTERACT_APT);
	}

	public int getDaylimit()
	{
		return daylimit;
	}

	public void setDaylimit(int daylimit)
	{
		this.daylimit = daylimit;
	}

	public List<OrderMappingModel> getList()
	{
		return list;
	}

	public void setList(List<OrderMappingModel> list)
	{
		this.list = list;
	}

	public Map<String, OrderMappingModel> getMappingMap()
	{
		Map<String, OrderMappingModel> map = new HashMap<String, OrderMappingModel>();
		if (list != null && list.size() > 0)
		{
			for (OrderMappingModel o : list)
			{
				map.put(o.getName(), o);
			}
		}
		return map;
	}
	
	/**
	 * ×Ô¶¨Òå×Ö¶Î
	 * @return
	 */
	public List<OrderMappingModel> getUdfList()
	{
		List<OrderMappingModel> udf=new ArrayList<OrderMappingModel>();
		if (list != null && list.size() > 0)
		{
			for (OrderMappingModel o : list)
			{
				Pattern p=Pattern.compile("f\\d+");
				if(p.matcher(o.getMapping()).matches()){
					udf.add(o);
				}
			}
		}
		return udf;
	}

	public long getStart()
	{
		return getStarttime().getTime();
	}

	public long getEnd()
	{
		return getEndtime().getTime();
	}
	
	public int getCoded()
	{
		return coded;
	}

	
	public void setCoded(int coded)
	{
		this.coded = coded;
	}

}
