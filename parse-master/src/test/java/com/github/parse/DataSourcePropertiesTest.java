
package com.github.parse;

import static com.google.common.truth.Truth.assertWithMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.parse.data.PropertiesData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataSourcePropertiesTest {

    @Test (dataProvider = "testData")
    public void readingPropertiesFileTest (final String testUrl, final String userName, final int port) {
        assertWithMessage ("testurl").that (testUrl)
            .isNotEmpty ();
        assertWithMessage ("username").that (userName)
            .isNotEmpty ();
        assertWithMessage ("port").that (port)
            .isNotNull ();
    }

    /**
     * @return testdata
     *
     */
    @DataProvider
    public Iterator<Object[]> testData () {
        final PropertiesData testData = DataSource.parse (PropertiesData.class);
        final List<Object[]> data = new ArrayList<> ();
        data.add (new Object[] { testData.getTesturl (), testData.getUsername (), testData.getPort () });
        return data.iterator ();
    }
}