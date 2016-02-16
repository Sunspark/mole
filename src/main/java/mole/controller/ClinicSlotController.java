package mole.controller;

import mole.model.dao.ClinicSlot;
import mole.model.repositories.ClinicSlotRepository;
import mole.model.resource.ClinicSlotResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ClinicSlots")
public class ClinicSlotController {
    @Autowired
    private ClinicSlotRepository clinicSlotRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ClinicSlotResource getClinicSlotById(@PathVariable("id") Long id) {
        return new ClinicSlotResource(this.clinicSlotRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<ClinicSlot> getAll() {
        return clinicSlotRepository.findAll();
    }
}
