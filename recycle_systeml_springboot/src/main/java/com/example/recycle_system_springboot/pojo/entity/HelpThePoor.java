package com.example.recycle_system_springboot.pojo.entity;

import java.io.Serializable;

/**
 * help_the_poor
 * @author 
 */
public class HelpThePoor implements Serializable {
    private Integer helpId;

    private String helpName;

    private Double longitude;

    private Double latitude;

    private String helpDetail;

    private String helpStatus;

    private static final long serialVersionUID = 1L;

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getHelpDetail() {
        return helpDetail;
    }

    public void setHelpDetail(String helpDetail) {
        this.helpDetail = helpDetail;
    }

    public String getHelpStatus() {
        return helpStatus;
    }

    public void setHelpStatus(String helpStatus) {
        this.helpStatus = helpStatus;
    }
}