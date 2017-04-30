package ar.com.flood.api;

import ar.com.flood.api.model.*;

import ar.com.flood.api.model.Error;
import ar.com.flood.api.model.Alerts;
import java.util.Date;
import ar.com.flood.api.model.Alert;

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
@RequestMapping(value = "/alerts", produces = {APPLICATION_JSON_VALUE})
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2017-04-30T11:31:31.822-03:00")
public interface AlertsApi {

  @RequestMapping(value = "", produces = { "application/json" },   method = RequestMethod.GET)
  ResponseEntity<Alerts> alertsGet(@RequestParam(value = "page", required = false, defaultValue="1") Integer page


,
    @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize


,
    @RequestParam(value = "lat", required = false) Double lat


,
    @RequestParam(value = "lon", required = false) Double lon


,
    @RequestParam(value = "date", required = false) Date date


);


  @RequestMapping(value = "", produces = { "application/json" },  consumes = { "application/json" }, method = RequestMethod.POST)
  ResponseEntity<Alert> alertsPost(

 @RequestBody Alert alert
);

}
