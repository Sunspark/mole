package mole.model.resourceAssembler;

import mole.controller.CaseController;
import mole.model.dao.Case;
import mole.model.dao.Report;
import mole.model.repositories.CaseRecordRepository;
import mole.model.repositories.ReportRepository;
import mole.model.resource.CaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CaseResourceAssembler extends ResourceAssemblerSupport<Case, CaseResource> {
    @Autowired
    private CaseRecordRepository caseRecordRepository;
    @Autowired
    private ReportRepository reportRepository;

    public CaseResourceAssembler() {
        super(CaseController.class, CaseResource.class);
    }

    @Override
    public CaseResource toResource(Case aCase) {
        CaseResource resource = createResourceWithId(aCase.getCaseId(), aCase);
        return resource;
    }

    @Override
    protected CaseResource instantiateResource(Case aCase) {
        // Should I be creating the links here?
        CaseResource caseResource = new CaseResource(aCase);

        // include case record query handler
        // do a count query x2
        // countByCaseIdAndRecordStatus 3 is good, anything else is bad
        Long goodStatus = 3L;
        Long recordsReceivedTotal = caseRecordRepository.countByCaseIdAndRecordStatusId(aCase.getCaseId(), goodStatus);
        Long recordsRequiredTotal = caseRecordRepository.countByCaseId(aCase.getCaseId());

        // assign values
        caseResource.setRecordsReceivedTotal(recordsReceivedTotal);
        caseResource.setRecordsRequiredTotal(recordsRequiredTotal);

        // include report query handler
        // get max report on this case by date
        Report mostRecentReport = reportRepository.findOneByOrderByModifiedDesc();
        // assign values
        Long x = 3L;



        return caseResource;
    }
}
