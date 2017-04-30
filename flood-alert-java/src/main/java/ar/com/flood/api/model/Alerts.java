package ar.com.flood.api.model;

import java.lang.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2017-04-30T11:31:31.822-03:00")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Alerts  {
  
  private Integer count = null;
  private List<Alert> alerts = new ArrayList<Alert>();

  public Alerts() {
  }

  public Alerts(Integer count, List<Alert> alerts) {
    this();
    this.count = count;
this.alerts = alerts;
  }

  public static class Builder {
    private final Alerts instance;

    private Builder(){
      instance = new Alerts();
    }

    public Builder setCount(Integer count) {
      instance.setCount(count);
      return this;
    }
    public Builder setAlerts(List<Alert> alerts) {
      instance.setAlerts(alerts);
      return this;
    }

    public Alerts build(){
      return instance;
    }
  }
  public static Builder newInstance(){
    return new Builder();
  }

  /**
   **/
  @JsonProperty("count")
  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   **/
  @JsonProperty("alerts")
  public List<Alert> getAlerts() {
    return alerts;
  }
  public void setAlerts(List<Alert> alerts) {
    this.alerts = alerts;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Alerts that = (Alerts) o;
    return Objects.equals(this.count, that.count) &&
        Objects.equals(this.alerts, that.alerts);
  }
  @Override
  public int hashCode() {
    return Objects.hash(count, alerts);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alerts {\n");
    
    sb.append("  count: ").append(count).append("\n");
    sb.append("  alerts: ").append(alerts).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
