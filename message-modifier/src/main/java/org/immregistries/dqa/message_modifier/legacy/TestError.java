package org.immregistries.dqa.message_modifier.legacy;

public class TestError
{
  private ErrorType errorType = ErrorType.UNKNOWN;
  private String description = "";

  public ErrorType getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorType errorType) {
    this.errorType = errorType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
