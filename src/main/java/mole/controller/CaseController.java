package mole.controller;

import mole.model.dao.Case;
import mole.model.repositories.CaseRepository;
import mole.model.resource.CaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cases")
public class CaseController {
    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CaseResource getCaseById(@PathVariable("id") Long id) {
        return new CaseResource(this.caseRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Case> getAll() {
        return caseRepository.findAll();
    }
}
