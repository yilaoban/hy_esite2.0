
package com.huiyee.esite.filter;

import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.HyFont;

public class ReadFontFilter implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		Map<String, Font> map=new HashMap<String, Font>();
		sce.getServletContext().setAttribute("fonts",map);
		
		File dir = new File(HyConfig.getRoot()+"/font");
		String[] fonts = dir.list(new FontFileNameFilter());
		if (fonts !=null && fonts.length > 0){
			for(String s : fonts){
				String key = s.substring(0,s.length()-4);
				map.put(key,HyFont.loadFont(HyConfig.getRoot()+"/font/"+s, 16f));
			}
		}

	}
	
	class FontFileNameFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			
			if (name.endsWith(".ttf") || name.endsWith(".otf"))
				return true;
			return false;
		}
		
	}
}
