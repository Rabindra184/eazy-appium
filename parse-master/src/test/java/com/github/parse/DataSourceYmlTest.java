
package com.github.parse;

import static com.google.common.truth.Truth.assertWithMessage;
import static java.lang.System.getProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.parse.data.Login;
import com.github.parse.data.LoginData;
import com.github.parse.data.SampleFile;
import com.github.parse.data.XmasFifthDay;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataSourceYmlTest {

    @DataProvider
    public Iterator<Object[]> getLoginDataYml () {
        final LoginData loginData = DataSource.parse (LoginData.class);
        final List<Object[]> data = new ArrayList<> ();
        loginData.getLoginData ()
            .forEach (d -> data.add (new Object[] { d }));
        return data.iterator ();
    }


    @Test (dataProvider = "testData")
    public void readingYamlFileTest (final String doe, final String ray, final float pi, final int frenchHens,
        final String[] callingBirds, final XmasFifthDay xmasFifthDay) {

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
    }


    @DataProvider
    public Iterator<Object[]> testData () {
        final SampleFile testData = DataSource.parse (SampleFile.class);
        final List<Object[]> data = new ArrayList<> ();
        data.add (new Object[] { testData.getDoe (), testData.getRay (), testData.getPi (), testData.getFrenchhens (),
            testData.getCallingBirds (), testData.getXmasFifthDay () });
        return data.iterator ();
    }

    /**
     * @param login Login data

     */
    @Test (dataProvider = "getLoginDataYml")
    public void testYmlDataSource (final Login login) {
        assertWithMessage ("User Name").that (login.getUserName ())
            .isNotEmpty ();
        assertWithMessage ("Password").that (login.getPassword ())
            .isNotEmpty ();
        assertWithMessage ("Path").that (login.getPath ())
            .isEqualTo (getProperty ("user.dir"));
    }
}