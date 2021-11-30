package org.openapi2puml.model;

public enum ErrorEnum {

  INVALID_INPUT_PATH("The input path is not a valid file."),
  INVALID_INPUT_SPEC_FILE("Input file does not contain a valid OpenAPI version declaration."),
  INVALID_OUTPUT_PATH("The output path is not a valid directory.");


  private final String errorDescription;

  ErrorEnum(String errorDescription){
    this.errorDescription = errorDescription;
  }

  public String getDescription() {
    return this.errorDescription;
  }
}
