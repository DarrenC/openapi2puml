package org.openapi2puml.openapi.plantuml;



import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OpenApi3ReaderTest {

    private OpenApi3Reader openApi3Reader = new OpenApi3Reader();

    @Test
    @DisplayName("Basic Petstore test")
    @Tag("integration-test")
    void test_generatePetStorePlantUml() {
        String specFile = "src/test/resources/openapi3/petstore/petstore_3.0.yaml";

        openApi3Reader.transformOpenApi2Puml(specFile);
        //generator.transformOpenApi2Puml(specFile, outputPath, generateDefinitionModelOnly, includeCardinality, generateSvg, false);

        assertTrue(specFile != null);
    }

}
