package com.t8y.lucanerlich.tests.volkswagencarnet.com;

import com.t8y.lucanerlich.pageobjects.volkswagencarnet.com.Kundenportal;
import com.t8y.lucanerlich.reporting.Exceptions.CompareException;
import com.t8y.lucanerlich.tests.Base;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static org.junit.Assert.assertTrue;

/**
 * Created by lucan on 23.05.2016.
 */
public class TestKundenportal extends Base {

    public static final String VW_METRIC = "metric";
    private Kundenportal kundenportal;

    @Before
    public void setUp() {
        kundenportal = new Kundenportal(proxy, driver);
    }

    /*
    @Test
    public void visitCarNetHome() {
        kundenportal.visitCarNetHome();
    }
    */

    @Test
    public void visitKundenportal() throws CompareException {
        //Sucht in allen requests. Reicht: "wurde min. 1x genau so gefunden"?
        //Was passiert bei doppelten Request Logs, ist dies als Fehler zu sehen?

        kundenportal.visitKundenportal();
        Har httpArchive = proxy.getHar();

        List<HarEntry> entries = httpArchive.getLog().getEntries();
        boolean trackingReqFound = false;

        Map<String, String> expectedValues = new HashMap<String, String>();
        expectedValues.put("pageName", "MS : Car-Net : Customer Portals");
        expectedValues.put("c8", "MS-Car-Net-de-de");
        expectedValues.put("c32", "MS-Car-Net");
        expectedValues.put("v8", "None");
        expectedValues.put("c42", "D=v8+\" > \"+pageName");
        expectedValues.put("v56", "None");
        expectedValues.put("v72", "None");

        for (HarEntry entry : entries) {
            String url = entry.getRequest().getUrl();
            if (url.contains(VW_METRIC)) {
                Map<String, String> parameter = new HashMap<String, String>();

                String[] parts = url.split("\\?")[1].split("&");

                for (String part : parts) {
                    Matcher m = urlParameterPattern.matcher(part);
                    if (m.matches()) parameter.put(m.group(1), m.group(2));
                }

                trackingReqFound = compareStringMap(parameter, expectedValues);
                if (trackingReqFound) break;
            }
        }
        errorLevel.isTestSuccessful(trackingReqFound, name.getMethodName());
    }
}
