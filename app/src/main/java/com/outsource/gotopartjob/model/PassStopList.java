package com.outsource.gotopartjob.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PassStopList {

    @SerializedName(value = "stations")
    private List<Station> stations; //정류장 정보 그룹노드

    public PassStopList() {
    }

    public PassStopList(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
