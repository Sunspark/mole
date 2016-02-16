package mole.controller;

import mole.model.dao.Claimant;
import mole.model.repositories.ClaimantRepository;
import mole.model.resource.ClaimantResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
