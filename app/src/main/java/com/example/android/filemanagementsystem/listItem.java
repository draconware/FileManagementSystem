package com.example.android.filemanagementsystem;

public class listItem {
    private String fileid,filename,filedepartment,fileholder;

    public listItem(String fileid, String filename, String filedepartment, String fileholder) {
        this.fileid = fileid;
        this.filename = filename;
        this.filedepartment = filedepartment;
        this.fileholder = fileholder;
    }

    public String getFileid() {
        return fileid;
    }

    public String getFilename() {
        return filename;
    }

    public String getFiledepartment() {
        return filedepartment;
    }

    public String getFileholder() {
        return fileholder;
    }
}
