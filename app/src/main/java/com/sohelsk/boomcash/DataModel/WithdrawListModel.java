package com.sohelsk.boomcash.DataModel;

import java.util.List;

public class WithdrawListModel {
    List<WithdrawModel> withdrawList=null;

    public WithdrawListModel(List<WithdrawModel> withdrawList) {
        this.withdrawList = withdrawList;
    }

    public List<WithdrawModel> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(List<WithdrawModel> withdrawList) {
        this.withdrawList = withdrawList;
    }
}
