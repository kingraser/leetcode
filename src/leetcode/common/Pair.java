
package leetcode.common;

import java.util.Objects;


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
    return (Objects.equals(key, other.key) || Objects.deepEquals(key, other.key))
        && (Objects.equals(value, other.value) || Objects.deepEquals(value, other.value));
  }

  @Override
  public String toString() {
    return String.format("(key:%s,value:%s)", key, value);
  }
}
