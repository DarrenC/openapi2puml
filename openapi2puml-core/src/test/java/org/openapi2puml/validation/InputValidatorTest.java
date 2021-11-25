package org.openapi2puml.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openapi2puml.model.TransactionContext;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  private TransactionContext transactionContext;

  private static final String NON_EXISTENT_FILE = "XYZ.txt";

  @BeforeEach
  public void setup() {
    transactionContext = new TransactionContext();
  }

  @Test
  @DisplayName("Should validate input spec file that exists")
  public void shouldValidateExistingFile() throws Exception {
    Path tempFile = Files.createTempFile("TestSpec", "txt");
    transactionContext.setSpecFile(tempFile.toString());
    assertTrue(InputValidator.validateInputs(transactionContext));
  }

  @Test
  @DisplayName("Should not validate input spec file that does not exist")
  public void shouldNotValidateNonExistentFile() {
    transactionContext.setSpecFile(NON_EXISTENT_FILE);
    assertFalse(InputValidator.validateInputs(transactionContext));
  }

  @Test
  @DisplayName("Should not validate a directory for input spec file")
  public void shouldNotValidateDirectoryForInputSpecFile() throws Exception {
    Path tempDirectory = Files.createTempDirectory("TempDir");
    transactionContext.setSpecFile(tempDirectory.toString());
    assertFalse(InputValidator.validateInputs(transactionContext));
  }
}