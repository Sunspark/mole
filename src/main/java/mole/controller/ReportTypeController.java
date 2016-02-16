package mole.controller;

import mole.model.dao.ReportType;
import mole.model.repositories.ReportTypeRepository;
import mole.model.resource.ReportTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ReportTypes")
public class ReportTypeController {
    @Autowired
    private ReportTypeRepository reportTypeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ReportTypeResource getReportTypeById(@PathVariable("id") Long id) {
        return new ReportTypeResource(this.reportTypeRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<ReportType> getAll() {
        return reportTypeRepository.findAll();
    }
}
