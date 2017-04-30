package ar.com.flood.alerts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

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
		          appContext.getResource("file:"+ mergeImages());
		TweetData tweetData = new TweetData(alert.getMessage())
													.atLocation(-0.126f, 51.502f)
													.displayCoordinates(true)
													.withMedia(resource);
		socialConfig.getTwitter().timelineOperations().updateStatus(tweetData);
		return new ResponseEntity<>(alertManager.toAlertEntity(alert), HttpStatus.CREATED);
	}
	
	private String mergeImages() {
		File path = new File("src/extra/images");

		// load source images
		BufferedImage image;
		try {
			image = ImageIO.read(new File(path, "image.png"));
			BufferedImage overlay = ImageIO.read(new File(path, "overlay.png"));
	
			// create the new image, canvas size is the max. of both image sizes
			int w = Math.max(image.getWidth(), overlay.getWidth());
			int h = Math.max(image.getHeight(), overlay.getHeight());
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	
			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(overlay, 0, 0, null);
		
			// Save as new image
			File result = new File(path, "combined.png");
			ImageIO.write(combined, "PNG", result);
			return result.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
