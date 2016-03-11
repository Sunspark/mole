package mole.controller;

import mole.model.dao.Claimant;
import mole.model.repositories.ClaimantRepository;
import mole.model.resource.ClaimantResource;
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
@RequestMapping("/Claimants")
public class ClaimantController {
    @Autowired
    private ClaimantRepository claimantRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ClaimantResource getClaimantById(@PathVariable("id") Long id) {
        return new ClaimantResource(this.claimantRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Claimant> getAll() {
        return claimantRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/Search/{field}/{value}")
    public List<Claimant> claimantSearch(
            @PathVariable("field") String field,
            @PathVariable("value") String value
    ) {
        List<Claimant> returnList = new ArrayList<Claimant>();
        switch (field) {
            case "lastName":
                returnList = claimantRepository.findByLastName(value);
                break;
            // no default, leave a blank array
        }

        return returnList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Add")
    public ResponseEntity<?> add(@RequestBody Claimant input) {
        //this.validateUser(userId);

        claimantRepository.save(input);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new ClaimantResource(input).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Update")
    public ResponseEntity<?> update(@RequestBody Claimant input) {
        //this.validateUser(userId);

        Claimant target = this.claimantRepository.findOne(input.getClaimantId());

        target.setFirstName(input.getFirstName());
        target.setLastName(input.getLastName());
        target.setDob(input.getDob());
        target.setDoa(input.getDoa());

        target.setModified(new Timestamp(new java.util.Date().getTime()));
        target.setModifiedBy(input.getModifiedBy());

        claimantRepository.save(target);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new ClaimantResource(target).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
}
