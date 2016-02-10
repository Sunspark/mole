package mole.rules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.test.web.servlet.MockMvc;

public class WebResponseRule extends TestWatcher {
    private MockMvc mockMvc;

    public WebResponseRule (MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        System.out.println("Only outputed when a test fails");
        //this.mockMvc.
    }
}
