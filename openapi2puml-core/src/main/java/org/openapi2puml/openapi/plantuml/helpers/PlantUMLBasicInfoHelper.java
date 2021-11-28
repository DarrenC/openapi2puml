package org.openapi2puml.openapi.plantuml.helpers;

import io.swagger.models.Swagger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapi2puml.openapi.plantuml.vo.BasicInfo;
import org.openapi2puml.openapi.plantuml.vo.ClassDiagram;

import java.util.List;

public class PlantUMLBasicInfoHelper {
  private static final Logger logger = LogManager.getLogger(PlantUMLBasicInfoHelper.class);

  public BasicInfo processSwaggerBasicInfo(Swagger swagger) {
    BasicInfo basicInfo = new BasicInfo();

    basicInfo.setTitle(swagger.getInfo().getTitle());
    basicInfo.setSpecificationVersion(swagger.getInfo().getVersion());
    basicInfo.setOpenApiVersion(swagger.getSwagger());

    return basicInfo;
  }
}
