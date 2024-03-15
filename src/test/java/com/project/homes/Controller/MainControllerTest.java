package com.example.Homes.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    @Test
    public void testLoginProcessSuccess() {
        MainController mainController = new MainController();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        String viewName = mainController.loginProcess();
        assertEquals("login:/login?success", viewName);
    }
}