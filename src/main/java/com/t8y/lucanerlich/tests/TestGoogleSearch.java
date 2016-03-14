package com.t8y.lucanerlich.tests;

import com.t8y.lucanerlich.pageobjects.GoogleSearch;
import com.t8y.lucanerlich.pageobjects.Login;
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
    public void seach(){
        googleSearch.searchFor("lucanerlich");
    }
}
