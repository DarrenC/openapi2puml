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

import java.io.File;

public class OpenApi3Reader {
  private static final Logger LOGGER = LogManager.getLogger(OpenApi3Reader.class);

  public OpenApi3Reader() {
    super();
  }

  public void transformOpenApi2Puml(String specFile) {
    File swaggerSpecFile = new File(specFile);

    LOGGER.info("Processing Swagger Spec File: " + specFile);

    OpenAPI openAPI = new OpenAPIV3Parser().read(swaggerSpecFile.getAbsolutePath());
    if (openAPI == null) {
      throw new RuntimeException("No OpenAPI yet");
    } else {
      LOGGER.info("Read OpenAPI paths: " + openAPI.getPaths().toString());
    }
  }
}
