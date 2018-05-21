package com.afordev.whentodo;

/**
 * Created by penguo on 2018-05-20.
 */
public class DataWhen {
    private String title;
    private int id;
    private String addDate, recentDate;

    public DataWhen(int id, String title, String addDate, String recentDate){
        this.id = id;
        this.title = title;
        this.addDate = addDate;
        this.recentDate = recentDate;
    }

    public int getId() {
        return id;
    }

    public String getAddDate() {
        return addDate;
    }

    public String getRecentDate() {
        return recentDate;
    }

    public String getTitle() {
        return title;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecentDate(String recentDate) {
        this.recentDate = recentDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
