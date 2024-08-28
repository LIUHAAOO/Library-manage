package org.liu.libraryElement;

import lombok.Data;

@Data
public class Student {
    int sid;
    String sname;
    Long snum;
    private String password;
}
