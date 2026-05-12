package edu.unc.comp301.a03connectcarolina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Some documentatoin, can use autcomplete
 * @param 
 * @return
 *
 *
 */



public class Adept {
  // Map String date to String event
  private Map<String, String> cleEvents;

  public Adept() {
    this.cleEvents = new HashMap<>();
    this.initCalendar();
  }

  public void initCalendar() {
    this.cleEvents.put("August 18", "Fall FDOC");
    this.cleEvents.put("September 05", "Honor Code Workshop");
    this.cleEvents.put("October 12", "Leadership Summit");
    this.cleEvents.put("November 03", "Community Service Night");
    this.cleEvents.put("December 01", "Study Skills Clinic");

    // Added
    this.cleEvents.put("December 15", "Pizza Ping Pong");
    this.cleEvents.put("May 5", "Bowling in the Pit");
  }

  // Maybe include Exceptions in constructor
  public void validateScan(String eventName, List<String> scannedEvents)
      throws CLEEventNotFoundException, CLEAlreadyScannedException {
    boolean eventInMap = false;
    for (String event : this.cleEvents.values()) {
      if (event.equals(eventName)) {
        eventInMap = true;
        break;
      }
    }
    // Specify order
    if (!eventInMap) {
      throw new CLEEventNotFoundException("event not found in map");
    }

    if (scannedEvents.contains(eventName)) {
      throw new CLEAlreadyScannedException();
    }
  }

  public List<String> getCLECredits(String eventName, List<String> scannedEvents) {
    if (scannedEvents == null) {
      return null;
    }
    try {
      this.validateScan(eventName, scannedEvents);
      scannedEvents.add(eventName);
      System.out.println("Thank you for attending!");
    } catch (CLEEventNotFoundException | CLEAlreadyScannedException e) {
      System.out.println("Error scanning event: " + e.getMessage());
    } finally {
      System.out.println("\"CLE credit processed for: " + eventName);
    }
    return scannedEvents;
  }
}
