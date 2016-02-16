package mole.controller;

        import mole.model.dao.Agency;
        import mole.model.repositories.AgencyRepository;
        import mole.model.resource.AgencyResource;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Agencies")
public class AgencyController {
    @Autowired
    private AgencyRepository agencyRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AgencyResource getAgencyById(@PathVariable("id") Long id) {
        return new AgencyResource(this.agencyRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Agency> getAll() {
        return agencyRepository.findAll();
    }
}
