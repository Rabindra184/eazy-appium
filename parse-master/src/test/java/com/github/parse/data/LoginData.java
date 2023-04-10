

package com.github.parse.data;

import java.util.List;

import com.github.parse.annotation.DataFile;
import lombok.Data;


@DataFile
@Data
public class LoginData {
    private List<Login> loginData;
}