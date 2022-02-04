package org.openapi2puml.openapi.plantuml.vo;

public class ClassMember {

  private String dataType;
  private String name;
  private String className;
  private String cardinality;
  private boolean isMultiple;

  public ClassMember() {
    super();
  }

  public ClassMember(String dataType, String name, String className, String cardinality, boolean isMultiple) {
    super();
    this.dataType = dataType;
    this.name = name;
    this.className = className;
    this.cardinality = cardinality;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getCardinality() {
    return cardinality;
  }

  public void setCardinality(String cardinality) {
    this.cardinality = cardinality;
  }

  public boolean isMultiple() {
    return isMultiple;
  }

  public void setMultiple(boolean multiple) {
    isMultiple = multiple;
  }

  @Override
  public String toString() {
    return "ClassMembers [dataType=" + dataType + ", name=" + name + ", className=" + className + ", cardinality="
        + cardinality + "]";
  }
}
