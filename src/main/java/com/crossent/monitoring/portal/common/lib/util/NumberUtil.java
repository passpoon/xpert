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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;


public class NumberUtil {

	private NumberUtil() {
		throw new AssertionError();
	}

	/** #,##0.0 */
	public static final String CURRENCY_BELOWTHEDECIMAL1 = "#,##0.0";
	/** #,##0.00 */
	public static final String CURRENCY_BELOWTHEDECIMAL2 = "#,##0.00";
	/** #,##0.000 */
	public static final String CURRENCY_BELOWTHEDECIMAL3 = "#,##0.000";
	/** #,##0.0000 */
	public static final String CURRENCY_BELOWTHEDECIMAL4 = "#,##0.0000";
	/** #,##0.00000 */
	public static final String CURRENCY_BELOWTHEDECIMAL5 = "#,##0.00000";
	/** #,##0 */
	public static final String CURRENCY_NO_DECIMALPOINT = "#,##0";

	private static Random generator = new Random(System.currentTimeMillis());

	/** # */
	public static final String NO_EFFCT_FORMAT = "#";

	/**
	 * 입력받은 문자열이 양수, 음수, 정수, 실수 인지 값을 받아 옳고 그름을 체크한다.
	 * 
	 * <pre>
	 * 입력 : 1234, positive
	 * 예제 :  NumberUtil.checkNumberType(&quot;1234&quot;, &quot;positive&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : 0.1234, positive
	 * 예제 :  NumberUtil.checkNumberType(&quot;0.1234&quot;, &quot;positive&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : -1234.12, negative
	 * 예제 :  NumberUtil.checkNumberType(&quot;-1234.12&quot;, &quot;negative&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : -0.1234, negative
	 * 예제 :  NumberUtil.checkNumberType(&quot;-0.1234&quot;, &quot;negative&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : 1234, whole
	 * 예제 :  NumberUtil.checkNumberType(&quot;1234&quot;, &quot;whole&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : -1234, whole
	 * 예제 :  NumberUtil.checkNumberType(&quot;-1234&quot;, &quot;whole&quot;) 
	 * 출력 : true
	 * <br>
	 * 입력 : -1234.123, real
	 * 예제 :  NumberUtil.checkNumberType(&quot;-1234.123&quot;, &quot;real&quot;) 
	 * 출력 : true
	 * <br>
	 * 입력 : 1.34, real
	 * 예제 :  NumberUtil.checkNumberType(&quot;1.34&quot;, &quot;real&quot;) 
	 * 출력 : true
	 * </pre>
	 * 
	 * @param number
	 *            체크할 문자열
	 * @param type
	 *            체크할 문자열 타입 - positive-양수, negative-음수, whole-정수, real-실수           
	 * @return 입력받은 문자열 타입이  양수, 음수, 정수, 실수이면 true 리턴, 아니면 false 리턴			
 	 *         <code>true</code>.
	 */
	public static boolean checkNumberType(String number, String type) {
		String positivePattern = "^[+]?([1-9]\\d*|[1-9]\\d*\\.\\d*|0?\\.\\d*[1-9]\\d*)$";
		String negativePattern = "^-([1-9]\\d*|[1-9]\\d*\\.\\d*|0?\\.\\d*[1-9]\\d*)$";
		String wholePattern = "^[+-]?[1-9]\\d*$";
		String realPattern = "^[+-]?([1-9]\\d*\\.\\d*|0?\\.\\d*[1-9]\\d*)$";

		if ("positive".equals(type)) {
			return Pattern.matches(positivePattern, number);
		} else if ("negative".equals(type)) {
			return Pattern.matches(negativePattern, number);
		} else if ("whole".equals(type)) {
			return Pattern.matches(wholePattern, number);
		} else if ("real".equals(type)) {
			return Pattern.matches(realPattern, number);
		}
		return false;
	}

	/**
	 * 입력한 int 값을 포맷에 맞는 문자열 반환<br>
	 * 
	 * <pre>
	 * 입력 : 1023412, ###,###,###
	 * 예제 :  NumberUtil.formatNumber(1023412, &quot;###,###,###&quot;)
	 * 출력 : 1,023,412
	 * <br>
	 * 입력 : 1023412123, ###,###
	 * 예제 :  NumberUtil.formatNumber(1023412123, &quot;###,###&quot;)
	 * 출력 : 1,023,412,123
	 * <br>
	 * 입력 : 1023412123, ##,##
	 * 예제 :   NumberUtil.formatNumber(1023412123, &quot;##,##&quot;) 
	 * 출력 : 10,23,41,21,23
	 * <br>
	 * 입력 : 1023412123, ##.##
	 * 예제 :   NumberUtil.formatNumber(1023412123, &quot;##.##&quot;) 
	 * 출력 : 1023412123
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 int값 
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @return 변환된 문자열
	 */
	public static String formatNumber(int number, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * 입력한 int 값을 ###,###,###포맷으로 변경하여 문자열 반환
	 * 
	 * <pre>
	 * 입력 : 1023412
	 * 예제 :  NumberUtil.formatNumber(1023412)
	 * 출력 : 1,023,412
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 int값
	 * @return 변환된 문자열
	 */
	public static String formatNumber(int number) {
		DecimalFormat df = new DecimalFormat("###,###,###");
		return df.format(number);
	}

	/**
	 * 입력한 short 값을 포맷에 맞는 문자열 반환.
	 * 
	 * <pre>
	 * 입력 : 12345s, ###,###
	 * 예제 :  NumberUtil.formatNumber(12345s, "###,###")
	 * 출력 : 12,345
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 short값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @return 변환된 문자열
	 */
	public static String formatNumber(short number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}

	/**
	 * 입력한 double값을 포맷에 맞는 문자열 반환<br>
	 * 
	 * <pre>
	 * 입력 : 12345.67d, ###,###.#
	 * 예제 :  NumberUtil.formatNumber(12345.67d, &quot;###,###.#&quot;)
	 * 출력 : 12,345.7
	 * </pre>
	 * 
	 * @param number
	 *             변환시킬 double값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @return 변환된 문자열
	 */
	public static String formatNumber(double number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}
	
	/**
	 * 입력한 double 값을 포맷에 맞는 문자열 반환  (RoundingMode 설정 가능)<br>
	 *
	 * <pre>
	 * 입력 : 12345999.999, "#,###.00",RoundingMode.DOWN
	 * 예제 : NumberUtil.formatNumber(12345999.999, "#,###.##",RoundingMode.DOWN)
	 * 출력 : 12,345,999.99
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 double값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @param roundingMode
	 *            반올림 모드
	 * @return 변환된 문자열
	 */
	public static String formatNumber(double number, String format, RoundingMode roundingMode) {
		if(roundingMode == null)	roundingMode = RoundingMode.HALF_EVEN;  //set default roundingMode
		
		DecimalFormat decimalformat = new DecimalFormat(format);
		decimalformat.setRoundingMode(roundingMode);	
		return decimalformat.format(number);
	}

	/**
	 * 입력한 long 값을 포맷에 맞는 문자열 반환<br>
	 * 
	 * <pre>
	 * 입력 : 12345.67L, ###,###.#
	 * 예제 :  NumberUtil.formatNumber(12345.67d, &quot;###,###.#&quot;)
	 * 출력 : 12,345.7
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 long값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @return 변환된 문자열
	 */
	public static String formatNumber(long number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}
	
	/**
	 * 입력한 BigDecimal 값을 포맷에 맞는 문자열 반환<br>
	 *
	 * <pre>
	 * 입력 : 1023412123.67, ###,###.#
	 * 예제 :  NumberUtil.formatNumber(new BigDecimal("1023412123.67"), "###,###.#")
	 * 출력 : 1,023,412,123.7
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 BigDecimal값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @return 변환된 문자열
	 */
	public static String formatNumber(BigDecimal number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}
	
	/**
	 * 입력한 BigDecimal 값을 포맷에 맞는 문자열 반환
	 * (포맷이 "00000.0000" 형태일때 '-'음수부호도 포맷길이에 포함시키도록 보완  -신용카드 요청) <br>
	 *
	 * <pre>
	 * 입력 : 98765.86789, "0000000.000"
	 * 예제 : NumberUtil.formatNumberLength(new BigDecimal("98765.86789"), "0000000.000")
	 * 출력 : 0098765.868
	 * <br>
	 * 입력 : 98765.86789, "0000000.000"
	 * 예제 : NumberUtil.formatNumberLength(new BigDecimal("-98765.86789"), "0000000.000")
	 * 출력 : -098765.868
	 * </pre>
	 * `
	 * @param number
	 *            변환시킬 BigDecimal값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @return 변환된 문자열
	 */
	public static String formatNumberLength(BigDecimal number, String format) {
		
		if (number == null || StringUtil.isEmpty(format)){
			return "";
		}

		DecimalFormat decimalformat = new DecimalFormat(format);
		
		// 음수이면서 format 형태가 0으로 시작될때
		if (number.signum() == -1 && format.charAt(0) == '0'){
			String tmp = decimalformat.format(number);

			return tmp.replaceFirst("0" , ""); //첫번째 '0' 문자를 삭제
		}
		return decimalformat.format(number);
	}
	
	/**
	 * 입력한 BigDecimal 값을 포맷에 맞는 문자열 반환  (RoundingMode 설정 가능)<br>
	 *
	 * <pre>
	 * 입력 : new BigDecimal("1234599999.999999999999999999"), "#,###.00",RoundingMode.DOWN
	 * 예제 :  NumberUtil.formatNumber(new BigDecimal("1234599999.999999999999999999"), "#,###.00",RoundingMode.DOWN)
	 * 출력 : 1,234,599,999.99 
	 * <br>
	 * 입력 : new BigDecimal("1023412123.34"), "#,###.#", RoundingMode.UP
	 * 예제 : NumberUtil.formatNumber(new BigDecimal("1023412123.34"), "#,###.#", RoundingMode.UP)
	 * 출력 : 1,023,412,123.4
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 BigDecimal값
	 * @param format
	 *            변환값에 적용할 String 형식
	 * @param roundingMode
	 *            반올림 모드
	 * @return 변환된 문자열
	 */
	public static String formatNumber(BigDecimal number, String format, RoundingMode roundingMode) {
		if (number == null)		return "";		
		if(roundingMode == null)	roundingMode = RoundingMode.HALF_EVEN;  //set default roundingMode
		
		DecimalFormat decimalformat = new DecimalFormat(format);
		decimalformat.setRoundingMode(roundingMode);	
		return decimalformat.format(number);
	}

	/**
	 * 입력한 문자열값을 포맷에 맞는 문자열 반환 <br>
	 * 
	 * <pre>
	 * 입력 : 1234567, #,##0.000
	 * 예제 :  NumberUtil.formatNumber(&quot;1234567&quot;, &quot;#,##0.000&quot;)
	 * 출력 : 1,234,567.000
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 String 문자열
	 * @param format
	 *            변환문자열에 적용할 String 형식
	 * @return 변환된 문자열
	 * @throws ParseException
	 */
	public static String formatNumber(String number, String format)
			throws ParseException {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern(format);
		Number sourceNumber = df.parse(StringUtil.deleteAny(number, ','));
		return df.format(sourceNumber);
	}

	/**
	 * 특정한 Locale에 맞는 통화 표기를 가져옴<br>
	 * 
	 * <pre>
	 * 입력 : 3527900, Locale.KOREA
	 * 예제 :  NumberUtil.formatNumberByLocale(3527900, Locale.KOREA)
	 * 출력 : ￦3,527,900
	 * <br>
	 * 입력 : 3527900, Locale.US
	 * 예제 :  NumberUtil.formatNumberByLocale(3527900, Locale.US)
	 * 출력 : $3,527,900.00
	 * </pre>
	 * 
	 * @param number
	 *            통화로 변환시킬 값
	 * @param locale
	 *            해당 국가
	 * @return 해당 국가 통화로 변환된 문자열
	 */
	public static String formatNumberByLocale(long number, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		return nf.format(number);
	}

	/**
	 * point 값에 따른 형식으로 입력값을 변환
	 * 
	 * <pre>
	 * 입력 : 10231023123.1213, 2
	 * 예제 :  NumberUtil.formatNumberByPoint(10231023123.1213, 2)
	 * 출력 : 10,231,023,123.12
	 * <br>
	 * 입력 : 10231023123.1213, 5
	 * 예제 :  NumberUtil.formatNumberByPoint(10231023123.1213, 5)
	 * 출력 : 10,231,023,123.12130
	 * <br>
	 * 입력 : 10231023123.1213, 6
	 * 예제 :  NumberUtil.formatNumberByPoint(10231023123.1213, 6)
	 * 출력 : 10,231,023,123.121
	 * </pre>
	 * 
	 * @param number
	 *            변환 시킬 값
	 * @param point
	 *            지정된 십진법 타입(소수점 포함)
	 * @return 해당 십진법 타입으로 변환된 문자열
	 */	
	public static String formatNumberByPoint(double number, int point) {
		String format = "";

		switch (point) {
		case 0:
			format = "###,###,###.###";
			break;
		case 1:
			format = "###,###,###,##0.0";
			break;
		case 2:
			format = "###,###,###,##0.00";
			break;
		case 3:
			format = "###,###,###,##0.000";
			break;
		case 4:
			format = "###,###,###,##0.0000";
			break;
		case 5:
			format = "###,###,###,##0.00000";
			break;
		default:
			format = "###,###,###.###";
			break;
		}
		DecimalFormat df = new DecimalFormat(format);
		return String.valueOf(df.format(number));
	}

	/**
	 * 랜덤한 수 반환
	 * 
	 * @param targetClass
	 *            반환될 랜덤 수의 타입 지정 - Integer, Long, Float, Double
	 *     
	 * @return 랜덤수
	 */
	public static <T extends Number> T getRandomNumber(Class<T> targetClass) {
		return getRandomNumber(targetClass, true);
	}

	/**
	 * 부호를 입력받아 랜덤한 수 반환
	 * 
	 * @param targetClass
	 *            반환될 랜덤 수의 타입 지정 - Integer, Long, Float, Double
	 * @param sign
	 *            양수면 true 지정, 음수면 false
	 * @return 랜덤수
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getRandomNumber(Class<T> targetClass,
			boolean sign) {
		if (sign) {
			if (targetClass.equals(Integer.class)) {
				return (T) (Integer) generator.nextInt();
			} else if (targetClass.equals(Long.class)) {
				return (T) (Long) generator.nextLong();
			} else if (targetClass.equals(Float.class)) {
				return (T) (Float) generator.nextFloat();
			} else if (targetClass.equals(Double.class)) {
				return (T) (Double) generator.nextDouble();
			}
		} else {
			if (targetClass.equals(Integer.class)) {
				return (T) (Integer) (-generator.nextInt());
			} else if (targetClass.equals(Long.class)) {
				return (T) (Long) (-generator.nextLong());
			} else if (targetClass.equals(Float.class)) {
				return (T) (Float) (-generator.nextFloat());
			} else if (targetClass.equals(Double.class)) {
				return (T) (Double) (-generator.nextDouble());
			}
		}
		return (T) null;
	}

	/**
	 * 지정된 길이를 가진 랜덤한 수 조회
	 * 
	 * @param targetClass
	 *            반환될 랜덤수의 타입 지정 - Integer, Long, Float, Double
	 * @param fixedLength
	 *            반환될 랜덤수의  길이
	 * @return 랜덤수
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getRandomNumber(Class<T> targetClass,
			int fixedLength) {
		if (fixedLength < 0)
			return (T) null;

		double randomNumber = 0.0;

		if (targetClass.equals(Integer.class)) {
			randomNumber = getRandomNumber(fixedLength, 10, Integer.MAX_VALUE);
			return (T) (Integer) (int) randomNumber;
		} else if (targetClass.equals(Long.class)) {
			randomNumber = getRandomNumber(fixedLength, 19, Long.MAX_VALUE);
			return (T) (Long) (long) randomNumber;
		} else if (targetClass.equals(Float.class)) {
			int digit = generator.nextInt(fixedLength);
			randomNumber = getRandomNumber(digit, 39, Float.MAX_VALUE);
			return (T) (Float) (float) randomNumber;
		} else if (targetClass.equals(Double.class)) {
			int digit = generator.nextInt(fixedLength);
			randomNumber = getRandomNumber(digit, 309, Double.MAX_VALUE);
			return (T) (Double) randomNumber;
		}
		return (T) null;
	}

	/**
	 * 최대 최소값 사이의 랜덤한 수 조회
	 * 
	 * @param fixedLength
	 *          최대, 최소 범위 길이  
	 * @param maxLength
	 *          최대 길이 지정
	 * @param maxValue
	 *          최대값 지정
	 * @return 랜덤수
	 */
	private static double getRandomNumber(int fixedLength, int maxLength,
			double maxValue) {
		double max = 0;
		double base = Math.pow(10, fixedLength - 1);

		if (fixedLength < maxLength) {
			max = Math.pow(10, fixedLength) - 1;
		} else if (fixedLength == maxLength) {
			max = maxValue;
		} else {
			return -1;
		}
		return (Math.random() * (max - base + 1)) + base;
	}

	/**
	 * 입력한 타입의 최대 최소값 사이의 랜덤한 수 조회
	 * 
	 * @param targetClass
	 *           반환될 랜덤수의 타입 지정 - Integer, Long, Float, Double
	 * @param min
	 *            최소값
	 * @param max
	 *            최대값
	 * @return random number
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getRandomNumber(Class<T> targetClass,
			T min, T max) {
		double minDouble = org.springframework.util.NumberUtils
				.convertNumberToTargetClass(min, Double.class);
		double maxDouble = org.springframework.util.NumberUtils
				.convertNumberToTargetClass(max, Double.class);
		double randomNumber = (Math.random() * (maxDouble - minDouble + 1))
				+ minDouble;

		if (targetClass.equals(Integer.class)) {
			return (T) (Integer) (int) randomNumber;
		} else if (targetClass.equals(Long.class)) {
			return (T) (Long) (long) randomNumber;
		} else if (targetClass.equals(Float.class)) {
			return (T) (Float) (float) randomNumber;
		} else if (targetClass.equals(Double.class)) {
			return (T) (Double) randomNumber;
		}
		return (T) null;
	}

	/**
	 * 입력된 문자열에 숫자가 있는지 확인
	 * 
	 * <pre>
	 * 입력 : str1ing
	 * 예제 : NumberUtil.hasNumber("str1ing")
	 * 출력 : true
	 * <br>
	 * 입력 : "" (empty string)
	 * 예제 : NumberUtil.hasNumber("")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            확인할 문자열
	 * @return 입력된 문자열에 숫자가 존재하면 true리턴
	 */
	public static boolean hasNumber(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) > 47 && str.charAt(index) < 58)
				return true;
		}
		
		return false;
	}

	/**
	 * 입력받은 bigDecimal을 문자열로 반환 
	 * 
	 * <pre>
	 * 입력 : null
	 * 예제 : NumberUtil.bigDecimalToString(null)
	 * 출력 : "" (empty string)
	 * <br>
	 * 입력 : 123
	 * 예제 : NumberUtil.bigDecimalToString(new BigDecimal(123))
	 * 출력 : "123"
	 * </pre>
	 * 
	 * @param number
	 *            문자열로 변환할 bigDecimal
	 * @return 변환된 문자열, 변환된 문자열이 null 또는 0이면 공백 리턴
	 *       
	 */
	public static String bigDecimalToString(BigDecimal number) {
		BigDecimal zero = BigDecimal.ZERO;

		if (number == null || zero.equals(number))
			return "";
		else
			return number.toString();
	}

	/**
	 * 입력받은 int를 문자열로 변환
	 * 
	 * <pre>
	 * 입력 : 14
	 * 예제 : NumberUtil.intToString(14)
	 * 출력 : "14"
	 * </pre>
	 * 
	 * @param number
	 *            입력받은 int값
	 * @return 변환된 int값
	 */
	public static String intToString(int number) {
		return String.valueOf(number);
	}
	
	/**
	 * 입력받은long을 문자열로 변환
	 * 
	 * <pre>
	 * 입력 : 14
	 * 예제 : NumberUtil.longToString(14)
	 * 출력 : "14"
	 * </pre>
	 * 
	 * @param number
	 *            입력받은 long값
	 * @return 변환된 long값
	 */
	public static String longToString(long number) {
		return String.valueOf(number);
	}

	/**
	 * 입력받은 문자열이 숫자인지 체크<br>
	 * 
	 * <pre>
	 * 입력 : 12312312.2f
	 * 예제 : NumberUtil.isNumber(&quot;12312312.2f&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : 1.7976931348623157E308
	 * 예제 : NumberUtil.isNumber(&quot;1.7976931348623157E308&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : 12sd312312
	 * 예제 : NumberUtil.isNumber(&quot;12sd312312&quot;)
	 * 출력 : false
	 * </pre>
	 * 
	 * @param str
	 *            체크할 문자열
	 * @return 문자열이 숫자형식이면 true 
	 */
	public static boolean isNumber(String str) {
		if (StringUtil.isEmpty(str))
			return false;

		// cf.) apache commons lang NumberUtils.isNumber - ex.) 875634512312312l
		// - true
		if (str.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			return true;
		} else {
			// try parse double
			try {
				@SuppressWarnings("unused")
				double doubleVal = Double.parseDouble(str);
				return true;
			} catch (NumberFormatException de) {
				// try BigDecimal
				try {
					@SuppressWarnings("unused")
					BigDecimal bigDecimalVal = new BigDecimal(str);
					return true;
				} catch (NumberFormatException be) {
					return false;
				}
			}
		}
	}
	
	/**
	 * 입력받은 수에 대해 target 숫자를 찾아서 replacement 숫자로 바꿔준다.
	 * 
	 * <pre>
	 * 입력 : 123456.2, 234, 567
	 * 예제 :  NumberUtil.replaceNumber(123456.2, 234, 567)
	 * 출력 : 156756.2
	 * <br>
	 * 입력 : 452345.823, 23, 567
	 * 예제 : NumberUtil.replaceNumber(452345.823, 23, 567)
	 * 출력 : 4556745.8567
	 * </pre>
	 * 
	 * @param source
	 *            입력받은 수
	 * @param target
	 *            찾을 숫자
	 * @param replacement
	 *            교체할 숫자
	 * @return 교체된 입력받은 수를 double 타입으로 변환하여 리턴
	 */
	public static double replaceNumber(double source, int target,
			int replacement) {
		String sourceStr = String.valueOf(source);
		String targetStr = String.valueOf(target);
		String replacementStr = String.valueOf(replacement);

		return Double.parseDouble(sourceStr.replaceAll(targetStr,
				replacementStr));
	}

	/**
	 * 입력받은 문자열을 BigDecimal로 변환
	 * 
	 * @param number
	 *           변활시킬 문자열
	 * @return BigDecimal로 변환된 문자열
	 */
	public static BigDecimal stringToBigDecimal(String number) {
		if (StringUtil.isEmptyTrimmed(number))
			return BigDecimal.ZERO;
		else
			return new BigDecimal(number);
	}

	/**
	 * 입력받은 String을 int로 변환 null 혹은 "" 인 경우 defaultValue 반환
	 * 
	 * <pre>
	 * 입력 : "" (empty string), -1
	 * 예제 :  NumberUtil.stringToInt("", -1)
	 * 출력 : -1
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 문자열
	 * @param defaultValue null 혹은 "" 인 경우 리턴할 defaultValue
	 * @return 변환된 int값
	 */
	public static int stringToInt(String number, int defaultValue) {
		if (StringUtil.isEmptyTrimmed(number)) {
			return defaultValue;
		}
		return Integer.parseInt(number.trim());
	}
	
	/**
	 * 입력받은 String을 long으로 변환, null 혹은 "" 인 경우 defaultValue 반환
	 * 
	 * <pre>
	 * 입력 : "" (empty string), -1
	 * 예제 :  NumberUtil.stringToLong("", -1)
	 * 출력 : -1
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 문자열
	 * @param defaultValue null 혹은 "" 인 경우 리턴할 defaultValue
	 * @return 변환된 long값
	 */
	public static long stringToLong(String number, long defaultValue) {
		if (StringUtil.isEmptyTrimmed(number)) {
			return defaultValue;
		}
		return Long.parseLong(number.trim());
	}

	/**
	 * 입력받은 String을 int로 변환 null이거나 "" 인 경우 0반환
	 * 
	 * <pre>
	 * 입력 : "123"
	 * 예제 :  NumberUtil.stringToInt("123")
	 * 출력 : 123
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 문자열
	 * @return 변환된 int값
	 */
	public static int stringToInt(String number) {
		return stringToInt(number, 0);
	}
	
	/**
	 * 입력받은 String을 long으로 변환, null이거나 "" 인 경우 0반환
	 * 
	 * <pre>
	 * 입력 : "123"
	 * 예제 :  NumberUtil.stringToLong("123")
	 * 출력 : 123
	 * </pre>
	 * 
	 * @param number
	 *            변환시킬 문자열
	 * @return 변환된 long값
	 */
	public static long stringToLong(String number) {
		return stringToLong(number, 0);
	}

	/**
	 * 입력받은 문자열을 BigDecimal로 변환
	 * 
	 * <pre>
	 * 입력 : "123456789", 5, 3
	 * 예제 :  NumberUtil.substringToBigDecimal("123456789", 5, 3)
	 * 출력 : 678
	 * </pre>
	 * 
	 * @param number
	 *             변환시킬 문자열
	 * @param beginIndex
	 *            시작 위치
	 * @param size
	 *            끝에서부터 시작 위치까지 길이  
	 * @return 변환된 BigDecimal
	 */
	public static BigDecimal substringToBigDecimal(String number,
			int beginIndex, int size) {
		if (StringUtil.isEmptyTrimmed(number))
			return BigDecimal.ZERO;
		else if (number.length() < beginIndex + size)
			return new BigDecimal(StringUtil.leftPad(number, beginIndex + size,
					"0"));
		else
			return new BigDecimal(number.substring(beginIndex, beginIndex
					+ size));
	}

	/**
	 * 입력한 문자열의 일부분을 int로 변환
	 * 
	 * <pre>
	 * 입력 : "123456789", 5, 3
	 * 예제 :  NumberUtil.substringToInt("123456789", 5, 3)
	 * 출력 : 678
	 * </pre>
	 * 
	 * @param number
	 *           변환시킬 문자열
	 * @param beginIndex
	 *          시작 위치
	 * @param size
	 *            끝에서부터 시작 위치까지 길이 
	 * @return 변환된 int값
	 */
	public static int substringToInt(String number, int beginIndex, int size) {
		if (StringUtil.isEmptyTrimmed(number))
			return 0;
		else if (number.length() < beginIndex + size)
			return Integer.parseInt(StringUtil.leftPad(number, beginIndex
					+ size, "0"));
		else
			return Integer.parseInt(number.substring(beginIndex, beginIndex
					+ size));
	}
	
	/**
	 * 입력한 문자열의 일부분을 long으로 변환
	 * 
	 * <pre>
	 * 입력 : "123456789", 5, 3
	 * 예제 :  NumberUtil.substringToLong("123456789", 5, 3)
	 * 출력 : 678
	 * </pre>
	 * 
	 * @param number
	 *           변환시킬 문자열
	 * @param beginIndex
	 *          시작 위치
	 * @param size
	 *            끝에서부터 시작 위치까지 길이 
	 * @return 변환된 long값
	 */
	public static long substringToLong(String number, int beginIndex, int size) {
		if (StringUtil.isEmptyTrimmed(number))
			return 0;
		else if (number.length() < beginIndex + size)
			return Integer.parseInt(StringUtil.leftPad(number, beginIndex
					+ size, "0"));
		else
			return Long.parseLong(number.substring(beginIndex, beginIndex
					+ size));
	}

	/**
	 * integer 값의 길이에 size 만큼 0을 채운 문자열을 반환한다.<br>
	 * 
	 * <pre>
	 * 입력 : 123, 5
	 * 예제 :  NumberUtil.intToZeroPadString(123, 5)
	 * 출력 : "00123"
	 * </pre>
	 * 
	 * @param number
	 *           0으로 채워 변환시킬 int값
	 * @param size
	 *           0의 길이 지정
	 * @return 0으로 채워진 문자열
	 */
	public static String intToZeroPadString(int number, int size) {
		
		if(number >= 0){
			return StringUtil.leftPad(String.valueOf(number), size, '0');
		}else{
			int absNumber = number * -1;
			return '-' + StringUtil.leftPad(String.valueOf(absNumber), size-1, '0');
		}		
	}
	
	/**
	 * long 값의 길이에 size 만큼 0을 채운 문자열을 반환한다.<br>
	 * 
	 * <pre>
	 * 입력 : 123, 5
	 * 예제 :  NumberUtil.longToZeroPadString(123, 5)
	 * 출력 : "00123"
	 * </pre>
	 * 
	 * @param number
	 *           0으로 채워 변환시킬 long값
	 * @param size
	 *           0의 길이 지정
	 * @return 0으로 채워진 문자열
	 */
	public static String longToZeroPadString(long number, int size) {
		
		if(number >= 0){
			return StringUtil.leftPad(String.valueOf(number), size, '0');	
		}else{	
			long absNumber = number * -1;
			return '-' + StringUtil.leftPad(String.valueOf(absNumber), size-1, '0');
		}
	}
	
	/**
	 * bigDecimal 값의 길이에 size 만큼 0을 채운 문자열을 반환한다.<br>
	 * 
	 * <pre>
	 * 입력 : 123, 5
	 * 예제 :  NumberUtil.decimalToZeroPadString(123, 5)
	 * 출력 : "00123"
	 * </pre>
	 * 
	 * @param number
	 *           0으로 채워 변환시킬 long값
	 * @param size
	 *           0의 길이 지정
	 * @return 0으로 채워진 문자열
	 */
	public static String decimalToZeroPadString(BigDecimal number, int size) {
		if (number == null){
			return "";
		}
		
		if (number.signum() == -1){			
			BigDecimal absNumber = number.negate();
			return '-' + StringUtil.leftPad(String.valueOf(absNumber), size-1, '0');			
		}
		
		return StringUtil.leftPad(String.valueOf(number), size, '0');		
	}	

	/**
	 * 입력한 문자열이 숫자로만 구성되어 있는지 여부 체크
	 * 
	 * <pre>
	 * 입력 : 1234
	 * 예제 :  NumberUtil.isDigit("1234")
	 * 출력 : true
	 * <br>
	 * 입력 : 1234A
	 * 예제 :  NumberUtil.isDigit("1234A")
	 * 출력 : false
	 * </pre>
	 * 
	 * @param number
	 *            체크할 문자열, null도 가능
	 * @return 문자열이 숫자만 포함하면 true, 문자를 포함하거나 null이면 false
	 */
	public static boolean isDigit(String number) {
		if (StringUtil.isEmptyTrimmed(number)) {
			return false;
		}
		char chars[] = number.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 입력한 문자열에 숫자가 아닌 다른 문자가 존재하는지 여부 체크
	 * 
	 * <pre>
	 * 입력 : 1234
	 * 예제 :  NumberUtil.isNotDigit("12345")
	 * 출력 : false
	 * <br>
	 * 입력 : 1234A
	 * 예제 :  NumberUtil.isNotDigit("12345ABC")
	 * 출력 : true
	 * </pre>
	 *
	 * @param number
	 *            체크할 문자열, null도 가능
	 * @return 어떤 문자라도 포함하면 true, 숫자이거나 null을 포함하면 false
	 */
	public static boolean isNotDigit(String number) {
		if (number == null) {
			return false;
		}
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  ---------------------------------------------------end of anyframe-core ------------------------------------
	 */
	
	/**
	 * null 인 BigDecimal 값을 0로 변환하여 반한환다.
	 * 
	 * <pre>
	 * 입력 : null
	 * 예제 :  NumberUtil.nullToZero(null)
	 * 출력 : "" (empty string)
	 * <br>
	 * 입력 : 123
	 * 예제 :  NumberUtil.nullToZero(new BigDecimal(123))
	 * 출력 : 123
	 * </pre>
	 * 
	 * @param param 입력 BigDecimal
	 * @return 변환된 BigDecimal 값
	 */
	public static BigDecimal nullToZero(BigDecimal param){
		return param == null ? BigDecimal.ZERO : param;
	}
	
	/**
	 * 입력으로 들어온 bigDecimal 값을 더해서 반환한다.
	 * 
	 * <pre>
	 * 입력 : 33.33, 999.99
	 * 예제 :  NumberUtil.sum(new BigDecimal("33.33"), new BigDecimal("999.99"))
	 * 출력 : 1033.32
	 * </pre>
	 * 
	 * @param operands 
	 * @return
	 */
	public static BigDecimal sum(BigDecimal... operands){
		BigDecimal summation = BigDecimal.ZERO;
		
		if (operands == null || operands.length == 0){
			return summation;
		}

        for (BigDecimal operand : operands) {
        	if (operand != null){
        		summation = summation.add(operand);
        	}
        }

        return summation;
	}
	
	/**
	 * 입력으로 들어온 bigDecimal 값을 차감하여 반환한다.
	 * 
	 * <pre>
	 * 입력 : new BigDecimal("102.4"), new BigDecimal("33.64"), new BigDecimal("40.05")
	 * 예제 :  NumberUtil.subtract(new BigDecimal("102.4"), new BigDecimal("33.64"), new BigDecimal("40.05"))
	 * 출력 : 28.71
	 * </pre>
	 * 
	 * @param operands 
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal... operands){
		int operLen= operands.length; //입력데이터의 갯수
		
		if (operands == null || operLen == 0){
			return BigDecimal.ZERO;
		}
		
		//차감연산을 위한 기준값
		BigDecimal baseVal = operands[0];
		
		if(operLen > 1){
	        for (int i=1; i < operLen; i++) {		        	
	        	BigDecimal operand = operands[i];
	        	if (operand != null){
	        		baseVal = baseVal.subtract(operand);
	        	}
	        }			
		}
        return baseVal;
	}	
	
	/**
	 * long 값을 int로 변환한다. 해당 값이 int 의 범위를 넘는 경우 ArithmeticException을 발생시킨다.
	 * @param l
	 * @return int 로 변환된 값
	 */
	public static int longToInt(long l)throws ArithmeticException{
		
		if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE){
			throw new ArithmeticException("the input value can not exceed integer range!");
		}
		
		return (int)l;
	}
	
	/**
	 * AS-IS decToCharWithFormat 매크로에 대응하는 포매팅 유틸리티 메소드 (BigDecimal 타입)
	 * @param number 입력숫자 
	 * @param format 변환 포맷 
	 * @return
	 */
	public static String decToCharWithFormat(BigDecimal number, String format){		
		return decimalToStringWithFormat(number, format, true);
	}
	
	/**
	 * AS-IS decToCharWithFormat 매크로에 대응하는 포매팅 유틸리티 메소드 (long 타입)
	 * @param number
	 * @param format
	 * @return
	 */
	public static String longToCharWithFormat(long number, String format){
		return decToCharWithFormat(new BigDecimal(number), format); 
	}
	
	/**
	 * AS-IS DTOC_FORMAT 매크로에 대응하는 포매팅 유틸리티 메소드 
	 * @param number
	 * @param format
	 * @return
	 */
	public static String dtocFormat(BigDecimal number, String format){
		return decimalToStringWithFormat(number, format, false);
	}
	
	/**
	 * AS-IS DTOC_FORMAT 매크로에 대응하는 포매팅 유틸리티 메소드 
	 * @param number
	 * @param format
	 * @return
	 */
	public static String dtocFormat(long number, String format){
		return decimalToStringWithFormat(new BigDecimal(number), format, false); 
	}

	private static String decimalToStringWithFormat(BigDecimal number, String format, boolean findFirstNonZero) {
		if (number == null || StringUtil.isEmpty(format)){
			return "";
		}

		boolean minus = false;
		boolean dollarProcessed = false, wonProcessed = false;
		boolean ampersandProcessed = false, signProcessed = false;   		

		StringBuilder upperPointStr = new StringBuilder();  //소수점 위 string
		StringBuilder lowerPointStr = new StringBuilder();  //소수점 아래 string 

		BigDecimal absNumber = number;
		
		//1. 입력값이 음수이면 minus indicator set하고 양수로 변경
		if (number.signum() == -1){
			minus = true;
			absNumber = number.negate();
		}

		// 입력 decimal값의 소수점 윗부분을 처리
		BigDecimal intValue = absNumber.setScale(0, BigDecimal.ROUND_DOWN);

		String intValueStr = intValue.toPlainString();
		
		int nonZeroPos = findFirstNonZero ? findFirstNonZeroPos(intValueStr) : countLeadingZeros(intValueStr);	
		
		String upperPointFormat = format;
		
		//FORMAT STRING의 소수점 앞부분 처리
		int periodPos = format.indexOf(".");

		if (periodPos > 0) {
			upperPointFormat = format.substring(0, periodPos);        
		}
		
		int srcIdx = intValueStr.length()-1;
		
		// 정수 FORMAT STRING의 각각의 char에 대한 처리 (정수의 경우 역방향으로 처리) 
		for (int j = upperPointFormat.length() -1 ; j >= 0; j--){
			char currentFormatChar = upperPointFormat.charAt(j);			
			
			switch (currentFormatChar){
			case '9': 
				// 포맷이 9이고 포맷의 길이가 입력된 값의 길이보다 긴 경우 0으로 채우기 
				if (srcIdx >=  0){
					upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
				}else{
					upperPointStr.insert(0, '0');
				}
				break;

			case ',':
				//, 포맷이 존재하는 경우 다음 포맷을 확인
				if (srcIdx < nonZeroPos){
					if (j > 0){
						char nextFormatChar = upperPointFormat.charAt(j-1);						

						switch (nextFormatChar){
						case '9':
							upperPointStr.insert(0, ','); break;
						case '*':
							upperPointStr.insert(0, '*'); break;
						case '+':
							if (signProcessed){
								upperPointStr.insert(0, ' ');
							}else{
								if (minus) {
									upperPointStr.insert(0, '-');
								}else {
									upperPointStr.insert(0, '+');
								}
								signProcessed = true;
							}
							
							break;
						case '-':
							if (signProcessed){
								upperPointStr.insert(0, ' ');
							}else{
								if (minus) {
									upperPointStr.insert(0, '-');
								}else {
									upperPointStr.insert(0, ' ');
								}
								signProcessed = true;
							}
							break;
						case '&': 
							if (ampersandProcessed){
								upperPointStr.insert(0, ' ');
							}else{
								if (minus) {
									upperPointStr.insert(0, '-');
								}else {
									upperPointStr.insert(0, '*');
								}
								ampersandProcessed = true;
							}							
							break;
						case '\\' :
							if (wonProcessed){
								upperPointStr.insert(0, ' ');
							}else{
								upperPointStr.insert(0, '\\');
								wonProcessed = true;
							}
							break;
						default:
							upperPointStr.insert(0, ' ');
							break;
						}
					}else{
						upperPointStr.insert(0, ' ');
					}// end of if (j > 0) 
				}
				else{
					upperPointStr.insert(0, ',');
				}
				// end of case ','
				break;

			case 'Z':case 'z':
				if (srcIdx < nonZeroPos){
					upperPointStr.insert(0, ' ');
				}else{
					upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
				}
				break;

			case '*':
				if (srcIdx < nonZeroPos){
					upperPointStr.insert(0, '*');
				}else{
					upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
				}
				break;

			case '-':
				if (signProcessed){
					upperPointStr.insert(0, ' ');
				}else{					
					if (srcIdx < nonZeroPos || j==0 /*srcIdx == 0*/){
						if (minus) {
							upperPointStr.insert(0,'-');
						}
						else {
							upperPointStr.insert(0, ' ');
						}
						signProcessed = true; // 부호처리여부 SET
					}else{
						upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
					}
				}
				break;

			case '&':				
				if (ampersandProcessed){
					upperPointStr.insert(0, ' ');
				}else{					
					if (srcIdx < nonZeroPos){						
							if (minus) {
								upperPointStr.insert(0, '-');
							}else{
								upperPointStr.insert(0, '*');
							}
 
						ampersandProcessed = true; // &처리여부 SET
					}else{
						upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
					}                       
				}
				break;

			case '+':
				if (signProcessed){
					upperPointStr.insert(0, ' ');
				}else{
					if (srcIdx < nonZeroPos || j==0){
						if (minus) {
							upperPointStr.insert(0, '-');
						}else {
							upperPointStr.insert(0, '+');
						}
						signProcessed = true;
					}else{
						upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
					}
				}
				break;

			case '$':                   
				if (dollarProcessed){
					upperPointStr.insert(0, ' ');
				}else{
					if (srcIdx < nonZeroPos){
						upperPointStr.insert(0, '$');
						dollarProcessed = true; //$처리여부 SET                           
					}else{
						upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
					}
				}
				break;

			case '\\':
				if (wonProcessed){
					upperPointStr.insert(0, ' ');
				}else{
					if (srcIdx < nonZeroPos){
						upperPointStr.insert(0, '\\');
						wonProcessed = true; //$처리여부 SET                          
					}else{
						upperPointStr.insert(0, intValueStr.charAt(srcIdx--));
					}
				}
				break;

			default:
				upperPointStr.insert(0, currentFormatChar);                 
				break;
			}
		}
		
		
		// 입력값의 소수점 아래부분을 처리
		
		String pointValueStr = absNumber.subtract(intValue).toPlainString();

		int pointStrLen = pointValueStr.length() - 2; //소수점 자릿수

		String lowerPointFormat = "";

		//TODO CHECK FORMAT STRING 의 소수점 아래부분 처리
		if (periodPos > 0)  {
			lowerPointFormat = format.substring(periodPos+1, format.length());
		}		

		srcIdx = 0;
		int tarIdx2 = 0;    //소수점 자리 인덱스

		boolean numProcFlag = false; // 소수점이하 숫자처리 여부

		// FORMAT STRING의 소수부에 대한 각각의 char에 대한 처리
		for (int i = 0; i < lowerPointFormat.length(); i++){
			char currentFormatChar = lowerPointFormat.charAt(i);
			switch (currentFormatChar){
			case '9':				
				// 포맷이 9이고 포맷의 길이가 입력된 값의 길이보다 긴 경우 0으로 채우기 
				if (srcIdx >= pointStrLen){
					lowerPointStr.append('0');					
					tarIdx2++;
				}else{					
					lowerPointStr.append(pointValueStr.charAt(tarIdx2+2));
					srcIdx++;
					tarIdx2++;
				}				
				numProcFlag = true;
				break;
			case 'Z':case 'z':
				if (srcIdx >= pointStrLen){
					lowerPointStr.append('0');
					tarIdx2++;
				}else{
					lowerPointStr.append(pointValueStr.charAt(tarIdx2+2));
					srcIdx++;
					tarIdx2++;
				}
				numProcFlag = true;
				break;
			case '*':
				// 숫자가 한번이라도 처리되었으면 *로 치환안함
				if (srcIdx >= pointStrLen && !numProcFlag){
					lowerPointStr.append('*');
				}else{
					lowerPointStr.append(pointValueStr.charAt(tarIdx2 + 2));
					srcIdx++;
					tarIdx2++;
					numProcFlag = true;
				}
				break;
			default:                    
				lowerPointStr.append(currentFormatChar);
				tarIdx2++;
				break;
			}
		}

		// 결과가 space인 경우 index 조정
		if (lowerPointStr.length() == 0) {
			tarIdx2 = 0;
		}

		String formattedStr;

		if (tarIdx2 > 0){
			formattedStr = upperPointStr.append('.').append(lowerPointStr).toString();
		}else {
			formattedStr = upperPointStr.append(lowerPointStr).toString();
		}

		return formattedStr;
	}

	private static int findFirstNonZeroPos(String intValueStr) {
		int nonZeroPos = 0; //'0'이 아닌 숫자가 처음 나타나는 위치
		
		for (int i = 0; i < intValueStr.length(); i++){
			if (intValueStr.charAt(i) != '0'){
				nonZeroPos = i;
				break;
			}
		}
		return nonZeroPos;
	}
	
	private static int countLeadingZeros(String intValueStr){
		int leadingZeros = 0;
		
		for (int i = 0; i < intValueStr.length(); i++){
			if (intValueStr.charAt(i) != '0'){
				break;
			}
			leadingZeros ++;
		}
				
		return leadingZeros;
	}
	
	

	/**
	 * Decimal 을 Char 로 변환한 다음 정수부 소수부 합쳐서 숫자만 추출하는 로직(AS-IS 기능)
	 * 
	 * <pre>
	 * 입력 : new BigDecimal("-123.45")
	 * 예제 :  NumberUtil.extractNumberToChar(new BigDecimal("-123.45"))
	 * 출력 : 12345
	 * <br>
	 * 입력 : new BigDecimal("1234567890.0")
	 * 예제 :  NumberUtil.extractNumberToChar(new BigDecimal("1234567890.0"))
	 * 출력 : 12345678900
	 * </pre>
	 * 
	 * @param number
	 * @return 숫자만 추출해 문자열로 리턴
	 */
	public static String extractNumberToChar(BigDecimal number){	
		if (number == null){
			return "";
		}
		
		String paramNum = number.toPlainString();
		StringBuilder resultStr = new StringBuilder();		
		
		// extract numeric chars only
		for (int j = 0; j <=  paramNum.length() -1; j++){
			if (Character.isDigit(paramNum.charAt(j))) {
				resultStr.append(paramNum.charAt(j));
			}
		}		
		return resultStr.toString();
	}
	
	/**
	 * Decimal 을 Char 로 변환한 다음 정수부 소수부 합쳐서 정수부만 추출
	 * 
	 * <pre>
	 * 입력 : new BigDecimal("-123.45")
	 * 예제 :  NumberUtil.extractIntegerToChar(new BigDecimal("-123.45"))
	 * 출력 : 123
	 * <br>
	 * 입력 : new BigDecimal("1234567890.0")
	 * 예제 :  NumberUtil.extractIntegerToChar(new BigDecimal("1234567890.0"))
	 * 출력 : 1234567890
	 * </pre>
	 * 
	 * @param number
	 * @return 숫자만 추출해 문자열로 리턴
	 */
	public static String extractIntegerToChar(BigDecimal number){	
		if (number == null){
			return "";
		}
		
		String paramNum = number.toPlainString();
		StringBuilder resultStr = new StringBuilder();
				
		// extract integer only
		for (int j = 0; j <=  paramNum.length() -1; j++){
			if (Character.isDigit(paramNum.charAt(j))) {
				resultStr.append(paramNum.charAt(j));
			}
			if (paramNum.charAt(j) == '.') {
				break;
			}
		}
		return resultStr.toString();
	}
	
	/**
	 * Decimal 을 Char 로 변환한 다음 점(".")을 포함한 정수부 소수부를 추출
	 * 
	 * <pre>
	 * 입력 : new BigDecimal("-123.45")
	 * 예제 :  NumberUtil.extractDecimalToChar(new BigDecimal("-123.45"))
	 * 출력 : 123.45
	 * <br>
	 * 입력 : new BigDecimal("1234567890.0")
	 * 예제 :  NumberUtil.extractDecimalToChar(new BigDecimal("1234567890.0"))
	 * 출력 : 1234567890.0
	 * </pre>
	 * 
	 * @param number
	 * @return 소수부 추출해 리턴 
	 */
	public static String extractDecimalToChar(BigDecimal number){	
		if (number == null){
			return "";
		}
		
		String paramNum = number.toPlainString();
		StringBuilder resultStr = new StringBuilder();
				
		for (int j = 0; j <=  paramNum.length() -1; j++){
			if (Character.isDigit(paramNum.charAt(j)) || paramNum.charAt(j) == '.') {
				resultStr.append(paramNum.charAt(j));
			}
		}
		return resultStr.toString();
	}
	
	/**
	 * '-'(dash) 가 포함된 포맷을 사용자로 부터 입력받아 해당 포맷으로 변환
	 *  
	 * <pre>
	 * 1. default case
	 * 입력 : "42891234563057","###-######-#####"
	 * 예제 :   NumberUtil.toDashNumberFormat("42891234563057","###-######-#####")
	 * 출력 : 428-912345-63057
	 * <br>
	 * 2. 포맷의 자릿수가 입력된 값의 자릿수보다 작은 경우 (뒷자리 숫자 잘리는것에 유의)
	 * 입력 : "42891234563057","&&&-&&-&&&&&"
	 * 예제 :  NumberUtil.toDashNumberFormat("42891234563057","&&&-&&-&&&&&")
	 * 출력 : 428-91-23456
	 * <br>
	 * 3, 입력된 포맷의 자릿수보다 입력된 값이 작은경우
	 * 입력 : "5555444","9999-999-9"
	 * 예제 :  NumberUtil.toDashNumberFormat("5555444","9999-999-9")
	 * 출력 : 5555-444
	 * </pre>
	 *	 
	 * @param number
	 * @param format
	 *            사용자가 원하는 포맷형태 ( ex. ###-######-#####)
	 * @return
	 */
	public static String toDashNumberFormat(String number, String format){
		if (number == null || StringUtil.isEmpty(format)){
			return "";
		}
		
		StringBuilder resultStr = new StringBuilder(); 

		int srcIdx = 0;		
		int numLen = number.length()-1;
				
		// number 각각의 char에 대한 처리		
		for (int i = 0; i < format.length(); i++){
			char currentFormatChar = format.charAt(i);			
			
			if(srcIdx <= numLen){
				if(currentFormatChar == '-'){				
						resultStr.append('-');								
				}else{
						resultStr.append(number.charAt(srcIdx));
						srcIdx++;
				}
			}else break;	
		}
		return resultStr.toString();
	}
	
	/**
	 * BigDecimal power 연산을 제공하는 함수 (임시)
	 * (주의) power연산을 위해 임시로 제공되는 메소드입니다. 차후 수정예정입니다.
	 *	 
	 * @param base
	 * @param exponent
	 * 
	 * @return
	 */
	public static BigDecimal pow(BigDecimal base, BigDecimal exponent){
        if (exponent.scale() == 0){
            long longValue = exponent.longValue();            

             // exponent 가 정수값의 범위인 경우는 api를 그대로 이용
            if (longValue >= 0 && longValue <= 999999999){
                return base.pow((int)longValue);
            }
        }
        
        double doubleExponent = exponent.doubleValue();
        double doubleBase = base.doubleValue();            
        double result = Math.pow(doubleBase, doubleExponent);
     

        if (Double.isInfinite(result) ){
            throw new ArithmeticException("The Result is infinite.");
        }

        if (Double.isNaN(result)){
            throw new ArithmeticException("The result is not a number(NAN).");
        }
        return new BigDecimal(Double.toString(result));
    }
	
	
	
	
	/**
	 * 입력받은 자릿수만큼 랜덤한 숫자릉 생성하여 String 형태로 반환 
	 * 
	 * @param length
	 * 
	 * @return 난수String 반환
	 */
	public static String getRandomString(int length){
		String firstCharacters = "123456789";
		String characters = "0123456789";
		char[] text = new char[length];
		
		for(int i = 0; i < length; i++){
			if(i == 0){ //개발자요청 - 첫자리는 0이 안나오도록
				text[i] = firstCharacters.charAt(generator.nextInt(firstCharacters.length()));
			}else{
				text[i] = characters.charAt(generator.nextInt(characters.length()));
			}
		}
		return new String(text);
	}
	
	
	/**
	 * 입력으로 들어온 BigDecimal 값에 대해서 최소값을 반환한다. (입력된 모든값이 null인 경우 null 반환)
	 * @param firstArg
	 * @param remainArgs
	 * @return
	 */
	public static BigDecimal min(BigDecimal firstArg, BigDecimal...remainArgs){
		
		if (remainArgs == null || remainArgs.length == 0){
			return firstArg;
		}
		
        BigDecimal minValue = firstArg;
        
        for (BigDecimal arg : remainArgs) {
        
            if (arg!= null && (minValue == null || minValue.compareTo(arg) > 0)) {
                minValue = arg;
            }
        }

        return minValue;
        
	}
	
	
	/**
	 * 입력으로 들어온 BigDecimal 값에 대해서 최대값을 반환한다. (입력된 모든값이 null인 경우 null 반환)
	 * @param firstArg
	 * @param remainArgs
	 * @return
	 */
	public static BigDecimal max(BigDecimal firstArg, BigDecimal...remainArgs){
		
		if (remainArgs == null || remainArgs.length == 0){
			return firstArg;
		}
		
		BigDecimal maxValue = firstArg;
        
		for (BigDecimal arg : remainArgs) {
            if (arg != null && (maxValue == null || maxValue.compareTo(arg) < 0)) {
            	maxValue = arg;
            }
        }

        return maxValue;
		
	}
	
	static boolean equalValues(BigDecimal[] left, BigDecimal[] right){
		for (int i =0; i < left.length; i++){
			if (!equalValue(left[i],right[i])){
				return false;
			}
		}
		return true;
	}
	
	static boolean equalValues(Collection<BigDecimal> left, Collection<BigDecimal> right){
		
		Iterator<BigDecimal> e1 = left.iterator();
		Iterator<BigDecimal> e2 = right.iterator();
        
		while (e1.hasNext() && e2.hasNext()) {
			if (!equalValue(e1.next(), e2.next())){
				return false;
			}
        }
		
		return true;
	}
	
	static boolean equalValue(BigDecimal left, BigDecimal right){
		if (left != null && right != null){
			return left.compareTo(right) == 0;
		}else{
			return left == right ? true : false;
		}
	}
	
	/***
	 * 입력된 BigDecimal 값이 입력된 scale 값으로 변환가능한지 여부를 반환한다.
	 * @param b
	 * @param expectedScale
	 * @return
	 */
	public static boolean checkScale(BigDecimal b, int expectedScale){
		if (b != null){
			int actualScale = b.scale();
			
			if (actualScale <= expectedScale){
				return true;
			}
			try{
				b.setScale(expectedScale);
				return true;
			}catch(ArithmeticException ae){
				return false;
			}
		}
		
		return false;
		
	}
	
}

   