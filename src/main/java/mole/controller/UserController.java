package mole.controller;

import mole.model.dao.User;
import mole.model.repositories.UserRepository;

import mole.model.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserResource getUserById(@PathVariable("id") Long userId) {
        return new UserResource(this.userRepository.findOne(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/Search/{field}/{value}")
    public List<User> userSearch(
            @PathVariable("field") String field,
            @PathVariable("value") String value
    ) {
        List<User> returnList = new ArrayList<User>();
        switch (field) {
            case "lastName":
                returnList = userRepository.findByLastName(value);
                break;
            case "firstName":
                returnList = userRepository.findByFirstName(value);
                break;
            // no default, leave a blank array
        }

        return returnList;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Add")
    public ResponseEntity<?> add(@RequestBody User input) {
        //this.validateUser(userId);

        userRepository.save(input);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new UserResource(input).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Update")
    public ResponseEntity<?> update(@RequestBody User input) {
        //this.validateUser(userId);

        User targetUser = this.userRepository.findOne(input.getUserId());

        targetUser.setFirstName(input.getFirstName());
        targetUser.setLastName(input.getLastName());
        targetUser.setEmail(input.getEmail());
        targetUser.setPower(input.getPower());
        // TODO password dependent on security.
        //targetUser.setPassword();

        targetUser.setModified(new Timestamp(new java.util.Date().getTime()));
        targetUser.setModifiedBy(input.getModifiedBy());

        userRepository.save(targetUser);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link newUserLink = new UserResource(targetUser).getLink("self");
        httpHeaders.setLocation(URI.create(newUserLink.getHref()));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
}
