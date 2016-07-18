package com.ldoblem.giftcardlib.models;

public class Buyer {

  private final String name;
  private final String region;

  private final String address;
  private final String availableDay;

  public Buyer(String name, String region, String address, String availableDay) {
    this.name = name;
    this.region = region;
    this.address = address;
    this.availableDay = availableDay;
  }

  public String getName() {
    return name;
  }

  public String getRegion() {
    return region;
  }

  public String getAddress() {
    return address;
  }

  public String getAvailableDay() {
    return availableDay;
  }
}
