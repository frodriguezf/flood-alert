package ar.com.flood.health;

import ar.com.flood.api.VersionApi;
import ar.com.flood.api.model.VersionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * @author rabella
 */
@Controller
public class VersionController implements VersionApi {

    private final String version;

    @Autowired
    public VersionController(@Value("${flood.build.version}") String version) {
        this.version = version;
    }

    @Override
    public ResponseEntity<VersionResponse> versionGet() {
        final VersionResponse response = new VersionResponse();
        response.setVersion(version);
        return ResponseEntity.ok(response);
    }
}
