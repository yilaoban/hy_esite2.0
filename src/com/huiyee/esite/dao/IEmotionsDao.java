package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.Emotions;
import com.huiyee.esite.model.EmotionsCategory;

public interface IEmotionsDao {
	
	public List<EmotionsCategory> findEmotionsCategory();
	
	public List<Emotions> findEmotionsByCategory(long category);

	public Emotions findEmotionsByValue(String imgtext);
}
