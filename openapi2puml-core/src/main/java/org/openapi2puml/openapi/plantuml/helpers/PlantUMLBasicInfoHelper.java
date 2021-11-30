package org.openapi2puml.openapi.plantuml.helpers;

import io.swagger.models.Swagger;
import org.openapi2puml.openapi.plantuml.vo.BasicInfo;

public class PlantUMLBasicInfoHelper {

  public BasicInfo processSwaggerBasicInfo(Swagger swagger) {
    BasicInfo basicInfo = new BasicInfo();

    basicInfo.setTitle(swagger.getInfo().getTitle());
    basicInfo.setSpecificationVersion(swagger.getInfo().getVersion());
    basicInfo.setOpenApiVersion(swagger.getSwagger());

    return basicInfo;
  }
}
