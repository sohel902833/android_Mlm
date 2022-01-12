package com.sohelsk.boomcash.DataModel;

public class SettingModel {

    String rechargeAddress,helpCenter="";

    public SettingModel(String rechargeAddress, String helpCenter) {
        this.rechargeAddress = rechargeAddress;
        this.helpCenter = helpCenter;
    }

    public String getRechargeAddress() {
        return rechargeAddress;
    }

    public void setRechargeAddress(String rechargeAddress) {
        this.rechargeAddress = rechargeAddress;
    }

    public String getHelpCenter() {
        return helpCenter;
    }

    public void setHelpCenter(String helpCenter) {
        this.helpCenter = helpCenter;
    }
}
