package mole.controller;

import mole.model.dao.Clinic;
import mole.model.repositories.ClinicRepository;
import mole.model.resource.ClinicResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Clinics")
public class ClinicController {
    @Autowired
    private ClinicRepository clinicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ClinicResource getClinicById(@PathVariable("id") Long id) {
        return new ClinicResource(this.clinicRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Clinic> getAll() {
        return clinicRepository.findAll();
    }
}
