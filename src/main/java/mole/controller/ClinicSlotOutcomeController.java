package mole.controller;

import mole.model.dao.ClinicSlotOutcome;
import mole.model.repositories.ClinicSlotOutcomeRepository;
import mole.model.resource.ClinicSlotOutcomeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ClinicSlotOutcomes")
public class ClinicSlotOutcomeController {
    @Autowired
    private ClinicSlotOutcomeRepository clinicSlotOutcomeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ClinicSlotOutcomeResource getClinicSlotOutcomeById(@PathVariable("id") Long id) {
        return new ClinicSlotOutcomeResource(this.clinicSlotOutcomeRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<ClinicSlotOutcome> getAll() {
        return clinicSlotOutcomeRepository.findAll();
    }
}
