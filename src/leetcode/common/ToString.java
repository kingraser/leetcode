/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode.common;

import java.lang.reflect.Field;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年4月25日;
//-------------------------------------------------------
public class ToString {

  public static String toString(Object object) {
    if (Objects.isNull(object)) return "null";
    ToStringHelper helper = MoreObjects.toStringHelper(object.getClass());
    Field[] fields = object.getClass().getDeclaredFields();
    try {
      for (Field field : fields) {
        field.setAccessible(true);
        helper.add(field.getName(), field.get(object));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return helper.toString();
  }

}
