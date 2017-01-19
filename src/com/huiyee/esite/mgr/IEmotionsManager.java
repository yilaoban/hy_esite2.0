package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.Emotions;
import com.huiyee.esite.model.EmotionsCategory;

public interface IEmotionsManager {
	
	public List<EmotionsCategory> findEmotionsCategory();
	
	public List<Emotions> findEmotionsByCategory(long category);

	public String changeValueToUrl(String text);
}
