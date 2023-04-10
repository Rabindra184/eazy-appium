
package com.github.parse.data;

import com.github.parse.annotation.DataFile;
import lombok.Data;


@Data
@DataFile (folderPath = "src/test/resources/files/xmlfiles", fileName = "testfile.xml")
public class XmlData {
    private String body;
    private String from;
    private String heading;
    private String to;
}