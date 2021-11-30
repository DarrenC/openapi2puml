package org.openapi2puml.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionContext {

  private String specFile;
  private String output;
  private boolean generateDefinitionModelOnly;
  private boolean includeCardinality;
  private boolean generateSvg;
  private boolean generatePng;
  private String title;
  private String openApiVersion;
  private String specificationVersion;

  private List<ErrorEnum> validationErrors = new ArrayList<>();

  // TODO - make a proper builder and handle putting default values in here too

  public static TransactionContext build() {
    return new TransactionContext();
  }

  public TransactionContext withSpecFile(String specFile) {
    this.specFile = specFile;
    return this;
  }

  public TransactionContext withOutput(String output) {
    this.output = output;
    return this;
  }

  public TransactionContext withGeneratDefinitionModelOnly(boolean generateDefinitionModelOnly) {
    this.generateDefinitionModelOnly = generateDefinitionModelOnly;
    return this;
  }

  public TransactionContext withIncludeCardinality(boolean includeCardinality) {
    this.includeCardinality = includeCardinality;
    return this;
  }

  public TransactionContext withGeneratSvg(boolean generateSvg) {
    this.generateSvg = generateSvg;
    return this;
  }

  public TransactionContext withGeneratPng(boolean generatePng) {
    this.generatePng = generatePng;
    return this;
  }

  public String getSpecFile() {
    return specFile;
  }

  public void setSpecFile(String specFile) {
    this.specFile = specFile;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public boolean isGenerateDefinitionModelOnly() {
    return generateDefinitionModelOnly;
  }

  public void setGenerateDefinitionModelOnly(boolean generateDefinitionModelOnly) {
    this.generateDefinitionModelOnly = generateDefinitionModelOnly;
  }

  public boolean isIncludeCardinality() {
    return includeCardinality;
  }

  public void setIncludeCardinality(boolean includeCardinality) {
    this.includeCardinality = includeCardinality;
  }

  public boolean isGenerateSvg() {
    return generateSvg;
  }

  public void setGenerateSvg(boolean generateSvg) {
    this.generateSvg = generateSvg;
  }

  public boolean isGeneratePng() {
    return generatePng;
  }

  public void setGeneratePng(boolean generatePng) {
    this.generatePng = generatePng;
  }

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

  public List<ErrorEnum> getValidationErrors() {
    return validationErrors;
  }

  public void setValidationErrors(List<ErrorEnum> validationErrors) {
    this.validationErrors = validationErrors;
  }

}
