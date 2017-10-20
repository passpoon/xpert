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

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidationUtil {
	private static Logger logger = LoggerFactory.getLogger(ValidationUtil.class);

	private ValidationUtil() {
		throw new AssertionError();
	}




	private static final String INCORP_CERT_NUMBER_PATTERN = "^((\\d{6})(\\d{7}))$";
	private static final String INCORP_CERT_NUMBER_PATTERN_WITH_DASH = "^((\\d{6})-(\\d{7}))$";

	/**
	 * 입력된 법인등록번호가 유효한 법인등록번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 110111-0398556
	 * 예제 : ValidationUtil.isIncorpCertNumber(110111-0398556);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param corpNumber
	 *            검증할 법인 등록번호
	 * @param withDash
	 *            입력 번호에 - 포함여부
	 * @return 유효한 법인등록번호인 경우 true를 리턴.
	 */
	public static boolean isIncorpCertNumber(String corpNumber, boolean withDash) {

		String pattern = withDash ? INCORP_CERT_NUMBER_PATTERN_WITH_DASH
				: INCORP_CERT_NUMBER_PATTERN;

		if (!ValidationUtil.isRegexPatternMatch(corpNumber, pattern)) {
			return false;
		}

		String replaceno = withDash ? corpNumber.replace("-", "") : corpNumber;

		int checkSum = 0;

		for (int index = 0; index < 12; index++) {
			checkSum += (Character.getNumericValue(replaceno.charAt(index)) * ((index % 2 == 0) ? 1
					: 2));
		}

		if ((10 - (checkSum % 10)) % 10 == Character.getNumericValue(replaceno
				.charAt(12))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 입력된 법인등록번호가 유효한 법인등록번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 110111-0398556
	 * 예제 : ValidationUtil.isIncorpCertNumber(110111-0398556);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param corpNumber
	 *            검증할 법인 등록번호
	 * @return 유효한 법인등록번호인 경우 true를 리턴.
	 */

	public static boolean isIncorpCertNumber(String corpNumber) {
		return isIncorpCertNumber(corpNumber, true);
	}

	private static final String BIZ_REG_NUMBER_PATTERN = "^((\\d{3})(\\d{2})(\\d{5}))$";
	private static final String BIZ_REG_NUMBER_PATTERN_WITH_DASH = "^((\\d{3})-(\\d{2})-(\\d{5}))$";

	/**
	 * 입력된 사업자등록번호가 유효한 사업자등록번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 110-81-28774
	 * 예제 : ValidationUtil.isBizRegNumber(110-81-28774);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param bizNumber
	 *            검증할 사업자등록번호
	 * @param withDash
	 * @return 유효한 형식의 사업자등록번호 인 경우 true 리턴.
	 */

	public static boolean isBizRegNumber(String bizNumber, boolean withDash) {

		String pattern = withDash ? BIZ_REG_NUMBER_PATTERN_WITH_DASH
				: BIZ_REG_NUMBER_PATTERN;

		if (!ValidationUtil.isRegexPatternMatch(bizNumber, pattern)) {
			return false;
		}

		String replaceno = bizNumber.replace("-", "");

		int checkSum = 0;
		int checkDigit[] = { 1, 3, 7, 1, 3, 7, 1, 3, 5 };

		for (int i = 0; i < 9; i++) {
			checkSum += (Character.getNumericValue(replaceno.charAt(i)) * checkDigit[i]);
		}

		checkSum += (Character.getNumericValue(replaceno.charAt(8)) * 5) / 10;

		if ((10 - (checkSum % 10)) % 10 == Character.getNumericValue(replaceno
				.charAt(9))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 입력된 사업자등록번호가 유효한 사업자등록번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 110-81-28774
	 * 예제 : ValidationUtil.isBizRegNumber(110-81-28774);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param bizNumber
	 *            검증할 사업자등록번호
	 * @return 유효한 형식의 사업자등록번호 인 경우 true 리턴.
	 */
	public static boolean isBizRegNumber(String bizNumber) {
		return isBizRegNumber(bizNumber, true);
	}

	private static final String TELEPHONE_PATTERN = "^\\d{2,4}\\d{3,4}\\d{4}$";
	private static final String TELEPHONE_PATTERN_WITH_DASH = "^\\d{2,4}-\\d{3,4}-\\d{4}$";

	/**
	 * 입력된 전화번호가 유효한 전화번호의 형식인지 여부를 체크한다.
	 * 
	 * @param phoneNumber
	 *            검증할 전화번호
	 * @param withDash
	 *            입력값에 - 포함여부
	 * @return 유효한 전화번호인 경우 true를 리턴.
	 */
	public static boolean isTelephoneNumber(String phoneNumber, boolean withDash) {

		String pattern = withDash ? TELEPHONE_PATTERN_WITH_DASH
				: TELEPHONE_PATTERN;

		if (!ValidationUtil.isRegexPatternMatch(phoneNumber, pattern)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 입력된 전화번호가 유효한 전화번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 0505-123-1231
	 * 예제 : ValidationUtil.isTelephoneNumber(0505-123-1231)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param phoneNumber
	 *            검증할 전화번호
	 * @return 유효한 전화번호인 경우 true를 리턴.
	 */
	public static boolean isTelephoneNumber(String phoneNumber) {
		return isTelephoneNumber(phoneNumber, true);
	}

	private static final String CELLPHONE_NUMBER_PATTERN = "^(01(0|1|6|7|8|9))\\d{3,4}\\d{4}$";
	private static final String CELLPHONE_NUMBER_PATTERN_WITH_DASH = "^(01(0|1|6|7|8|9))-\\d{3,4}-\\d{4}$";

	/**
	 * 입력된 휴대전화번호가 유효한 휴대전화번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 018-1231-0912
	 * 예제 : ValidationUtil.isCellphoneNumber(018-1231-0912);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param cellPhoneNumber
	 *            검증할 휴대전화번호
	 * @param withDash
	 *            - 포함여부
	 * @return 유효한 휴대전화번호 형식인 경우 true를 리턴.
	 */
	public static boolean isCellphoneNumber(String cellPhoneNumber,
			boolean withDash) {
		String pattern = withDash ? CELLPHONE_NUMBER_PATTERN_WITH_DASH
				: CELLPHONE_NUMBER_PATTERN;

		if (!ValidationUtil.isRegexPatternMatch(cellPhoneNumber, pattern)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 입력된 휴대전화번호가 유효한 휴대전화번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 018-1231-0912
	 * 예제 : ValidationUtil.isCellphoneNumber(018-1231-0912);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param cellPhoneNumber
	 *            검증할 휴대전화번호
	 * @return 유효한 휴대전화번호 형식인 경우 true를 리턴.
	 */
	public static boolean isCellphoneNumber(String cellPhoneNumber) {
		return isCellphoneNumber(cellPhoneNumber, true);
	}

	/**
	 * 입력된 이메일주소가 유효한 이메일주소의
	 * 
	 * <pre>
	 * 입력 : test@xxxxx.co.kr
	 * 예제 : ValidationUtil.isEmailAddress(test@xxxxx.co.kr);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param email
	 *            검증할 이메일 주소
	 * @return 유효한 이메일 주소 형식인 경우 true를 리턴.
	 */
	public static boolean isEmailAddress(String email) {
		String pattern = "([\\w-\\.]+)@((?:[\\w|-]+\\.)+)([a-zA-Z]{2,4})$";

		if (!ValidationUtil.isRegexPatternMatch(email, pattern)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 입력된 카드번호가 유효한 카드번호의 형식인지 여부를 체크한다.
	 * 
	 * <pre>
	 * 입력 : 4009-1311-1234-4321
	 * 예제 : ValidationUtil.isCardNumber(4009-1311-1234-4321);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param cardNumber
	 *            검증할 카드 번호
	 * @return 유효한 카드번호 형식인 경우 true를 리턴
	 */
	public static boolean isCardNumber(String cardNumber) {

		String pattern = "^\\d{4}[\\s\\-]?\\d{4}[\\s\\-]?\\d{4}[\\s\\-]?\\d{4}$";
		if (!ValidationUtil.isRegexPatternMatch(cardNumber, pattern)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 문자열의 길이가 최소, 최대 길이 사이에 존재하는지 체크
	 * 
	 * <pre>
	 * 입력 : Anyframe Java Test
	 * 예제 : ValidationUtil.isRangeLength("Anyframe Java Test", 10, 20)
	 * 출력 : true
	 * </pre>
	 *
	 * @param str
	 *            체크할 문자열
	 * @param min
	 *            최소길이
	 * @param max
	 *            최대길이
	 * @return 입력된 문자열의 길이가 최대와 최소 사이인 경우 true 를 리턴
	 */
	public static boolean isRangeLength(String str, int min, int max) {
		if (StringUtil.getLength(str) >= min
				&& StringUtil.getLength(str) <= max) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 주어진 문자열이 null 또는 ""(empty string)인지 체크 (StringUtil.isEmpty) 과 동일
	 * 
	 * <pre>
	 * 입력 : ""
	 * 예제 : ValidationUtil.isEmpty("")
	 * 출력 : true
	 * 
	 * <br>
	 * 
	 * 입력 : " " (space)
	 * 예제 : ValidationUtil.isEmpty(" ")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return null 또는 ""(empty string)인지 여부
	 * @see StringUtil#isEmpty(String)
	 */
	public static boolean isEmpty(String str) {
		return StringUtil.isEmpty(str);
	}

	/**
	 * 주어진 문자열이 null 또는 ""(empty string)아닌 지 여부를 체크
	 * 
	 * <pre>
	 * 입력 : ""
	 * 예제 : ValidationUtil.isNotEmpty("")
	 * 출력 : false
	 * 
	 * <br>
	 * 
	 * 입력 : " " (space)
	 * 예제 : ValidationUtil.isNotEmpty(" ")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return null 또는 ""(empty string)아닌지 여부
	 * @see StringUtil#isEmpty(String)
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 입력 컬렉션이 null 또는 size 가 0 인지 체크
	 * 
	 * <pre>
	 * 입력 : null
	 * 예제 : ValidationUtil.isEmpty(null);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param collection
	 *            입력 컬렉션
	 * @return null 또는 size 가 0 인지 여부
	 */
	public static boolean isEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 입력 컬렉션이 null 또는 size 가 0이 아닌지 여부를 체크
	 * 
	 * <pre>
	 * 입력 : null
	 * 예제 : ValidationUtil.isNotEmpty(null);
	 * 출력 : false
	 * </pre>
	 * 
	 * @param collection
	 *            입력 컬렉션
	 * @return null 또는 size 가 0 아닌지 여부
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 입력 BigDecimal 값이 null 인지 여부를 체크
	 * 
	 * <pre>
	 * 입력 : BigDecimal.ZERO
	 * 예제 : ValidationUtil.isEmpty(BigDecimal.ZERO);
	 * 출력 : false
	 * </pre>
	 * 
	 * @param b
	 *            입력 BigDeciaml
	 * @return null 여부를 리턴
	 */
	public static boolean isEmpty(BigDecimal b) {
		return b == null;
	}

	/**
	 * 입력 BigDecimal 값이 null 아닌지 여부를 체크
	 * 
	 * <pre>
	 * 입력 : BigDecimal.ZERO
	 * 예제 : ValidationUtil.isNotEmpty(BigDecimal.ZERO);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param b
	 *            입력 BigDeciaml
	 * @return null 이 아닌 경우 true 리턴
	 */
	public static boolean isNotEmpty(BigDecimal b) {
		return !isEmpty(b);
	}

	/**
	 * trim한 문자열이 null 또는 "" 공백인지 판별
	 * 
	 * <pre>
	 * 입력 : "  "
	 * 예제 : ValidationUtil.isEmptyTrimmed(" ");
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return null 이거나 trim한 문자열 공백인 경우 true 를 리턴
	 */
	public static boolean isEmptyTrimmed(String str) {
		if (str == null) {
			return true;
		}
		return isEmpty(str.trim());
	}

	/**
	 * trim한 문자열이 null 또는 공백이 아닌지 판별
	 * 
	 * <pre>
	 * 입력 : "  "
	 * 예제 : ValidationUtil.isEmptyTrimmed(" ");
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력 문자열
	 * @return null 이거나 trim한 문자열 공백이 아닌경우 true 를 리턴
	 */
	public static boolean isNotEmptyTrimmed(String str) {
		return !isEmptyTrimmed(str);
	}

	/**
	 * 해당 입력문자가 한글인지의 여부를 판별한다.
	 * 
	 * <pre>
	 * 입력 : '한'
	 * 예제 : ValidationUtil.isHangul('한')
	 * 출력 : true
	 * </pre>
	 * 
	 * @param achar
	 *            입력 문자
	 * @return 해당 입력 문자가 한글인 경우 true를 리턴
	 */
	public static boolean isHangul(char achar) {
		String unicodeBlock = Character.UnicodeBlock.of(achar).toString();
		return "HANGUL_JAMO".equals(unicodeBlock)
				|| "HANGUL_SYLLABLES".equals(unicodeBlock)
				|| "HANGUL_COMPATIBILITY_JAMO".equals(unicodeBlock);
	}

	private static boolean isHangulJamo(char achar) {
		String unicodeBlock = Character.UnicodeBlock.of(achar).toString();

		return "HANGUL_JAMO".equals(unicodeBlock)
				|| "HANGUL_COMPATIBILITY_JAMO".equals(unicodeBlock)
				|| "HANGUL_JAMO_EXTENDED_A".equals(unicodeBlock)
				|| "HANGUL_JAMO_EXTENDED_B".equals(unicodeBlock);

	}

	private static boolean isHangulJamo(String str, boolean checkForAll) {
		char chars[] = str.toCharArray();
		if (!checkForAll) {
			for (int i = 0; i < chars.length; i++) {
				if (isHangulJamo(chars[i])) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < chars.length; i++) {
				if (!isHangulJamo(chars[i])) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 주어진 String에 대해서, 한글로만 되어 있는지 혹은 한글이 포함되어 있는지를 판별한다. <br>
	 * 
	 * <pre>
	 * 입력 : "가나다abc", true
	 * 예제 : ValidationUtil.isHangul("가나다abc", true)
	 * 출력 : false
	 * <br>
	 * 
	 * 입력 : "가나다abc", false
	 * 예제 : ValidationUtil..isHangul("가나다abc", false);
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력
	 * @param checkForAll
	 *            해당 문자열이 전부 한글로 구성되어 있는지 체크할지 여부
	 * @return checkForAll true 인 경우 모든 문자가 한글인 경우 true, checkForAll false 인 경우
	 *         한글 문자가 하나라도 존재하면 true 반환
	 */
	public static boolean isHangul(String str, boolean checkForAll) {
		char chars[] = str.toCharArray();
		if (!checkForAll) {
			for (int i = 0; i < chars.length; i++) {
				if (isHangul(chars[i])) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < chars.length; i++) {
				if (!isHangul(chars[i])) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 주어진 String이 숫자나 기호가 아닌 일반 문자로만 구성되어 있는지를 판별한다.
	 * 
	 * <pre>
	 * 입력 :  test123
	 * 예제 : ValidationUtil.isLetter("test123")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 숫자 특수기호가 포함되지 않은 일반문자만 포함된 경우 true 리턴
	 */
	public static boolean isLetter(String str) {
		if (isEmpty(str)) {
			return false;
		}

		char chars[] = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isLetter(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 주어진 String이 기호 및 특수문자가 아닌 일반 문자나 숫자로만 구성되어 있는지를 판별한다.
	 * 
	 * <pre>
	 * 입력 : "12@#%"
	 * 예제 : ValidationUtil.isLetterOrDigit("12@#%")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력된 문자열이 기호 및 특수문자가 아닌 일반 문자가와 숫자로만 구성된 경우 true 리턴
	 */
	public static boolean isLetterOrDigit(String str) {
		if (isEmpty(str)) {
			return false;
		}
		char chars[] = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isLetterOrDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 주어진 String이 white space만을 가지고 있는지를 검사한다.
	 * 
	 * <pre>
	 * 입력 : " a  "
	 * 예제 : ValidationUtil.isWhiteSpaceOnly(" a  ")
	 * 출력 : false
	 * </pre>
	 * 
	 * ex) isWhiteSpaceOnly("   ") => true isWhiteSpaceOnly("") => true
	 * isWhiteSpaceOnly("test") => false
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 white space로만 구성된 경우 true를 리턴
	 */
	public static boolean isWhiteSpaceOnly(String str) {
		if (str == null) {
			return false;
		} else {
			return isEmptyTrimmed(str);
		}
	}

	/**
	 * 전체 문자열이 입력된 정규식 패턴에 맞는지 체크한다.
	 * 
	 * <pre>
	 * 입력 : &quot;aaaaab&quot; , &quot;a*b&quot;
	 * 예제 : StringUtil.isRegexPatternMatch(&quot;aaaaab&quot;, &quot;a*b&quot;)
	 * 출력 : true
	 * 
	 * <br>
	 * 입력 : &quot;cabbbb&quot; , &quot;a*b&quot;
	 * 예제 : StringUtil.isRegexPatternMatch(&quot;cabbbb&quot;, &quot;a*b&quot;)
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param pattern
	 *            검사할 정규식 패턴
	 * @return 입력문자열이 지정된 정규식 패턴과 일치하는 경우 true를 리턴
	 */
	public static boolean isRegexPatternMatch(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 문자열이 입력된 정규식 패턴에 맞는지 체크한다. *는 전체 문자를 표현한다.
	 * 
	 * <pre>
	 * 입력 : 
	 * 예제 :
	 * 출력 :
	 * </pre>
	 * 
	 * <pre>
	 * StringUtil.isPatternMatching("abc-def', "*-*") 	= true
	 * StringUtil.isPatternMatching("abc", "*-*") 	    = false
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param pattern
	 *            정규식 패턴 물자열
	 * @return 입력한 문자열이 주어진 패턴과 일치하는 경우 true를 리턴
	 */
	public static boolean isPatternMatching(String str, String pattern) {
		// if url has wild key, i.e. "*", convert it to ".*" so that we can
		String regExPattern = pattern;
		// perform regex matching
		if (regExPattern.indexOf('*') >= 0) {
			regExPattern = regExPattern.replaceAll("\\*", ".*");
		}

		regExPattern = "^" + regExPattern + "$";

		return Pattern.matches(regExPattern, str);
	}

	/**
	 * 사용자가 지정한 포맷에 맞는 글자가 들어왔는지 체크한다. #은 숫자를 S는 문자를 표현한다.
	 * 
	 * <pre>
	 * 입력 : &quot;123-456&quot; , &quot;###-###&quot;
	 * 예제 : StringUtil.isUserFormat(&quot;123-456&quot;, &quot;###-###&quot;)
	 * 출력 :true
	 * <br>
	 * 
	 * 입력 : &quot;123.456&quot;, &quot;###.###&quot;
	 * 예제 : StringUtil.isUserFormat(&quot;123.456&quot;, &quot;###.###&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            체크할 입력 문자열
	 * @param pattern
	 *            정규표현식
	 * @return 사용자가 지정한 포맷에 맞는 문자가 들어온 경우 true를 리턴
	 */
	public static boolean isUserFormat(String str, String pattern) {
		String metaChange = regexMetaCharEscape(pattern);
		String regexChange = metaChange.replaceAll("#", "\\\\d").replaceAll(
				"S", "[a-zA-Z]");
		return str.matches(regexChange);
	}

	/**
	 * 입력된 문자열이 주어진 필터 패턴에 맞는 문자열인지 확인
	 * 
	 * <pre>
	 * 입력 : &quot;asdf@5456&quot;, &quot;s&quot;
	 * 예제 : StringUtil.isPatternInclude(&quot;asdf@5456&quot;, &quot;s&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            입력문자열
	 * @param param
	 *            필터 정규식 표현
	 * @return 입력문자열이 필터패턴과 일치하는 경우 true를 리턴
	 */
	public static boolean isPatternInclude(String str, String param) {

		if (param.indexOf("s") >= 0) {
			return isRegexPatternMatch(str, ".*[~!@\\#$%<>^&*\\()\\-=+_\\'].*");
		}
		if (param.indexOf("k") >= 0) {
			return isRegexPatternMatch(str, ".*[ㄱ-ㅎ|ㅏ-ㅣ|가-?].*");
		}
		if (param.indexOf("e") >= 0) {
			return isRegexPatternMatch(str, ".*[a-zA-Z].*");
		}
		if (param.indexOf("n") >= 0) {
			return isRegexPatternMatch(str, ".*\\d.*");
		}
		return true;
	}

	/**
	 * 전체 문자열 중에 일부 문자열이 패턴에 맞는지 체크한다
	 * 
	 * <pre>
	 * 입력 : cabbbb, a*b
	 * 예제 : StringUtil.isRegexPatternInclude("cabbbb", "a*b"))
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str
	 *            체크할 입력 문자열
	 * @param pattern
	 *            입력 정규식 표현
	 * @return 입력 문자열의 일부에 해당 패턴이 존재하는 경우 true를 리턴
	 */
	public static boolean isRegexPatternInclude(String str, String pattern) {
		return isRegexPatternMatch(str, ".*" + pattern + ".*");
	}

	private static String regexMetaCharEscape(String orgPattern) {
		return orgPattern.replaceAll("([\\[\\\\\\^\\$\\.\\|\\?\\*\\+\\(\\)])",
				"\\\\$1");
	}

	/**
	 * 입력한 문자가 EUC-KR에서 유효한 문자인지 여부를 체크하고 입력한 문자열에 한글자모가 입력되었는지 여부를 체크한다.
	 * 
	 * @param str
	 *            체크할 문자열
	 * @return Euc-kr에서 유효한 문자인지 여부
	 */
	public static boolean isValidLetters(String str) {
		return isValidLetters(str, "EUC-KR");
	}

	/**
	 * 입력한 문자가 주어진 charset 에서 유효한 문자인지 여부와 한글자모가 입력되었는지 여부를 체크한다.
	 * 
	 * @param str
	 *            체크할 문자열
	 * @param strCharset
	 *            charset명
	 * @return
	 */
	public static boolean isValidLetters(String str, String strCharset) {

		if (!isValidCharsetLettters(str, strCharset)) {
			return false;
		}

		// 자음 모음 체크
		if (isHangulJamo(str, false)) {
			return false;
		}

		return true;
	}

	public static boolean isValidCharsetLettters(String str, String strCharset) {
		Charset charset = Charset.forName(strCharset);
		CharsetEncoder encoder = charset.newEncoder();

		return encoder.canEncode(str);
	}

	/**
	 * 입력된 클래스명(패키지 포함)에 대한 클래스가 존재하는 지 체크
	 * 
	 * @param className
	 *            체크할 클래스명 (풀패키지 포함)
	 * @return 해당 클래스 존재 여부
	 */
	public static boolean isPresentClass(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (Throwable localThrowable) {
			return false;
		}
	}





	
	
	 

}
