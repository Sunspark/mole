package mole.controller;

import mole.model.dao.User;
import mole.model.repositories.UserRepository;

import mole.model.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST, value = "/Add")
    public ResponseEntity<?> add(@RequestBody User input) {
        //this.validateUser(userId);

        User newUser = new User();
        newUser.setFirstName(input.getFirstName());
        newUser.setLastName(input.getLastName());
        newUser.setCreatedBy(input.getCreatedBy());
        newUser.setModifiedBy(input.getModifiedBy());

        userRepository.save(newUser);

        int k = 1;
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        /*
        return accountRepository.findByUsername(userId)
                .map(account -> {
                            Bookmark bookmark = bookmarkRepository.
                            save(new Bookmark(account, input.uri, input.description));

                            HttpHeaders httpHeaders = new HttpHeaders();

                            Link forOneBookmark = new BookmarkResource(bookmark).getLink("self");
                            httpHeaders.setLocation(URI.create(forOneBookmark.getHref()));

                            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                        }
                ).get();
                */
    }
}
