/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode.util;

import java.util.Objects;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月19日;
//-------------------------------------------------------
public class Pair<K, V> {
  public K key;
  public V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (Objects.isNull(o) || !(o instanceof Pair)) return false;
    Pair<?, ?> other = (Pair<?, ?>) o;
    return Objects.equals(key, other.key) && Objects.equals(value, other.value);
  }
}
