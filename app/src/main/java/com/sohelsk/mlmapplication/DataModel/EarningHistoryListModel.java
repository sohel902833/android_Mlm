package com.sohelsk.mlmapplication.DataModel;

import java.util.List;

public class EarningHistoryListModel {
    List<EarningHistoryModel> incomeHistory=null;

    public EarningHistoryListModel(List<EarningHistoryModel> incomeHistory) {
        this.incomeHistory = incomeHistory;
    }

    public List<EarningHistoryModel> getIncomeHistory() {
        return incomeHistory;
    }

    public void setIncomeHistory(List<EarningHistoryModel> incomeHistory) {
        this.incomeHistory = incomeHistory;
    }
}
