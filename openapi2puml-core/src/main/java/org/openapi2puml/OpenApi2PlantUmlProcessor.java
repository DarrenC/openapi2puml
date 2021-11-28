package org.openapi2puml;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.model.TransactionContext;
import org.openapi2puml.openapi.OpenApi3Reader;
import org.openapi2puml.openapi.plantuml.MustacheUtility;
import org.openapi2puml.openapi.plantuml.vo.BasicInfo;
import org.openapi2puml.openapi.plantuml.vo.ClassDiagram;
import org.openapi2puml.openapi.v3.OpenApi3ToBasicInfoModelHelper;
import org.openapi2puml.openapi.v3.OpenApi3ToClassModelHelper;
import org.openapi2puml.validation.InputValidator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OpenApi2PlantUmlProcessor {
  private static final Logger LOGGER = LogManager.getLogger(OpenApi2PlantUmlProcessor.class);

  public static void main(String[] args) {
    CliArgs cliArgs = new CliArgs(args);

    final TransactionContext context =
        TransactionContext.build()
            .withSpecFile(cliArgs.getArgumentValue("-i", ""))
            .withOutput(cliArgs.getArgumentValue("-o", ""))
            .withGeneratDefinitionModelOnly(Boolean.parseBoolean(cliArgs.getArgumentValue("-generateDefinitionModelOnly", "false")))
            .withIncludeCardinality(Boolean.parseBoolean(cliArgs.getArgumentValue("-includeCardinality", "true")))
            .withGeneratSvg(Boolean.parseBoolean(cliArgs.getArgumentValue("-generateSvg", "true")))
            .withGeneratPng(Boolean.parseBoolean(cliArgs.getArgumentValue("-generatePng", "true")));

    OpenApi2PlantUmlProcessor processor = new OpenApi2PlantUmlProcessor();
    processor.process(context);
  }

  // TODO - this to become the main entry point
  public void process(TransactionContext context) {

    if (InputValidator.validateInputs(context)) {
      try {
        // TODO - read the OpenAPI spec + return an OpenAPI/Swagger object
        OpenApi3Reader openApi3Reader = new OpenApi3Reader();
        final OpenAPI openAPI = openApi3Reader.readOpenApiSpecification(context.getSpecFile());

        // TODO - Call the Model Builders - Basic Info, Class, Interface, Relationship
        BasicInfo basicInfoFromOpenApi = OpenApi3ToBasicInfoModelHelper.getBasicInfoFromOpenApi(openAPI);
        List<ClassDiagram> classesFromOpenApi = OpenApi3ToClassModelHelper.getClassesFromOpenApi(openAPI);

        // TODO - Call the Context builder to add the model into the mustache template context map
        Map<String, Object> mustacheContextObject = new TreeMap<>();

        final String BASIC_INFO = "basicInfo";
        final String CLASS_DIAGRAMS = "classDiagrams";
        final String INTERFACE_DIAGRAMS = "interfaceDiagrams";
        final String ENTITY_RELATIONS = "entityRelations";

        mustacheContextObject.put(BASIC_INFO, basicInfoFromOpenApi);
        mustacheContextObject.put(CLASS_DIAGRAMS, classesFromOpenApi);

        // TODO - Call the mustache template handler to build the PlantUML diagram
        // TODO - Produce the output(s) - plantuml file, (string version???), svg file
        MustacheUtility mustacheUtility = new MustacheUtility();
        // TODO - better file handling
        mustacheUtility.createPlantUmlFile(new File(context.getOutput()), mustacheContextObject);

      } catch (Exception e) {
        // TODO - handle all types of exceptions with proper information ....
        LOGGER.error("ABORTING - Failed to complete transaction. ", e);
      }
    } else {
      LOGGER.error("ABORTING - Input validation failed.");
    }

  }
}
