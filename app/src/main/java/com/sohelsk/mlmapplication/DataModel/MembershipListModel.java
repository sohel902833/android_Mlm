package com.sohelsk.mlmapplication.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MembershipListModel {

        @SerializedName("membership")
        @Expose
        private List<CurrentMembership> membership = null;

        public List<CurrentMembership> getMembership() {
            return membership;
        }
        public void setMembership(List<CurrentMembership> membership) {
            this.membership = membership;
        }
}
