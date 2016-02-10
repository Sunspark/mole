package mole.controller;
//https://github.com/ekcode/spring-boot-dbunit-example
//https://springtestdbunit.github.io/spring-test-dbunit/
//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html //Section 14.6

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import mole.Application;
import mole.rules.WebResponseRule;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.junit.Before;
import org.junit.Rule;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;

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
            .andExpect(jsonPath("$.userId").value(101));
    }
    //countRowsInTable(..): counts the number of rows in the given table
}
