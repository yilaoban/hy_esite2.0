package com.huiyee.esite.util;

import java.awt.Color;

import jxl.format.Colour;

public class ColorUtil {
 
     
    public static Colour getNearestColour(Color awtColor) {
          Colour color = null;
 
          Colour[] colors = Colour.getAllColours();
          if ((colors != null) && (colors.length > 0)) {
           Colour crtColor = null;
           int[] rgb = null;
           int diff = 0;
           int minDiff = 999;
 
           for (int i = 0; i < colors.length; i++) {
            crtColor = colors[i];
            rgb = new int[3];
            rgb[0] = crtColor.getDefaultRGB().getRed();
            rgb[1] = crtColor.getDefaultRGB().getGreen();
            rgb[2] = crtColor.getDefaultRGB().getBlue();
 
            diff = Math.abs(rgb[0] - awtColor.getRed())
              + Math.abs(rgb[1] - awtColor.getGreen())
              + Math.abs(rgb[2] - awtColor.getBlue());
 
            if (diff < minDiff) {
             minDiff = diff;
             color = crtColor;
            }
           }
          }
          if (color == null)
           color = Colour.BLACK;
          return color;
         }
    //Color转换为16进制显示
    public static String toHexEncoding(Color color) {
        String R, G, B;
        StringBuffer sb = new StringBuffer();
 
        R = Integer.toHexString(color.getRed());
        G = Integer.toHexString(color.getGreen());
        B = Integer.toHexString(color.getBlue());
 
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
 
        sb.append("0x");
        sb.append(R);
        sb.append(G);
        sb.append(B);
 
        return sb.toString();
    }
     
    //把字符串表达的颜色值转换成java.awt.Color
    public static Color parseToColor(final String c) {
        Color convertedColor = Color.ORANGE;
        try {
            convertedColor = new Color(Integer.parseInt(c, 16));
        } catch(NumberFormatException e) {
            // codes to deal with this exception
        }
        return convertedColor;
    }
    public static Colour  getColour(final String c) {      
        Color  cl=parseToColor(c);     
        return  getNearestColour(cl);
    }
    //
    
    public static void main(String[] args)
	{
    	Color c = parseToColor("47acf5");
    	System.out.println(c.getRed()+","+c.getGreen()+","+c.getBlue());
	}
}
