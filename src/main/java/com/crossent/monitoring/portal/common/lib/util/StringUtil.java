/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crossent.monitoring.portal.common.lib.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.util.HtmlUtils;



public class StringUtil {

	private StringUtil() {
		throw new AssertionError(); 
	}

	private static final char[] ALPHAS = new char[] { 'A', 'B', 'C', 'D', 'E',
		'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
		'S', 'T', 'U', 'X', 'Y', 'V', 'W', 'Z', 'a', 'b', 'c', 'd', 'e',
		'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		's', 't', 'u', 'x', 'y', 'v', 'w', 'z' };

	public static final String DEFAULT_EMPTY_STRING = "";

	private static final Random GENERATOR = new Random(System
			.currentTimeMillis());

	/** For UTF-8 character set, 1 byte code */
	//private static final int ONE_BYTE = 0x00007F;

	/** For UTF-8 character set, 3 byte code */
	//private static final int THREE_BYTE = 0x00FFFF;

	/** For UTF-8 character set, 2 byte code */
	//private static final int TWO_BYTE = 0x0007FF;

	/**
	 * 입력한 String에 끝에 주어진 길이만큼 공백 문자을 붙인다. - 주어진 길이가 0보다 작을 경우 무시된다.<br>
	 * 
	 * <pre>
	 * 입력 : 12345, 5
	 * 예제 : StringUtil.addSpace(&quot;12345&quot;, 5)
	 * 출력 : &quot;12345     &quot
	 * </pre>
	 * 
	 * @param str
	 *            공백을 붙일 문자열
	 * @param size
	 *            공백문자를 붙일 횟수
	 * @return 옹백문자가 추가된 문자열
	 */
	public static String addSpace(String str, int size) {
		StringBuffer stringBuffer = new StringBuffer();
		if (str == null) {
			if (size == 0) {
				return null;
			}
		} else {
			stringBuffer.append(str);
		}
		for (int j = 0; j < size; j++) {
			stringBuffer.append(' ');
		}
		return stringBuffer.toString();
	}

	/**
	 * 주어진 String[]에 하나의 String을 추가한다. <br>
	 * 
	 * <pre>
	 * 입력 : String[] test = {&quot;aaa&quot;, &quot;bbb&quot;, &quot;ccc&quot;};,		ddd
	 * 예제 :	StringUtil.addStringToArray(test, &quot;ddd&quot;);	 * 
	 * 출력 : String[] result = {&quot;aaa&quot;, &quot;bbb&quot;, &quot;ccc&quot;, &quot;ddd&quot;}
	 * </pre>
	 * 
	 * @param strings
	 *            입력 String 배열
	 * @param str
	 *            추가될 문자열
	 * @return 입력 문자열이 추가된 String[]
	 */
	public static String[] addStringToArray(String[] strings, String str) {
		String newArray[] = new String[strings.length + 1];
		System.arraycopy(strings, 0, newArray, 0, strings.length);
		newArray[strings.length] = str;
		return newArray;
	}

	/**
	 * 입력된 문자열에 두음법칙을 적용하여 반환한다. <br>
	 * <pre>
	 * 입력 : 림업례제
	 * 예제 : StringUtil.applyInitialLaw("림업례제")
	 * 출력 : 임업예제 
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return 투음법칙이 적용된 문자열
	 */
	public static String applyInitialLaw(String str) {

		String[] fstPtnP = { "라", "로", "량", "리", "림", "랑", "류", "뢰", "란" };
		String[] fstPtnN = { "나", "노", "양", "이", "임", "낭", "유", "뇌", "난" };

		String[] sndPtnP = { "례", "륭", "란", "률", "래", "로", "량", "락", "라", "님",
				"림", "련", "년", "니", "리", "륜", "랑", "룰", "린", "람", "녕", "령",
				"롱", "룡", "료", "립", "록", "류", "렬", "릉", "녀", "려", "뇨", "뉴",
				"렴", "념", "닉", "력", "루", "르", "론", "뢰" };
		String[] sndPtnN = { "예", "융", "난", "율", "내", "노", "양", "낙", "나", "임",
				"임", "연", "연", "이", "이", "윤", "낭", "울", "인", "남", "영", "영",
				"농", "용", "요", "입", "녹", "유", "열", "능", "여", "여", "요", "뉴",
				"염", "염", "익", "역", "누", "느", "논", "뇌" };

		String outStr = "";
		String inStrAry[] = null;

		String inputString = str.trim();

		int inStrSize = inputString.length();
		char[] chStrAry = inputString.toCharArray();

		// apply initial law to the first character
		if (inStrSize > 0) {
			inStrAry = new String[inStrSize];

			for (int i = 0; i < inStrSize; i++) {
				inStrAry[i] = String.valueOf(chStrAry[i]);
			}

			for (int i = 0; i < fstPtnP.length; i++) {
				if (inStrAry[0].compareTo(fstPtnP[i]) == 0) {
					inStrAry[0] = fstPtnN[i];
				}
				outStr = inStrAry[0];
			}
		}

		// apply initial law after the first character
		StringBuffer sb = new StringBuffer();

		if (inStrSize > 1) {
			inStrAry = new String[inStrSize];

			for (int i = 1; i < inStrSize; i++) {
				boolean isExsit = false;
				inStrAry[i] = String.valueOf(chStrAry[i]);

				for (int j = 0; j < sndPtnP.length; j++) {
					if (inStrAry[i].compareTo(sndPtnP[j]) == 0
							&& !isExsit) {
						sb.append(sndPtnN[j]);
						isExsit = true;
					}
				}
				if (!isExsit) {
					sb.append(inStrAry[i]);
				}
			}
			outStr += sb.toString();
		}
		return outStr;
	}

	/**
	 * Object[]를 입력으로 받아 콤마로 각 element를 연결하여 String을 생성한다. 
	 * 
	 * @param objects
	 *            각 element를 콤마를 구분자로 하여 연결할 Object[]
	 * @return 콤마로 연결된 문자열
	 */
	public static String arrayToDelimitedString(Object[] objects) {
		return arrayToDelimitedString(objects, ",");
	}

	/**
	 * Object[]를 입력으로 받아 delimiter로 각 element를 연결하여 String을 생성한다. <br>
	 * 
	 * <pre>
	 * 입력 :  String[] test = {"aaa", "bbb", "ccc"};
	 * 예제 : StringUtil.arrayToDelimitedString(test,",")
	 * 출력 : "aaa,bbb,ccc"
	 * </pre>
	 * 
	 * @param objects
	 *            각 element를 콤마를 구분자로 하여 연결할 Object[]
	 * @param delimiter
	 *            각 element 연결시 사용할 구분자
	 * @return delimiter를 구분자로 연결된 문자열
	 */
	public static String arrayToDelimitedString(Object[] objects,
			String delimiter) {
		if (objects == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			if (i > 0 && delimiter != null) {
				sb.append(delimiter);
			}
			sb.append(objects[i]);
		}
		return sb.toString();
	}

	/**
	 * Collection을 입력으로 받아 콤마로 각 element를 연결하여 String을 생성한다. 
	 * 
	 * @param strings
	 *             각 element를 콤마를 구분자로 하여 연결할 Collection
	 * @return 콤마로 연결된 문자열
	 */
	public static String collectionToDelimitedString(Collection<String> strings) {
		return collectionToDelimitedString(strings, ",");
	}

	/**
	 * Collection을 입력으로 받아 delimiter로 각 element를 연결하여 String을 생성한다. 
	 * 
	 * @param strings
	 *             각 element를 콤마를 구분자로 하여 연결할 Collection
	 * @param delimiter
	 *             각 element 연결시 사용할 구분자
	 * @return delimiter를 구분자로 연결된 문자열
	 */
	public static String collectionToDelimitedString(
			Collection<String> strings, String delimiter) {
		if (strings == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = strings.iterator();
		int i = 0;
		for (; it.hasNext(); sb.append(it.next())) {
			if (i++ > 0 && delimiter != null) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	/**
	 * 주어진 String에 대해서 "," 를 이용하여 분리하여 후 Set으로 변환한다.
	 * 
	 * @param str
	 *            콤마로 연결된 문자열
	 * @return 콤마로 분리된 set 
	 */
	public static Set<String> commaDelimitedStringToSet(String str) {
		Set<String> set = new HashSet<String>();
		String tokens[] = tokenizeToStringArray(str);
		if (tokens == null) {
			return null;
		}
		for (int i = 0; i < tokens.length; i++) {
			set.add(tokens[i]);
		}
		return set;
	}

	/**
	 * 입력인자로 전달된 문자열에 '*'나 '**'가 있으면 공백으로 변환한다. <br>
	 * 
	 * <pre>
	 * 입력 :  test**test
	 * 예제 : StringUtil.asteriskToSpace("test**test")
	 * 출력 : "test  test"
	 * </pre>
	 * 
	 * @param str
	 *            변환할 문자열
	 * @return 변환된 문자열
	 */
	public static String asteriskToSpace(String str) {
		String target = "";
		target = str.replaceAll("\\*\\*", " ");
		target = target.replaceAll("\\*", " ");
		return target;
	}

	/**
	 * 입력된 문자열의 첫번째 문자의 case를 변환한다.  <br>
	 * 
	 * <pre>
	 * 입력 :  Password
	 * 예제 : StringUtil.swapFirstLetterCase("Password")
	 * 출력 : password
	 * <br>
	 * 입력 :  password
	 * 예제 : StringUtil.swapFirstLetterCase("password")
	 * 출력 : Password
	 * </pre>
	 *
	 * @param str
	 *            변환할 문자열
	 * @return 변환된 문자열
	 */
	public static String swapFirstLetterCase(String str) {
		if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0])) {
			return changeFirstCharacterCase(true, str);
		} else {
			return changeFirstCharacterCase(false, str);
		}
	}

	private static String changeFirstCharacterCase(boolean capitalize,
			String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	/**
	 * 두 문자열을 사전식으로 비교한다.
	 * 
	 * <pre>
	 * 입력 :  Anyframe Java Test, Anyframe Java Test
	 * 예제 : StringUtil.compareTo("Anyframe Java Test", "Anyframe Java Test")
	 * 출력 : 0
	 * <br>
	 * 입력 :  anyframe java test, Anyframe Java Test
	 * 예제 : assertEquals(32, StringUtil.compareTo("anyframe java test", "Anyframe Java Test")
	 * 출력 : 32 // 97(a's ascii code) - 65(A's ascii code) = 32
	 * </pre>
	 * 
	 * @param sourceStr
	 *            비교할 기준 문자열
	 * @param targetStr
	 * 			  비교할 대상 문자열
	 * 
	 * @return
	 * 			두문자열을 사전식으로 비교하여 동일한 문자열인 경우 0 리턴, sourceStr이 targetStr이 사전식 순서가 빠른 경우 
	 * 음수 리턴, sourceStr이 targetStr이 사전식 순서가 이후인 경우 양수 리턴 
	 *            
	 * @see String#compareTo(String)
	 */
	public static int compareTo(String sourceStr, String targetStr) {
		if (sourceStr == null || targetStr == null) {
			return -1;
		}
		return sourceStr.compareTo(targetStr);
	}

	/**
	 * 두 문자열을 대소문자 구분없이 사전식으로 비교한다.
	 * 
	 * <pre>
	 * 입력 :  anyframe java test, Anyframe Java Test
	 * 예제 : StringUtil.compareToIgnoreCase("anyframe java test", "Anyframe Java Test")
	 * 출력 : 0
	 * </pre>
	 * 
	 * @param sourceStr
	 *            비교할 기준 문자열
	 * @param targetStr
	 *            비교할 대상 문자열
	 * @return 두문자열을 대소문자 구분없이 사전식으로 비교하여 동일한 문자열인 경우 0 리턴, sourceStr이 targetStr이 사전식 순서가 빠른 경우 
	 * 음수 리턴, sourceStr이 targetStr이 사전식 순서가 이후인 경우 양수 리턴
	 * @see String#compareToIgnoreCase(String)
	 */
	public static int compareToIgnoreCase(String sourceStr, String targetStr) {
		if (sourceStr == null || targetStr == null) {
			return -1;
		}
		return sourceStr.compareToIgnoreCase(targetStr);
	}

	/**
	 * 입력문자열에 char[]에 존재하는 문자중에 하나라도 포함되어 있는지 체크한다. 
	 * 
	 * <pre>
	 * 입력 :  null, *
	 * 예제 : StringUtil.containsAny(null, *)
	 * 출력 : false
	 * <br>
	 * 입력 :  *, null
	 * 예제 : StringUtil.containsAny(*, null)
	 * 출력 : false
	 * <br>
	 * 입력 :  abab, "xyz".toCharArray()
	 * 예제 : StringUtil.containsAny("abab", "xyz".toCharArray())
	 * 출력 : false
	 * <br>
	 * 입력 :  abc/, new char[] {'*','/'}
	 * 예제 : StringUtil.containsAny("abc/", new char[] { '*', '/' })
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param chars
	 *            체크할 해당 문자 배열
	 * @return chars에 char[]에 포함된 문자가 하나라도 존재하는 경우 true를 리턴 
	 */
	public static boolean containsAny(String str, char[] chars) {
		if (str == null || chars == null) {
			return false;
		}

		return containsAnyChar(str, chars);
	}

	private static boolean containsAnyChar(String str, char[] chars) {
		int strSize = str.length();
		int validSize = chars.length;
		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < validSize; j++) {
				if (chars[j] == ch) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * chars에 존재하는 문자중에 하나라도 str에 존재하는지 여부를검사한다
	 * 
	 * <pre>
	 * 입력 :  null, *
	 * 예제 : StringUtil.containsAny(null, *)
	 * 출력 : false
	 * <br>
	 * 입력 :  *, null
	 * 예제 : StringUtil.containsAny(*, null)
	 * 출력 : false
	 * <br>
	 * 입력 :  abab, xyz
	 * 예제 : StringUtil.containsAny("abab", "xyz")
	 * 출력 : false
	 * <br>
	 * 입력 :  xbz, xyz
	 * 예제 : StringUtil.containsAny("xbz", "xyz")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param chars
	 *            체크할 해당 문자열
	 * @return chars에 char[]에 포함된 문자가 하나라도 존재하는 경우 true를 리턴
	 */
	public static boolean containsAny(String str, String chars) {
		if (str == null || chars == null) {
			return false;
		}
		return containsAnyChar(str, chars.toCharArray());
	}

	/**
	 * 입력인자로 전달된 String에 maxSeqNumber 이상으로 연속으로 반복하는 문자가 있는지 검사한다
	 * 
	 * <pre>
	 * 입력 :  password, 2
	 * 예제 : StringUtil.containsMaxOccurences("password", "2")
	 * 출력 : true
	 * <br>
	 * 입력 :  my000, 3
	 * 예제 : StringUtil.containsMaxOccurences("my000", "3")
	 * 출력 : true
	 * <br>
	 * 입력 :  abbbbc, 5
	 * 예제 : StringUtil.containsMaxOccurences("abbbbc", "5")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            체크할 문자열
	 * @param maxSeqNumber
	 *            연속된 문자의 반복 횟수
	 * @return maxSeqNumber 이상으로 연속으로 반복된 문자가 있는경우 true 를 리턴 
	 */
	public static boolean containsMaxOccurences(String str, String maxSeqNumber) {
		int occurence = 1;
		int max = NumberUtil.stringToInt(maxSeqNumber);
		if (str == null) {
			return false;
		}

		int sz = str.length();
		for (int i = 0; i < (sz - 1); i++) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				occurence++;

				if (occurence == max){
					return true;
				}
			} else {
				occurence = 1;
			}
		}
		return false;
	}

	/**
	 * 입력된 underscore(_) 형태의 문자열을 camel case 형태로 변환
	 * 
	 * <pre>
	 * 입력 :  anyframe_java_test
	 * 예제 : StringUtil.convertToCamelCase("anyframe_java_test")
	 * 출력 : anyframeJavaTest
	 * </pre>
	 * 
	 * @param str
	 *            _로 구분渦?있는 문자열
	 * @return 변환된 camel case 형태의 문자열
	 */
	public static String convertToCamelCase(String str) {
		return convertToCamelCase(str, '_');
	}

	/**
	 * 주어진 delimiter의 형태에 맞게 잘라진 문자열을 camel case 형태로 변환
	 * 
	 * <pre>
	 * 입력 :  anyframe-java-test, -
	 * 예제 : StringUtil.convertToCamelCase("anyframe-java-test", '-')
	 * 출력 : anyframeJavaTest
	 * </pre>
	 * 
	 * @param str
	 *            camel case 로 변환할 문자열
	 * @param delimiter
	 * 			  입력 문자열의 구분자
	 * @return 변환된 camel case 형태의 문자열 
	 */
	public static String convertToCamelCase(String str, char delimiter) {
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		String allLower = str.toLowerCase();

		for (int i = 0; i < allLower.length(); i++) {
			char currentChar = allLower.charAt(i);
			if (currentChar == delimiter) {
				nextUpper = true;
			} else {
				if (nextUpper) {
					currentChar = Character.toUpperCase(currentChar);
					nextUpper = false;
				}
				result.append(currentChar);
			}
		}
		return result.toString();
	}

	/**
	 * camel case 형태의 문자열을 under score 형태의 문자열로 변환
	 * 
	 * <pre>
	 * 입력 :  anyframeJavaTest
	 * 예제 : StringUtil.convertToUnderScore("anyframeJavaTest")
	 * 출력 : anyframe_java_test
	 * </pre>
	 * 
	 * @param str
	 *            변환할 camel case 형태의 문자열
	 * @return 변환된 underscore 형태의 문자열
	 */
	public static String convertToUnderScore(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			// This is starting at 1 so the result does not end up with an
			// underscore at the begin of the value
			if (i > 0 && Character.isUpperCase(currentChar)) {
				result = result.concat("_");
			}
			result = result.concat(Character.toString(currentChar)
					.toLowerCase());
		}
		return result;
	}

	/**
	 * 첫번째 문자열과 두번째 문자열을 비교해서 같으면 세번째 문자열을 다르면 네번재 문자열을 반환한다. 
	 * 
	 * <pre>
	 * 입력 :  Java, Test, Good, Bad
	 * 예제 : StringUtil.decode("Java", "Test", "Good", "Bad")
	 * 출력 : Bad
	 * <br>
	 * 입력 :  Java, Java, Good, Bad
	 * 예제 : StringUtil.decode("Java", "Java", "Good", "Bad")
	 * 출력 : Good
	 * </pre>
	 * 
	 * @param source
	 *            비교할 문자열
	 * @param target
	 *            source와 비교할 문자열
	 * @param result
	 *            두 문자열이 같은 경우 반환된 결과 문자열
	 * @param base
	 *            두 문자열이 같지 않은 경우 반환될 문자열
	 * @return 비교결과에 따른 문자열
	 */
	public static String decode(String source, String target, String result,
			String base) {
		if (source == null && target == null) {
			return result;
		} else if (source == null && target != null) {
			return base;
		} else if (source.trim().equals(target)) {
			return result;
		}
		return base;
	}


	/**
	 * 주어진 문자열에 대해서 해당하는 char[]이 포함되어 있는 회수 반환
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test, new char[] { 'a' }
	 * 예제 : StringUtil.countMatches("Anyframe Java Test", new char[] { 'a' })
	 * 출력 : 3
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param chars
	 *            검색할 문자배열 
	 * @return 입력 문자열에서 char[]이 존재하는 횟수
	 */
	public static int countMatches(String str, char[] chars) {
		return countMatches(str, new String(chars));
	}

	/**
	 * 주어진 문자열에 대해서 해당하는 문자열이 포함되어 있는 회수 반환
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test, test
	 * 예제 : StringUtil.countMatches("Anyframe Java Test", "test")
	 * 출력 : 0
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열.
	 * @param sub
	 *            검색할 문자열
	 * @return 입력 문자열에서 검색할 문자열이 존재하는 횟수
	 */
	public static int countMatches(String str, String sub) {
		return org.springframework.util.StringUtils
				.countOccurrencesOf(str, sub);
	}

	/**
	 * 주어진 문자열에 대해서 해당하는 대소문자예 관계없이 char[]이 포함되어 있는 회수 반환
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test, new char[] { 'a' }
	 * 예제 : StringUtil.countMatchesIgnoreCase("Anyframe Java Test", new char[] { 'a' })
	 * 출력 : 4
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param chars
	 *            검색할 문자배열 
	 * @return 대소문자 구분없이 입력 문자열에서 char[]이 존재하는 횟수
	 */
	public static int countMatchesIgnoreCase(String str, char[] chars) {
		char[] lowerChar = new char[chars.length];
		for (int j = 0; j < chars.length; j++) {
			String res = String.valueOf(chars[j]).toLowerCase();
			lowerChar[j] = res.charAt(0);
		}
		return countMatches(str.toLowerCase(), lowerChar);
	}

	/**
	 * 주어진 문자열에 대해서 해당하는 대소문자예 관계없이 문자열이 포함되어 있는 회수 반환
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test, test
	 * 예제 : StringUtil.countMatchesIgnoreCase("Anyframe Java Test", "test")
	 * 출력 : 1
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param sub
	 *            검색할 문자배열
	 * @return 대소문자 구분없이 입력 문자열에서 검색문자열이 존재하는 횟수
	 *            
	 * @see org.springframework.util.StringUtils#countOccurrencesOf(String,
	 *      String)
	 */
	public static int countMatchesIgnoreCase(String str, String sub) {
		return org.springframework.util.StringUtils.countOccurrencesOf(str
				.toLowerCase(), sub.toLowerCase());
	}

	/**
	 * 입력된 문자열을 주어진 token에 대해서 분리 후 마지막 문자열 반환<br>
	 * 
	 * <pre>
	 * 입력 : Anyframe_Java_Test, _
	 * 예제 : StringUtil.getLastString("Anyframe_Java_Test", "_")
	 * 출력 : Test
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param token
	 *            분리할 토큰 문자열
	 * @return 마지막으로 분리된 문자열
	 */
	public static String getLastString(String str, String token) {
		StringTokenizer tokenizer = new StringTokenizer(str, token);
		String lastStr = "";
		while (tokenizer.hasMoreTokens()) {
			lastStr = tokenizer.nextToken();
		}
		return lastStr;
	}

	/**
	 * 전달받은 스트링의 길이를 반환.
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test
	 * 예제 : StringUtil.getLength("Anyframe Java Test")
	 * 출력 : 18
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return 입력문자열의 길이
	 * @see String#length()
	 */
	public static int getLength(String str) {
		if (str == null) {
			return -1;
		}
		return str.length();
	}

	/**
	 * 특정한 길이 만큼의 랜덤한 문자열을 반환한다.
	 * 
	 * <pre>
	 * 입력 : 8
	 * 예제 : StringUtil.getRandomString(8)
	 * 출력 : BzMOUnvP (예시)
	 * </pre>
	 * 
	 * @param size
	 *            생성할 랜덤 문자열의 길이
	 * @return 특정 길이를 가진 임의의 문자열
	 */
	public static String getRandomString(int size) {
		return randomAlphabetic(size);
	}

	/**
	 * 최소자리수, 최대자리수 사이의 랜덤한 자리수를 가진 임의의 문자열을 반환한다.
	 * 
	 * <pre>
	 * 입력 : 10, 15
	 * 예제 : StringUtil.getRandomString(10, 15)
	 * 출력 : nGhMlImAcm (예시)
	 * </pre>
	 * 
	 * @param minSize
	 *            최소자리수
	 * @param maxSize
	 *            최대길이
	 * @return 최소자리수, 최대자리수 사이의 랜덤한 자리수를 가진 임의의 문자열
	 */
	public static String getRandomString(int minSize, int maxSize) {
		Random generator = new Random(System.currentTimeMillis());
		int randomLength = generator.nextInt(maxSize - minSize) + minSize;

		return randomAlphabetic(randomLength);
	}

	/**
	 * 특정한 알파벳 사이의 지정된 길이만큼의 랜덤한 문자열을 반환한다.
	 * 
	 * <pre>
	 * 입력 : 10, a, e
	 * 예제 : StringUtil.getRandomString(10, 'a', 'e')
	 * 출력 : dabdceadca (예시)
	 * </pre>
	 * 
	 * @param size
	 *            생성된 문자열의 길이
	 * @param startChar
	 *            생성할 랜덤 문자의 시작 알파벳
	 * @param endChar
	 *            생성할 랜덤 문자의 마지막 알파벳
	 * @return 특정길이를 가진 임의의 문자열
	 */
	public static String getRandomString(int size, char startChar, char endChar) {
		int startInt = Integer.valueOf(startChar);
		int endInt = Integer.valueOf(endChar);

		int gap = endInt - startInt;
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int chInt;
			do {
				chInt = StringUtil.GENERATOR.nextInt(gap + 1) + startInt;
			} while (!Character.toString((char) chInt).matches("^[a-zA-Z]$"));
			buf.append((char) chInt);
		}
		return buf.toString();
	}

	/**
	 * 특정한 길이만큼의 주어진 캐릭터 셋 문자열을 반환한다.
	 * 
	 * <pre>
	 * 입력 : 20, UTF-8
	 * 예제 : StringUtil.getRandomStringByCharset(20, "UTF-8")
	 * 출력 : RAGpKLzhjPpBrOTKyaWb (예시)
	 * </pre>
	 * 
	 * @param size
	 *            랜덤으로 새성할 문자열의 길이
	 * @param charset
	 *            생성될 문자열의 charset
	 * @return 특정길이를 가진 임의의 문자열
	 */
	public static String getRandomStringByCharset(int size, String charset) {
		return getRandomString(size);
	}

	/*
	 * 특정한 길이만큼의 한글 문자열을 반환한다. (UTF-8
	 * only).
	 * 
	 * @param size
	 *            length of random string to be made
	 * @return String random string in Korean characters
	 * @throws UnsupportedEncodingException
	 */
	//	public static String getRandomStringByKorean(int size)
	//			throws UnsupportedEncodingException {
	//		StringBuilder buf = new StringBuilder();
	//		for (int i = 0; i < size; i++) {
	//			buf.append((char) (StringUtil.GENERATOR.nextInt(11172) + 0xAC00));
	//		}
	//		return buf.toString();
	//	}

	/**
	 * 입력된 문자열을 콤마로 분리하여 List형태로 반환
	 * 
	 * <pre>
	 * 입력 : "Anyframe,Java,Test"
	 * 예제 : StringUtil.getTokens("Anyframe,Java,Test")
	 * 출력 : Anyframe, Java, Test 
	 * </pre>
	 * 
	 * @param str
	 *            콤마로 구분되어 있는 문자열
	 * @return 콤마롤 기준으로 분리되어 있는 문자열 List
	 */
	public static List<String> getTokens(String str) {
		return getTokens(str, ",");
	}

	/**
	 * 입력된 문자열을 delimeter 로 분리하고 각 토큰을 trim 처리하여 List형태로 반환 
	 * 
	 * <pre>
	 * 입력 : "Anyframe/Java/Test"
	 * 예제 : StringUtil.getTokens("Anyframe/Java/Test", "/")
	 * 출력 : Anyframe, Java, Test 
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param delimeter
	 *            토큰으로 분리할 구분자 문자열
	 * @return delimeter 롤 기준으로 분리되어 있는 문자열 List
	 */
	public static List<String> getTokens(String str, String delimeter) {
		List<String> tokens = new ArrayList<String>();

		if (str != null) {
			StringTokenizer st = new StringTokenizer(str, delimeter);
			while (st.hasMoreTokens()) {
				String en = st.nextToken().trim();
				tokens.add(en);
			}
		}
		return tokens;
	}
	
	/**
	 * 입력된 문자열을 delimeter 로 분리하여 각토큰의 trim 여부를 입력받아 List형태로 반환
	 * @param str 입력 문자열
	 * @param delimeter 토큰으로 분리할 구분자 문자열
	 * @param trim 토큰을 trim 처리 할지 여부 
	 * @return delimeter 롤 기준으로 분리되어 있는 문자열 List
	 */
	public static List<String> getTokens(String str, String delimeter, boolean trim){
		
		List<String> tokens = new ArrayList<String>();
		if (str != null) {
			StringTokenizer tokenizer = new StringTokenizer(str, delimeter);
			while (tokenizer.hasMoreTokens()) {
				String token = trim ? tokenizer.nextToken().trim() : tokenizer.nextToken();
				tokens.add(token);
			}
		}
		return tokens;
		
	}

	/**
	 * 주어진 String 객체가 0보다 큰 길이를 가지고 있는지 검사한다. <br>
	 * 
	 * <pre>
	 * 입력 : test
	 * 예제 : StringUtil.hasLength("test")
	 * 출력 : true
	 * <br>
	 * 입력 : "" (empty string)
	 * 예제 : StringUtil.hasLength("")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열의 길이가 0 보다 큰 경우 true를 리턴
	 */
	public static boolean hasLength(String str) {
		return !isEmpty(str);
	}

	/**
	 * 주어진 String 객체가 Whitespace 가 아닌 문자를 가지고 있는지 검사한다. <br>
	 * 
	 * <pre>
	 * 입력 : " test "
	 * 예제 : StringUtil.hasText(" test ")
	 * 출력 : true 
	 * <br>
	 * 입력 : " " (space)
	 * 예제 : StringUtil.hasText(" ") 
	 * 출력 : false
	 * <br>
	 * 입력 : "" (empty string)
	 * 예제 : StringUtil.hasText("") 
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check
	 * @return 입력문자열이 WhilteSpace가 아닌 문자를 가지고 있는 경우 true를 리턴
	 */
	public static boolean hasText(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 코드를 받아 문자열로 변환함 (유니코드)
	 * 
	 * @param str
	 *            변환할 문자열
	 * @return UniCode String
	 */
	public static String hexToString(String str) {
		String inStr = str;
		char inChar[] = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < inChar.length; i += 4) {
			String hex = str.substring(i, i + 4);
			sb.append((char) Integer.parseInt(hex, 16));
		}
		return sb.toString();
	}

	/**
	 * 문자열에서 검색 문자열이 포함되어 있는 경우 그 첫번째 인덱스를 반환한다. 
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test, Java
	 * 예제 : StringUtil.indexOf("Anyframe Java Test", "Java")
	 * 출력 : 9
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param search
	 *            검색 문자열
	 * @return 검색 문자열이 존재하는 첫번째 인덱스, 존재하지 않는 경우 -1 리턴
	 * @see String#indexOf(String)
	 */
	public static int indexOf(String str, String search) {
		if (str == null || search == null) {
			return -1;
		}
		return str.indexOf(search);
	}

	/**
	 * 대소문자를 구분없이 입력 문자열에서 검색 문자열이 포함되어 있는 경우 그 첫번째 인덱스를 반환한다.
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test, java
	 * 예제 : StringUtil.indexOfIgnoreCase("Anyframe Java Test", "java")
	 * 출력 : 9
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param search
	 *            검색문자열
	 * @return 대소문자 구분 없이 검색 문자열이 존재하는 첫번째 인덱스, 존재하지 않는 경우 -1 리턴
	 * @see String#indexOf(String)
	 */
	public static int indexOfIgnoreCase(String str, String search) {
		if (str == null || search == null) {
			return -1;
		}
		return str.toLowerCase().indexOf(search.toLowerCase());
	}

	/**
	 * 주어진 문자열이 null 또는 ""(empty string)인지 체크
	 * 
	 * <pre>
	 * 입력 : "" (empty string)
	 * 예제 : StringUtil.isEmpty("")
	 * 출력 : true
	 * <br>
	 * 입력 : null
	 * 예제 : StringUtil.isEmpty(null) 
	 * 출력 : true
	 * <br>
	 * 입력 : abc
	 * 예제 : StringUtil.isEmpty(abc) 
	 * 출력 : false
	 * <br>
	 * 입력 : "       "
	 * 예제 : StringUtil.isEmpty("       ") 
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return 입력 문자열이 null 이거나 ""(empty string)인 경우 true를 리턴
	 */
	public static boolean isEmpty(String str) {		
		if (str == null || "".equals(str)) return true;
		return false;
	}

	/**
	 * trim한 문자열이 null 또는 공백인지 판별
	 * 
	 * <pre>
	 * 입력 : "       "
	 * 예제 : StringUtil.isEmptyTrimmed("       ") 
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return null 이거나 공백인 경우 true를 리턴
	 */
	public static boolean isEmptyTrimmed(String str) {
		if (str == null){
			return true;
		}
		return isEmpty(str.trim());
	}

	/**
	 * 주어진 문자열이 null 또는 ""(empty String)이 아닐 경우 참 반환
	 * 
	 * <pre>
	 * 입력 : "" (empty string)
	 * 예제 : StringUtil.isNotEmpty("") 
	 * 출력 : false
	 * <br>
	 * 입력 : null
	 * 예제 : StringUtil.isNotEmpty(null) 
	 * 출력 : false
	 * <br>
	 * 입력 : abc
	 * 예제 : StringUtil.isNotEmpty("abc") 
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 null 또는 "" 이 아닌 경우 true 리턴
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 주어진 String 객체에 대해서 주어진 길이만큼 왼쪽 부분을 떼어 반환한다.
	 * 
	 * <pre>
	 * 입력 : "1234567", 3
	 * 예제 : StringUtil.left("1234567", 3)
	 * 출력 : "123"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            추출할 문자열의 길이 
	 * @return size 만큼 왼쪽 부분 문자열
	 */
	public static String left(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return str.substring(0, size);
		}
	}

	/**
	 * 주어진 문자열에 대해서 입력된 길이만큼 부족한 길이를 왼쪽부터 공백으로 채워넣음. <br>
	 * 
	 * <pre>
	 * 입력 : "" (empty string)
	 * 예제 : StringUtil.leftPad("", 3)
	 * 출력 : "   "
	 * <br>
	 * 입력 : bat, 5
	 * 예제 : StringUtil.leftPad("bat", 5)
	 * 출력 : "  bat"
	 * <br>
	 * 입력 : bat, 1
	 * 예제 : StringUtil.leftPad("bat", 1)
	 * 출력 : "bat"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *           공백을 포함할 전체 문자열의 길이 
	 * @return 공백이 왼쪽에 추가된 문자열
	 */
	public static String leftPad(String str, int size) {
		return leftPad(str, size, ' ');
	}
	
	/**
	 * 주어진 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 왼쪽부터 공백으로 채워넣음. <br>
	 * 
	 * <pre>
	 * 입력 : "" (empty string), 3
	 * 예제 : StringUtil.leftBytesPad("", 3)
	 * 출력 : "   "
	 * <br>
	 * 입력 : 한글AB, 8
	 * 예제 : StringUtil.leftBytesPad("한글AB", 8)
	 * 출력 : "  한글AB"
	 * <br>
	 * 입력 : 가나다, 1
	 * 예제 : StringUtil.leftBytesPad("가나다", 1)
	 * 출력 : "가나다"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *           공백을 포함할 전체 문자열의 바이트 수 
	 * @return 공백이 왼쪽에 추가된 문자열
	 */
	public static String leftBytesPad(String str, int size) {
		return leftBytesPad(str, size, ' ');
	}

	/**
	 * 해당하는 문자열에 대해서 입력된 길이만큼 부족한 길이를 왼쪽부터 지정된 character로 채워넣는다. <br>
	 *
	 * <pre>
	 * 입력 : "" (empty string), 3, 'z'
	 * 예제 : StringUtil.leftPad("", 3, 'z')
	 * 출력 : "zzz"
	 * <br>
	 * 입력 : "bat", 5, 'z'
	 * 예제 : StringUtil.leftPad("bat", 5, 'z')
	 * 출력 : "zzbat"
	 * <br>
	 * 입력 : "bat", 1, 'z'
	 * 예제 : StringUtil.leftPad("bat", 1, 'z')
	 * 출력 : "bat"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            padChar을 포함할 전체 문자열의 길이
	 * @param padChar
	 *            채워넣을 문자
	 * @return 입력문자열이 길이 만큼 왼쪽에 채워진 문자열 
	 */
	public static String leftPad(String str, int size, char padChar) {
		return padString(str, size, String.valueOf(padChar), true);
	}
	
	/**
	 * 해당하는 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 왼쪽부터 지정된 character로 채워넣는다. <br>
	 *
	 * <pre>
	 * 입력 : "" (empty string), 3, '가'
	 * 예제 : StringUtil.leftBytesPad("", 3, '가')
	 * 출력 : "가"
	 * <br>
	 * 입력 : "입력문자열", 12, 'z'
	 * 예제 : StringUtil.leftBytesPad("입력문자열", 12, 'z')
	 * 출력 : "zz입력문자열"
	 * <br>
	 * 입력 : "테스트", 1, 'z'
	 * 예제 : StringUtil.leftBytesPad("테스트", 1, 'z')
	 * 출력 : "테스트"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            padChar을 포함할 전체 문자열의 바이트 수
	 * @param padChar
	 *            채워넣을 문자
	 * @return 입력문자열이 길이 만큼 왼쪽에 채워진 문자열 
	 */
	public static String leftBytesPad(String str, int size, char padChar) {
		return padByteString(str, size, String.valueOf(padChar), true);
	}

	/**
	 * 입력한 문자열에 대해서 입력된 길이만큼 부족한 길이를 왼쪽부터 지정된 문자열로 채워넣는다.<br>
	 *
	 * <pre>
	 * 입력 : "bat", 8, "yz"
	 * 예제 : StringUtil.leftPad("bat", 8, "yz")
	 * 출력 : "yzyzybat"
	 * <br>
	 * 입력 : "bat", 5, null
	 * 예제 : StringUtil.leftPad("bat", 5, null)
	 * 출력 : "  bat"
	 * <br>
	 * 입력 : "bat", 1, "yz"
	 * 예제 : StringUtil.leftPad("bat", 1, "yz")
	 * 출력 : "bat"
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param size
	 *            padStr를 포함한 전체 문자열의 길이
	 * @param padStr
	 *            채워넣을 문자열
	 * @return 전체 길이가 size가 되도록 padStr이 왼쪽에 추가된 문자열
	 */
	public static String leftPad(String str, int size, String padStr) {
		return padString(str, size, padStr, true);
	}
	
	/**
	 * 입력한 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 왼쪽부터 지정된 문자열로 채워넣는다.<br>
	 *
	 * <pre>
	 * 입력 : "테스트Test", 13, "가나다"
	 * 예제 : StringUtil.leftBytesPad("테스트Test", 13, "가나다")
	 * 출력 : "가테스트Test"
	 * <br>
	 * 입력 : "한글포함AB", 11, null
	 * 예제 : StringUtil.leftBytesPad("한글포함AB", 11, null)
	 * 출력 : " 한글포함AB"
	 * <br>
	 * 입력 : "한글", 1, "yz"
	 * 예제 : StringUtil.leftBytesPad("한글", 1, "yz")
	 * 출력 : "한글"
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @param size
	 *            padStr를 포함한 전체 문자열의 바이트 수
	 * @param padStr
	 *            채워넣을 문자열
	 * @return 전체 길이가 size가 되도록 padStr이 왼쪽에 추가된 문자열
	 */
	public static String leftBytesPad(String str, int size, String padStr) {
		return padByteString(str, size, padStr, true);
	}

	/**
	 * 문자열의 왼쪽의 공백 문자열 제거
	 * 
	 * <pre>
	 * 입력 : "   Anyframe Java Test"
	 * 예제 : StringUtil.leftTrim("   Anyframe Java Test")
	 * 출력 : "Anyframe Java Test"
	 * <br>
	 * 입력 : "   Anyframe Java Test   "
	 * 예제 : StringUtil.leftTrim("   Anyframe Java Test   ")
	 * 출력 : "Anyframe Java Test   "
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 왼쪽 공백이 제거된 문자열
	 * @see org.springframework.util.StringUtils#trimLeadingWhitespace(String)
	 */
	public static String leftTrim(String str) {
		return org.springframework.util.StringUtils.trimLeadingWhitespace(str);
	}

	/**
	 * CRLF(newLine)가 포함된 문자열을 입력인자로 받아 CRLF(개행문자)를 SPACE로 변환하여 리턴한다. ("\r\ntest") => " test"
	 * 
	 * @param str
	 *            입력문자열
	 * @return CRLF가 공백으로 변환된 문자열
	 */
	public static String newLineToSpace(String str) {
		return str.replace("\r\n", " ");
	}

	/**
	 * 입력문자열이 null 인 경우 "" 로 변환하여 반환한다.
	 * 
	 * <pre>
	 * 입력 : null
	 * 예제 : StringUtil.nullToString(null)
	 * 출력 : "" (empty string)
	 * <br>
	 * 입력 : "Anyframe Java Test"
	 * 예제 : StringUtil.nullToString("Anyframe Java Test")
	 * 출력 : "Anyframe Java Test"
	 * </pre>
	 * 
	 * @param org
	 *            입력문자열
	 * @return 변환된 문자열
	 */
	public static String nullToString(String org) {
		return nullToString(org, "");
	}

	/**
	 * 입력문자열이 null 인 경우 defaultStr을 반환한다.
	 * 
	 * <pre>
	 * 입력 : null, "empty"
	 * 예제 : StringUtil.nullToString(null, "empty")
	 * 출력 : "empty"
	 * <br>
	 * 입력 : "" (empty string), "empty"
	 * 예제 : StringUtil.nullToString("", "empty")
	 * 출력 : "empty"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param defaultStr
	 *            입력문자열이 null인 경우 반환될 문자열
	 * @return 변환된 문자열
	 */
	public static String nullToString(String str, String defaultStr) {
		if (isEmpty(str)) {
			return defaultStr;
		} else {
			return str;
		}
	}

	/**
	 * 입력된 object가 null인 경우 defaultObj를 반환한다.
	 * 
	 * <pre>
	 * 입력 : null, "NULL TEST"
	 * 예제 : String test = null; StringUtil.nullToObject(test, "NULL TEST")
	 * 출력 : "NULL TEST"
	 * <br>
	 * 입력 : "" (empty string), "empty"
	 * 예제 : String test = "test"; StringUtil.nullToObject(test, "NULL TEST"))
	 * 출력 : "test"
	 * </pre> 
	 * 
	 * @param obj
	 *            입력 object
	 * @param defaultObj
	 *            입력 object가 null인 경우 반환될 object
	 * @return 결과 object
	 */
	public static Object nullToObject(Object obj, Object defaultObj) {
		return obj != null ? obj : defaultObj;
	}

	/**
	 * 특정한 문자(char)와 일정한 길이 값을 입력으로 받아 해당 크기만큼 문자가 반복되는 문자열을 생성한다.
	 * 
	 * <pre>
	 * 입력 : 5, e
	 * 예제 : StringUtil.repeat(5, 'e')
	 * 출력 : "eeeee"
	 * </pre> 
	 * 
	 * @param size
	 *            생성될 문자열의 길이
	 * @param ch
	 *            반복할 문자
	 * @return 입력문자가 길이반큼 반복된 문자열
	 */
	public static String repeat(int size, char ch) {
		return leftPad("", size, ch);
	}

	private static String padString(String str, int size, String padStr,
			boolean isLeft) {

		String inputString = str;
		String padString = padStr;
		
		if (inputString == null) {
			return null;
		}
		int originalStrLength = inputString.length();

		if (size < originalStrLength){
			return inputString;
		}

		int difference = size - originalStrLength;

		if (difference > 0) {			
			if (padString == null || "".equals(padString)) {
				padString = " ";
			}
			
			String tempPad = null;
			int padStrLen = padString.length();
			int inputLen = inputString.length();
			
			// 
			if (padStrLen == 1) {
				char padChar = padString.charAt(0);
				char[] tempChars = new char[difference];
				for (int i = 0; i < tempChars.length; i++) {
					tempChars[i] = padChar;
				}			
				tempPad = new String(tempChars);
				
			}else {
				
				StringBuilder tempBuilder = new StringBuilder(difference);
				do {
					for (int j = 0; j < padStrLen; j++) {
						tempBuilder.append(padString.charAt(j));
						if (inputLen + tempBuilder.length() >= size) {
							break;
						}
					}
				} while (difference > tempBuilder.length());
				
				tempPad = tempBuilder.toString();
			}
						
			if (isLeft) {
				inputString = tempPad + inputString;
			} else {
				inputString = inputString + tempPad;
			}
		}

		return inputString;
	}

	/**
	 * String으로 표현된 path 정보를 표준화한다.
	 * 
	 * <pre>
	 * 입력 : "../aaaa\\bbbb\\cccc\\dddd"
	 * 예제 : StringUtil.pathClean("../aaaa\\bbbb\\cccc\\dddd")
	 * 출력 : "aaaa/bbbb/cccc/dddd"
	 * </pre>
	 * 
	 * @param path 표준화할 경로 문자열 
	 *            
	 * @return "/" 로 구분된 경로 문자열 
	 */
	public static String pathClean(String path) {
		if (path == null) {
			return null;
		}
		String p = replaceSubString(path, "\\", "/");

		String pArray[] = tokenizeToStringArray(p, "/");
		List<String> pList = new LinkedList<String>();
		int tops = 0;
		for (int i = pArray.length - 1; i >= 0; i--) {
			if (".".equals(pArray[i])) {
				continue;
			} else if ("..".equals(pArray[i])) {
				tops++;
				continue;
			}
			if (tops > 0) {
				tops--;
			} else {
				pList.add(0, pArray[i]);
			}
		}
		return collectionToDelimitedString(pList, "/");
	}

	/**
	 * 주어진 두 개의 path 정보 String에 대해서, 표준화 후 같은 경로인지를 판별한다.
	 * 
	 * <pre>
	 * 입력 : "../aaaa\\bbbb\\cccc\\dddd", "aaaa/bbbb/cccc/dddd"
	 * 예제 : StringUtil.pathEquals("../aaaa\\bbbb\\cccc\\dddd", "aaaa/bbbb/cccc/dddd")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param path1
	 *            비교할 경로 문자열
	 * @param path2
	 *            비교대상 경로 문자열
	 * @return 두 입력 경로가 동일한 경우 true를 리턴
	 */
	public static boolean pathEquals(String path1, String path2) {
		if (path1 == null) {
			if (path2 == null) {
				return true;
			} else {
				return false;
			}
		}
		return pathClean(path1).equals(pathClean(path2));
	}

	private static String randomAlphabetic(int size) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < size; i++) {
			buf.append(ALPHAS[StringUtil.GENERATOR.nextInt(52)]);
		}
		return buf.toString();
	}

	/**
	 * 하나의 String 객체 안에서 삭제할 문자열 안에 포함된 모든 character들을 제거하여 반환한다.
	 * 
	 * <pre>
	 * 입력 : "Anyframe Java Test", "Java"
	 * 예제 : StringUtil.deleteAny("Anyframe Java Test", "Java")
	 * 출력 : Anyfrme  Test
	 * <br>
	 * 입력 : "Anyframe Java Test", "AnyJ"
	 * 예제 : StringUtil.deleteAny("Anyframe Java Test", "AnyJ")
	 * 출력 : frame ava Test
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param charsToDelete
	 *            삭제할 문자열
	 * @return 입력문자열에서 삭제할 문자열에 포함된 모든 문자가 삭제된 문자열
	 * @see org.springframework.util.StringUtils#deleteAny(String, String)
	 */
	public static String deleteAny(String str, String charsToDelete) {
		return org.springframework.util.StringUtils.deleteAny(str,
				charsToDelete);
	}

	/**
	 * 하나의 String 객체 안에서 삭제할 char[] 안에 포함된 모든 character들을 제거하여 반환한다.<br>
	 * 
	 * <pre>
	 * 입력 : AbbzzB, char[] ch
	 * 예제 : char[] ch = new char[2]; ch[0] = 'b'; ch[1] = 'z';
	 * StringUtil.deleteAny("AbbzzB", ch))
	 * 출력 : AB
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param charsToDelete
	 *            삭제할 char[]
	 * @return 입력문자열에서 삭제할 char[]에 포함된 모든 문자가 삭제된 문자열
	 */
	public static String deleteAny(String str, char[] charsToDelete) {
		return deleteAny(str, new String(charsToDelete));
	}

	/**
	 * 하나의 String 객체 안에서 입력된 문자를 모두 제거하여 반환한다.
	 * 
	 * <pre>
	 * 입력 : ABBBBBC, B
	 * 예제 : StringUtil.deleteAny("ABBBBBC", 'B')
	 * 출력 : AC
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param charToDelete
	 *            삭제할 문자 
	 * @return 입력문자열에서 삭제할 문자가 모두 제거된 문자열
	 */
	public static String deleteAny(String str, char charToDelete) {
		return deleteAny(str, String.valueOf(charToDelete));
	}

	/**
	 * 하나의 문자열에서 입력된 문자열을 모두 제거하여 반환한다.
	 * 
	 * @param str
	 *            입력된 문자열
	 * @param subStr
	 *            삭제할 문자열
	 * @return 입력 문자열에서 삭제 문자열 전체와 일치된 항목이 모두 제거된 문자열
	 * @see org.springframework.util.StringUtils#delete(String, String)
	 */
	public static String deleteMatches(String str, String subStr) {
		return org.springframework.util.StringUtils.delete(str, subStr);
	}

	/**
	 * 하나의 문자열에서 입력된 문자열 중 첫번째를 제거하여 반환한다.
	 * 
	 * <pre>
	 * 입력 : pass*word, *
	 * 예제 : StringUtil.deleteFirstMatches(&quot;pass*word&quot;, &quot;*&quot;)
	 * 출력 : password
	 * </pre>
	 * 
	 * @param str
	 *            입력된 문자열
	 * @param deletedStr
	 *            삭제할 문자열
	 * @return 입력 문자열에서 삭제 문자열 전체와 첫번째로 일치된 항목이 제거된 문자열
	 */
	public static String deleteFirstMatches(String str, String deletedStr) {
		int startIndex = str.indexOf(deletedStr);
		if (startIndex != -1) {
			int endIndex = deletedStr.length() + startIndex;
			return str.substring(0, startIndex) + str.substring(endIndex);
		}
		return str;
	}

	/**
	 * 문자열의 모둔 공백 문자열 제거
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test
	 * 예제 : StringUtil.removeWhitespace("Anyframe Java Test")
	 * 출력 : AnyframeJavaTest
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return 공백문자가 제거된 문자열 
	 * @see org.springframework.util.StringUtils#trimAllWhitespace(String)
	 */
	public static String removeWhitespace(String str) {
		return org.springframework.util.StringUtils.trimAllWhitespace(str);
	}

	/**
	 * 입력된 문자열이 주어진 정규식 표현과 일치하는 모든 문자열을 바꿔야할 문자열로 변경.
	 * 
	 * <pre>
	 * 입력 : 12ab12ab12ab, ab, 34
	 * 예제 : StringUtil.replaceAll("12ab12ab12ab", "ab", "34")
	 * 출력 : 123412341234
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param regex
	 *            정규식 표현
	 * @param replacement
	 *            변경할 문자열
	 * @return 입력 문자열에서 해당 정규식 표현에 해당하는 부분이 모두  변경된 문자열
	 * @see String#replaceAll(String, String)
	 */
	public static String replaceAll(String str, String regex, String replacement) {
		if (str == null) {
			return null;
		}
		return str.replaceAll(regex, replacement);
	}

	/**
	 * 입력된 문자열이 주어진 정규식 표현과 일치하는 첫번째 문자열을 바꿔야할 문자열로 변경
	 * 
	 * <pre>
	 * 입력 : "Anyframe Java Test Anyframe Java Test", "Anyframe", "Enterprise"
	 * 예제 : StringUtil.replaceFirst("Anyframe Java Test Anyframe Java Test", "Anyframe","Enterprise")
	 * 출력 : Enterprise Java Test Anyframe Java Test
	 * </pre>
	 * 
	 * @param src
	 *            입력문자열
	 * @param regex
	 *            정규식 표현
	 * @param replacement
	 *            변경할 문자열
	 * @return 입력 문자열에서 해당 정규식 표현에 해당하는 첫번째 부분이 변경된 문자열
	 * @see String#replaceFirst(String, String)
	 */
	public static String replaceFirst(String src, String regex,
			String replacement) {
		if (src == null) {
			return null;
		}
		return src.replaceFirst(regex, replacement);
	}

	/**
	 * 입력된 문자열이 주어진 정규식 표현과 일치하는 마지막 문자열을 바꿔야할 문자열로 변경
	 * 
	 * <pre>
	 * 입력 : "Anyframe Java Test Anyframe Java Test", "Anyframe", "Enterprise"
	 * 예제 : StringUtil.replaceLast("Anyframe Java Test Anyframe Java Test","Anyframe", "Enterprise")
	 * 출력 : Anyframe Java Test Enterprise Java Test
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param regex
	 *            정규식 표현
	 * @param replacement
	 *            변경할 문자열 
	 * @return 입력 문자열에서 해당 정규식 표현에 해당하는 마지막 부분이 변경된 문자열
	 */
	public static String replaceLast(String str, String regex,
			String replacement) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find()) {
			return str;
		}
		int lastMatchStart = 0;
		do {
			lastMatchStart = matcher.start();
		} while (matcher.find());
		matcher.find(lastMatchStart);
		StringBuffer sb = new StringBuffer(str.length());
		matcher.appendReplacement(sb, replacement);
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 입력된 문자열중 바꿔야 할 문자열과 일치하는 문자열을 모두 변경하여 리턴.
	 * 
	 * <pre>
	 * 입력 : "abaa", "aba", "bab"
	 * 예제 : StringUtil.replaceSubString("abaa", "aba", "bab")
	 * 출력 : baba
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param replacedStr
	 *            변경대상 문자열 (정규식 표현이 아님)
	 * @param replacement
	 *            변경할 문자열
	 * @return 입력 문자열에서 변경대상 문자열을 찾아 변경할 문자열로 대치된 문자열
	 */
	public static String replaceSubString(String str, String replacedStr,
			String replacement) {
		if (str == null) {
			return null;
		}
		if (replacedStr == null || replacement == null) {
			return str;
		}
		StringBuffer sbuf = new StringBuffer();
		int pos = 0;
		int index = str.indexOf(replacedStr);
		int patLen = replacedStr.length();
		for (; index >= 0; index = str.indexOf(replacedStr, pos)) {
			sbuf.append(str.substring(pos, index));
			sbuf.append(replacement);
			pos = index + patLen;
		}
		sbuf.append(str.substring(pos));
		return sbuf.toString();
	}

	/**
	 * html 태그를 입력으로 받아 escape 문자열로 변경해준다.
	 * 
	 * <pre>
	 * 입력 : "<html>Anyframe Java Test<html>"
	 * 예제 : StringUtil.htmlEscape("<html>Anyframe Java Test<html>")
	 * 출력 : "&lt;html&gt;Anyframe Java Test&lt;html&gt;"
	 * </pre>
	 * 
	 * @param input
	 *            입력 문자열
	 * @return html escape 문자로 변경된 문자열 
	 * @see HtmlUtils#htmlEscape(String)
	 */
	public static String htmlEscape(String input) {
		return HtmlUtils.htmlEscape(input);
	}

	/**
	 * html에서 사용가능한 escape 문자열을 입력받아 html태그 형태의 문자열로 변환한다. 
	 * 
	 * <pre>
	 * 입력 : "&lt;html&gt;Anyframe Java Test&lt;html&gt;"
	 * 예제 : StringUtil.htmlUnescape("&lt;html&gt;Anyframe Java Test&lt;html&gt;")
	 * 출력 : <html>Anyframe Java Test<html>
	 * </pre>
	 * 
	 * @param input
	 *            html escape 문자로 작성된 문자열 
	 * @return html escape 문자가 대응된는 일반문자로 변경된 문자열
	 * @see HtmlUtils#htmlUnescape(String)
	 */
	public static String htmlUnescape(String input) {
		return HtmlUtils.htmlUnescape(input);
	}

	/**
	 * 입력된 문자열의 순서를 반대로 바꿈 {@link StringBuffer#reverse()}.
	 * 
	 * <A code>null</code> String returns <code>null</code>.
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test
	 * 예제 : StringUtil.reverse("Anyframe Java Test")
	 * 출력 : tseT avaJ emarfynA
	 * </pre>
	 * 
	 * @param str 입력문자열
	 *            the String to reverse, may be null
	 * @return 입력문자열의 순서가 반대로 바뀐 문자열
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * 주어진 String 객체에 대해서 주어진 길이만큼 오른쪽 부분을 떼어 반환한다.
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            추출할 문자열의 길이
	 * @return 입력문자열에서 size만큼 오른쪽에서 떼어낸 문자열
	 */
	public static String right(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return str.substring(str.length() - size);
		}
	}

	/**
	 * 해당하는 문자열에 대해서 입력된 길이만큼 부족한 길이를 오른쪽부터 공백으로 채워넣는다.<br>
	 * 
	 * <pre>
	 * 입력 : "" (empty string), 3
	 * 예제 :  StringUtil.rightPad("", 3)
	 * 출력 : "   "
	 * <br>
	 * 입력 : "bat", 5
	 * 예제 :  StringUtil.rightPad("bat", 5)
	 * 출력 : "bat  " 
	 *  <br>
	 * 입력 : "bat", 1
	 * 예제 :  StringUtil.rightPad("bat", 1) 
	 * 출력 : "bat"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 길이 
	 * @return 입력문자열의 우측에 공백이 채워넣어진 문자열
	 */
	public static String rightPad(String str, int size) {
		return rightPad(str, size, ' ');
	}
	
	/**
	 * 해당하는 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 오른쪽부터 공백으로 채워넣는다.<br>
	 * 
	 * <pre>
	 * 입력 : "" (empty string), 3
	 * 예제 :  StringUtil.rightBytesPad("", 3)
	 * 출력 : "   "
	 *  <br>
	 * 입력 : "가ABC", 8
	 * 예제 :  StringUtil.rightBytesPad("가ABC", 8) 
	 * 출력 : "가ABC   "
	 * <br>
	 * 입력 : "가나12", 5
	 * 예제 :  StringUtil.rightBytesPad("가나12", 5)
	 * 출력 : "가나12" 
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 바이트 수
	 * @return 입력문자열의 우측에 공백이 채워넣어진 문자열
	 */
	public static String rightBytesPad(String str, int size) {
		return rightBytesPad(str, size, ' ');
	}

	/**
	 * 해당하는 문자열에 대해서 입력된 길이만큼 부족한 길이를 오른쪽부터 지정된 문자로 채워넣는다.<br>
	 * 
	 * <pre>
	 * 입력 : "" (empty string), 3, 'z'
	 * 예제 :  StringUtil.rightPad("", 3, 'z')
	 * 출력 : "zzz"
	 * <br>
	 * 입력 : "bat", 5, 'z'
	 * 예제 :  StringUtil.rightPad("bat", 5, 'z')
	 * 출력 : "batzz" 
	 *  <br>
	 * 입력 : "bat", 1, 'z'
	 * 예제 :  StringUtil.rightPad("bat", 1, 'z')
	 * 출력 : "bat"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 길이
	 * @param padChar
	 *            채워넣은 문자
	 * @return 입력문자열의 우측에 padChar이 채워넣어진 문자열
	 */
	public static String rightPad(String str, int size, char padChar) {
		return padString(str, size, String.valueOf(padChar), false);
	}
	
	/**
	 * 해당하는 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 오른쪽부터 지정된 문자로 채워넣는다.<br>
	 * 
	 * <pre>
	 * 입력 : "ABC알파벳", 12, 'z'
	 * 예제 :  StringUtil.rightBytesPad("ABC알파벳", 12, 'z')
	 * 출력 : "ABC알파벳zzz"
	 *  <br>
	 * 입력 : "하하하", 11, '호'
	 * 예제 :  StringUtil.rightBytesPad("하하하", 11, '호')
	 * 출력 : "하하하호호"
	 *  <br>
	 * 입력 : "안녕", 1, '!'
	 * 예제 :  StringUtil.rightBytesPad("안녕", 1, '!')
	 * 출력 : "안녕"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 바이트 수
	 * @param padChar
	 *            채워넣은 문자
	 * @return 입력문자열의 우측에 padChar이 채워넣어진 문자열
	 */
	public static String rightBytesPad(String str, int size, char padChar) {
		return padByteString(str, size, String.valueOf(padChar), false);
	}

	/**
	 * 해당하는 문자열에 대해서 입력된 길이만큼 부족한 길이를 오른쪽부터 지정된 문자로 채워넣는다.<br>
	 * 
	 * <pre>
	 * 입력 : null, 4, "" (empty string)
	 * 예제 :  StringUtil.rightPad(null, 4, "")
	 * 출력 : null
	 * <br>
	 * 입력 : "bat", 8, "yz"
	 * 예제 :  StringUtil.rightPad("bat", 8, "yz")
	 * 출력 : "batyzyzy"
	 * <br>
	 * 입력 : "bat", 5, null
	 * 예제 :  StringUtil.rightPad("bat", 5, null)
	 * 출력 : "bat  " 
	 *  <br>
	 * 입력 : "bat", 1, 'z'
	 * 예제 :  StringUtil.rightPad("bat", 1, 'z')
	 * 출력 : "bat"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 길이
	 * @param padStr
	 *            채워넣을 문자열
	 * @return 입력문자열의 우측에 padStr이 채워넣어진 문자열
	 */
	public static String rightPad(String str, int size, String padStr) {
		return padString(str, size, padStr, false);
	}
	
	/**
	 * 해당하는 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 오른쪽부터 지정된 문자로 채워넣는다.<br>
	 * 
	 * <pre>
	 * 입력 : null, 4, "" (empty string)
	 * 예제 :  StringUtil.rightPad(null, 4, "")
	 * 출력 : null
	 * <br>
	 * 입력 : "한글포함", 15 , "가나1"
	 * 예제 :  StringUtil.rightBytesPad("한글포함", 15 , "가나1")
	 * 출력 : "한글포함가나1가"
	 * <br>
	 * 입력 : "ABC가나", 10 , null
	 * 예제 :  StringUtil.rightBytesPad("ABC가나", 10 , null)
	 * 출력 : "ABC가나   "
	 *  <br>
	 * 입력 : "ABC", 1, 'z'
	 * 예제 :  StringUtil.rightBytesPad("ABC", 1, 'z')
	 * 출력 : "ABC"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 바이트 수
	 * @param padStr
	 *            채워넣을 문자열
	 * @return 입력문자열의 우측에 padStr이 채워넣어진 문자열
	 */
	public static String rightBytesPad(String str, int size, String padStr) {
		return padByteString(str, size, padStr, false);
	}

	private static String padByteString(String str, int size, String padStr, boolean isLeft) {
		String inputString = str;
		String padString = padStr;
		
		if (inputString == null) {
			return null;
		}		

		int originalStrLength = 0;
		String charsetName = Charset.defaultCharset().displayName(); //default charset of this Java virtual machine
		
		for (int i=0; i < inputString.length(); i++){
			originalStrLength += getByteLength(inputString.charAt(i), charsetName);
		}

		if (size < originalStrLength){
			return inputString;
		}

		int difference = size - originalStrLength;
		if (difference > 0) {
			if (padString == null || "".equals(padString)) {
				padString = " ";
			}
			
			String tempPad = null;
			int padStrLen =0;
			
			for (int i=0; i < padString.length(); i++){
				padStrLen += getByteLength(padString.charAt(i), charsetName);
			}

			if (padStrLen == 1) {
				char padChar = padString.charAt(0);
								
				char[] tempChars = new char[difference];
				for (int i = 0; i < tempChars.length; i++) {
					tempChars[i] = padChar;
				}			
				tempPad = new String(tempChars);
				
			}else {		
				StringBuilder tempBuilder = new StringBuilder();
				int len = 0;				
				
				do {
					for (int i = 0; i < padString.length(); i++) {
						len = getByteLength(padString.charAt(i), charsetName);
						
						if(len <= difference){
							difference -= len;
							tempBuilder.append(padString.charAt(i));
						}else break;
					}
				} while (difference >= len); 
				
				tempPad = tempBuilder.toString();
			}
						
			if (isLeft) {
				inputString = tempPad + inputString;
			} else {
				inputString = inputString + tempPad;
			}
		}
		return inputString;
	}
	
	/**
	 * 해당하는 문자열에 대해서 입력된 바이트 수만큼 부족한 길이를 양쪽으로부터 공백을 채워넣는다. 
	 * (부족한 길이가 홀수인경우 앞쪽부터 채워넣는다.)
	 * 
	 * <pre>
	 * 입력 : "Anyframe", 12
	 * 예제 :  StringUtil.centerBytesPad("Anyframe", 12)
	 * 출력 : "  Anyframe  "
	 * <br>
	 * 입력 : "한글AB", 8
	 * 예제 :  StringUtil.centerBytesPad("한글AB", 8)
	 * 출력 : " 한글AB "
	 *  <br>
	 * 입력 : "테스트", 7
	 * 예제 :  StringUtil.centerBytesPad("테스트", 7)
	 * 출력 : " 테스트"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            패딩문자를 포함한 전체 문자열의 바이트 수
	 * @return 입력문자열의 양쪽에 공백이 채워넣어진 문자열
	 */
	public static String centerBytesPad(String str, int size) {
		return centerPadByteString(str, size);		
	}
	
	private static String centerPadByteString(String str, int size) {
		String inputString = str;
		char padChar = ' ';
		
		if (inputString == null) {
			return null;
		}

		int originalStrLength = 0;
		String charsetName = Charset.defaultCharset().displayName(); //default charset of this Java virtual machine
		
		for (int i=0; i < inputString.length(); i++){
			originalStrLength += getByteLength(inputString.charAt(i), charsetName);
		}

		if (size < originalStrLength){
			return inputString;
		}

		int difference = size - originalStrLength;
		if (difference > 0) {			
			String tempPad = null;		
								
			char[] tempChars = new char[difference];
			for (int i = 0; i < tempChars.length; i++) {
				tempChars[i] = padChar;
			}
			tempPad = new String(tempChars);
			
			int tempPadLen = tempPad.length();
			int halfLen = tempPadLen/2;

			if(tempPad.length()%2 == 0){
				String temp = mid(tempPad, 0, halfLen);
				inputString = temp + inputString + temp;	
			}else{
				String lTemp = mid(tempPad, 0, halfLen + 1);
				String rTemp = mid(tempPad, 0, halfLen);				
				inputString = lTemp + inputString + rTemp;
			}
		}	
		return inputString;
	}
	
	/**
	 * 
	 * 문자열의 오른쪽의 공백 문자열 제거
	 * 
	 * <pre>
	 * 입력 : "   Anyframe Java Test   "
	 * 예제 :  StringUtil.rightTrim("   Anyframe Java Test   ")
	 * 출력 : "   Anyframe Java Test"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 우측공백문자가 제거된 문자열
	 * @see org.springframework.util.StringUtils#trimTrailingWhitespace(String)
	 */
	public static String rightTrim(String str) {
		return org.springframework.util.StringUtils.trimTrailingWhitespace(str);
	}

	/**
	 * 주어진 String 객체에대해 왼쪽에서부터 len만큼 길이를 얻어내고 문자열 끝에 ...를 붙여서 반환한다.
	 * 
	 * <pre>
	 * 입력 : "12345678", 3
	 * 예제 :  StringUtil.abbreviateFromLeft("12345678", 3)
	 * 출력 : 123...
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            입력문자열중 표시할 문자열의 길이
	 * @return 왼쪽 부분문자열에 줄임표(...)가 붙여진 문자열
	 */
	public static String abbreviateFromLeft(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return str.substring(0, size) + "...";
		}
	}

	/**
	 * 주어진 String 객체에대해 왼쪽에서부터 len만큼 길이를 얻어내고 문자열 앞에...를 붙여서 반환한다.
	 * 
	 * <pre>
	 * 입력 : "12345678", 3
	 * 예제 :  StringUtil.abbreviateFromRight("12345678", 3)
	 * 출력 : ...678
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param size
	 *            입력문자열중 표시할 문자열의 길이
	 * @return 오른쪽 부분문자열에 줄임표(...)가 붙여진 문자열
	 */
	public static String abbreviateFromRight(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return "..." + str.substring(str.length() - size);
		}
	}

	/**
	 * 문자열을 받아 해당하는 hex 코드로 만들어 반환한다.
	 * 
	 * <pre>
	 * 입력 : 123
	 * 예제 :  StringUtil.stringToHex("123")
	 * 출력 : 003100320033
	 * </pre>
	 * 
	 * @param str
	 *            the String to convert
	 * @return the converted hex string
	 */
	public static String stringToHex(String str) {

		String inStr = str;

		char inChar[] = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < inChar.length; i++) {
			String hex = Integer.toHexString((int) inChar[i]);
			if (hex.length() == 2) {
				hex = "00" + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 입력된 문자열을 10자리 사업자 번호 형식으로 변환한다. <br>
	 * 
	 * <pre>
	 * 입력 : 1111111111
	 * 예제 :  StringUtil.toBusinessNoPattern("1111111111")
	 * 출력 : 111-11-11111
	 * </pre>
	 * 
	 * @param str
	 *            입력된 10자리 사업자번호
	 * @return 사업자번호 협식으로 변환된 문자열
	 */
	public static String toBusinessNoPattern(String str) {
		if (str == null) {
			return "";
		}
		if (str.length() != 10 || !NumberUtil.isDigit(str)) {
			return "";
		} else {
			return String.format("%s-%s-%s", str.substring(0, 3), str
					.substring(3, 5), str.substring(5, 10));
		}
	}

	/**
	 * 문자열을 ','로 분리하여 String[]로 반환한다.
	 * 
	 * <pre>
	 * 입력 : "Anyframe/Java/Test", "/"
	 * 예제 :  StringUtil.tokenizeToStringArray("Anyframe/Java/Test", "/")
	 * 출력 : new String[] { "Anyframe", "Java", "Test" }
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 콤마를 구분자로 구분된 String[]
	 */
	public static String[] tokenizeToStringArray(String str) {
		return tokenizeToStringArray(str, ",", false, false);
	}

	/**
	 * 문자열을 입력받은 구분자로 분리하여 String[]로 반환한다.
	 * 
	 * <pre>
	 * 입력 : "aaa,bbb,ccc", ','
	 * 예제 :  String[] test; test = tokenizeToStringArray("aaa,bbb,ccc", ',')
	 * 출력 : test[0]="aaa", test[1]="bbb", test[2]="ccc"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param delimeter
	 *            구문문자
	 * @return delimiter 구분자로 구분된 String[]
	 */
	public static String[] tokenizeToStringArray(String str, char delimeter) {
		return tokenizeToStringArray(str, String.valueOf(delimeter), false,
				false);
	}

	/**
	 * 문자열을 입력받은 구분자로 분리하여 String[]로 반환한다.
	 * 
	 * <pre>
	 * 입력 : "aaa.bbb.ccc.ddd", "."
	 * 예제 :  String[] test; test = tokenizeToStringArray("aaa.bbb.ccc.ddd", ".")
	 * 출력 : test[0]="aaa", test[1]="bbb", test[2]="ccc"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param delimiter
	 *            구문문자열
	 * @return delimiter 구분자로 구분된 String[]
	 */
	public static String[] tokenizeToStringArray(String str, String delimiter) {
		return tokenizeToStringArray(str, delimiter, false, false);
	}

	/**
	 * 주어진 String에 대해서 delimiter를 이용하여 tokenize한 후 String[]로 뽑아낸다.
	 * 
	 * <pre>
	 * 입력 : "aaa.bbb.ccc.ddd", "."
	 * 예제 :  String[] test; test = tokenizeToStringArray("aaa.bbb.ccc.ddd", ".", true, true)
	 * 출력 : test[0]="aaa", test[1]="bbb", test[2]="ccc"
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param delimeter
	 *            구분문자열
	 * @param trimTokens
	 *            각각의 토큰을 trim 처리할지 여부
	 * @param ignoreEmptyTokens
	 *            "" 토큰을 무시할지 여부 
	 * @return delimiter 구분자로 구분된 String[]
	 */
	public static String[] tokenizeToStringArray(String str, String delimeter,
			boolean trimTokens, boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}
		if (delimeter == null) {
			return new String[] { str };
		}
		StringTokenizer st = new StringTokenizer(str, delimeter);
		List<String> tokens = new ArrayList<String>();
		do {
			if (!st.hasMoreTokens()) {
				break;
			}
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() != 0) {
				tokens.add(token);
			}
		} while (true);
		return tokens.toArray(new String[tokens.size()]);
	}

	/**
	 * 주어진 String의 첫번째 글자를 소문자로 변환한다. <br>
	 * 
	 * <pre>
	 * 입력 : ABCD
	 * 예제 : StringUtil.toLowerCaseFirstLetter("ABCD")
	 * 출력 : aBCD
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 첫번째 문자가 소문자로 변환된 문자열
	 * 
	 */
	public static String toLowerCaseFirstLetter(String str) {
		return changeFirstCharacterCase(false, str);
	}

	/**
	 * 주어진 13자리 숫자 String을 "111111-1111111" 형태의 주민등록번호 포맷으로 변환한다. 
	 * 
	 * <pre>
	 * 입력 : 1111112222222
	 * 예제 : StringUtil.toSocialSecuNoPattern("1111112222222")
	 * 출력 : 111111-2222222
	 * </pre>
	 * 
	 * @param str
	 *            형식없는 주민등록 번호 문자열
	 * @return "111111-1111111" 형식의 문자열
	 */
	public static String toSocialSecuNoPattern(String str) {
		if (str == null) {
			return "";
		}
		if (str.length() != 13 || !NumberUtil.isDigit(str)) {
			return "";
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(str.substring(0, 6));
			buffer.append('-');
			buffer.append(str.substring(6));
			return buffer.toString();
		}
	}

	/**
	 * 입력된 문자열로 부터 숫자만 추출하여 '-'가 포함된 전화번호 형태의 문자열로 포매팅하여 리턴한다.
	 * 
	 * <pre>
	 * 입력 : 021234567
	 * 예제 : StringUtil.toTelephoneNumberFormat(&quot;021234567&quot;)
	 * 출력 : 02-123-4567
	 * <br>
	 * 입력 : 0212345678
	 * 예제 : StringUtil.toTelephoneNumberFormat(&quot;0212345678&quot;)
	 * 출력 : 02-1234-5678
	 * <br>
	 * 입력 : 1234567
	 * 예제 : StringUtil.toTelephoneNumberFormat(&quot;1234567&quot;)
	 * 출력 : 123-4567
	 * </pre>
	 * 
	 * @param str
	 *            입력 전화번호
	 * @return - 로 구분된 형식화된 전화번호 문자열
	 */
	public static String toTelephoneNumberFormat(String str) {

		int endNumberDigit = 4;
		int minNumberDigit = 7;

		if (StringUtil.isEmpty(str)) {
			return null;
		}

		String origin = str.trim();
		String tempNumber;

		int originLength = origin.length();

		// extract numeric chars only
		if (NumberUtil.isNotDigit(origin)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < originLength; i++) {
				if (Character.isDigit(origin.charAt(i))) {
					sb.append(origin.charAt(i));
				}
			}
			tempNumber = sb.toString();
		} else {
			tempNumber = origin;
		}

		int numberLength = tempNumber.length();

		if (numberLength < minNumberDigit) {
			return tempNumber;
		}

		String firstNumber = "";
		String secondNumber = "";
		String thirdNumber = "";

		if (tempNumber.charAt(0) == '0') { // local number or mobile number
			if (tempNumber.charAt(1) == '2') { // Seoul
				firstNumber = tempNumber.substring(0, 2);
				secondNumber = tempNumber.substring(2, numberLength
						- endNumberDigit);
				thirdNumber = tempNumber.substring(numberLength
						- endNumberDigit, numberLength); // split last 4 digits
			} else { // local number or mobile number
				firstNumber = tempNumber.substring(0, 3);
				secondNumber = tempNumber.substring(3, numberLength
						- endNumberDigit);
				thirdNumber = tempNumber.substring(numberLength
						- endNumberDigit, numberLength); // split last 4 digits
			}
			return firstNumber + "-" + secondNumber + "-" + thirdNumber;
		} else { // telephone number without local number
			firstNumber = tempNumber
					.substring(0, numberLength - endNumberDigit);
			secondNumber = tempNumber.substring(numberLength - endNumberDigit,
					numberLength);

			return firstNumber + "-" + secondNumber;
		}

	}

	/**
	 * 주어진 String의 첫번째 글자를 대문자로 변환한다.
	 * 
	 * <pre>
	 * 입력 : abcd
	 * 예제 : StringUtil.toUpperCaseFirstLetter("abcd")
	 * 출력 : Abcd
	 * </pre>
	 * 
	 * @param inputString
	 *            입력문자열
	 * @return 첫번째 문자가 대문자로 변환된 문자열
	 */
	public static String toUpperCaseFirstLetter(String inputString) {
		return changeFirstCharacterCase(true, inputString);
	}

	/**
	 * 주어진 6자리 숫자 String을 "111-111" 형태의 우편번호 포맷으로 변환한다.
	 * 
	 * <pre>
	 * 입력 : 111111
	 * 예제 : StringUtil.toZipCodePattern("111111")
	 * 출력 : 111-111
	 * </pre>
	 * 
	 * @param str
	 *            입력 우편번호
	 * @return '-' 로 구분된 형식화된 우편번호
	 */
	public static String toZipCodePattern(String str) {
		if (str == null) {
			return "";
		}
		if (str.length() != 6 || !NumberUtil.isDigit(str)) {
			return "";
		} else {
			return String.format("%s-%s", str.substring(0, 3), str.substring(3,
					6));
		}
	}

	/**
	 * 주어진 두 개의 String 객체에 대해서, trim()후 같은지를 비교한다.
	 * 
	 * <pre>
	 * 입력 : "     test     ", "test"
	 * 예제 : StringUtil.trimEquals("     test     ", "test")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str1
	 *            비교할 문자열
	 * @param str2
	 *            비교대상 문자열
	 * @return 두 문자열을 trim 처리 하여 같은경우 true를 리턴
	 */
	public static boolean trimEquals(String str1, String str2) {
		if (str1 == null) {
			if (str2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (str2 == null) {
			return false;
		} else {
			String trimBaseStr = str1.trim();
			String trimTargetStr = str2.trim();
			return trimBaseStr.equals(trimTargetStr);
		}
	}

	/**
	 * Qualified Name으로 표현된 String을 받아서 Unqualified Name 형태의 String으로 변환한다. 기본 구분자는 '.'
	 * 
	 * @param str
	 *            입력문자열
	 * @return the converted string
	 */
	public static String unqualify(String str) {
		return unqualify(str, '.');
	}

	/**
	 * Qualified Name으로 표현된 String을 받아서 Unqualified Name 형태의 String으로 변환한다.
	 * 
	 * @param str
	 *            입력문자열
	 * @param delimiter
	 *            구분 문자
	 * @return 구분문자로 구분된 문자열중 가장 마지막 문자열
	 */
	public static String unqualify(String str, char delimiter) {
		return str.substring(str.lastIndexOf(delimiter) + 1);
	}

	

	/**
	 * 전각문자열을 반각 문자열로 변환
	 * @param fullString 입력 전각 문자  
	 * @return 반각형태로 변환된 문자열
	 */
	public static String fullToHalf(String fullString){
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < fullString.length(); i++){
			char ch = fullString.charAt(i);
			// 기준값은 65248
			if (ch >= 65281 && ch <= 65374){
				ch -= 0xfee0;
			}else if (ch == 12288){
				ch = 0x20;
			}
			sb.append(ch);
		}

		return sb.toString();
	}

	/**
	 * 반각문자열을 전각 문자열로 변환 
	 * @param halfString 입력 반각 문자열 
	 * @return 전각형태로 변환된 문자열 
	 */
	public static String halfToFull(String halfString){
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < halfString.length(); i++){
			char ch = halfString.charAt(i);
			if (ch >= 0x21 && ch <= 0x7e){
				ch += 0xfee0;
			}else if (ch == 0x20){
				ch = 0x3000;
			}
			sb.append(ch);
		}

		return sb.toString();
	}

	/**
	 * char의 바이트 길이를 구한다. (바이트 길이는 default charset을 기준으로 구한다.)
	 * @param ch
	 * @return
	 */
	public static int getByteLength(char ch){
		return Character.toString(ch).getBytes().length;
	}

	/**
	 * char의 입력된 charset 에 해당하는 바이트 길이를 구한다. 
	 * @param ch
	 * @return
	 */
	public static int getByteLength(char ch, String charset){
		return String.valueOf(ch).getBytes(Charset.forName(charset)).length;
	}
	
	/**
	 * char의 입력된 charset 에 해당하는 바이트 길이를 구한다. 
	 * @param ch
	 * @return
	 */
	public static int getByteLength(char ch, Charset charset){
		return String.valueOf(ch).getBytes(charset).length;
	}
	

	/**
	 * 입력 문자값의 특정 부분을 반환한다. (첫번째 인덱스는 0부터 )
	 *
	 * <pre>
	 * 입력 : "880401-1234567", 3, 2
	 * 예제 : StringUtil.mid("880401-1234567", 3, 2)
	 * 출력 : 40
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @param pos
	 *          자르기  시작할 자릿수 (0번째부터 시작)
	 * @param len
	 *           총 가져올 길이 값
	 * @return 입력 문자열의 특정 부분을 리턴, null일 경우 empty string 리턴
	 */
	public static String mid(String str, int pos, int len){
		if (str == null) {
			return "";
		}

		if ((len < 0) || (pos > str.length())) {
			return "";
		}

		if (pos < 0) {
			pos = 0;
		}

		if (str.length() <= pos + len) {
			return str.substring(pos);
		}
		return str.substring(pos, pos + len);
	}
	
	/**
	 * 첫번째 인덱스부터 입력받은 바이트수만큼 문자열을 가져오는 함수 (한글은 2byte라 지정)
	 * 
	 * <pre>
	 * 입력 : "가나123-ABC",6
	 * 예제 : StringUtil.strCutLengthBytes("가나123-ABC",6)
	 * 출력 : 가나12
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @param cutlen
	 *           자를 바이트 길이
	 * @return 바이트길이가 cutlen인 문자열
	 */
	/*public  static String  strCutLengthBytes(String str, int cutlen){
		if(str ==null) return "";
		
		int rSize =0; int len = 0;
		
		if(str.getBytes().length > cutlen){
			for(;rSize<str.length(); rSize++){
				if(str.charAt(rSize) > 0x007F) len += 2;
				else len++;
				
				if(len > cutlen) break;
			}
			str = str.substring(0,rSize);
		}
		return str;											
	}*/
	
	/**
	 * 첫번째 인덱스부터 입력받은 바이트수만큼 문자열을 가져오는 함수 (인코딩방식 지정가능)
	 *        
	 * <pre>
	 * 입력 : "가나ABC123",5,"EUC-KR"
	 * 예제 : StringUtil.strCutLengthBytes("가나ABC123",5,"EUC-KR" )
	 * 출력 : 가나A
	 * <br>
	 * 입력 : "가나ABC123",5,"UTF-8"
	 * 예제 : StringUtil.strCutLengthBytes("가나ABC123",5,"UTF-8" )
	 * 출력 : 가
	 * </pre>
	 *        
	 * @param str
	 *           입력 문자열
	 * @param cutlen
	 *           자를 바이트 길이
	 * @param charset
	 *           인코딩 방식
	 * @return charset 에 해당하는 바이트길이를 가진 문자열
	 */
	public  static String  strCutLengthBytes(String str, int cutlen, String charset){	
		if(str ==null) return ""; 
		
		int  len = 0;
		StringBuilder newStr = new StringBuilder();
		Charset charsetName = Charset.forName(charset);
				
		for (int i=0; i < str.length(); i++){
			len = getByteLength(str.charAt(i), charsetName);
			
			if(len <= cutlen){
				cutlen -= len;
				newStr.append(str.charAt(i));
			}else break;
			
		}
		
		return newStr.toString();		
	}
	
	/**
	 * 첫번째 인덱스부터 입력받은 바이트수만큼 문자열을 가져오는 함수 
	 * (charset : default charset of this Java virtual machine)
	 *        
	 * <pre>
	 * 입력 : "가나AB123-4567",9
	 * 예제 : StringUtil.strCutLengthBytes("가나AB123-4567",9)
	 * 출력 : 가나AB123
	 * <br>
	 * 입력 : "가나AB123-4567",3
	 * 예제 : StringUtil.strCutLengthBytes("가나AB123-4567",3)
	 * 출력 : 가
	 * </pre>
	 *        
	 * @param str
	 *           입력 문자열
	 * @param cutlen
	 *           자를 바이트 길이
	 * @return charset 에 해당하는 바이트길이를 가진 문자열
	 */
	public  static String  strCutLengthBytes(String str, int cutlen){
		if(str ==null) return ""; 
		
		int  len = 0;
		StringBuilder newStr = new StringBuilder();		
		String charsetName = Charset.defaultCharset().displayName();
		
		for (int i=0; i < str.length(); i++){
			len = getByteLength(str.charAt(i), charsetName);
			
			if(len <= cutlen){
				cutlen -= len;
				newStr.append(str.charAt(i));
			}else break;
			
		}
		
		return newStr.toString();		
	}
	
	/**
	 * 시작위치(byte단위)와 자를 바이트 수를 입력받아 문자값의 일부분을 반환한다. 
	 *        
	 * <pre>
	 * 입력 : "가나1233다55", 2, 2
	 * 예제 : StringUtil.strCutLengthBytes("가나1233다55", 2, 2)
	 * 출력 : 나 (2부터 2byte만큼)
	 * <br>
	 * 입력 : "가나1233다55", 4, 1
	 * 예제 : StringUtil.strCutLengthBytes("가나1233다55", 4, 1)
	 * 출력 : 1 (4부터 1byte만큼)
	 * <br>
	 * 입력 : "가나1233다55", 4, 5
	 * 예제 : StringUtil.strCutLengthBytes("가나1233다55", 4, 5)
	 * 출력 : 1233 (4부터 5byte만큼)
	 * </pre>
	 *        
	 * @param str
	 *           자를 문자열
	 * @param startIdx
	 *          자르기 시작할 위치 (byte단위)
	 * @param bytes
	 *           자를 바이트 수 (2byte문자의 경우 바이트가 부족하면 그 앞 글자까지 자름)
	 * @return  일부 문자열
	 */	
	public  static String  strCutLengthBytes(String str, int startIdx, int bytes){

		if(startIdx == 0 && str.getBytes().length <= bytes) {
			return str;
		}

		String tmp = str;
		tmp = strCutLengthBytes(tmp, startIdx);
		return strCutLengthBytes(str.substring(tmp.length()), bytes);
	}
	
	/**
	 * 문자열을 입력받아 숫자값만 추출하여 출력한다.
	 * 
	 * <pre>
	 * 입력 : 010-3233-4444
	 * 예제 : StringUtil.getNumericChars("010-3233-4444" )
	 * 출력 : 01032334444
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열 
	 * @return 숫자값만으로 구성된 문자열을 반환
	 */
	public  static String getNumericChars(String str){
		if (StringUtil.isEmpty(str)) {
			return null;
		}		
		String origin = str.trim();
		String tempNumber;
	
		// extract numeric chars only
		if (NumberUtil.isNotDigit(origin)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < origin.length(); i++) {
				if (Character.isDigit(origin.charAt(i))) {
					sb.append(origin.charAt(i));
				}
			}
			tempNumber = sb.toString();
		} else {
			tempNumber = origin;
		}
		return tempNumber;
	}
	
	/**
	 * 파라미터로 받은 문자값의 위치를 int형 배열로 반환한다.
	 * 
	 * <pre>
	 * 입력 : 010-3233-4444
	 * 예제 : StringUtil.getLocTargetStr("010-3233-4444","-" )
	 * 출력 : 3, 8
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	* @param targetStr
	 *           찾을 문자
	 * @return 파라미터로 받은 문자값의 위치를 int형 배열 반환한다.
	 */
	public  static int[] getLocTargetStr(String str, char targetStr){
		if (StringUtil.isEmpty(str)) {
			return null;
		}		
		String origin = str.trim();
		
		//set of array size
		int arraySize = 0; int arryIdx = 0;
		for (int i = 0; i < origin.length(); i++) {
			if (origin.charAt(i) == targetStr) {
				arraySize++;
			}
		}
		int array[] = new int[arraySize];
		
		for (int i = 0; i < origin.length(); i++) {
			if (origin.charAt(i) == targetStr) {								
				array[arryIdx++] = i;
			}			
		}		
		return array;
	}
	
	/**
	 * 배열로 받은 위치에 targetStr 문자값을 삽입하여 문자를 조합한다.
	 * 
	 * <pre>
	 * 입력 : "01032334444",'-', new int[] {3,8}
	 * 예제 : StringUtil.getInsertedTargetStr("01032334444",'-', new int[] {3,8} )
	 * 출력 : 010-3233-4444
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @param targetStr
	 *           삽입할 문자
	 * @param insertIdx 
	 *           삽입할 위치
	 * @return 조합한 문자열을 반환한다.
	 */
	public  static String  getInsertedTargetStr(String str, char targetStr, int insertIdx[]){
		if (StringUtil.isEmpty(str)) {
			return null;
		}		
		String origin = str.trim();	
		StringBuffer sb = new StringBuffer(origin);
		
		for (int i = 0; i < insertIdx.length; i++) {
			sb.insert(insertIdx[i],targetStr);
		}		
		return sb.toString();
	}
	
	/**
	 * 파라미터로 받은 문자열에 알파벳, 숫자이외의 모든 문자를 제거
	 * 
	 * <pre>
	 * 입력 : ABC!DE@FG
	 * 예제 : StringUtil.deleteExceptAlphaNum("ABC!DE@FG")
	 * 출력 : ABCDEFG
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @return 알파벳, 숫자 이외의 모든 문자를 제거
	 */	
	public static String deleteExceptAlphaNum(String str){		
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		
	    String match = "[^0-9a-zA-Z]";
	    str = str.replaceAll(match,"");
	    return str;
	}
	
	/**
	 * 파라미터로 받은 문자열에 알파벳, 숫자,  공백이외의 모든 문자를 제거
	 * 
	 * <pre>
	 * 입력 : "ABC DE@ FG"
	 * 예제 : StringUtil.deleteExceptAlphaNumSpace("ABC DE@ FG")
	 * 출력 : ABC DE FG
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @return 알파벳, 숫자, 공백 이외의 모든 문자를 제거
	 */	
	public static String deleteExceptAlphaNumSpace(String str){
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		
	    String match = "[^\\s0-9a-zA-Z]";
	    str = str.replaceAll(match,"");
	    return str;
	}
	
	/**
	 * 입력받은 문자열이, String[] 중에 일치하는 것이 있는지 판단 (sql-IN함수 기능)
	 * 
	 * <pre>
	 * 입력 : "203", new String[] {"201","202","203"}
	 * 예제 : StringUtil.findStringFromArray("203",new String[] {"201","202","203"})
	 * 출력 : true
	 * <br>
	 * 입력 : "200", new String[] {"201","202","203"}
	 * 예제 : StringUtil.findStringFromArray("200",new String[] {"201","202","203"})
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @param strArray
	 *           검색 문자열 배열
	 * @return String[]중에 일치하는 것이 있다면 true, 그렇지 않으면 false를 리턴
	 */
	public  static boolean  findStringFromArray(String str, String strArray[]){
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		
		for (int i = 0; i < strArray.length; i++) {
			if(strArray[i].equals(str))					
				return true;
		}		
		return false;
	}
	
	/**
	 * 입력받은 문자열이, String[] 중에 일치하는 것이 없는지 판단
	 * 
	 * <pre>
	 * 입력 : "203", new String[] {"201","202","203"}
	 * 예제 : StringUtil.noFindStringFromArray("203",new String[] {"201","202","203"})
	 * 출력 : false
	 * <br>
	 * 입력 : "200", new String[] {"201","202","203"}
	 * 예제 : StringUtil.noFindStringFromArray("200",new String[] {"201","202","203"})
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *           입력 문자열
	 * @param strArray
	 *           검색 문자열 배열
	 * @return String[]중에 일치하는 것이 없다면 true, 그렇지 않으면 true를 리턴
	 */
	public  static boolean  noFindStringFromArray(String str, String strArray[]){
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		
		for (int i = 0; i < strArray.length; i++) {
			if(strArray[i].equals(str))					
				return false;
		}		
		return true;
	}
	
	/**
	 * 계좌번호 Formatting
	 * 입력받은 string길이값에 따라 "-"가 붙은 형태로 반환 (화면에 멀티패턴과 동일기능)
	 * 
	 * 10자 - AAAA-AAAA-AA, 11자 - AAA-AA-AAA-AAA or AAA-AAAA-AAAA (3번째 인덱스가 0이 아니거나, 4번째 인덱스가 6, 7이 아닐경우)
	 * 12자 - AAA-AA-AAAA-AAA, 13자 - AAAAAAAAAAAAA, 14자 - AAA-AAAAAA-AA-AAA
	 * 15자 - AAAAA-AAA-AA-AAAAA, 16자 - AAA-AAAAAA-AA-AAAAA, 그외는 입력값 그대로 리턴
	 * 
	 * <pre>
	 * 입력 : "1234563057"
	 * 예제 : StringUtil.processMultiPattern("1234563057")
	 * 출력 : 1234-5630-57 (10자인경우 AAAA-AAAA-AA패턴 적용)
	 * <br>
	 * 입력 : "42809236305"
	 * 예제 : StringUtil.processMultiPattern("42809236305")
	 * 출력 : 428-0923-6305 (11자이면서,  3번째 인덱스가 0이 아니거나, 4번째 인덱스가 6, 7이 아닐경우 AAA-AAAA-AAAA패턴 적용)
	 * </pre>
	 * 
	 * @param strInput
	 *           입력 문자열
	 * @return string길이값에 따라 "-"가 붙은 형태로 반환
	 */
	public static String processMultiPattern(String strInput){
		if (strInput == null || "".equals(strInput)) {
			return "";
		}
		
		int nLen = strInput.length();
		String strPattern = "";
		
		if(nLen==10) strPattern = "AAAA-AAAA-AA";
        else if(nLen==11) 
        {
            strPattern = "AAA-AA-AAA-AAA";

            if(strInput.charAt(3) !='0' ||(strInput.charAt(4) !='6' && strInput.charAt(4) !='7'))
            {
               strPattern = "AAA-AAAA-AAAA";
            }
        }
        else if(nLen==12) strPattern = "AAA-AA-AAAA-AAA";
        else if(nLen==13) strPattern = "AAAAAAAAAAAAA";
        else if(nLen==14) strPattern = "AAA-AAAAAA-AA-AAA";
        else if(nLen==15) strPattern = "AAAAA-AAA-AA-AAAAA";
        else if(nLen==16) strPattern = "AAA-AAAAAA-AA-AAAAA";

		
		if (strPattern == null || "".equals(strPattern)) {
			return strInput;
		}
		
		StringBuilder resultStr = new StringBuilder();
		int srcIdx = 0;		
		for(int i = 0; i < strPattern.length(); i++ ){
			char currentFormatChar = strPattern.charAt(i);
			
			if(srcIdx <= nLen){
				if(currentFormatChar == '-'){
					resultStr.append('-');					
				}else{
					resultStr.append(strInput.charAt(srcIdx));
					srcIdx++;
				}
			}else break;
		}		
		return resultStr.toString();
	}

	/**
	 * 계좌번호(default), 사업자/주민번호, 카드번호 Formatting (화면에 멀티패턴과 동일기능)
	 * [option] 1 - 계좌번호, 2 - 사업자/주민번호, 3 - 카드번호
	 * 
	 * <pre>
	 * 입력 : "4210895999", 2
	 * 예제 : StringUtil.processMultiPattern("4210895999", 2)
	 * 출력 : 421-08-95999 (사업자/주민번호패턴 선택)
	 * <br>
	 * 입력 : "0100944598706665", 3
	 * 예제 : StringUtil.processMultiPattern("0100944598706665", 3)
	 * 출력 : 0100-9445-9870-6665 (카드번호패턴 선택)
	 * </pre>
	 * 
	 * @param strInput
	 *           입력 문자열
	 * @return string길이값에 따라 "-"가 붙은 형태로 반환
	 */
	public static String processMultiPattern(String strInput, int option){		
		if(option == 1){ //계좌번호
			return processMultiPattern(strInput);			
		}else if(option == 2){ // 사업자/주민번호
			return processMultiPattern_bizNum(strInput);
		}else if(option == 3){ // 카드번호
			return processMultiPattern_crdNum(strInput);
		}else
			return processMultiPattern(strInput);
	}
	
	private static String processMultiPattern_bizNum(String strInput){
		if (strInput == null || "".equals(strInput)) {
			return "";
		}
		
		int nLen = strInput.length();
		String strPattern = "";
		
		if(nLen==10) strPattern = "AAA-AA-AAAAA";
        else if(nLen==13) strPattern = "AAAAAA-AAAAAAA";
		
		if (strPattern == null || "".equals(strPattern)) {
			return strInput;
		}
		
		StringBuilder resultStr = new StringBuilder();
		int srcIdx = 0;		
		for(int i = 0; i < strPattern.length(); i++ ){
			char currentFormatChar = strPattern.charAt(i);
			
			if(srcIdx <= nLen){
				if(currentFormatChar == '-'){
					resultStr.append('-');					
				}else{
					resultStr.append(strInput.charAt(srcIdx));
					srcIdx++;
				}
			}else break;
		}		
		return resultStr.toString();
	}
	
	private static String processMultiPattern_crdNum(String strInput){
		if (strInput == null || "".equals(strInput)) {
			return "";
		}
		
		int nLen = strInput.length();
		String strPattern = "";
		
		if(nLen==9) strPattern = "AAAAAAA-AA";
        else if(nLen==16) strPattern = "AAAA-AAAA-AAAA-AAAA";
		
		if (strPattern == null || "".equals(strPattern)) {
			return strInput;
		}
		
		StringBuilder resultStr = new StringBuilder();
		int srcIdx = 0;		
		for(int i = 0; i < strPattern.length(); i++ ){
			char currentFormatChar = strPattern.charAt(i);
			
			if(srcIdx <= nLen){
				if(currentFormatChar == '-'){
					resultStr.append('-');					
				}else{
					resultStr.append(strInput.charAt(srcIdx));
					srcIdx++;
				}
			}else break;
		}		
		return resultStr.toString();
	}
	
	/**
	 * 16진수를 받아 문자열로 변환함 (ASCII)
	 * 
	 * <pre>
	 * 입력 : "4A617661"
	 * 예제 : StringUtil.hexToASCII("4A617661")
	 * 출력 : Java
	 * </pre> 
	 * 
	 * @param str
	 *            변환할 문자열
	 * @return ASCII String
	 */
	public static String hexToASCII(String hex) {
		
		if(hex.length()%2 != 0){
			return null;
		}
		
		StringBuilder sb = new StringBuilder();

		//split into two characters
		for (int i=0; i<hex.length()-1; i+=2) {			
			//grab the hex in pairs
			String output = hex.substring(i, i+2);
			//convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			//convert the decimal to character
			sb.append((char)decimal);
		}
		return sb.toString();
	}
	
	/**
	 * 문자열(ASCII)을 16진수로 변환함
	 * 
	 * <pre>
	 * 입력 : "Java"
	 * 예제 : StringUtil.asciiToHex("Java")
	 * 출력 : 4A617661
	 * </pre> 
	 * 
	 * @param str
	 *            변환할 문자열
	 * @return hexdecimal String
	 */
	public static String asciiToHex(String ascii) {				
		StringBuilder hex = new StringBuilder();

		for (int i = 0; i < ascii.length(); i++) {
			hex.append(Integer.toHexString(ascii.charAt(i)));
		}
		return hex.toString().toUpperCase();
	}
	
	/**
	 * 입력 문자열의 지정 인덱스 부터 사이즈만큼의 문자열을 치환문자열로 치환한다.
	 * <pre>
	 * 입력 : "abcAAAghi"
	 * 예제 : StringUtil.replaceUsingIndex("abcAAAghi", 3, 3);
	 * 출력 : abcdefghi
	 * </pre> 
	 * @param src 입력문자열
	 * @param replaceIndex 치환 인덱스
	 * @param size 
	 * @param replacement 치환문자열
	 * @return
	 */
	public static String replaceUsingIndex(String src, int replaceIndex, int size, String replacement){
		
		// 치환할 문자열이 없는 경우 
		if (StringUtil.isEmpty(src) || src.length() <= replaceIndex) {
			return src;
		}
		
		StringBuilder result = new StringBuilder(src.substring(0, replaceIndex));
		result.append(replacement);
		
		if (replaceIndex + size < src.length()){
			result.append(src.substring(replaceIndex + size, src.length()));
		}

		return result.toString();
	}
	
	/**
	 * 첫번째 문자열에서 검색문자열에 포함되지 않는 첫번째 문자의 Index를 반환 
	 * @param src 입력문자열
	 * @param searchStr 검색문자열
	 * @return
	 */
	public static int indexOfAnyBut(String src, String searchStr){
		
		if (StringUtil.isEmpty(src) || StringUtil.isEmpty(searchStr)){
			return -1;
		}
		
		for (int i=0; i < src.length(); i++){
			int index = searchStr.indexOf(src.charAt(i)); 
			if (index == -1){
				return i;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * 입력된 문자열을 구분자와 완전히 일하는 경우 분리하여 각  List형태로 반환
	 * @param str 입력문자열 
	 * @param delimeter 구분자 문자열
	 * @return
	 */
	public static List<String> splitByWholeSeparator(String str, String delimeter){ 
		
		List<String> tokens = new ArrayList<String>();
		
		if (StringUtil.isEmpty(str) || StringUtil.isEmpty(delimeter)){
			return tokens;
		}
		
		int fromIndex = 0;
	
		do{
			int delimIndex = str.indexOf(delimeter, fromIndex);
			// 구분자가 존재하지 않는 경우  
			if (delimIndex == -1){
				if (fromIndex < str.length()-1){
					tokens.add(str.substring(fromIndex));
				}
				break;
			// 구분 문자열이 존재하는 경우 
			}else{
				fromIndex = delimIndex + delimeter.length();
				// 다음 구분자를 확인 
				int nextDelimIndex = str.indexOf(delimeter, fromIndex);
				
				// 다음 구분문자가 존재하지 않는 경우
				if (nextDelimIndex == -1){
					if (fromIndex < str.length()-1){
						tokens.add(str.substring(fromIndex, str.length()));
					}
					
					break;
				//다음 구분자 존재하는 경우 	
				}else{
					if (nextDelimIndex != fromIndex){
						tokens.add(str.substring(fromIndex, nextDelimIndex));
					}
				}
			}
			
		}while(true);
		
		return tokens;
	}
	/**
	 * 입력된 문자열을 셋팅되지 않은 경우 입력한 Default값으로 변경
	 * @param data 입력문자열 
	 * @param defaultValue default 문자열
	 * @return
	 */
	public static String trimWithDefaultValue(String inputData, String defaultValue){ 
		
		if(inputData == null) 
			return defaultValue;
		else 
			return inputData.trim().length()==0 ? defaultValue:inputData;
	}
	/**
	 * 입력된 문자열을 셋팅되지 않은 경우 Space로 채우는 로직
	 * @param data 입력문자열 
	 * @return
	 */
	public static String trimWithDefaultValue(String inputData){ 
		String defaultValue=" ";
		if(inputData == null) 
			return defaultValue;
		else 
			return inputData.trim().length()==0 ? defaultValue:inputData;
	}
	// non public utility 
	static boolean trimEquals(Collection<String> left, Collection<String> right){
		if (left == right){
			return true;
		}
		
		if (left != null && right != null){
			if (left.size() != right.size()){
				return false;
			}
			
			Iterator<String> e1 = left.iterator();
			Iterator<String> e2 = right.iterator();
			
			while(e1.hasNext() && e2.hasNext()){
				if (!trimEquals(e1.next(), e2.next())){
					return false;
				}
			}
			return true;
			
		}
		
		return false;
	}
	
	// non public utility 
	static boolean trimEquals(String[] left, String[] right){
		if (left == right){
			return true;
		}
		
		if (left != null && right != null){
			if (left.length != right.length){
				return false;
			}
			for (int i=0; i < left.length; i++){
				if (!trimEquals(left[i], right[i])){
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	private static final char DEFAULT_MASK_CHAR = '*';
	
	/**
	 * masking 처리된 계좌번호를 반환한다.
	 * @param accountNo 마스킹처리된 111-111111-11-111 패턴의 계좌번호
	 * @return 마스킹처리된 111-111111-**-*** 패턴의 계좌번호
	 */
	public static String getMaskedAccountNumber(String accountNo){
		return applyMaskedString(accountNo, "###-######-**-***", DEFAULT_MASK_CHAR, DEFAULT_MASK_CHAR);
	}

	private static String applyMaskedString(String str, String pattern, char targetChar, char maskchar) {
		
		if (isEmptyTrimmed(str)){
			return str;
		}
		
		StringBuilder sb = new StringBuilder(str.length());
		
		for (int i = 0; i < str.length(); i++){
			if (pattern.length() <= i || pattern.charAt(i) != targetChar){
				sb.append(str.charAt(i));
			}else{
				sb.append(maskchar);
			}	
		}
	
		return sb.toString();
	}	
	
}   