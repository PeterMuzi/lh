package com.huhui.util.reflect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface ReflectConstants {

	public static final String DEFALT_DATE_PATTERN = "yyyy-MM-dd";

	public static final String DEFALT_DATE_PATTERN2 = "yyyyMMdd";

	public static final DateFormat parseFormat = new SimpleDateFormat("yyMMdd");

	public static final DateFormat formattingFormat = new SimpleDateFormat("yyyyMMdd");

	public static final DateFormat wellparseFormat = new SimpleDateFormat("yy-MM-dd");

	public static final DateFormat wellformattingFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static final String STRING_DOLLAR = "\\$";

	public static final String STRING_COMMA = ",";

	public static final String STRING_T = "T";

	public static final String STRING_F = "F";

	public static final String STRING_TRUE = "TRUE";

	public static final String STRING_FALSE = "FALSE";

	public static final String STRING_ZERO = "0";

	public static final String STRING_ONE = "1";

	public static final String STRING_Y = "Y";

	public static final String STRING_N = "N";

	public static final String STRING_DASH = "-";

	public static final String STRING_METHOD_GET = "get";

	public static final String STRING_METHOD_SET = "set";

	public static final String STRING_CLASS_STRING = "class java.lang.String";

	public static final String STRING_CLASS_DATE = "class java.util.Date";

	public static final String STRING_CLASS_INT = "int";

	public static final String STRING_CLASS_BOOLEAN = "boolean";

	public static final String STRING_CLASS_DOUBLE = "double";
	
	public static final String STRING_CLASS_FLOAT = "float";
	
	public static final String STRING_CLASS_LONG = "long";

	public static final char CHAR_DOLLAR = '$';

	public static final char CHAR_DOT = '.';

	public static final char CHAR_COMMA = ',';
}
