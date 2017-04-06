package com.huhui.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
/*
 * 增加，修改 sql
 */
public class GenericRepository {
	
	public static final String columnsKey = "columns";

	public static final String valuesKey = "values";
	
	public static final String COMMA = ",";
	protected static final String INSERT = " INSERT ";
	protected static final String INTO = " INTO ";
	protected static final String DELETE = " DELETE ";
	protected static final String SET = " SET ";
	protected static final String VALUES = " VALUES ";
	protected static final String OPENPAR = " ( ";
	protected static final String CLOSEPAR = " ) ";
	protected static final String OBJECTIDENTIFIER = "objectIdentifier";


	private static final Map<String, Map<String, Method>> _methods = new HashMap<String, Map<String, Method>>();
	
	private static final Object[] _blankOA = new Object[0];
	
	public static String updateSql(Object object,String pkName,String tablename){
		pkName=pkName(pkName);
		StringBuffer sb=new StringBuffer("update "+tablename+" set ");
		Map dataMap = GenericRepository.convertDomainToSQLMap(object);
		sb.append(getUpdateColumnValue(dataMap)).append(" where ").append(pkName).append("=").append(dataMap.get(pkName));
		return sb.toString();
	}
	
	public static String getSql(Object object,String[] pkNames,String tablename){
		Map dataMap = GenericRepository.convertDomainToSQLMap(object);
		for(int i=0;i<pkNames.length;i++){
			if(dataMap.get(pkNames[i])==null){
				return null;
			}
		}
		
		StringBuffer sb=new StringBuffer("select * from  "+tablename+" where ");
		
		
		for(int i=0;i<pkNames.length;i++){
			if(i==0){
				sb.append(pkNames[i]).append("=").append(dataMap.get(pkNames[i])).append("");
			}else{
				sb.append("  and ").append(pkNames[i]).append("=").append(dataMap.get(pkNames[i])).append("");
			}
		}
		return sb.toString();
	}
	
	public static String updateSql(Object object,String[] pkNames,String tablename){
		StringBuffer sb=new StringBuffer("update "+tablename+" set ");
		Map dataMap = GenericRepository.convertDomainToSQLMap(object);
		
		sb.append(getUpdateColumnValue(dataMap)).append(" where ");
		for(int i=0;i<pkNames.length;i++){
			if(i==0){
				sb.append(pkNames[i]).append("=").append(dataMap.get(pkNames[i])).append("");
			}else{
				sb.append("  and ").append(pkNames[i]).append("=").append(dataMap.get(pkNames[i])).append("");
			}
		}
		return sb.toString();
	}
	
	public static String insertSql(Object object,String pkName,String tablename){
		pkName=pkName(pkName);
		StringBuilder sb = new StringBuilder();
        Map dataMap = GenericRepository.convertDomainToSQLMap(object);
        if(pkName!=null){
        	dataMap.remove(pkName);
        }
        Map<String, String> map = GenericRepository.getColumnValue(dataMap);
		sb.append(GenericRepository.insertPrefix(tablename));
		sb.append(map.get("columns"));
		sb.append(GenericRepository.insertMiddle());
		sb.append(map.get("values"));
		sb.append(GenericRepository.insertSuffix());
		return sb.toString();
	}
	/*
	 * 第一个字符变大写
	 */
	private static String pkName(String pkName){
		if(pkName.length()==1){
			return pkName.toUpperCase();
		}
		return pkName.substring(0, 1).toUpperCase()+pkName.substring(1, pkName.length());
	}
	
	private static String insertPrefix(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append(INSERT);
		sb.append(INTO);
		sb.append(tableName);
		sb.append(OPENPAR);
		return (sb.toString());
	}

	private static String insertMiddle() {
		StringBuilder sb = new StringBuilder();
		sb.append(CLOSEPAR);
		sb.append(VALUES);
		sb.append(OPENPAR);
		return (sb.toString());
	}

	private static String insertSuffix() {
		StringBuilder sb = new StringBuilder();
		sb.append(CLOSEPAR);
		return (sb.toString());
	}
	
	private static Map<String, String> getColumnValue(Map<String, Object> map) {
		Map<String, String> columnValueMap = new HashMap<String, String>();
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			columns.append(entry.getKey());
			values.append(entry.getValue()); // NOTE: value may be null

			if (iterator.hasNext()) {
				values.append(COMMA);
				columns.append(COMMA);
			}
		}
		columnValueMap.put(columnsKey, columns.toString());
		columnValueMap.put(valuesKey, values.toString());
		return columnValueMap;
	}
	
	private static String getUpdateColumnValue(Map<String, Object> map) {
		StringBuilder columns = new StringBuilder();
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			columns.append(entry.getKey()).append("=").append(entry.getValue());
			// NOTE: value may be null
			if (iterator.hasNext()) {
				columns.append(COMMA);
			}
		}
		return columns.toString();
	}

	private static Map convertDomainToSQLMap(Object obj) {
		Map<String, Object> map = new TreeMap<String, Object>();
		if (obj == null) {
			return map;
		} else if (obj instanceof Map) {
			return (Map) obj;
		}
		Class cls = obj.getClass();

		Map<String, Method> mMethods = _methods.get(cls.getName());
		if (mMethods == null) {
			mMethods = new HashMap<String, Method>();

			Method[] methods = cls.getDeclaredMethods();
			int len = methods.length;
			for (int i = 0; i < len; i++) {
				// String getMethod = "get" + name.substring(0, 1).toUpperCase()
				// + name.substring(1);
				Method method = methods[i];
				String methodName = method.getName();
				if (!methodName.startsWith("get")
						|| method.getParameterTypes().length > 0)
					continue;

				Class retClass = method.getReturnType();

				String name = methodName.substring(3);
				mMethods.put(name, method);

			}

			synchronized (_methods) {
				_methods.put(cls.getName(), mMethods);
			}
		}

		for (Map.Entry<String, Method> entry : mMethods.entrySet()) {
			String name = entry.getKey();
			Method method = entry.getValue();
			String mappedName = name;

			try {
				if (mappedName != null && !"".equals(mappedName)) {
					Object retVal = method.invoke(obj, _blankOA);
					if (retVal == null)
						map.put(mappedName, "NULL");
					else if(retVal.getClass().getName().endsWith("Date")){
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String sqlValue = "'"+df.format(retVal)+"'";
						map.put(mappedName, sqlValue);
					} else {
						String sqlValue = "'"+retVal+"'";
						map.put(mappedName, sqlValue);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return map;
	}

}
