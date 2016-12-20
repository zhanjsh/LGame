/**
 * Copyright 2008 - 2015 The Loon Game Engine Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loon
 * @author cping
 * @email：javachenpeng@yahoo.com
 * @version 0.5
 */
package loon.utils;

import loon.LSystem;

final public class StringUtils {

	private StringUtils() {
	}

	/**
	 * 一个仿C#的String.format实现（因为GWT不支持String.format）
	 * 
	 * @param format
	 * @param params
	 * @return
	 */
	public static final String format(String format, Object... params) {
		StringBuilder b = new StringBuilder();
		int p = 0;
		for (;;) {
			int i = format.indexOf('{', p);
			if (i == -1) {
				break;
			}
			int idx = format.indexOf('}', i + 1);
			if (idx == -1) {
				break;
			}
			if (p != i) {
				b.append(format.substring(p, i));
			}
			String nstr = format.substring(i + 1, idx);
			try {
				int n = Integer.parseInt(nstr);
				if (n >= 0 && n < params.length) {
					b.append(params[n]);
				} else {
					b.append('{').append(nstr).append('}');
				}
			} catch (NumberFormatException e) {
				b.append('{').append(nstr).append('}');
			}
			p = idx + 1;
		}
		b.append(format.substring(p));

		return b.toString();
	}

	public static boolean isBoolean(String o) {
		String str = o.trim().toLowerCase();
		return str.equals("true") || str.equals("false") || str.equals("yes")
				|| str.equals("no") || str.equals("ok");
	}

	public static boolean toBoolean(String o) {
		String str = o.trim().toLowerCase();
		if (str.equals("true") || str.equals("yes") || str.equals("ok")) {
			return true;
		} else if (str.equals("no") || str.equals("false")) {
			return false;
		} else if (MathUtils.isNan(str)) {
			return Double.parseDouble(str) > 0;
		}
		return false;
	}

	public static boolean equals(String a, String b) {
		if (a == null || b == null) {
			return (a == b);
		} else {
			return a.equals(b);
		}
	}

	public static String trim(String text) {
		return (rtrim(ltrim(text.trim()))).trim();
	}

	public static String rtrim(String s) {
		int off = s.length() - 1;
		while (off >= 0 && s.charAt(off) <= ' ') {
			off--;
		}
		return off < s.length() - 1 ? s.substring(0, off + 1) : s;
	}

	public static String ltrim(String s) {
		int off = 0;
		while (off < s.length() && s.charAt(off) <= ' ') {
			off++;
		}
		return off > 0 ? s.substring(off) : s;
	}

	public final static boolean startsWith(String n, char tag) {
		return n.charAt(0) == tag;
	}

	public final static boolean endsWith(String n, char tag) {
		return n.charAt(n.length() - 1) == tag;
	}

	/**
	 * 联合指定对象并输出为字符串
	 * 
	 * @param flag
	 * @param o
	 * @return
	 */
	public static String join(Character flag, Object... o) {
		StringBuilder sbr = new StringBuilder();
		int size = o.length;
		for (int i = 0; i < size; i++) {
			sbr.append(o[i]);
			if (i < size - 1) {
				sbr.append(flag);
			}
		}
		return sbr.toString();
	}

	/**
	 * 联合指定对象并输出为字符串
	 * 
	 * @param flag
	 * @param o
	 * @return
	 */
	public static String join(Character flag, float[] o) {
		StringBuilder sbr = new StringBuilder();
		int size = o.length;
		for (int i = 0; i < size; i++) {
			sbr.append(o[i]);
			if (i < size - 1) {
				sbr.append(flag);
			}
		}
		return sbr.toString();
	}

	/**
	 * 联合指定对象并输出为字符串
	 * 
	 * @param flag
	 * @param o
	 * @return
	 */
	public static String join(Character flag, int[] o) {
		StringBuilder sbr = new StringBuilder();
		int size = o.length;
		for (int i = 0; i < size; i++) {
			sbr.append(o[i]);
			if (i < size - 1) {
				sbr.append(flag);
			}
		}
		return sbr.toString();
	}

	/**
	 * 拼接指定对象数组为String
	 * 
	 * @param res
	 * @return
	 */
	public static String concat(Object... res) {
		StringBuffer sbr = new StringBuffer(res.length);
		for (int i = 0; i < res.length; i++) {
			if (res[i] instanceof Integer) {
				sbr.append((Integer) res[i]);
			} else {
				sbr.append(res[i]);
			}
		}
		return sbr.toString();
	}

	/**
	 * 判定是否由纯粹的西方字符组成
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEnglishAndNumeric(String string) {
		if (string == null || string.length() == 0) {
			return false;
		}
		char[] chars = string.toCharArray();
		int size = chars.length;
		for (int j = 0; j < size; j++) {
			char letter = chars[j];
			if ((97 > letter || letter > 122) && (65 > letter || letter > 90)
					&& (48 > letter || letter > 57)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判定是否为半角符号
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isSingle(final char c) {
		return (':' == c || '：' == c)
				|| (',' == c || '，' == c)
				|| ('"' == c || '“' == c)
				|| ((0x0020 <= c)
						&& (c <= 0x007E)
						&& !((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')))
						&& !('0' <= c) && (c <= '9'));

	}

	/**
	 * 分解字符串
	 * 
	 * @param str
	 * @param tag
	 * @return
	 */
	public static String[] split(String str, Character flag) {
		if (str == null || str.length() == 0) {
			return new String[0];
		}
		int count = 1;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == flag) {
				count++;
			}
		}
		String[] results = new String[count];
		int index = 0;
		for (int i = 0; i < count; i++) {
			int nextIndex = str.indexOf(flag, index);
			if (nextIndex == -1) {
				results[i] = str.substring(index);
			} else {
				results[i] = str.substring(index, nextIndex);
				index = nextIndex + 1;
			}
		}
		return results;
	}

	/**
	 * 分解字符串(同时过滤多个符号)
	 * 
	 * @param str
	 * @param flags
	 * @return
	 */
	public static String[] split(String str, char[] flags) {
		return split(str, flags, false);
	}

	/**
	 * 分解字符串(同时过滤多个符号)
	 * 
	 * @param str
	 * @param flags
	 * @return
	 */
	public static String[] split(String str, char[] flags, boolean newline) {
		if ((flags.length == 0) || (str.length() == 0)) {
			return new String[0];
		}
		char[] chars = str.toCharArray();
		int maxparts = chars.length + 1;
		int[] start = new int[maxparts];
		int[] end = new int[maxparts];
		int count = 0;
		start[0] = 0;
		int s = 0, e;
		if (CharUtils.equalsOne(chars[0], flags)) {
			end[0] = 0;
			count++;
			s = CharUtils.findFirstDiff(chars, 1, flags);
			if (s == -1) {
				return new String[] { "", "" };
			}
			start[1] = s;
		}
		for (;;) {
			e = CharUtils.findFirstEqual(chars, s, flags);
			if (e == -1) {
				end[count] = chars.length;
				break;
			}
			end[count] = e;
			count++;
			s = CharUtils.findFirstDiff(chars, e, flags);
			if (s == -1) {
				start[count] = end[count] = chars.length;
				break;
			}
			start[count] = s;
		}
		count++;
		String[] result = null;
		if (newline) {
			count *= 2;
			result = new String[count];
			for (int i = 0, j = 0; i < count; j++, i += 2) {
				result[i] = str.substring(start[j], end[j]);
				result[i + 1] = LSystem.LS;
			}
		} else {
			result = new String[count];
			for (int i = 0; i < count; i++) {
				result[i] = str.substring(start[i], end[i]);
			}
		}
		return result;
	}

	/**
	 * 分解字符串
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String[] split(String str, String separator) {
		int sepLength = separator.length();
		if (sepLength == 0) {
			return new String[] { str };
		}
		TArray<String> tokens = new TArray<String>();
		int length = str.length();
		int start = 0;
		do {
			int p = str.indexOf(separator, start);
			if (p == -1) {
				p = length;
			}
			if (p > start) {
				tokens.add(str.substring(start, p));
			}
			start = p + sepLength;
		} while (start < length);
		String[] result = new String[tokens.size];
		for (int i = 0; i < tokens.size; i++) {
			result[i] = tokens.get(i);
		}
		return result;
	}

	/**
	 * 解析csv文件
	 * 
	 * @param str
	 * @return
	 */
	public static String[] splitCsv(String str) {
		TArray<String> stringList = new TArray<String>();
		String tempString;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '"') {
				i++;
				while (i < str.length()) {
					if (str.charAt(i) == '"' && str.charAt(i + 1) == '"') {
						sb.append('"');
						i = i + 2;
					}
					if (str.charAt(i) == '"') {
						break;
					} else {
						sb.append(str.charAt(i));
						i++;
					}
				}
				i++;
			}

			if (str.charAt(i) != ',') {
				sb.append(str.charAt(i));
			} else {
				tempString = sb.toString();
				stringList.add(tempString);
				sb.setLength(0);
			}
		}

		tempString = sb.toString();
		stringList.add(tempString);
		sb.setLength(0);
		String[] stockArr = new String[stringList.size];
		stockArr = stringList.toArray(stockArr);
		return stockArr;
	}

	/**
	 * 以指定大小过滤字符串
	 * 
	 * @param str
	 * @param size
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static String[] splitSize(String str, int size)
			throws NullPointerException, IllegalArgumentException {
		if (str == null) {
			throw new NullPointerException("The str parameter is null.");
		}
		if (size <= 0) {
			throw new IllegalArgumentException(
					"The size parameter must be more than 0.");
		}
		int num = str.length() / size;
		int mod = str.length() % size;
		String[] ret = mod > 0 ? new String[num + 1] : new String[num];
		for (int i = 0; i < num; i++) {
			ret[i] = str.substring(i * size, (i + 1) * size);
		}
		if (mod > 0) {
			ret[num] = str.substring(num * size);
		}
		return ret;
	}

	/**
	 * 过滤指定字符串
	 * 
	 * @param string
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static final String replace(String string, String oldString,
			String newString) {
		if (string == null)
			return null;
		if (newString == null)
			return string;
		int i = 0;
		if ((i = string.indexOf(oldString, i)) >= 0) {
			char string2[] = string.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(string2.length);
			buf.append(string2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
				buf.append(string2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(string2, j, string2.length - j);
			return buf.toString();
		} else {
			return string;
		}
	}

	/**
	 * 不匹配大小写的过滤指定字符串
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	/**
	 * 不匹配大小写的过滤指定字符串
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @param count
	 * @return
	 */
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString, int count[]) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 1;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	/**
	 * 以指定条件过滤字符串
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @param count
	 * @return
	 */
	public static final String replace(String line, String oldString,
			String newString, int[] count) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 1;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	/**
	 * 检查一组字符串是否完全由中文组成
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinaLanguage(char[] chars) {
		int[] ints = new int[2];
		boolean isChinese = false;
		int length = chars.length;
		byte[] bytes = null;
		for (int i = 0; i < length; i++) {
			bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
						&& ints[1] <= 0xFE) {
					isChinese = true;
				}
			} else {
				return false;
			}
		}
		return isChinese;
	}

	public static boolean isChinese(char c) {
		return c >= 0x4e00 && c <= 0x9fa5;
	}

	/**
	 * 判断是否为null
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String param) {
		return param == null || param.length() == 0 || "".equals(param.trim());
	}

	/**
	 * 判断是否为null
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String... param) {
		return param == null || param.length == 0;
	}

	/**
	 * 检查指定字符串中是否存在中文字符。
	 * 
	 * @param checkStr
	 *            指定需要检查的字符串。
	 * @return 逻辑值（True Or False）。
	 */
	public static final boolean hasChinese(String checkStr) {
		boolean checkedStatus = false;
		boolean isError = false;
		String spStr = " _-";
		int checkStrLength = checkStr.length() - 1;
		for (int i = 0; i <= checkStrLength; i++) {
			char ch = checkStr.charAt(i);
			if (ch < '\176') {
				ch = Character.toUpperCase(ch);
				if (((ch < 'A') || (ch > 'Z')) && ((ch < '0') || (ch > '9'))
						&& (spStr.indexOf(ch) < 0)) {
					isError = true;
				}
			}
		}
		checkedStatus = !isError;
		return checkedStatus;
	}

	/**
	 * 检查是否为纯字母
	 * 
	 * @param value
	 * @return
	 */
	public final static boolean isAlphabet(String value) {
		if (value == null || value.length() == 0)
			return false;
		for (int i = 0; i < value.length(); i++) {
			char letter = Character.toUpperCase(value.charAt(i));
			if (('a' <= letter && letter <= 'z')
					|| ('A' <= letter && letter <= 'Z'))
				return true;
		}
		return false;
	}

	/**
	 * 检查是否为纯字母
	 * 
	 * @param letter
	 * @return
	 */
	public final static boolean isAlphabet(char letter) {
		return (('a' <= letter && letter <= 'z') || ('A' <= letter && letter <= 'Z'));
	}

	/**
	 * 检查是否为字母与数字混合
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isAlphabetNumeric(String value) {
		if (value == null || value.trim().length() == 0)
			return true;
		for (int i = 0; i < value.length(); i++) {
			char letter = value.charAt(i);
			if (('a' > letter || letter > 'z')
					&& ('A' > letter || letter > 'Z')
					&& ('0' > letter || letter > '9'))
				return false;
		}
		return true;
	}

	/**
	 * 替换指定字符串
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String replaceMatch(String line, String oldString,
			String newString) {
		int i = 0;
		int j = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buffer = new StringBuffer(line2.length);
			buffer.append(line2, 0, i).append(newString2);
			i += oLength;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				buffer.append(line2, j, i - j).append(newString2);
				i += oLength;
			}
			buffer.append(line2, j, line2.length - j);
			return buffer.toString();
		} else {
			return line;
		}
	}

	/**
	 * @see #indexOf(int[], int, int)
	 */
	public static int indexOf(int[] arr, int v) {
		return indexOf(arr, v, 0);
	}

	/**
	 * @param arr
	 *            数组
	 * @param v
	 *            值
	 * @param off
	 *            从那个下标开始搜索(包含)
	 * @return 第一个匹配元素的下标
	 */
	public static int indexOf(int[] arr, int v, int off) {
		if (null != arr)
			for (int i = off; i < arr.length; i++) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * @param arr
	 * @param v
	 * @return 最后一个匹配元素的下标
	 */
	public static int lastIndexOf(int[] arr, int v) {
		if (null != arr)
			for (int i = arr.length - 1; i >= 0; i--) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * @see #indexOf(char[], char, int)
	 */
	public static int indexOf(char[] arr, char v) {
		if (null != arr)
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * @param arr
	 *            数组
	 * @param v
	 *            值
	 * @param off
	 *            从那个下标开始搜索(包含)
	 * @return 第一个匹配元素的下标
	 */
	public static int indexOf(char[] arr, char v, int off) {
		if (null != arr)
			for (int i = off; i < arr.length; i++) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * @param arr
	 * @param v
	 * @return 第一个匹配元素的下标
	 */
	public static int lastIndexOf(char[] arr, char v) {
		if (null != arr)
			for (int i = arr.length - 1; i >= 0; i--) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * @see #indexOf(long[], long, int)
	 */
	public static int indexOf(long[] arr, long v) {
		return indexOf(arr, v, 0);
	}

	/**
	 * @param arr
	 *            数组
	 * @param v
	 *            值
	 * @param off
	 *            从那个下标开始搜索(包含)
	 * @return 第一个匹配元素的下标
	 */
	public static int indexOf(long[] arr, long v, int off) {
		if (null != arr)
			for (int i = off; i < arr.length; i++) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * @param arr
	 * @param v
	 * @return 第一个匹配元素的下标
	 */
	public static int lastIndexOf(long[] arr, long v) {
		if (null != arr)
			for (int i = arr.length - 1; i >= 0; i--) {
				if (arr[i] == v)
					return i;
			}
		return -1;
	}

	/**
	 * 获得特定字符总数
	 * 
	 * @param str
	 * @param chr
	 * @return
	 */
	public static int charCount(String str, char chr) {
		int count = 0;
		if (str != null) {
			int length = str.length();
			for (int i = 0; i < length; i++) {
				if (str.charAt(i) == chr) {
					count++;
				}
			}
			return count;
		}
		return count;
	}

	public static String unescape(String escaped) {
		int length = escaped.length();
		int i = 0;
		StringBuilder sb = new StringBuilder(escaped.length() / 2);

		while (i < length) {
			char n = escaped.charAt(i++);
			if (n != '%') {
				sb.append(n);
			} else {
				n = escaped.charAt(i++);
				int code;

				if (n == 'u') {
					String slice = escaped.substring(i, i + 4);
					code = Integer.valueOf(slice, 16);
					i += 4;
				} else {
					String slice = escaped.substring(i - 1, ++i);
					code = Integer.valueOf(slice, 16);
				}
				sb.append((char) code);
			}
		}

		return sb.toString();
	}

	public static String escape(String raw) {
		int length = raw.length();
		int i = 0;
		StringBuilder sb = new StringBuilder(raw.length() / 2);

		while (i < length) {
			char c = raw.charAt(i++);

			if (CharUtils.isLetterOrDigit(c) || CharUtils.isEscapeExempt(c)) {
				sb.append(c);
			} else {
				int i1 = raw.codePointAt(i - 1);
				String escape = Integer.toHexString(i1);

				sb.append('%');

				if (escape.length() > 2) {
					sb.append('u');
				}
				sb.append(escape.toUpperCase());

			}
		}

		return sb.toString();
	}

	public final static String unificationStrings(String mes) {
		if (isEmpty(mes)) {
			return mes;
		}
		CharArray chars = new CharArray();
		for (int i = 0, size = mes.length(); i < size; i++) {
			char ch = mes.charAt(i);
			if (!chars.contains(ch)) {
				chars.add(ch);
			}
		}
		return chars.getString();
	}

	public final static String unificationStrings(String[] messages) {
		if (isEmpty(messages)) {
			return "";
		}
		CharArray chars = new CharArray();
		for (String text : messages) {
			for (int i = 0, size = text.length(); i < size; i++) {
				char ch = text.charAt(i);
				if (!chars.contains(ch)) {
					chars.add(ch);
				}
			}
		}
		return chars.getString();
	}
}
