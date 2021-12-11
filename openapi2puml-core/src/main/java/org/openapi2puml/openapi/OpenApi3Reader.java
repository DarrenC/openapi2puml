package org.openapi2puml.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class OpenApi3Reader {

  private static final Logger LOGGER = LogManager.getLogger(OpenApi3Reader.class);

  /**
   * Reads an OpenAPI specification from the given file path and returns an OpenAPI object
   *
   *  TODO - error handling
   *
   * @param specificationFilePath Input file containing an OpenApi v3 specification as yaml/json
   * @return OpenApi object representing the OpenApi specification in the file
   * @throws Exception
   */
  public OpenAPI readOpenApiSpecification(String specificationFilePath) throws Exception {
    File openApiSpecificationFile = new File(specificationFilePath);

    LOGGER.info("Processing OpenAPI Specification File: " + specificationFilePath);

    OpenAPI openAPI = new OpenAPIV3Parser().read(openApiSpecificationFile.getAbsolutePath());

    if (openAPI == null) {
      // TODO - create a proper exception for this case
      LOGGER.error("ABORTING - Failed to read OpenApi file: " + specificationFilePath);
      throw new Exception("Error reading OpenAPI file.");
    }

    return openAPI;

  }
}
