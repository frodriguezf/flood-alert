package ar.com.flood.api;

import ar.com.flood.api.model.*;

import ar.com.flood.api.model.VersionResponse;
import ar.com.flood.api.model.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping(value = "/version", produces = {APPLICATION_JSON_VALUE})
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2017-04-30T11:31:31.822-03:00")
public interface VersionApi {

  @RequestMapping(value = "", produces = { "application/json" },   method = RequestMethod.GET)
  ResponseEntity<VersionResponse> versionGet();

}
