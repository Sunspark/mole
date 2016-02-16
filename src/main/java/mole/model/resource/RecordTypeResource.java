package mole.model.resource;

import mole.model.dao.RecordType;
import mole.controller.RecordTypeController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RecordTypeResource extends ResourceSupport {
    private final RecordType recordType;

    public RecordTypeResource(RecordType recordType) {
        this.recordType = recordType;
        this.add(linkTo(methodOn(RecordTypeController.class).getRecordTypeById(recordType.getRecordTypeId())).withSelfRel());

        Long createdBy = recordType.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = recordType.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public RecordType getContent() {
        return recordType;
    }

}
