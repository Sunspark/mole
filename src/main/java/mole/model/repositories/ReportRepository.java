package mole.model.repositories;

import mole.model.dao.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {
    Report findOneByOrderByModifiedDesc();
    Report findTop1ByOrderByModifiedDesc();
}
