package mole.model.repositories;

import java.util.List;

import mole.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
    List<User> findByFirstName(String firstName);
}
