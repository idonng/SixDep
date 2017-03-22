package com.zbt.six.pojo;

import java.util.Date;

public class OptionsRegistrInfo {
    private Long id;

    private Long foruserid;

    private Date realtime;

    private Long optionsid;

    private Integer num;

    private String remark1;

    private String remark2;

    private Long updateuserid;

    private Date updatetime;

    private Long adduserid;

    private Date addtime;

    private Integer isenable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getForuserid() {
        return foruserid;
    }

    public void setForuserid(Long foruserid) {
        this.foruserid = foruserid;
    }

    public Date getRealtime() {
        return realtime;
    }

    public void setRealtime(Date realtime) {
        this.realtime = realtime;
    }

    public Long getOptionsid() {
        return optionsid;
    }

    public void setOptionsid(Long optionsid) {
        this.optionsid = optionsid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public Long getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(Long updateuserid) {
        this.updateuserid = updateuserid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getAdduserid() {
        return adduserid;
    }

    public void setAdduserid(Long adduserid) {
        this.adduserid = adduserid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }
}