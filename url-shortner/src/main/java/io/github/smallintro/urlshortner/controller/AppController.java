package io.github.smallintro.urlshortner.controller;

import io.github.smallintro.urlshortner.service.AppService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/")
    public ResponseEntity generateShortURL(@RequestBody String longUrl) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(longUrl)) {
            return new ResponseEntity(appService.getShortURL(longUrl), HttpStatus.OK);
        }
        return new ResponseEntity("Invalid Web URL: " + longUrl, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity getActualUrl(@PathVariable("id") String urlId) {
        String longUrl = appService.getActualUrl(urlId);
        if (StringUtils.hasLength(longUrl)) {
            return new ResponseEntity(longUrl, HttpStatus.OK);
        }
        return new ResponseEntity("Invalid URL Id: " + urlId, HttpStatus.BAD_REQUEST);
    }

}
