package mole.model.resource;

import mole.model.dao.RecordStatus;
import mole.controller.RecordStatusController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RecordStatusResource extends ResourceSupport {
    private final RecordStatus recordStatus;

    public RecordStatusResource(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
        this.add(linkTo(methodOn(RecordStatusController.class).getRecordStatusById(recordStatus.getRecordStatusId())).withSelfRel());

        Long createdBy = recordStatus.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = recordStatus.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public RecordStatus getContent() {
        return recordStatus;
    }

}
