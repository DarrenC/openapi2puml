package org.openapi2puml.openapi.v3;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.openapi.plantuml.vo.BasicInfo;
import org.openapi2puml.openapi.plantuml.vo.ClassDiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenApi3ToBasicInfoModelHelper {

  private static final Logger LOGGER = LogManager.getLogger(OpenApi3ToBasicInfoModelHelper.class);

  public static BasicInfo getBasicInfoFromOpenApi(OpenAPI openAPI) {
    BasicInfo basicInfo = new BasicInfo();

    basicInfo.setOpenApiVersion(openAPI.getOpenapi());
    basicInfo.setTitle(openAPI.getInfo().getTitle());
    basicInfo.setSpecificationVersion(openAPI.getInfo().getVersion());

    return basicInfo;
  }
}
