package org.openapi2puml.openapi.plantuml;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.openapi.plantuml.helpers.PlantUMLClassHelper;
import org.openapi2puml.openapi.plantuml.helpers.PlantUMLInterfaceDiagramHelper;
import org.openapi2puml.openapi.plantuml.helpers.PlantUMLRelationHelper;
import org.openapi2puml.openapi.plantuml.vo.ClassDiagram;
import org.openapi2puml.openapi.plantuml.vo.InterfaceDiagram;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class OpenApi3Reader {
  private static final Logger LOGGER = LogManager.getLogger(OpenApi3Reader.class);

  // TODO - duplication to refactor from PlantUMLCodeGen
  public static final String TITLE = "title";
  public static final String VERSION = "version";
  public static final String CLASS_DIAGRAMS = "classDiagrams";
  public static final String INTERFACE_DIAGRAMS = "interfaceDiagrams";
  public static final String ENTITY_RELATIONS = "entityRelations";

  public OpenApi3Reader() {
    super();
  }

  public String transformOpenApi2Puml(String specFile) throws Exception {
    File swaggerSpecFile = new File(specFile);

    LOGGER.info("Processing Swagger Spec File: " + specFile);

    // TODO - add as a parameter
    String outputFilePath = swaggerSpecFile.getParent();

    OpenAPI openAPI = new OpenAPIV3Parser().read(swaggerSpecFile.getAbsolutePath());
    if (openAPI == null) {
      throw new RuntimeException("No OpenAPI yet");
    } else {
      LOGGER.info("Read OpenAPI component schemas: " + openAPI.getComponents().getSchemas().keySet().toString());
      return generatePlantUmlFile(openAPI, outputFilePath);

    }
  }


  /**
   * generate a PlantUML File based on this classes Swagger property
   *
   * @return filepath to the PlantUML file as a String
   * @throws IOException            - If there is an error writing the file
   * @throws IllegalAccessException - if there is an issue generating the file information
   */
  private String generatePlantUmlFile(OpenAPI openAPI, String outputFilePath) throws IOException,
      IllegalAccessException {
    Map<String, Object> plantUmlObjectModelMap = convertOpenApiToPlantUmlObjectModelMap(openAPI);

    MustacheUtility mustacheUtility = new MustacheUtility();
    String plantUmlFilePath = mustacheUtility.createPlantUmlFile(new File(outputFilePath),
        plantUmlObjectModelMap);

    return plantUmlFilePath;
  }

  private Map<String, Object> convertOpenApiToPlantUmlObjectModelMap(OpenAPI openAPI) {
    Map<String, Object> context = new TreeMap<>();

    context.put(TITLE, openAPI.getInfo().getTitle());
    context.put(VERSION, openAPI.getInfo().getVersion());

    // TODO - Class Diagrams
    // OpenApi3ToClassModelHelper

    // TODO - Interface Diagrams
    // List<InterfaceDiagram> interfaceDiagrams = new ArrayList<>();

    // TODO - Relationships


    return context;
  }



}
