package com.huiyee.interact.research.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;

public class ResearchModel extends SuperHdModel implements Serializable {

	private static final long serialVersionUID = -6789753548035456542L;
	private List<ResearchQuestionModel> questions;
	public ResearchModel() {
		setFeatureid(IInteractConstants.INTERACT_RESEARCH);
	}

	public List<ResearchQuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ResearchQuestionModel> questions) {
		this.questions = questions;
	}

}
