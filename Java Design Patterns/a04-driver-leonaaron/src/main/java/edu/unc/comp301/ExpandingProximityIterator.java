package edu.unc.comp301;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
  private Iterable<Driver> driverPool;
  private Iterator<Driver> driverIterator;
  private Position clientPosition;
  private int expansionStep;
  private int lowerBound;
  private int upperBound;
  private int remainingDrivers = 0;

  public ExpandingProximityIterator(Iterable<Driver> driverPool, Position
          clientPosition, int expansionStep) {
    if (driverPool == null || clientPosition == null) {
      throw new IllegalArgumentException();
    }

    this.driverPool = driverPool;
    this.driverIterator = driverPool.iterator();
    this.clientPosition = clientPosition;
    this.expansionStep = expansionStep;
    this.lowerBound = -1;
    this.upperBound = 1;

    for (Driver d : driverPool) {
      this.remainingDrivers++;
    }
  }

  @Override
  public boolean hasNext() {
    return this.remainingDrivers > 0;
  }

  @Override
  public Driver next() {
    if (hasNext()) {
      while (true) {
        while (driverIterator.hasNext()) {
          Driver d = driverIterator.next();
          int dist = d.getVehicle().getPosition().getManhattanDistanceTo(clientPosition);
          if (dist > lowerBound && dist <= upperBound) {
            remainingDrivers--;
            return d;
          }
        }
        lowerBound = upperBound;
        upperBound += expansionStep;
        driverIterator = driverPool.iterator();
      }
    } else {
      throw new NoSuchElementException();
    }
  }

  //  private final Iterable<Driver> driverPool;
  //  private int totalDrivers;
  //
  //  private final Position clientPosition;
  //  private final int expansionStep; // Amount to increase range by at every iteration
  //
  //  private Iterator<Driver> driverIterator;
  //
  //  // Define bounds for iteration
  //  private int maxProximity;
  //  private int minProximity;
  //
  //  private int currDriverCount;
  //
  //  public ExpandingProximityIterator(
  //      Iterable<Driver> driverPool, Position clientPosition, int expansionStep) {
  //
  //    if (driverPool == null) {
  //      throw new IllegalArgumentException("null driverPool in constructor");
  //    }
  //
  //    this.driverPool = driverPool;
  //
  //    // Count total drivers to determine when hasNext() false
  //
  //    for (Driver d : driverPool) {
  //      this.totalDrivers++;
  //    }
  //
  //    if (clientPosition == null) {
  //      throw new IllegalArgumentException("null clientPosition in constructor");
  //    }
  //
  //    this.clientPosition = clientPosition;
  //    this.expansionStep = expansionStep;
  //
  //    this.driverIterator = driverPool.iterator();
  //
  //    this.maxProximity = 1;
  //    this.minProximity = -1;
  //
  //    this.currDriverCount = 0;
  //  }
  //
  //  @Override
  //  public boolean hasNext() {
  //    return (this.currDriverCount < this.totalDrivers);
  //  }
  //
  //  @Override
  //  public Driver next() {
  //    if (hasNext()) {
  //      while (hasNext()) {
  //        while (this.driverIterator.hasNext()) {
  //          Driver currDriver = this.driverIterator.next();
  //          int currManhattanPosition =
  //              this.clientPosition.getManhattanDistanceTo(currDriver.getVehicle().getPosition());
  //
  //          if (currManhattanPosition > this.minProximity
  //              && currManhattanPosition <= this.maxProximity) {
  //            // Increment before return @TODO
  //            this.currDriverCount++;
  //            return currDriver;
  //          }
  //        }
  //
  //        // When we reach end of current iterator, modify both min and max proximity and
  //        // driverIterator
  //        this.minProximity = this.maxProximity;
  //        this.maxProximity += this.expansionStep;
  //        this.driverIterator = driverPool.iterator();
  //      }
  //    } else {
  //      throw new NoSuchElementException();
  //    }
  //    return null;
  //  }
}
