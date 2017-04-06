package com.huhui.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.codec.binary.StringUtils;

/**
 * 
 * Class descriptions:
 * 
 */

public class ReflectUtil implements ReflectConstants {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReflectUtil.class.getName());


	private static Integer toInteger(String s) {
		try {
			return new Integer(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Long toLong(String s) {
		try {
			return new Long(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Double toDouble(String s) {
		try {
			String amount = s;
			if (s != null) {
				int indexOfDollar = s.indexOf(ReflectConstants.CHAR_DOLLAR);
				int indexOfDot = s.indexOf(ReflectConstants.CHAR_DOT);
				if (indexOfDollar != -1 && indexOfDot != -1) {
					s = s.replaceFirst(STRING_DOLLAR, "");
					if (s.indexOf(ReflectConstants.CHAR_COMMA) != -1)
						s = s.replaceAll(ReflectConstants.STRING_COMMA,"");
					amount = s;
				}
			}
			return new Double(amount);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Float toFloat(String s) {
		try {
			String amount = s;
			return new Float(amount);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static BigDecimal toBigDecimal(String s) {
		try {
			return new BigDecimal(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Short toShort(String s) {
		try {
			return new Short(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Boolean toBoolean(String strVal) {
		try {
			if (strVal == null) {
				return Boolean.FALSE;
			}

			String aString = strVal.trim().toUpperCase();
			if (aString.equals(ReflectConstants.STRING_T)) {
				return Boolean.TRUE;
			}

			if (aString.equals(ReflectConstants.STRING_F)) {
				return Boolean.FALSE;
			}

			if (aString.equals(ReflectConstants.STRING_Y)) {
				return Boolean.TRUE;
			}

			if (aString.equals(ReflectConstants.STRING_N)) {
				return Boolean.FALSE;
			}

			if (aString.equals(ReflectConstants.STRING_TRUE)) {
				return Boolean.TRUE;
			}

			if (aString.equals(ReflectConstants.STRING_FALSE)) {
				return Boolean.FALSE;
			}

			if (aString.equals(ReflectConstants.STRING_ONE)) {
				return Boolean.TRUE;
			}
			if (aString.equals(ReflectConstants.STRING_ZERO)) {
				return Boolean.FALSE;
			}

			return Boolean.FALSE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	public static Date toDate(String s) {
		if (s.contains(ReflectConstants.STRING_DASH)) {
			try {
				Date date = parserwell(s);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				Date date = parser(s);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private static Timestamp toTimestamp(String s) {
		try {
			Date date = toDate(s);
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Date parser(String dateString) throws ParseException {
		String s = formattingFormat.format(parseFormat.parse(dateString));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_DATE_PATTERN2);
			return sdf.parse(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Date parserwell(String dateString) throws ParseException {
		String s = wellformattingFormat.format(wellparseFormat.parse(dateString));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_DATE_PATTERN);
			return sdf.parse(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Class protected methods.
	 * 
	 */

	/**
	 * 
	 * Class public interface.
	 * 
	 */
	
	public static void setFieldValueByMethod(Object target, String fieldName, String fieldValue, String suffix) {
		if (target == null || fieldName == null)
			return;

		if (suffix == null) {
			suffix = "";
		}
		if (!"".equals(suffix)) {
			if (fieldName.endsWith(suffix)) {
				fieldName = fieldName.substring(0, fieldName.length() - suffix.length());
			} else {
				return;
			}
		}
		String methodName = ReflectConstants.STRING_METHOD_SET + Character.toUpperCase(fieldName.charAt(0))
		+ fieldName.substring(1);
		invokeMethod( target,methodName,  fieldValue);
	}

	public static Object invokeMethod(Object target, String methodName, String fieldValue) {

		try {
			Method[] ms = target.getClass().getMethods();
			for (Method m : ms) {
				if (methodName.equals(m.getName()) && fieldValue != null && fieldValue.length() > 0) {
					Class cls = m.getParameterTypes()[0];

					Object value = fieldValue;

					if (cls.equals(Integer.class) || cls.getName().equals(ReflectConstants.STRING_CLASS_INT)) {
						value = toInteger(fieldValue);
					} else if (cls.equals(BigDecimal.class)) {
						value = toBigDecimal(fieldValue);
					} else if (cls.equals(Short.class)) {
						value = toShort(fieldValue);
					} else if (cls.equals(Date.class)) {
						value = toDate(fieldValue);
					} else if (cls.equals(Boolean.class) || cls.getName().equals(ReflectConstants.STRING_CLASS_BOOLEAN)) {
						value = toBoolean(fieldValue);
					} else if (cls.equals(Double.class) || cls.getName().equals(ReflectConstants.STRING_CLASS_DOUBLE)) {
						value = toDouble(fieldValue);
					} else if (cls.equals(Timestamp.class)) {
						value = toTimestamp(fieldValue);
					}else if (cls.equals(Float.class) || cls.getName().equals(ReflectConstants.STRING_CLASS_FLOAT)) {
						value = toFloat(fieldValue);
					}else if (cls.equals(Long.class) || cls.getName().equals(ReflectConstants.STRING_CLASS_LONG)) {
						value = toLong(fieldValue);
					}

					if (value != null) {
						return m.invoke(target, new Object[] { value });
						//break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setFieldValueByMethod(Object target, String fieldName, String fieldValue) {
		setFieldValueByMethod(target, fieldName, fieldValue, "");
	}

	public static void setArrayFieldByMethod(Object target, String fieldName, String[] fieldValue, String suffix) {
		if (target == null || fieldName == null)
			return;

		if (suffix == null)
			suffix = "";
		if (!"".equals(suffix)) {
			if (fieldName.endsWith(suffix)) {
				fieldName = fieldName.substring(0, fieldName.length() - suffix.length());
			} else {
				return;
			}
		}

		try {
			String methodName = ReflectConstants.STRING_METHOD_SET + Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);
			Method[] ms = target.getClass().getMethods();
			for (Method m : ms) {
				if (methodName.equals(m.getName())) {
					Class cls = m.getParameterTypes()[0];

					Object value = fieldValue;

					if (cls.equals(Integer[].class)) {
						Integer[] temp = new Integer[fieldValue.length];
						for (int i = 0; i < temp.length; i++) {
							temp[i] = toInteger(fieldValue[i]);
						}
						value = temp;
					} else if (cls.equals(BigDecimal[].class)) {
						BigDecimal[] temp = new BigDecimal[fieldValue.length];
						for (int i = 0; i < temp.length; i++) {
							temp[i] = toBigDecimal(fieldValue[i]);
						}
						value = temp;
					} else if (cls.equals(Short[].class)) {
						Short[] temp = new Short[fieldValue.length];
						for (int i = 0; i < temp.length; i++) {
							temp[i] = toShort(fieldValue[i]);
						}
						value = temp;
					} else if (cls.equals(Date[].class)) {
						Date[] temp = new Date[fieldValue.length];
						for (int i = 0; i < temp.length; i++) {
							temp[i] = toDate(fieldValue[i]);
						}
						value = temp;
					}
					m.invoke(target, new Object[] { value });
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setArrayFieldByMethod(Object target, String fieldName, String[] fieldValue) {
		setArrayFieldByMethod(target, fieldName, fieldValue, "");
	}

	public static void setFieldValue(Object target, String fname, Class ftype, Object fvalue) {
		if (target == null || fname == null || "".equals(fname)
				|| (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod(ReflectConstants.STRING_METHOD_SET
					+ Character.toUpperCase(fname.charAt(0)) + fname.substring(1), ftype);
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, fvalue);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {
				fe.printStackTrace();
			}
		}
	}

	/**
	 * Put all the properties in the obj into the map, use the property name as
	 * it's key, and the property value as the map's value.
	 * 
	 * All Object need to extend the AbstractDataType class.
	 * 
	 * @param obj
	 *            the Object.
	 * @return a Map.
	 */

	public static Map mapDataTypeToMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (obj == null) {
			return map;
		} else if (obj instanceof Map) {
			return (Map) obj;
		}

		Class cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			try {
				String name = fields[i].getName();
				String getMethod = ReflectConstants.STRING_METHOD_GET + name.substring(0, 1).toUpperCase()
						+ name.substring(1);
				Object value = (Object) cls.getMethod(getMethod, new Class[0]).invoke(obj,
						new Object[0]);
				if (value != null)
					map.put(name, value.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public static String toStringDate(Date dateObj) {
		if (dateObj == null)
			return "";
		StringBuffer dateBuf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateObj.getTime());
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		if (month < 10)
			dateBuf.append(ReflectConstants.STRING_ZERO);
		dateBuf.append(month + 1);
		dateBuf.append(ReflectConstants.STRING_DASH);
		if (day < 10)
			dateBuf.append(ReflectConstants.STRING_ZERO);
		dateBuf.append(day);
		dateBuf.append(ReflectConstants.STRING_DASH);
		dateBuf.append(cal.get(Calendar.YEAR));
		return dateBuf.toString();
	}

	public static void main(String[] args) {
		//invokeMethod(daoBean,get_method,Integer.parseInt(arg0));
	}
	
	public static void convertToBO(Object source, Object target) {
		Class sourceClass = source.getClass();
		Field[] sourceFields = sourceClass.getDeclaredFields();

		Class targetClass = target.getClass();
		Field[] targetFields = targetClass.getDeclaredFields();

		for (int i = 0; i < sourceFields.length; i++) {
			try {
				String name = sourceFields[i].getName();
				String getMethod = ReflectConstants.STRING_METHOD_GET + name.substring(0, 1).toUpperCase()
						+ name.substring(1);
				Object value = (Object) sourceClass.getMethod(getMethod, new Class[0]).invoke(source, new Object[0]);

				for (int j = 0; j < targetFields.length; j++) {
					String tname = targetFields[j].getName();
					if (tname.equalsIgnoreCase(name)) {
						if (value.getClass().toString().equalsIgnoreCase(ReflectConstants.STRING_CLASS_STRING)) {
							ReflectUtil.setFieldValueByMethod(target, tname, value.toString().toUpperCase(),
									"");
						} else if (value.getClass().toString().equalsIgnoreCase(ReflectConstants.STRING_CLASS_DATE)) {
							Date dateValue = (Date) value;
							Calendar c = Calendar.getInstance();
							c.clear();
							c.setTimeInMillis(dateValue.getTime());
							// this is a java Calendar bug : Months are numbered
							// starting at January=0, rather than 1 as everyone
							// else on the planet does.
							// see
							// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6615044
							String dateString = (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH)
									+ ReflectConstants.STRING_DASH + c.get(Calendar.YEAR);
							ReflectUtil.setFieldValueByMethod(target, tname, dateString, "");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
