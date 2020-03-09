package com.nihar.timeseries.data.analysis.bin;

import java.util.Objects;

public class UniqueIDAndValue implements Comparable<UniqueIDAndValue> {

  private String id;
  private Integer value;

  public UniqueIDAndValue(String id, Integer value) {
    this.id = id;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  @Override
  public int compareTo(UniqueIDAndValue o) {
    // Descending order.
    return this.value.compareTo(o.value);
  }

  // Only check uniqueness for ID. Value can be duplicated.
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UniqueIDAndValue)) {
      return false;
    }
    UniqueIDAndValue that = (UniqueIDAndValue) o;
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public String toString() {
    return "UniqueIDAndValue{" + "id='" + id + '\'' + ", value=" + value + '}';
  }
}
