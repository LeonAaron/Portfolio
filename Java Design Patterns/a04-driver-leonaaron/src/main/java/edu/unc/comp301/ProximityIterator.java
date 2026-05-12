package edu.unc.comp301;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
  private Iterator<Driver> driverIterator;
  private Position clientPosition;
  private int proximityRange;

  private Driver nextDriver;


  public ProximityIterator(Iterable<Driver> driverPool, Position clientPosition,
                           int proximityRange) {
    if (driverPool == null || clientPosition == null) {
      throw new IllegalArgumentException();
    }

    this.driverIterator = driverPool.iterator();
    this.clientPosition = clientPosition;
    this.proximityRange = proximityRange;

    this.nextDriver = null;
  }

  @Override
  public boolean hasNext() {
    loadNextDriver();
    return nextDriver != null;
  }

  @Override
  public Driver next() {
    if (hasNext()) {
      Driver d = nextDriver;
      nextDriver = null;
      return d;
    } else {
      throw new NoSuchElementException();
    }
  }

  public void loadNextDriver() {
    if (nextDriver != null) {
      return;
    }

    while (driverIterator.hasNext()) {
      Driver d = driverIterator.next();
      int dist = d.getVehicle().getPosition().getManhattanDistanceTo(clientPosition);
      if (dist <= proximityRange) {
        this.nextDriver = d;
        return;
      }
    }
  }
  //  private final Iterable<Driver> driverPool;
  //  private Position clientPosition;
  //
  //  // Range of drivers to loop through relative to clientPosition
  //  private int proximityRange;
  //
  //  private Iterator<Driver> driverIterator;
  //  private Driver nextDriver;
  //
  //  public ProximityIterator(
  //      Iterable<Driver> driverPool, Position clientPosition, int proximityRange) {
  //
  //    if (driverPool == null) {
  //      throw new IllegalArgumentException("driver pool null in constructor");
  //    }
  //
  //    this.driverPool = driverPool;
  //
  //    if (clientPosition == null) {
  //      throw new IllegalArgumentException("client position null in constructor");
  //    }
  //
  //    this.clientPosition = clientPosition;
  //    this.proximityRange = proximityRange;
  //
  //    this.driverIterator = driverPool.iterator();
  //    this.nextDriver = null;
  //  }
  //
  //  @Override
  //  public boolean hasNext() {
  //    this.loadNextDriverInRange();
  //    return this.nextDriver != null;
  //  }
  //
  //  @Override
  //  public Driver next() {
  //    if (hasNext()) {
  //      Driver driver = this.nextDriver;
  //      this.nextDriver = null;
  //      return driver;
  //    } else {
  //      throw new NoSuchElementException();
  //    }
  //  }
  //
  //  private void loadNextDriverInRange() {
  //    while (driverIterator.hasNext() && this.nextDriver == null) {
  //      Driver currDriver = driverIterator.next();
  //      int currManhattanDistance =
  //          clientPosition.getManhattanDistanceTo(currDriver.getVehicle().getPosition());
  //
  //      if (currManhattanDistance <= this.proximityRange) {
  //        this.nextDriver = currDriver;
  //      }
  //    }
  //  }
}

// Implement Iterator<Driver>
/*
Check for illegel argument exception, init iterator, next, range, position
@Override hasNext() -> load next and check if nextDriver null
create private loadNext() -> if nextDriver null abort, while iterator.hasNext():
  get next driver and check if it is withing proximityRange
@Override next-> if hasNext() { then get nextDriver, set to null so get works, and return next

 */

