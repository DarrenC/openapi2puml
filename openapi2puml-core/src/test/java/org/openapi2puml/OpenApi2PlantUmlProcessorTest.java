package org.openapi2puml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openapi2puml.model.TransactionContext;

public class OpenApi2PlantUmlProcessorTest {
  @Test
  @DisplayName("Basic Petstore test")
  @Tag("integration-test")
  public void basicTest() {

    final TransactionContext context =
        TransactionContext.build()
            .withSpecFile("src/test/resources/openapi3/petstore/petstore_3.0.yaml")
            .withOutput("src/test/resources/openapi3/petstore/")
            .withGeneratDefinitionModelOnly(false)
            .withIncludeCardinality(true)
            .withGeneratSvg(false)
            .withGeneratPng(false);

    OpenApi2PlantUmlProcessor processor = new OpenApi2PlantUmlProcessor();
    processor.process(context);

  }

}
