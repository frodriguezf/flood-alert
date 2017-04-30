package ar.com.flood.alerts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.flood.api.AlertsApi;
import ar.com.flood.api.model.Alerts;
import ar.com.flood.api.model.Alert;
import ar.com.flood.social.SocialConfig;

/**
 * @author rabella
 *
 */
@Controller
public class AlertsController implements AlertsApi {

	private AlertManager alertManager;

	private SocialConfig socialConfig;

	/**
	 * Constructor
	 *
	 * @param companyManager
	 */
	@Autowired
	public AlertsController(AlertManager alertManager, SocialConfig socialConfig) {
		super();
		this.alertManager = alertManager;
		this.socialConfig = socialConfig;
	}

	@Override
	public ResponseEntity<Alerts> alertsGet(Integer page, Integer pageSize,
			Double lat, Double lon, Date date) {
		Alerts alerts = new Alerts();
		alertManager.allAlerts().map(alertManager::toAlert).forEach(alerts.getAlerts()::add);
		alerts.setCount(alerts.getAlerts().size());
		return new ResponseEntity<>(alerts, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Alert> alertsPost(@RequestBody Alert alert) {
		ApplicationContext appContext =
		    	   new ClassPathXmlApplicationContext(new String[] {"flood-alert-context.xml"});
		Resource resource =
		   		          appContext.getResource(getUrlGoogleMaps(alert.getLatitude(), alert.getLongitude()));
		TweetData tweetData = new TweetData(alert.getMessage() + " #spaceappsriocuarto #spaceappschallenge @SpaceAppsCba" + "\n" + "http://maps.google.com/maps?q=loc:" + alert.getLatitude() + "," + alert.getLongitude())
													.withMedia(resource);
		socialConfig.getTwitter().timelineOperations().updateStatus(tweetData);
		playAlertSound();
		return new ResponseEntity<>(alertManager.toAlertEntity(alert), HttpStatus.CREATED);
	}

	private String getUrlGoogleMaps(Double lat, Double lon) {
		String urlFormat = "https://maps.googleapis.com/maps/api/staticmap?zoom=12&size=600x300&path=color:0x00000000|fillcolor:0xFFCC00|weight:1|%s&key=AIzaSyCG-6POJlzu9CHR2r5K4-Dtk8CBU9CtNtQ";
		StringBuilder url = new StringBuilder();
		url.append("url:")
			.append(urlFormat);
		return String.format(url.toString(), getPolygon(lat, lon));
	}
	
	private String getPolygon(Double lat, Double lon){
		StringBuilder polygon = new StringBuilder();
		Double km = 0.058500059;
		String v1 = Double.toString(lat + km) + "," + Double.toString(lon + km) + "|";
		String v2 = Double.toString(lat + km) + "," + Double.toString(lon - km) + "|";
		String v3 = Double.toString(lat - km) + "," + Double.toString(lon - km) + "|";
		String v4 = Double.toString(lat - km) + "," + Double.toString(lon + km) + "|";
		String v5 = Double.toString(lat + km) + "," + Double.toString(lon + km);
		polygon.append(v1).append(v2).append(v3).append(v4).append(v5);
		return polygon.toString();
	}
	private void playAlertSound() {
		try {
			File yourFile = new File("src/extra/sounds/Siren_Nois.wav");
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
