package mole.controller;

import mole.model.dao.Report;
import mole.model.repositories.ReportRepository;
import mole.model.resource.ReportResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Reports")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ReportResource getReportById(@PathVariable("id") Long id) {
        return new ReportResource(this.reportRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Report> getAll() {
        return reportRepository.findAll();
    }
}
