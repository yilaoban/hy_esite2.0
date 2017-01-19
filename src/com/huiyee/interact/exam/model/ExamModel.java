package com.huiyee.interact.exam.model;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;

public class ExamModel extends SuperHdModel implements Serializable {

	private static final long serialVersionUID = -6789753548035456542L;
	private List<ExamQuestionModel> questions;
	public ExamModel() {
		setFeatureid(IInteractConstants.INTERACT_EXAM);
	}

	public List<ExamQuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ExamQuestionModel> questions) {
		this.questions = questions;
	}

}
