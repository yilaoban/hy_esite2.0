package com.huiyee.interact.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 敏感词过滤
 * @Project：test
 * @Author : chenming
 * @Date ： 2014年4月20日 下午4:17:15
 * @version 1.0
 */
public class SensitiveWordFilter {
	public static Map<Object, Object> sensitiveWordMap = null;
	public static int minMatchTYpe = 1; // 最小匹配规则
	public static int maxMatchType = 2; // 最大匹配规则

	/**
	 * 构造函数，初始化敏感词库
	 */

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = { isEnd = 0 国 = {<br>
	 * isEnd = 1 人 = {isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = { isEnd =
	 * 1 } } } } 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 = { isEnd = 1 } } } }
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet
	 *            敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public static void addSensitiveWordToHashMap(List<String> keyWords) {
		sensitiveWordMap = new HashMap<Object, Object>(keyWords.size()); // 初始化敏感词容器，减少扩容操作
		// 迭代keyWords
		for (String key : keyWords) {
			Map<Object, Object> nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取

				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map<Object, Object>) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					Map<Object, Object> newWord = new HashMap<Object, Object>();
					newWord.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWord);
					nowMap = newWord;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}

		// Iterator<String> iterator = keyWordSet.iterator();
		// while (iterator.hasNext()) {
		// key = iterator.next(); // 关键字
		// nowMap = sensitiveWordMap;
		// for (int i = 0; i < key.length(); i++) {
		// char keyChar = key.charAt(i); // 转换成char型
		// Object wordMap = nowMap.get(keyChar); // 获取
		//
		// if (wordMap != null) { // 如果存在该key，直接赋值
		// nowMap = (Map) wordMap;
		// } else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
		// newWorMap = new HashMap<String, String>();
		// newWorMap.put("isEnd", "0"); // 不是最后一个
		// nowMap.put(keyChar, newWorMap);
		// nowMap = newWorMap;
		// }
		//
		// if (i == key.length() - 1) {
		// nowMap.put("isEnd", "1"); // 最后一个
		// }
		// }
		// }
	}

	/**
	 * 替换敏感字字符
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午5:12:07
	 * @param txt
	 * @param matchType
	 * @param replaceChar
	 *            替换字符，默认*
	 * @version 1.0
	 */
	public static String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
		String resultTxt = txt;
		Set<String> set = getSensitiveWord(txt, matchType); // 获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}

		return resultTxt;
	}

	/**
	 * 获取文字中的敏感词
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午5:10:52
	 * @param txt
	 *            文字
	 * @param matchType
	 *            匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 * @version 1.0
	 */
	private static Set<String> getSensitiveWord(String txt, int matchType) {
		Set<String> sensitiveWordList = new HashSet<String>();

		for (int i = 0; i < txt.length(); i++) {
			int length = checkSensitiveWord(txt, i, matchType); // 判断是否包含敏感字符
			if (length > 0) { // 存在,加入list中
				sensitiveWordList.add(txt.substring(i, i + length));
				i = i + length - 1; // 减1的原因，是因为for会自增
			}
		}

		return sensitiveWordList;
	}

	/**
	 * 检查文字中是否包含敏感字符，检查规则如下：<br>
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午4:31:03
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return，如果存在，则返回敏感词字符的长度，不存在返回0
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
		boolean flag = false; // 敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0; // 匹配标识数默认为0
		char word = 0;
		Map<Object, Object> nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			nowMap = (Map<Object, Object>) nowMap.get(word); // 获取指定key
			if (nowMap != null) { // 存在，则判断是否为最后一个
				matchFlag++; // 找到相应key，匹配标识+1
				if ("1".equals(nowMap.get("isEnd"))) { // 如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true; // 结束标志位为true
					if (SensitiveWordFilter.minMatchTYpe == matchType) { // 最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			} else { // 不存在，直接返回
				break;
			}
		}
		if (matchFlag < 2 || !flag) { // 长度必须大于等于1，为词
			matchFlag = 0;
		}
		return matchFlag;
	}

	/**
	 * 获取替换字符串
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午5:21:19
	 * @param replaceChar
	 * @param length
	 * @return
	 * @version 1.0
	 */
	private static String getReplaceChars(String replaceChar, int length) {
		String resultReplace = replaceChar;
		for (int i = 1; i < length; i++) {
			resultReplace += replaceChar;
		}

		return resultReplace;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		List<String> sets = new ArrayList<String>();
		sets.add("啊啊");
		sets.add("打啊的");
		sets.add("太多");

		addSensitiveWordToHashMap(sets);
		System.out.println("敏感词的数量：" + sensitiveWordMap.size());
		String string = "啊啊太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。" + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
				+ "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
		System.out.println("待检测语句字数：" + string.length());
		long beginTime = System.currentTimeMillis();
		Set<String> set = getSensitiveWord(string, 1);
		long endTime = System.currentTimeMillis();
		System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
		System.out.println("总共消耗时间为：" + (endTime - beginTime));

		string = replaceSensitiveWord(string, 1, "*");
		System.out.println(string);

	}
}
