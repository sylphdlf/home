package com.dlf.model.po;

import java.util.Date;

public class weather {
    private Long id;

    private String country;

    private String province;

    private String city;

    private String weatherCurrent;

    private String weatherWholeDay;

    private Integer tempCurrent;

    private Integer tempMax;

    private Integer tempMin;

    private Integer humidity;

    private String wind;

    private Date websiteUpdateTime;

    private Date createTime;

    private String remark;

    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getWeatherCurrent() {
        return weatherCurrent;
    }

    public void setWeatherCurrent(String weatherCurrent) {
        this.weatherCurrent = weatherCurrent == null ? null : weatherCurrent.trim();
    }

    public String getWeatherWholeDay() {
        return weatherWholeDay;
    }

    public void setWeatherWholeDay(String weatherWholeDay) {
        this.weatherWholeDay = weatherWholeDay == null ? null : weatherWholeDay.trim();
    }

    public Integer getTempCurrent() {
        return tempCurrent;
    }

    public void setTempCurrent(Integer tempCurrent) {
        this.tempCurrent = tempCurrent;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind == null ? null : wind.trim();
    }

    public Date getWebsiteUpdateTime() {
        return websiteUpdateTime;
    }

    public void setWebsiteUpdateTime(Date websiteUpdateTime) {
        this.websiteUpdateTime = websiteUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}