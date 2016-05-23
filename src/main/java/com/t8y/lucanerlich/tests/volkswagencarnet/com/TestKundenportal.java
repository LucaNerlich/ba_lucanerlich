package com.t8y.lucanerlich.tests.volkswagencarnet.com;

import com.t8y.lucanerlich.pageobjects.random.Login;
import com.t8y.lucanerlich.pageobjects.volkswagencarnet.com.Kundenportal;
import com.t8y.lucanerlich.tests.Base;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lucan on 23.05.2016.
 */
public class TestKundenportal extends Base {

    Kundenportal kundenportal;

    @Before
    public void setUp() {
        kundenportal = new Kundenportal(proxy, driver);
    }

    @Test
    public void visitCarNetHome(){
        kundenportal.visitCarNetHome();
    }

    @Test
    public void visitKundenportal(){
        kundenportal.visitKundenportal();
    }
}
