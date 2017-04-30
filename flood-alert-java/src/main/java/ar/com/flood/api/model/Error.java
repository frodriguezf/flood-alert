package ar.com.flood.api.model;

import java.lang.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2017-04-30T11:31:31.822-03:00")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error  {
  
  private String message = null;

  public Error() {
  }

  public Error(String message) {
    this();
    this.message = message;
  }

  public static class Builder {
    private final Error instance;

    private Builder(){
      instance = new Error();
    }

    public Builder setMessage(String message) {
      instance.setMessage(message);
      return this;
    }

    public Error build(){
      return instance;
    }
  }
  public static Builder newInstance(){
    return new Builder();
  }

  /**
   **/
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error that = (Error) o;
    return Objects.equals(this.message, that.message);
  }
  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("  message: ").append(message).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
