package com.example.android.filemanagementsystem;

import java.util.ArrayList;

public class FileItems {
    private String mName;
    private boolean mOnline;

    public FileItems(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static ArrayList<FileItems> createContactsList(int numContacts) {
        ArrayList<FileItems> fileItems = new ArrayList<FileItems>();

        for (int i = 1; i <= numContacts; i++) {
            fileItems.add(new FileItems("Person " + ++lastContactId, i <= numContacts / 2));
        }

        return fileItems;
    }
}
