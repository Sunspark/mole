package mole.model.repositories;

import mole.model.dao.Claimant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClaimantRepository extends CrudRepository<Claimant, Long> {
    List<Claimant> findByLastNameLike(String lastName);
}
