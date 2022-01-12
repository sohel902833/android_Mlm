package com.sohelsk.boomcash.DataModel;

import java.util.List;

public class PackageModelList {
    private List<PackageModel> packageList=null;

    public PackageModelList(List<PackageModel> packageList) {
        this.packageList = packageList;
    }

    public List<PackageModel> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<PackageModel> packageList) {
        this.packageList = packageList;
    }
}
