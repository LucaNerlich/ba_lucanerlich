package com.t8y.lucanerlich.tests.webspecial.volskwagen.de;

import com.t8y.lucanerlich.pageobjects.webspecial.volkswagen.de.Video;
import com.t8y.lucanerlich.tests.Base;
import com.t8y.lucanerlich.tests.groups.Deep;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by lnerlich on 21.03.2016.
 */
public class TestVideo extends Base {

    private Video video;

    @Before
    public void setUp() {
        video = new Video(proxy, driver);
        //doenst load page
        System.out.println();
    }

    @Test
    @Category(Deep.class)
    public void succeeded() {
//        video.playVideo();
    }
}
