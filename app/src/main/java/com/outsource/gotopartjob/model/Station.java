package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName(value = "index")
    private int index; //정류장 순번

    @SerializedName(value = "stationID")
    private int stationID; //정류장 ID

    @SerializedName(value = "stationName")
    private String stationName; //정류장 명칭

    @SerializedName(value = "x")
    private String x; //정류장 X좌표

    public Station() {
    }

    public Station(int index, int stationID, String stationName, String x, String y) {
        this.index = index;
        this.stationID = stationID;
        this.stationName = stationName;
        this.x = x;
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @SerializedName(value = "y")
    private String y; //정류장 y좌표
}
