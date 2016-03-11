package mole.controller;

import mole.model.dao.Case;
import mole.model.repositories.CaseRepository;
import mole.model.resource.CaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Cases")
public class CaseController {
    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Case> getAll() {
        return caseRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CaseResource getCaseById(@PathVariable("id") Long id) {
        return new CaseResource(this.caseRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/Search/{field}/{value}")
    public List<Case> caseSearch(
            @PathVariable("field") String field,
            @PathVariable("value") String value
    ) {
        List<Case> returnList = new ArrayList<Case>();
        switch (field) {
            case "agencyRefCode":
                returnList = caseRepository.findByAgencyRefCode(value);
                break;
            // no default, leave a blank array
        }

        return returnList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Add")
    public ResponseEntity<?> add(@RequestBody Case input) {
        //this.validateUser(userId);

        caseRepository.save(input);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new CaseResource(input).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Update")
    public ResponseEntity<?> update(@RequestBody Case input) {
        //this.validateUser(userId);

        Case target = this.caseRepository.findOne(input.getCaseId());

        target.setAgencyId(input.getAgencyId());
        target.setClaimantId(input.getClaimantId());
        target.setAgencyRefCode(input.getAgencyRefCode());

        target.setModified(new Timestamp(new java.util.Date().getTime()));
        target.setModifiedBy(input.getModifiedBy());

        caseRepository.save(target);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new CaseResource(target).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }


}
