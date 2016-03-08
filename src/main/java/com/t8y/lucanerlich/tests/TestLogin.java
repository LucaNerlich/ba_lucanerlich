package com.t8y.lucanerlich.tests;

import com.t8y.lucanerlich.pageobjects.Login;
import com.t8y.lucanerlich.tests.groups.Deep;
import com.t8y.lucanerlich.tests.groups.Shallow;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.junit.Assert.assertTrue;


public class TestLogin extends Base {

    private Login login;

    @Before
    public void setUp() {
        login = new Login(proxy, driver);
    }

    @Test
    @Category(Shallow.class)
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue("Success message not present", login.successMessagePresent());
        assertTrue("Der HTTP Status des Requests .*/login ist nicht 200", login.getStatusOfHttpResponse(".*/login") == 200);
        assertTrue("Der HTTP Status des Requests .*/favicon.* ist nicht 404", login.getStatusOfHttpResponse(".*/favicon.*") == 404);
        assertTrue("Der Content des Requests .*/login enthält nicht den String \"!DOCTYPE\"", login.getContentOfHttpResponse(".*/login").contains("!DOCTYPE"));
        assertTrue(login.httpStatusOfPageIs200());
    }

    @Test
    @Category({Shallow.class, Deep.class})
    public void failed() {
        login.with("tomsmith", "bad password");
        assertTrue("Failure message wasn't present after providing bogus credentials", login.failureMessagePresent());
    }

}
