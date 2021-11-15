package org.openapi2puml.openapi.v3;

import io.swagger.models.Swagger;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.openapi.plantuml.OpenApi3Reader;
import org.openapi2puml.openapi.plantuml.vo.ClassDiagram;
import org.openapi2puml.openapi.plantuml.vo.ClassMembers;
import org.openapi2puml.openapi.plantuml.vo.ClassRelation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenApi3ToClassModelHelper {

  private static final Logger LOGGER = LogManager.getLogger(OpenApi3ToClassModelHelper.class);

  public static List<ClassDiagram> getClassesFromOpenApi(OpenAPI openAPI) {
    List<ClassDiagram> classes = new ArrayList<>();

    // TODO - stream ???
    for(Map.Entry schemaEntry : openAPI.getComponents().getSchemas().entrySet()){
      classes.add(new ClassDiagram(schemaEntry.getKey().toString(), null, null,
          null, true, null));
    }

    return classes;
  }
}
