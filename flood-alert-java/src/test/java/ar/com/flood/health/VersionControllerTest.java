package ar.com.flood.health;

import ar.com.flood.api.model.VersionResponse;
import ar.com.flood.health.VersionController;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

/**
 * @author rabella
 */
public class VersionControllerTest {

    @Test
    public void getReturnsVersion() throws Exception {
        String version = "1.0.0-SNAPSHOT";
        VersionController controller = new VersionController(version);

        final ResponseEntity<VersionResponse> response = controller.versionGet();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getVersion(), version);
   }
}