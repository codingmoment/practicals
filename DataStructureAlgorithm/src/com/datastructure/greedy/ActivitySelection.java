package com.datastructure.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Greedy algorithm to select maximum activities that a user can perform. User can perform only single activity at a time.
 */
public class ActivitySelection {

  /**
   * Test function
   */
  public static void main(String[] args) {
    ActivitySelection activitySelection = new ActivitySelection();

    List<Activity> activities = new ArrayList<>();
    activities.add(new Activity("A", 0, 6));
    activities.add(new Activity("B", 1, 2));
    activities.add(new Activity("C", 1, 6));
    activities.add(new Activity("D", 2, 3));
    activities.add(new Activity("E", 4, 6));
    activities.add(new Activity("F", 8, 10));
    activities.add(new Activity("G", 9, 11));

    List<Activity> maxDoableActivities = activitySelection.maxDoableActivities(activities);

    for (Activity activity : maxDoableActivities) {
      System.out.println(activity);
    }
  }

  /**
   * Main algorithm
   */
  public List<Activity> maxDoableActivities(List<Activity> activities) {
    List<Activity> selectedActivities = new ArrayList<>();

    // Sort the activities by end time
    Collections.sort(activities, new ActivityComparatorByEndTime());

    int lastEndTime = 0;

    // Traverse the activities
    for (Activity activity : activities) {
      // If the end time of last activity is less than or equal to current activity's start time
      // Then select the current activity
      if (lastEndTime <= activity.getStartTime()) {
        selectedActivities.add(activity);
        lastEndTime = activity.getEndTime();
      }
    }

    return selectedActivities;
  }

  /**
   * Activity domain
   */
  private static class Activity {
    private String activityName;
    private int startTime;
    private int endTime;

    public Activity(String activityName, int startTime, int endTime) {
      this.activityName = activityName;
      this.startTime = startTime;
      this.endTime = endTime;
    }

    public int getStartTime() {
      return startTime;
    }

    public int getEndTime() {
      return endTime;
    }

    @Override
    public String toString() {
      return activityName + "[" + startTime + "-" + endTime + "]";
    }
  }

  /**
   * Comparator to compare activities by end time
   */
  private static class ActivityComparatorByEndTime implements Comparator<Activity> {
    @Override
    public int compare(Activity a1, Activity a2) {
      return a1.endTime - a2.endTime;
    }

  }
}
