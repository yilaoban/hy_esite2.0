package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IEmotionsDao;
import com.huiyee.esite.mgr.IEmotionsManager;
import com.huiyee.esite.model.Emotions;
import com.huiyee.esite.model.EmotionsCategory;

public class EmotionsManagerImpl implements IEmotionsManager {

	private IEmotionsDao emotionsDao;

	public void setEmotionsDao(IEmotionsDao emotionsDao) {
		this.emotionsDao = emotionsDao;
	}

	@Override
	public List<EmotionsCategory> findEmotionsCategory() {
		return emotionsDao.findEmotionsCategory();
	}

	@Override
	public List<Emotions> findEmotionsByCategory(long category) {
		return emotionsDao.findEmotionsByCategory(category);
	}

	@Override
	public String changeValueToUrl(String imgText) {
		String text = imgText;
		String text1 = imgText;
		while (text.contains("[")) {
			int a = text.indexOf("[");
			int b = text.indexOf("]", a);
			if (b != -1) {
				String imgtext = text.substring(a, b + 1);
				Emotions e = emotionsDao.findEmotionsByValue(imgtext);
				if (e != null) {
					text1 = text1
							.replace(imgtext, "<img src='"
									+ e.getIconurl()
									+ "' alt='"
									+ imgtext
											.substring(1, imgtext.length() - 1)
									+ "'>");
				}
			}
			text = text.substring(b + 1, text.length());
		}
		return text1;
	}
}
