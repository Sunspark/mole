package mole.controller;

import mole.model.entities.User;
import mole.model.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // If I want a method that is overloaded, how?
    // Should I overload?
    // why doesn't spring boot 'assume' get/set ?
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUserById(@PathVariable("id") long userId) {
        return userRepository.findOne(userId);
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
/*
    @RequestMapping(method = RequestMethod.GET, value = "/Search")
    public List<User> userSearchByFirstName(@RequestParam(value = "firstName", required = false) String firstName) {
        return userRepository.findByFirstName(firstName);
    }
*/
}
