package ar.com.flood.social;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
public class SocialConfig {

	@Value(value="${twitter.consumerKey}")	
	private String consumerKey;

	@Value(value="${twitter.consumerSecret}")
	private String consumerSecret;

	@Value(value="${twitter.accessToken}")
	private String accessToken;
	
	@Value(value="${twitter.accessTokenSecret}")
	private String accessTokenSecret;
	
	private Twitter twitter;
	
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        TwitterConnectionFactory twitterConnectionFactory = new TwitterConnectionFactory(
                consumerKey,
                consumerSecret);
        registry.addConnectionFactory(twitterConnectionFactory);
        this.twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return registry;
    }

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
}