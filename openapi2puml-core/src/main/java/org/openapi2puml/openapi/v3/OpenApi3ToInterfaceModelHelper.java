package org.openapi2puml.openapi.v3;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.openapi.plantuml.vo.InterfaceDiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenApi3ToInterfaceModelHelper {

  private static final Logger LOGGER = LogManager.getLogger(OpenApi3ToInterfaceModelHelper.class);

  public static List<InterfaceDiagram> getInterfacesFromOpenApi(OpenAPI openAPI) {
    List<InterfaceDiagram> interfaceDiagrams = new ArrayList<>();

    Paths paths = openAPI.getPaths();
    for(Map.Entry<String, PathItem> pathEntry : paths.entrySet()) {
      String pathName = pathEntry.getKey();
      PathItem pathItem = pathEntry.getValue();

      LOGGER.info("Path Name: {}", pathName);
    }

    return interfaceDiagrams;
  }
}
