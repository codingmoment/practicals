package com.datastructure.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Algorithm to choose the items of limited weight but with maximum value.
 */
public class Knapsack {

  public static void main(String[] args) {
    Item item1 = new Item("Item-1", 6, 6);
    Item item2 = new Item("Item-2", 10, 2);
    Item item3 = new Item("Item-3", 3, 1);
    Item item4 = new Item("Item-4", 5, 8);
    Item item5 = new Item("Item-5", 1, 3);
    Item item6 = new Item("Item-6", 3, 5);

    List<Item> items = Arrays.asList(item1, item2, item3, item4, item5, item6);

    Knapsack knapsack = new Knapsack();
    List<Item> selectedItems = knapsack.chooseItems(items, 10);

    double totalWeight = 0;
    double totalValue = 0;

    for (Item item : selectedItems) {
      System.out.println(item.getName() + " with quantity " + item.getChosenWeight() + " having value " + item.getChosenValue());

      totalWeight += item.getChosenWeight();
      totalValue += item.getChosenValue();
    }

    System.out.println("Total weight - " + totalWeight);
    System.out.println("Total value - " + totalValue);
  }

  public List<Item> chooseItems(List<Item> items, int capacity) {
    List<Item> selectedItems = new ArrayList<>();

    // Sort the items in decreasing order of value per weight
    Collections.sort(items, new ItemComparator());

    // Traverse the sorted items
    for (Item item : items) {
      // How much quantity of current item can be added into capacity ?

      // If the weight of item is less than capacity, then select complete item
      if (item.getWeight() <= capacity) {
        item.setChosenWeight(item.getWeight());
        item.setChosenValue(item.getValue());
        selectedItems.add(item);

        capacity -= item.getChosenWeight();
      }
      // If the weight of the item is greater than the capacity,
      // then we should choose the fraction of the item that fits within capacity
      else {
        item.setChosenWeight(capacity);
        item.setChosenValue(capacity * (1.0 * item.getValue() / item.getWeight()));
        selectedItems.add(item);

        capacity = 0;
        break;
      }
    }

    return selectedItems;
  }

  private static class Item {
    private String name;
    private Integer weight;
    private Integer value;

    private double chosenWeight;
    private double chosenValue;

    public Item(String name, Integer weight, Integer value) {
      this.name = name;
      this.weight = weight;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getWeight() {
      return weight;
    }

    public void setWeight(Integer weight) {
      this.weight = weight;
    }

    public Integer getValue() {
      return value;
    }

    public void setValue(Integer value) {
      this.value = value;
    }

    public double getChosenWeight() {
      return chosenWeight;
    }

    public void setChosenWeight(double chosenWeight) {
      this.chosenWeight = chosenWeight;
    }

    public double getChosenValue() {
      return chosenValue;
    }

    public void setChosenValue(double chosenValue) {
      this.chosenValue = chosenValue;
    }
  }

  private class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item i1, Item i2) {
      double i1Density = (1.0 * i1.getValue()) / i1.getWeight();
      double i2Density = (1.0 * i2.getValue()) / i2.getWeight();
      if (i1Density > i2Density) {
        return -1;
      }
      return 1;
    }
  }
}
