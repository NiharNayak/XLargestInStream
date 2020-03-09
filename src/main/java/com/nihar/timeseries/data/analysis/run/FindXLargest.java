package com.nihar.timeseries.data.analysis.run;

import com.nihar.timeseries.data.analysis.bin.UniqueIDAndValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FindXLargest {

  private Integer xValue;
  private Queue<UniqueIDAndValue> minHeap;

  private static void usage() {
    System.out.println(
        "Usage: \n java -cp target/find-x-largets-stream.jar:. com.nihar.timeseries.data.analysis.run.FindXLargest <x-value-to-find-largest-values> <input-file-fully-qualified-path (optional)>");
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      usage();
      System.exit(1);
    }
    try {
      FindXLargest findXLargest = new FindXLargest();
      findXLargest.xValue = Integer.parseInt(args[0]);
      findXLargest.minHeap =
          new PriorityQueue<>(findXLargest.xValue); // Extra Space Complexity O(xValue)
      Scanner inputScanner;
      if (args.length > 1) {
        // Scanner will read the file content as line by line. should be fine for large files.
        inputScanner = new Scanner(new File(args[1]));
        while (inputScanner.hasNext()) {
          findXLargest.addOrUpdateToHeap(inputScanner.nextLine());
        }
      } else {
        System.out.println("No input file provided , please enter input in console");
        boolean run = true;
        while (run) {
          inputScanner = new Scanner(System.in);
          if (inputScanner.hasNext()) {
            findXLargest.addOrUpdateToHeap(inputScanner.nextLine());
          }
          System.out.println("Do you want to continue ? [y/n]");
          boolean yesOrNo = true;
          while (yesOrNo) {
            inputScanner = new Scanner(System.in);
            if (inputScanner.hasNext()) {
              String input = inputScanner.nextLine();
              if (input.equalsIgnoreCase("n")) {
                run = false;
                yesOrNo = false;
              } else if (input.equalsIgnoreCase("y")) {
                yesOrNo = false;
              } else {
                System.out.println("Please enter valid input ? [y/n]");
              }
            }
          }
        }
      }
      System.out.println(
          String.format("Ids having %s largest value are bellow:", findXLargest.xValue));
      while (findXLargest.minHeap.size() > 0) {
        System.out.println(findXLargest.minHeap.poll().getId());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used for adding the element to fixed size Heap if the new value is greater than
   * or equal. Else the no need to add , because new-Value is less than the minimum of queue-
   * elements. The queue element (fixed size = xValue), always contains x-largest element.
   */
  public void addOrUpdateToHeap(String line) {
    try {
      String[] s = line.split("\\s+", -1);
      if (s.length > 1) {
        String id = s[0];
        int val = Integer.parseInt(s[s.length - 1]);
        UniqueIDAndValue newValue = new UniqueIDAndValue(id, val);
        if (minHeap.size() == xValue) {
          UniqueIDAndValue largest = minHeap.peek(); // Time Complexity - O(1)
          if (largest != null) {
            if (val >= largest.getValue()) {
              // Check if the ID is already exists.
              // (Do not add the duplicate id, but value can be duplicate)
              if (minHeap.contains(newValue)) { // Time Complexity - O(xValue) - linear
                System.err.println(String.format("ID: '%s' can not be duplicate ", id));
              } else {
                minHeap.poll(); // Time Complexity - O(1)
                minHeap.add(newValue); // Time Complexity - O(log(xValue))
              }
            } else {
              System.out.println(
                  String.format(
                      "Value: %d is less than the minimum value: %d in the queue, so do noting.",
                      val, largest.getValue()));
            }
          } else {
            System.err.println("Value in Queue can not be null.");
          }
        } else {
          minHeap.add(newValue); // Time Complexity - O(log(xValue))
        }
      } else {
        System.err.println("Not a valid record " + line);
      }
    } catch (NumberFormatException e) {
      System.err.println(e.getMessage());
    }
  }
}
