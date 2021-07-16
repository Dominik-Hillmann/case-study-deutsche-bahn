package com.bahn.casestudy;

import com.bahn.casestudy.info.NonOperationSiteController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiNotImplementedTests {
    static NonOperationSiteController controller;

    @BeforeAll
    public static void init() {
        controller = new NonOperationSiteController();
    }

    @Test
    @DisplayName("Information for not calling .../betriebsstellen")
    public void nonOperationSiteCall() {
        String wantedErrorMsg = "Diese API ist noch nicht implementiert.";
        String givenErrorMsg = controller.getNotImplemented().getBody().getMessage();
        Assertions.assertEquals(wantedErrorMsg, givenErrorMsg);

        givenErrorMsg = controller.getNotImplementedWithSlash().getBody().getMessage();
        Assertions.assertEquals(wantedErrorMsg, givenErrorMsg);
    }
}
