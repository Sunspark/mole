package mole.model.repositories;

import mole.model.dao.CaseRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseRecordRepository extends CrudRepository<CaseRecord, Long> {
    List<CaseRecord> findByCaseId(Long caseId);
    Long countByCaseIdAndRecordStatusId(Long caseId, Long recordStatusId);
}
