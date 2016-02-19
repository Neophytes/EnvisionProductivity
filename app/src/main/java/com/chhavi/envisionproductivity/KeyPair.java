package com.chhavi.envisionproductivity;

import java.io.Serializable;

/**
 * Created by chhavi on 20/2/16.
 */
public class KeyPair implements Serializable{
    String name;

    public KeyPair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;
}
