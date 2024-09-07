package com.example.demo.suggest;

public class Suggestion {

    private String gadget;

    private int hoursGuaranteed;

    public Suggestion(String gadget, int hoursGuaranteed) {
        this.gadget = gadget;
        this.hoursGuaranteed = hoursGuaranteed;
    }

    public Suggestion() {
    }

    public String getGadget() {
        return gadget;
    }

    public void setGadget(String gadget) {
        this.gadget = gadget;
    }

    public int getHoursGuaranteed() {
        return hoursGuaranteed;
    }

    public void setHoursGuaranteed(int hoursGuaranteed) {
        this.hoursGuaranteed = hoursGuaranteed;
    }
}
