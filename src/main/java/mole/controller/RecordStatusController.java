package mole.controller;

import mole.model.dao.RecordStatus;
import mole.model.repositories.RecordStatusRepository;
import mole.model.resource.RecordStatusResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RecordStatuses")
public class RecordStatusController {
    @Autowired
    private RecordStatusRepository recordStatusRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public RecordStatusResource getRecordStatusById(@PathVariable("id") Long id) {
        return new RecordStatusResource(this.recordStatusRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<RecordStatus> getAll() {
        return recordStatusRepository.findAll();
    }
}
