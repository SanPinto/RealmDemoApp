package com.realm.demo.realmmvpdemoapp.person.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sangeetha on 20/7/17.
 */

public class Person extends RealmObject{
    @PrimaryKey
    public int id;
    public String name;
    public String email;
    public String address;
    public int age;
}
