package ar.com.flood.api.model;

import java.lang.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2017-04-30T11:31:31.822-03:00")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Alert  {
  
  private Long id = null;
  private Date date = null;
  private String message = null;
  public enum SeverityEnum {
     WARNING,  CAUTION,  DANGER, 
  };
  private SeverityEnum severity = null;
  private Double latitude = null;
  private Double longitude = null;

  public Alert() {
  }

  public Alert(Long id, Date date, String message, SeverityEnum severity, Double latitude, Double longitude) {
    this();
    this.id = id;
this.date = date;
this.message = message;
this.severity = severity;
this.latitude = latitude;
this.longitude = longitude;
  }

  public static class Builder {
    private final Alert instance;

    private Builder(){
      instance = new Alert();
    }

    public Builder setId(Long id) {
      instance.setId(id);
      return this;
    }
    public Builder setDate(Date date) {
      instance.setDate(date);
      return this;
    }
    public Builder setMessage(String message) {
      instance.setMessage(message);
      return this;
    }
    public Builder setSeverity(SeverityEnum severity) {
      instance.setSeverity(severity);
      return this;
    }
    public Builder setLatitude(Double latitude) {
      instance.setLatitude(latitude);
      return this;
    }
    public Builder setLongitude(Double longitude) {
      instance.setLongitude(longitude);
      return this;
    }

    public Alert build(){
      return instance;
    }
  }
  public static Builder newInstance(){
    return new Builder();
  }

  /**
   **/
  @JsonProperty("id")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   **/
  @JsonProperty("date")
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
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

  /**
   **/
  @JsonProperty("severity")
  public SeverityEnum getSeverity() {
    return severity;
  }
  public void setSeverity(SeverityEnum severity) {
    this.severity = severity;
  }

  /**
   **/
  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   **/
  @JsonProperty("longitude")
  public Double getLongitude() {
    return longitude;
  }
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Alert that = (Alert) o;
    return Objects.equals(this.id, that.id) &&
        Objects.equals(this.date, that.date) &&
        Objects.equals(this.message, that.message) &&
        Objects.equals(this.severity, that.severity) &&
        Objects.equals(this.latitude, that.latitude) &&
        Objects.equals(this.longitude, that.longitude);
  }
  @Override
  public int hashCode() {
    return Objects.hash(id, date, message, severity, latitude, longitude);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alert {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  date: ").append(date).append("\n");
    sb.append("  message: ").append(message).append("\n");
    sb.append("  severity: ").append(severity).append("\n");
    sb.append("  latitude: ").append(latitude).append("\n");
    sb.append("  longitude: ").append(longitude).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
