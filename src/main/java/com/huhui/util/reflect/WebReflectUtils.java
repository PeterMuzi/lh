package com.huhui.util.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.MethodUtils;

/**
 * 
 * Class descriptions:
 * 
 */

public class WebReflectUtils implements ReflectConstants {

	public final static Object bindRequestToData(HttpServletRequest request, Object o) {

		Enumeration paramNames = request.getParameterNames();
		try {
			while (paramNames != null && paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					ReflectUtil.setArrayFieldByMethod(o, paramName, values, "");
				} else {
					if (values[0]!=null)
						ReflectUtil.setFieldValueByMethod(o, paramName, values[0], "");
				}
			}
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A Default Contrsutructor for your commondClass is required!!!");
		}
	}
	
	public final static Object bindRequestToData(HttpServletRequest request, Class c, String suffix) {

		Enumeration paramNames = request.getParameterNames();
		try {
			Object o = c.newInstance();
			while (paramNames != null && paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					ReflectUtil.setArrayFieldByMethod(o, paramName, values, suffix);
				} else {
					if (values[0]!=null)
						ReflectUtil.setFieldValueByMethod(o, paramName, values[0], suffix);
				}
			}
			return o;
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException("A Default Contrsutructor for your commondClass is required!!!");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("A Default Contrsutructor for your commondClass is required!!!");
		}
	}

	public final static Object bindRequestToData(HttpServletRequest request, Class c) {
		return bindRequestToData(request, c, "");
	}

	public final static Object bindRequestToData(HttpServletRequest request, String className) {

		try {
			Class c;
			c = Class.forName(className);
			return bindRequestToData(request, c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(className + "-Class Not Found!!!");
		}
	}

}
