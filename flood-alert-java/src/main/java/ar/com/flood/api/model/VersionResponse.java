package ar.com.flood.api.model;

import java.lang.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2017-04-30T11:31:31.822-03:00")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VersionResponse  {
  
  private String version = null;

  public VersionResponse() {
  }

  public VersionResponse(String version) {
    this();
    this.version = version;
  }

  public static class Builder {
    private final VersionResponse instance;

    private Builder(){
      instance = new VersionResponse();
    }

    public Builder setVersion(String version) {
      instance.setVersion(version);
      return this;
    }

    public VersionResponse build(){
      return instance;
    }
  }
  public static Builder newInstance(){
    return new Builder();
  }

  /**
   **/
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionResponse that = (VersionResponse) o;
    return Objects.equals(this.version, that.version);
  }
  @Override
  public int hashCode() {
    return Objects.hash(version);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionResponse {\n");
    
    sb.append("  version: ").append(version).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
