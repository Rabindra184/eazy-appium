
package com.github.parse;

import static com.google.common.truth.Truth.assertWithMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.parse.data.XmlData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataSourceXmlTest {

    @Test (dataProvider = "testData")
    public void readingXmlFileTest (final String to, final String from, final String heading, final String body) {
        assertWithMessage ("to").that (to)
            .isNotEmpty ();
        assertWithMessage ("from").that (from)
            .isNotEmpty ();
        assertWithMessage ("heading").that (heading)
            .isNotEmpty ();
        assertWithMessage ("body").that (body)
            .isNotEmpty ();
    }


    @DataProvider
    public Iterator<Object[]> testData () {
        final XmlData testData = DataSource.parse (XmlData.class);
        final List<Object[]> data = new ArrayList<> ();
        data.add (new Object[] { testData.getTo (), testData.getFrom (), testData.getHeading (), testData.getBody () });
        return data.iterator ();
    }
}