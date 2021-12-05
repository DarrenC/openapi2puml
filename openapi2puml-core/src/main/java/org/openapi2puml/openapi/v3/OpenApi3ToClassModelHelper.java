package org.openapi2puml.openapi.v3;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.openapi.plantuml.vo.ClassDiagram;
import org.openapi2puml.openapi.plantuml.vo.ClassMembers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenApi3ToClassModelHelper {

  private static final Logger LOGGER = LogManager.getLogger(OpenApi3ToClassModelHelper.class);

  private static final String SCHEMA_TYPE_OBJECT = "object";

  public static List<ClassDiagram> getClassesFromOpenApi(OpenAPI openAPI) {
    List<ClassDiagram> classes = new ArrayList<>();

    try {
      // TODO - stream ???
      for (Map.Entry<String, Schema> schemaEntry : openAPI.getComponents().getSchemas().entrySet()) {
        String className = schemaEntry.getKey();
        Schema schema = schemaEntry.getValue();

        LOGGER.info(" Schema: " + className
            + " Schema type: " + schema.getType());

        boolean isClass = SCHEMA_TYPE_OBJECT.equalsIgnoreCase(schema.getType());

        List<ClassMembers> classMembers = new ArrayList<>();
        if (schema.getProperties() != null) {
          Map<String, Schema> properties = schema.getProperties();
          properties.forEach((key, propertySchema) -> {

            if (key != null) {
              // TODO - find a better way of doing this
              if (propertySchema instanceof ArraySchema || propertySchema instanceof ComposedSchema) {
                LOGGER.warn("Warning, unhandled schema type for key: " + key);
              } else {
                //String dataType, String name, String className, String cardinality
                String dataType = (propertySchema.getFormat() != null) ? propertySchema.getFormat() : propertySchema.getType();
                classMembers.add(new ClassMembers(dataType, key, null, null));
              }
            }
          });
        }

        classes.add(new ClassDiagram(className, null, classMembers,
            null, isClass, null));
      }
    } catch (Exception e) {
      // TODO - HANDLE exceptions during the generation + log/add errors to context
      LOGGER.error("Error while building Class diagram.", e);
    }

    return classes;
  }
}
