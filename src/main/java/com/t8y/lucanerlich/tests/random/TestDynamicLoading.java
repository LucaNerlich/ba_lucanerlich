package com.t8y.lucanerlich.tests.random;

import com.t8y.lucanerlich.pageobjects.random.DynamicLoading;
import com.t8y.lucanerlich.tests.Base;
import com.t8y.lucanerlich.tests.groups.Deep;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertTrue;

@Category(Deep.class)
public class TestDynamicLoading extends Base {

    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver, proxy);
    }

    @Test
    public void hiddenElementLoads() {
        dynamicLoading.loadExample("1");
        assertTrue("Finish text didn't display after loading", dynamicLoading.finishTextPresent());
    }

    @Test
    public void elementAppears() {
        dynamicLoading.loadExample("2");
        assertTrue("Finish text didn't render after loading", dynamicLoading.finishTextPresent());
    }


}
