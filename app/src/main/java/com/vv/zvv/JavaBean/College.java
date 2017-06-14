package com.vv.zvv.JavaBean;

import java.util.List;

/**
 * Created by zvv on 2017/6/7 10:24.
 */
public class College {
    String collegeName;
    List<Classes> classList;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<Classes> getClassList() {
        return classList;
    }

    public void setClassList(List<Classes> classList) {
        this.classList = classList;
    }
}
