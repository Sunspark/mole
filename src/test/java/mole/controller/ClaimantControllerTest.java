package mole.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mole.Application;
import mole.model.dao.Claimant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})

@SpringApplicationConfiguration(classes = Application.class)
@DatabaseSetup("/moleTestDbData.xml")
@WebAppConfiguration
public class ClaimantControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    private MediaType contentTypeJson = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8")
    );

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetClaimantById() throws Exception {
        this.mockMvc
                .perform(
                        get("/Claimants/1")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                )
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.content.claimantId").value(1))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/Claimants/1"))
                .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
                .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/101"))
        ;
    }


    @Test
    public void testAddClaimant() throws Exception {
        String firstName = "Mick";
        String lastName = "Jagger";

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDob = fmt.parse("1913-05-06");
        java.util.Date utilDoa = fmt.parse("2013-05-06");

        java.sql.Date dob = new java.sql.Date(utilDob.getTime());
        java.sql.Date doa = new java.sql.Date(utilDoa.getTime());

        Long createdBy = 101L;
        Long modifiedBy = 101L;

        Claimant newObject = new Claimant();
        newObject.setFirstName(firstName);
        newObject.setLastName(lastName);
        newObject.setDob(dob);
        newObject.setDoa(doa);
        newObject.setCreatedBy(createdBy);
        newObject.setModifiedBy(modifiedBy);

        String stringNewObject = mapper.writeValueAsString(newObject);
        MvcResult userMvcResult = this.mockMvc
            .perform(
                post("/Claimants/Add")
                    .contentType(contentTypeJson)
                    .content(stringNewObject)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(redirectedUrlPattern("http://localhost/Claimants/*"))
            .andReturn()
        ;

        String addedUrl = userMvcResult.getResponse().getRedirectedUrl();

        this.mockMvc
            .perform(
                get(addedUrl)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.firstName").value(firstName))
            .andExpect(jsonPath("$.content.lastName").value(lastName))
            .andExpect(jsonPath("$.content.dob").value(dob.toString()))
            .andExpect(jsonPath("$.content.doa").value(doa.toString()))
            .andExpect(jsonPath("$._links.self.href").value(addedUrl))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/" + createdBy))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/" + modifiedBy))
        ;
    }

    @Test
    public void testUpdateClaimant() throws Exception {
        MvcResult caseMvcResult = this.mockMvc
            .perform(
                get("/Claimants/Search/lastName/Lacrosse")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        ;

        String stringSearchResponse = caseMvcResult.getResponse().getContentAsString();
        JsonNode jsonStringSearchResponse = mapper.readTree(stringSearchResponse);
        JsonNode jsonTarget = jsonStringSearchResponse.get(0);
        Claimant target = mapper.readValue(jsonTarget.toString(), Claimant.class);

        // Set new values
        String firstName = "Mick";
        String lastName = "Jagger";

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDob = fmt.parse("1913-05-06");
        java.util.Date utilDoa = fmt.parse("2013-05-06");

        java.sql.Date dob = new java.sql.Date(utilDob.getTime());
        java.sql.Date doa = new java.sql.Date(utilDoa.getTime());

        Long modifiedBy = 102L;

        Long claimantId = target.getClaimantId();
        target.setFirstName(firstName);
        target.setLastName(lastName);
        target.setDob(dob);
        target.setDoa(doa);
        target.setModifiedBy(modifiedBy);

        String targetJson = mapper.writeValueAsString(target);

        this.mockMvc
            .perform(
                post("/Claimants/Update")
                    .contentType(contentTypeJson)
                    .content(targetJson)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
        ;

        this.mockMvc
            .perform(
                get("/Claimants/" + claimantId)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.claimantId").value(claimantId.intValue()))
            .andExpect(jsonPath("$.content.firstName").value(firstName))
            .andExpect(jsonPath("$.content.lastName").value(lastName))
            .andExpect(jsonPath("$.content.dob").value(dob.toString()))
            .andExpect(jsonPath("$.content.doa").value(doa.toString()))
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/Claimants/" + claimantId))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/" + modifiedBy))
        ;
    }

}
