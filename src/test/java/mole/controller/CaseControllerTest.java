package mole.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mole.Application;
import mole.model.dao.Case;
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
import static java.lang.Math.toIntExact;

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
public class CaseControllerTest {
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
    public void testGetCaseById() throws Exception {
        this.mockMvc
                .perform(
                        get("/Cases/1")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                )
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.content.caseId").value(1))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/Cases/1"))
                .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
                .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/101"))
        ;
    }

    @Test
    public void testAddCase() throws Exception {
        Long agencyId = 2L;
        Long claimantId = 4L;
        String agencyRefCode = "thisIsAFish";
        Long createdBy = 101L;
        Long modifiedBy = 101L;

        Case testCase = new Case();
        testCase.setAgencyId(agencyId);
        testCase.setClaimantId(claimantId);
        testCase.setAgencyRefCode(agencyRefCode);
        testCase.setCreatedBy(createdBy);
        testCase.setModifiedBy(modifiedBy);

        String userJson = mapper.writeValueAsString(testCase);
        MvcResult userMvcResult = this.mockMvc
            .perform(
                post("/Cases/Add")
                    .contentType(contentTypeJson)
                    .content(userJson)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(redirectedUrlPattern("http://localhost/Cases/*"))
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
            .andExpect(jsonPath("$.content.agencyId").value(agencyId.intValue()))
            .andExpect(jsonPath("$.content.claimantId").value(claimantId.intValue()))
            .andExpect(jsonPath("$.content.agencyRefCode").value(agencyRefCode))
            .andExpect(jsonPath("$._links.self.href").value(addedUrl))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/" + createdBy))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/" + modifiedBy))
        ;
    }

    @Test
    public void testUpdateCase() throws Exception {
        MvcResult caseMvcResult = this.mockMvc
            .perform(
                get("/Cases/Search/agencyRefCode/s-0001")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        ;

        String stringSearchResponse = caseMvcResult.getResponse().getContentAsString();
        JsonNode jsonStringSearchResponse = mapper.readTree(stringSearchResponse);
        JsonNode jsonTarget = jsonStringSearchResponse.get(0);
        Case testCase = mapper.readValue(jsonTarget.toString(), Case.class);

        // Set new values
        Long agencyId = 2L;
        Long claimantId = 4L;
        String agencyRefCode = "thisIsAFish";
        Long modifiedBy = 102L;

        Long caseId = testCase.getCaseId();
        testCase.setAgencyId(agencyId);
        testCase.setClaimantId(claimantId);
        testCase.setAgencyRefCode(agencyRefCode);
        testCase.setModifiedBy(modifiedBy);

        String caseJson = mapper.writeValueAsString(testCase);

        this.mockMvc
            .perform(
                post("/Cases/Update")
                    .contentType(contentTypeJson)
                    .content(caseJson)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
        ;

        this.mockMvc
            .perform(
                get("/Cases/" + caseId)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.caseId").value(caseId.intValue()))
            .andExpect(jsonPath("$.content.agencyId").value(agencyId.intValue()))
            .andExpect(jsonPath("$.content.claimantId").value(claimantId.intValue()))
            .andExpect(jsonPath("$.content.agencyRefCode").value(agencyRefCode))
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/Cases/" + caseId))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/" + modifiedBy))
        ;
    }

}
