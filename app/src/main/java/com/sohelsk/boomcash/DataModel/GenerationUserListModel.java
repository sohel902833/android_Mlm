package com.sohelsk.boomcash.DataModel;

import java.util.List;

public class GenerationUserListModel {
    List<ReferUserModel> firstGen=null;
    List<ReferUserModel> secondGen=null;
    List<ReferUserModel> thirdGen=null;

    public GenerationUserListModel(){}
    public GenerationUserListModel(List<ReferUserModel> firstGen, List<ReferUserModel> secondGen, List<ReferUserModel> thirdGen) {
        this.firstGen = firstGen;
        this.secondGen = secondGen;
        this.thirdGen = thirdGen;
    }
    public List<ReferUserModel> getFirstGen() {
        return firstGen;
    }

    public void setFirstGen(List<ReferUserModel> firstGen) {
        this.firstGen = firstGen;
    }

    public List<ReferUserModel> getSecondGen() {
        return secondGen;
    }

    public void setSecondGen(List<ReferUserModel> secondGen) {
        this.secondGen = secondGen;
    }

    public List<ReferUserModel> getThirdGen() {
        return thirdGen;
    }

    public void setThirdGen(List<ReferUserModel> thirdGen) {
        this.thirdGen = thirdGen;
    }
}
