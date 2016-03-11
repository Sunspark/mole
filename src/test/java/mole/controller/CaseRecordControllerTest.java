package mole.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mole.Application;
import mole.model.dao.CaseRecord;
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
public class CaseRecordControllerTest {
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
    public void testGetCaseRecordById() throws Exception {
        this.mockMvc
            .perform(
                get("/CaseRecords/1")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.caseRecordId").value(1))
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/CaseRecords/1"))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/101"))
        ;
    }

    @Test
    public void testAddCaseRecord() throws Exception {
        Long caseId = 3L;
        Long recordTypeId = 2L;
        Long recordStatusId = 1L;
        String filepath = "/path/to/file";

        Long createdBy = 101L;
        Long modifiedBy = 101L;

        CaseRecord newObject = new CaseRecord();
        newObject.setCaseId(caseId);
        newObject.setRecordTypeId(recordTypeId);
        newObject.setRecordStatusId(recordStatusId);
        newObject.setFilepath(filepath);
        newObject.setCreatedBy(createdBy);
        newObject.setModifiedBy(modifiedBy);

        String stringNewObject = mapper.writeValueAsString(newObject);
        MvcResult userMvcResult = this.mockMvc
            .perform(
                post("/CaseRecords/Add")
                    .contentType(contentTypeJson)
                    .content(stringNewObject)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(redirectedUrlPattern("http://localhost/CaseRecords/*"))
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
            .andExpect(jsonPath("$.content.caseId").value(caseId.intValue()))
            .andExpect(jsonPath("$.content.recordTypeId").value(recordTypeId.intValue()))
            .andExpect(jsonPath("$.content.recordStatusId").value(recordStatusId.intValue()))
            .andExpect(jsonPath("$.content.filepath").value(filepath))
            .andExpect(jsonPath("$._links.self.href").value(addedUrl))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/" + createdBy))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/" + modifiedBy))
        ;
    }

    @Test
    public void testUpdateCaseRecord() throws Exception {
        MvcResult caseMvcResult = this.mockMvc
            .perform(
                get("/CaseRecords/Search/caseId/2")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        ;

        String stringSearchResponse = caseMvcResult.getResponse().getContentAsString();
        JsonNode jsonStringSearchResponse = mapper.readTree(stringSearchResponse);
        JsonNode jsonTarget = jsonStringSearchResponse.get(0);
        CaseRecord target = mapper.readValue(jsonTarget.toString(), CaseRecord.class);

        // Set new values
        Long caseId = 3L;
        Long recordTypeId = 2L;
        Long recordStatusId = 1L;
        String filepath = "/path/to/file";
        Long modifiedBy = 102L;

        Long caseRecordId = target.getCaseRecordId();
        target.setCaseId(caseId);
        target.setRecordTypeId(recordTypeId);
        target.setRecordStatusId(recordStatusId);
        target.setFilepath(filepath);
        target.setModifiedBy(modifiedBy);

        String targetJson = mapper.writeValueAsString(target);

        this.mockMvc
            .perform(
                post("/CaseRecords/Update")
                    .contentType(contentTypeJson)
                    .content(targetJson)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
        ;

        this.mockMvc
            .perform(
                get("/CaseRecords/" + caseRecordId)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.caseRecordId").value(caseRecordId.intValue()))
            .andExpect(jsonPath("$.content.caseId").value(caseId.intValue()))
            .andExpect(jsonPath("$.content.recordStatusId").value(recordStatusId.intValue()))
            .andExpect(jsonPath("$.content.recordTypeId").value(recordTypeId.intValue()))
            .andExpect(jsonPath("$.content.filepath").value(filepath))
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/CaseRecords/" + caseRecordId))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/" + modifiedBy))
        ;
    }

}
