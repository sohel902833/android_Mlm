package com.sohelsk.boomcash.DataModel;

public class JobViewModel {
    int jobId;
    long time;
    public JobViewModel(int jobId, long time) {
        this.jobId = jobId;
        this.time = time;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
