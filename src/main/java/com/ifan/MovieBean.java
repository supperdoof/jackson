package com.ifan;

public class MovieBean {

    private long movie;
    private long rate;
    private long timeStamp;
    private long uid;

    public MovieBean()
    {
    }

    public MovieBean(Long movie,Long rate,Long timeStamp,Long uid)
    {
        this.movie = movie;
        this.rate = rate;
        this.timeStamp = timeStamp;
        this.uid = uid;
    }

    public long getMovie() {
        return movie;
    }

    public long getRate() {
        return rate;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public long getUid() {
        return uid;
    }

    public void setMovie(long movie) {
        this.movie = movie;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "movie@ " + movie + " rate@ " + rate + " timestamp@ " + timeStamp + " uid@ " + uid;
    }
}
