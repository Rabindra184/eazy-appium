

package com.github.parse.data;


import com.github.parse.annotation.DataFile;
import lombok.Data;


@Data
@DataFile
public class JsonTestData {
    private XmasFifthDay XmasFifthDay;
    private String[]     callingBirds;
    private String       doe;
    private int          frenchHens;
    private float        pi;
    private String       ray;
    private String       user;
    private boolean      xmas;
}