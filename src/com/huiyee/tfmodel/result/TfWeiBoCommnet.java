package com.huiyee.tfmodel.result;

import java.io.Serializable;

import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbSrc;

public class TfWeiBoCommnet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HyWbComment hyWbComment;
	private HyWbSrc hyWbSrc;

	public HyWbComment getHyWbComment()
	{
		return hyWbComment;
	}

	public void setHyWbComment(HyWbComment hyWbComment)
	{
		this.hyWbComment = hyWbComment;
	}

	public HyWbSrc getHyWbSrc()
	{
		return hyWbSrc;
	}

	public void setHyWbSrc(HyWbSrc hyWbSrc)
	{
		this.hyWbSrc = hyWbSrc;
	}
}
