package org.openapi2puml.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openapi2puml.model.ErrorEnum;
import org.openapi2puml.model.TransactionContext;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  private TransactionContext transactionContext;

  private static final String NON_EXISTENT_FILE = "XYZ.txt";
  private static final String NON_EXISTENT_DIR = "XYZ";

  @BeforeEach
  public void setup() {
    transactionContext = new TransactionContext();
  }

  @Test
  @DisplayName("Should validate input spec file that exists")
  public void shouldValidateExistingInputFile() throws Exception {
    Path tempFile = Files.createTempFile("TestSpec", "yaml");
    // Explicitly write the valid first line of an OpenAPI spec.
    Files.write(tempFile, "openapi: 3.0".getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    transactionContext.setSpecFile(tempFile.toString());
    assertTrue(InputValidator.validateInputs(transactionContext));
  }

  @Test
  @DisplayName("Should validate input spec file that exists and output directory that exists")
  public void shouldValidateExistingInputFileAndOutputDirectory() throws Exception {
    Path tempFile = Files.createTempFile("TestSpec", "yaml");
    // Explicitly write the valid first line of an OpenAPI spec.
    Files.write(tempFile, "openapi: 3.0".getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    transactionContext.setSpecFile(tempFile.toString());

    Path tempDir = Files.createTempDirectory("TestDir");
    transactionContext.setOutput(tempDir.toString());

    assertTrue(InputValidator.validateInputs(transactionContext));
  }

  @Test
  @DisplayName("Should not validate input spec file that does not exist")
  public void shouldNotValidateNonExistentFile() {
    transactionContext.setSpecFile(NON_EXISTENT_FILE);
    assertAll("Should not validate and should have error for input spec",
        () -> assertFalse(InputValidator.validateInputs(transactionContext)),
        () -> assertFalse(transactionContext.getValidationErrors().isEmpty()),
        () -> assertTrue(transactionContext.getValidationErrors().contains(ErrorEnum.INVALID_INPUT_PATH))
    );
  }

  @Test
  @DisplayName("Should not validate input spec file that exists but has invalid schmea line")
  public void shouldNotValidateExistingInputFileWithInvalidSchemaLine() throws Exception {
    Path tempFile = Files.createTempFile("TestSpec", "yaml");

    // Explicitly write the valid first line which is NOT an OpenAPI spec.
    Files.write(tempFile, "dslfkjsdlfkj".getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    transactionContext.setSpecFile(tempFile.toString());

    assertAll("Should not validate and should have error for non OpenAPI input file",
        () -> assertFalse(InputValidator.validateInputs(transactionContext)),
        () -> assertFalse(transactionContext.getValidationErrors().isEmpty()),
        () -> assertTrue(transactionContext.getValidationErrors().contains(ErrorEnum.INVALID_INPUT_SPEC_FILE))
    );
  }

  @Test
  @DisplayName("Should not validate a directory for input spec file")
  public void shouldNotValidateDirectoryForInputSpecFile() throws Exception {
    Path tempDirectory = Files.createTempDirectory("TempDir");
    transactionContext.setSpecFile(tempDirectory.toString());
    assertAll("Should not validate and should have error for non OpenAPI input file",
        () -> assertFalse(InputValidator.validateInputs(transactionContext)),
        () -> assertFalse(transactionContext.getValidationErrors().isEmpty()),
        () -> assertTrue(transactionContext.getValidationErrors().contains(ErrorEnum.INVALID_INPUT_PATH))
    );
  }

  @Test
  @DisplayName("Should not validate input spec file that does not exist and directory that does not exist")
  public void shouldNotValidateNonExistentSpecFileAndOutputDirectory() {
    transactionContext.setSpecFile(NON_EXISTENT_FILE);
    transactionContext.setOutput(NON_EXISTENT_DIR);

    assertAll("Should not validate and should have error for input spec and output dir",
        () -> assertFalse(InputValidator.validateInputs(transactionContext)),
        () -> assertFalse(transactionContext.getValidationErrors().isEmpty()),
        () -> assertTrue(transactionContext.getValidationErrors().contains(ErrorEnum.INVALID_INPUT_PATH)),
        () -> assertTrue(transactionContext.getValidationErrors().contains(ErrorEnum.INVALID_OUTPUT_PATH))
    );
  }

}