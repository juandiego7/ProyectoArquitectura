package com.udea.prestamodispositivos.dto;

import java.io.Serializable;

/**
 *
 * @author Juan Diego
 */
public class DeviceId implements Serializable {

    private String code;
    private String copy;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

}
