package org.openapi2puml.validation;

import org.openapi2puml.model.TransactionContext;

import java.nio.file.Files;
import java.nio.file.Paths;

public class InputValidator {

  public static boolean validateInputs(TransactionContext context) {

    // Check OpenApi specification file exists
    boolean valid = Files.exists(Paths.get(context.getSpecFile()));
    // TODO - check if it's a file
    // TODO - check if the file has a valid OpenApi version entry

    // TODO - check output file path

    // TODO - return a list of failed validation checks

    return valid;
  }
}
