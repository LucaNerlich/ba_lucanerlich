package com.t8y.lucanerlich.tests.random;

import com.t8y.lucanerlich.pageobjects.random.GoogleSearch;
import com.t8y.lucanerlich.tests.Base;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lucan on 11.03.2016.
 */
public class TestGoogleSearch extends Base {

    private GoogleSearch googleSearch;

    @Before
    public void setUp() {
        googleSearch = new GoogleSearch(proxy, driver);
    }

    @Test
    public void search() {
        googleSearch.searchFor("lucanerlich");
    }
}
