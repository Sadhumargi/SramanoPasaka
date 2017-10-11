package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import com.j256.ormlite.field.DatabaseField;

public class OfflineTable {

    @DatabaseField(id = true)
    public String id;

    @DatabaseField
    public String data;
}