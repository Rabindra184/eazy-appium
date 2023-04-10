

package com.github.parse.data;


import com.github.parse.annotation.DataFile;
import lombok.Data;


@Data
@DataFile (fileName = "config.properties", folderPath = "src/test/resources/properties_file")
public class PropertiesData {
    private int    port;
    private String testurl;
    private String username;
}