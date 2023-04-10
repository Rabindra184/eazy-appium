
package com.github.parse.data;

import com.github.parse.annotation.DataFile;
import lombok.Data;

/**
 * @author Faisal Khatri
 * @since Aug 29, 2020
 */
@Data
@DataFile (fileName = "SampleFile.yaml")
public class SampleFile {
    private XmasFifthDay XmasFifthDay;
    private String[]     callingBirds;
    private String       doe;
    private int          frenchhens;
    private float        pi;
    private String       ray;
    private boolean      xmas;
}