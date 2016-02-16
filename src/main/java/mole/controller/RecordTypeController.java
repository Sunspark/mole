package mole.controller;

import mole.model.dao.RecordType;
import mole.model.repositories.RecordTypeRepository;
import mole.model.resource.RecordTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RecordTypes")
public class RecordTypeController {
    @Autowired
    private RecordTypeRepository recordTypeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RecordTypeResource getRecordTypeById(@PathVariable("id") Long id) {
        return new RecordTypeResource(this.recordTypeRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<RecordType> getAll() {
        return recordTypeRepository.findAll();
    }
}
