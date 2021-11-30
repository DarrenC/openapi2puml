package org.openapi2puml.validation;

import org.apache.commons.lang3.StringUtils;
import org.openapi2puml.model.ErrorEnum;
import org.openapi2puml.model.TransactionContext;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputValidator {

  public static boolean validateInputs(TransactionContext context) {
    List<ErrorEnum> errors = new ArrayList<>();
    boolean valid = true;

    // Check OpenApi specification file exists
    if (!Files.isRegularFile(Paths.get(context.getSpecFile()))) {
      valid = false;
      errors.add(ErrorEnum.INVALID_INPUT_PATH);
    } else {
      // Check if the file has a valid OpenApi version entry
      try {
        valid = Files.lines(Paths.get(context.getSpecFile()))
            .findFirst()
            .filter(s -> s.startsWith("swagger:") || s.startsWith("openapi:"))
            .isPresent();

        // TODO - Refactor this
        if(!valid){
          errors.add(ErrorEnum.INVALID_INPUT_SPEC_FILE);
        }
      } catch (java.io.IOException e) {
        valid = false;
        errors.add(ErrorEnum.INVALID_INPUT_SPEC_FILE);
      }
    }

    // Check output file path exists
    if (StringUtils.isNotEmpty(context.getOutput()) && !Files.isDirectory(Paths.get(context.getOutput()))) {
      valid = false;
      errors.add(ErrorEnum.INVALID_OUTPUT_PATH);
    }

    context.setValidationErrors(errors);
    return valid;
  }


}
