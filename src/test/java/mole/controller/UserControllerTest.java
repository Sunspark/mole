package mole.controller;
//https://github.com/ekcode/spring-boot-dbunit-example
//https://springtestdbunit.github.io/spring-test-dbunit/
//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html //Section 14.6

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import mole.Application;
import mole.model.dao.User;
import mole.rules.WebResponseRule;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;




//http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
//@DbUnitConfiguration(databaseConnection = "HsqldbDataTypeFactory")
@SpringApplicationConfiguration(classes = Application.class)
@DatabaseSetup("/moleTestDbData.xml")
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private MediaType contentTypeJson = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8")
    );

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    //https://thomassundberg.wordpress.com/2012/07/08/performing-an-action-when-a-test-fails/
    //@Rule
    //public WebResponseRule ruleExample = new WebResponseRule();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNothing() {}

    @Test
    public void testGetUserById() throws Exception {
        this.mockMvc
            .perform(
                get("/Users/101")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.userId").value(101))
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/101"))
        ;
    }
    //countRowsInTable(..): counts the number of rows in the given table

    @Test
    public void testAddUser() throws Exception {
        String firstName = "William";
        String lastName = "Tell";
        Long createdBy = 101L;
        Long modifiedBy = 101L;

        User testUser = new User();
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setCreatedBy(createdBy);
        testUser.setModifiedBy(modifiedBy);

        String userJson = objectToJson(testUser);
        this.mockMvc
            .perform(
                post("/Users/Add")
                .contentType(contentTypeJson)
                .content(userJson)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            .andDo(print())
            .andExpect(status().isCreated())
        ;

        //int rowCount = countRowsInTable("USERS");

        this.mockMvc
            .perform(
                get("/Users/105")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.content.userId").value(105))
            .andExpect(jsonPath("$._links.self.href").value("http://localhost/Users/105"))
            .andExpect(jsonPath("$._links.createdByUser.href").value("http://localhost/Users/101"))
            .andExpect(jsonPath("$._links.modifiedByUser.href").value("http://localhost/Users/101"))
        ;
    }

    protected String objectToJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
