package mole.controller;

import mole.model.dao.ClinicLocation;
import mole.model.repositories.ClinicLocationRepository;
import mole.model.resource.ClinicLocationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ClinicLocations")
public class ClinicLocationController {
    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ClinicLocationResource getClinicLocationById(@PathVariable("id") Long id) {
        return new ClinicLocationResource(this.clinicLocationRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<ClinicLocation> getAll() {
        return clinicLocationRepository.findAll();
    }
}
