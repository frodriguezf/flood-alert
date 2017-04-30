package ar.com.flood.alerts;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.com.flood.api.model.Alert;

/**
 * @author rabella
 *
 */
@Component
public class AlertManager {
	private AlertRepository alertRepository;
	
	@Autowired
	public AlertManager(AlertRepository alertRepository) {
		this.alertRepository = alertRepository;
	}
	
	public Stream<AlertEntity> allAlerts() {
		return StreamSupport.stream(alertRepository.findAll().spliterator(), false);
	}
	
	public Alert toAlert(AlertEntity alertEntity) {
		Alert alert = new Alert();
		alert.setId(alertEntity.getId());
		alert.setDate(alertEntity.getDate());
		alert.setLatitude(alertEntity.getLatitude());
		alert.setLongitude(alertEntity.getLongitude());
		alert.setMessage(alertEntity.getMessage());
		alert.setSeverity(Alert.SeverityEnum.valueOf(alertEntity.getSeverity().name()));
		return alert;
	}
	
	public Alert toAlertEntity(Alert alert){
		AlertEntity alertEntity = new AlertEntity();
		alertEntity.setDate(alert.getDate());
		alertEntity.setLatitude(alert.getLatitude());
		alertEntity.setLongitude(alert.getLongitude());
		alertEntity.setMessage(alert.getMessage());
		alertEntity.setSeverity(AlertEntity.Severity.valueOf(alert.getSeverity().name()));
		alertRepository.save(alertEntity);
		
		alert.setId(alert.getId());
		
		return alert;
	}
}

