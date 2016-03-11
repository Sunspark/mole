package mole.controller;

import mole.model.dao.CaseRecord;
import mole.model.repositories.CaseRecordRepository;
import mole.model.resource.CaseRecordResource;
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
@RequestMapping("/CaseRecords")
public class CaseRecordController {
    @Autowired
    private CaseRecordRepository caseRecordRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CaseRecordResource getCaseRecordById(@PathVariable("id") Long id) {
        return new CaseRecordResource(this.caseRecordRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<CaseRecord> getAll() {
        return caseRecordRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/Search/{field}/{value}")
    public List<CaseRecord> claimantSearch(
            @PathVariable("field") String field,
            @PathVariable("value") Long value
    ) {
        List<CaseRecord> returnList = new ArrayList<CaseRecord>();
        switch (field) {
            case "caseId":
                returnList = caseRecordRepository.findByCaseId(value);
                break;
            // no default, leave a blank array
        }

        return returnList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Add")
    public ResponseEntity<?> add(@RequestBody CaseRecord input) {
        //this.validateUser(userId);

        caseRecordRepository.save(input);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new CaseRecordResource(input).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Update")
    public ResponseEntity<?> update(@RequestBody CaseRecord input) {
        //this.validateUser(userId);

        CaseRecord target = this.caseRecordRepository.findOne(input.getCaseRecordId());

        target.setCaseId(input.getCaseId());
        target.setRecordStatusId(input.getRecordStatusId());
        target.setRecordTypeId(input.getRecordTypeId());
        target.setFilepath(input.getFilepath());

        target.setModified(new Timestamp(new java.util.Date().getTime()));
        target.setModifiedBy(input.getModifiedBy());

        caseRecordRepository.save(target);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new CaseRecordResource(target).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
}
