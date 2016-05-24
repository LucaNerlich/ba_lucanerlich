package com.t8y.lucanerlich.tests.volkswagencarnet.com;

import com.t8y.lucanerlich.pageobjects.volkswagencarnet.com.Kundenportal;
import com.t8y.lucanerlich.tests.Base;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Created by lucan on 23.05.2016.
 */
public class TestKundenportal extends Base {

    private Kundenportal kundenportal;

    @Before
    public void setUp() {
        kundenportal = new Kundenportal(proxy, driver);
    }

    @Test
    public void visitCarNetHome() {
        kundenportal.visitCarNetHome();
    }

    @Test
    public void visitKundenportal() {
        kundenportal.visitKundenportal();
        Har httpArchive = proxy.getHar();
        List<HarEntry> entries = httpArchive.getLog().getEntries();
        boolean trackingReqFound = false;
        for (HarEntry entry : entries) {
            String url = entry.getRequest().getUrl();
            if (url.contains("metric")) {
                Map<String, String> parameter = new HashMap();

                String[] parts = url.split("\\?")[1].split("&");
                Pattern p = Pattern.compile("([^=]+)\\=([^&#]+)");

                for (String part : parts) {
                    Matcher m = p.matcher(part);
                    if (m.matches()) {
                        parameter.put(m.group(1), m.group(2));
                    }
                }
                System.out.println();


                //calls .equals()
                //todo Problem:
                //Sucht in allen requests. Reicht: "wurde min. 1x genau so gefunden"?


                if (parameter.get("pageName").equals("MS : Car-Net : Customer Portals")) {
                    if (parameter.get("c8").equals("MS-Car-Net-de-de")) {
                        if (parameter.get("c32").equals("MS-Car-Net")) {
                            if (parameter.get("v8").equals("None")) {
                                if (parameter.get("c42").equals("D=v8+\" > \"+pageName")) {
                                    if (parameter.get("v56").equals("None")) {
                                        if (parameter.get("v72").equals("None")) {
                                            trackingReqFound = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        assertTrue(trackingReqFound);
    }
}
