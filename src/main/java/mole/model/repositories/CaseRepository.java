package mole.model.repositories;

import mole.model.dao.Case;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseRepository extends CrudRepository<Case, Long> {
    List<Case> findByAgencyRefCode(String agencyRefCode);
    List<Case> findAllByOrderByModifiedByDesc(Pageable pageable);
}
