package ar.com.flood.alerts;

import java.util.Date;

import javax.persistence.*;

/**
 * @author rabella
 * 
 */
@Entity
public class AlertEntity {

	public enum Severity {
		WARNING, CAUTION, DANGER
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)	
	private Long id;
	
	@Basic(optional = false)
	private Double latitude;

	@Basic(optional = false)
	private Double longitude;
	
	@Basic(optional = false)
	private Date date;	
	
	@Basic(optional = false)
	private String message;
	
	@Enumerated(EnumType.STRING)
	private Severity severity;	
	
	public AlertEntity() {
	}

	/**
	 * @param latitude
	 * @param longitude
	 * @param date
	 * @param message
	 * @param severity
	 */
	public AlertEntity(Double latitude, Double longitude, Date date,
			String message, Severity severity) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.message = message;
		this.severity = severity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the severity
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}	
}

