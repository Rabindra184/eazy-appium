
package com.github.parse;

import static com.google.common.truth.Truth.assertWithMessage;
import static java.lang.System.getProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.parse.data.JsonTestData;
import com.github.parse.data.XmasFifthDay;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataSourceJsonTest {

    @Test (dataProvider = "testData")
    public void readingJsonFileTest (final String doe, final String ray, final float pi, final int frenchHens,
        final String[] callingBirds, final XmasFifthDay xmasFifthDay, final String user) {
        assertWithMessage ("doe").that (doe)
            .isNotEmpty ();
        assertWithMessage ("ray").that (ray)
            .isNotEmpty ();
        assertWithMessage ("pi").that (pi)
            .isNonZero ();
        assertWithMessage ("frenchHens").that (frenchHens)
            .isNotNull ();
        assertWithMessage ("callingBirds").that (callingBirds)
            .isNotEmpty ();
        assertWithMessage ("xmasFifthDay").that (xmasFifthDay)
            .isNotNull ();
        assertWithMessage ("user").that (user)
            .isEqualTo (getProperty ("user.name"));
    }

    /**
     * @return testdata
     *
     */
    @DataProvider
    public Iterator<Object[]> testData () {
        final JsonTestData testData = DataSource.parse (JsonTestData.class);
        final List<Object[]> data = new ArrayList<> ();
        data.add (new Object[] { testData.getDoe (), testData.getRay (), testData.getPi (), testData.getFrenchHens (),
            testData.getCallingBirds (), testData.getXmasFifthDay (), testData.getUser () });
        return data.iterator ();
    }
}