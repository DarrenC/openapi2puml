package org.openapi2puml.openapi.plantuml.vo;

public class BasicInfo {

  private String title;
  private String openApiVersion;
  private String specificationVersion;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOpenApiVersion() {
    return openApiVersion;
  }

  public void setOpenApiVersion(String openApiVersion) {
    this.openApiVersion = openApiVersion;
  }

  public String getSpecificationVersion() {
    return specificationVersion;
  }

  public void setSpecificationVersion(String specificationVersion) {
    this.specificationVersion = specificationVersion;
  }

}
