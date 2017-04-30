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
		playAlertSound();
		return new ResponseEntity<>(alerts, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Alert> alertsPost(@RequestBody Alert alert) {
		ApplicationContext appContext =
		    	   new ClassPathXmlApplicationContext(new String[] {"flood-alert-context.xml"});
		Resource resource =
		   		          appContext.getResource("url:http://flood.umd.edu/plots/fmapR-30241.gif?mct=0.14470000");
		TweetData tweetData = new TweetData(alert.getMessage())
													.atLocation(-0.126f, 51.502f)
													.displayCoordinates(true)
													.withMedia(resource);
		socialConfig.getTwitter().timelineOperations().updateStatus(tweetData);
		return new ResponseEntity<>(alertManager.toAlertEntity(alert), HttpStatus.CREATED);
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
