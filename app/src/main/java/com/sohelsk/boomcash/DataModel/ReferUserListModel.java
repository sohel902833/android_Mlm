package com.sohelsk.boomcash.DataModel;

import java.util.List;

public class ReferUserListModel {
    List<ReferUserModel> users=null;

    public ReferUserListModel(List<ReferUserModel> users) {
        this.users = users;
    }

    public List<ReferUserModel> getUsers() {
        return users;
    }

    public void setUsers(List<ReferUserModel> users) {
        this.users = users;
    }



}
