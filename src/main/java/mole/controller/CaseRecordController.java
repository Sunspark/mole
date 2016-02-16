package mole.controller;

import mole.model.dao.CaseRecord;
import mole.model.repositories.CaseRecordRepository;
import mole.model.resource.CaseRecordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
