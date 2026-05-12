package edu.unc.comp301;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterator<Driver>> driverIterators = new ArrayList<>();
    private int poolIndex = 0;
    private int remainingDrivers = 0;
    private boolean forward = true;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driverPools) {
        for (Iterable<Driver> d : driverPools) {
            this.driverIterators.add(d.iterator());
            for (Driver driver : d) {
                this.remainingDrivers++;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.remainingDrivers > 0;
    }

    @Override
    public Driver next() {
        if (hasNext()) {
            while (!driverIterators.get(poolIndex).hasNext()) {
                getNextPool();
            }
            Driver d = driverIterators.get(poolIndex).next();
            getNextPool();
            this.remainingDrivers--;
            return d;

        } else {
            throw new NoSuchElementException();
        }
    }

    public void getNextPool() {
        if (forward) {
            this.poolIndex++;
        } else {
            this.poolIndex--;
        }

        if (poolIndex >= driverIterators.size()) {
            poolIndex = driverIterators.size() -1;
            forward = false;
        }
        if (poolIndex < 0) {
            poolIndex = 0;
            forward = true;
        }
    }

//  private List<Iterator<Driver>> driverIterators = new ArrayList<>();
//  private int poolIndex = 0;
//  private boolean forward = true;
//  private int totalDrivers;
//
//  public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driverPools) {
//
//    for (Iterable<Driver> drivers : driverPools) {
//      this.driverIterators.add(drivers.iterator());
//      for (Driver driver : drivers) {
//        this.totalDrivers++;
//      }
//    }
//  }
//
//  @Override
//  public boolean hasNext() {
//
//    return !(totalDrivers <= 0);
//  }
//
//  @Override
//  public Driver next() {
//    if (hasNext()) {
//      while (!driverIterators.get(poolIndex).hasNext()) {
//        getNextDriver();
//      }
//      Driver tempDriver = driverIterators.get(poolIndex).next();
//      getNextDriver();
//      totalDrivers--;
//      return tempDriver;
//    } else {
//      throw new NoSuchElementException();
//    }
//  }
//
//  public void getNextDriver() {
//    if (forward) {
//      poolIndex++;
//    } else {
//      poolIndex--;
//    }
//    if (poolIndex == driverIterators.size()) {
//      poolIndex = driverIterators.size() - 1;
//      forward = false;
//    }
//    if (poolIndex < 0) {
//      poolIndex = 0;
//      forward = true;
//    }
//  }
}
