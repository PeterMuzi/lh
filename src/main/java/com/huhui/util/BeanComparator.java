package com.huhui.util;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;
/**
 * 列表排序比较器
 * @author Zark
 */
public class BeanComparator  implements Comparator{
	      /** The direction to sort. */
	      //
	      private int direction = -1;

	      /** The method to sort upon. */
	      private String propertyName = null;
	      public static final String desc = "desc";
	      public static final String asc = "asc";
	      /**
	       * Default constructor.
	       * 
	       * @param pmethod
	       *            The method to sort upon.
	       * @param pdirection
	       *            The direction to sort.
	       */
	      //pdirection=1表示升序;-1表示降序
	      public BeanComparator(String propertyName, String direction)
	      {
	         this.propertyName = propertyName;
	         if(desc.equals(direction)){
	        	 this.direction=-1; 
	         }else if(asc.equals(direction)){
	        	 this.direction=1; 
	         }
	      }

	      /**
	       * @see java.util.Comparator.compare(Object, Object)
	       */
	      public int compare(Object obj1, Object obj2)
	      {
	         try
	         {
	            obj1 = PropertyUtils.getProperty(obj1, propertyName);
	            obj2 = PropertyUtils.getProperty(obj2, propertyName);

	            if (obj1 == null)
	            {
	               return direction * -1;
	            }
	            else if (obj2 == null)
	            {
	               return direction;
	            }
	            else if (obj1 instanceof Comparable)
	            {
	               return ((Comparable) obj1).compareTo(obj2) * direction;
	            }
	         }
	         catch (Exception e)
	         {
	            e.printStackTrace();
	         }

	         return 0;
	      }
}
