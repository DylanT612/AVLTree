/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 10/5/2024

Author: Dylan Theis
Date: Fall 2024
Class: CSC420
Project: ProcessScheduler
Description: ProcessScheduler adds process's from a file, prints loaded process's, displays them by level,
displays completion and results.
*/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessScheduler {
    public static void main(String[] args) {
        System.out.println("Dylan Theis - theisd@csp.edu");
        System.out.println("I certify that this is my own work.");
        System.out.println();

        // Create new AVLTree, assigning root as null and creating a new arraylist for process's
        AVLTree processTree = new AVLTree();
        AVLNode root = null;
        ArrayList<ProcessInfo> processes = new ArrayList<>();

        // Read the processes from a file
        try (BufferedReader br = new BufferedReader(new FileReader("processListMaster.txt"))) {
            String line;
            // While line is not blank
            while ((line = br.readLine()) != null) {
                // Separate on pipe character
                String[] parts = line.split("\\|");
                // Break into parts assigning characteristics for parts
                String name = parts[0];
                int id = Integer.parseInt(parts[1]);
                int priority = Integer.parseInt(parts[2]);
                int remainingRuntime = Integer.parseInt(parts[3]);

                // Create new ProcessInfo object and insert it into the AVL tree
                ProcessInfo process = new ProcessInfo(name, id, priority, remainingRuntime);
                // Set new root
                root = processTree.insert(root, process);
                // Add process to the arraylist
                processes.add(process);
            }
            // Catch any errors while reading
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // For each process in process's
        for (ProcessInfo process : processes) {
            // Display process characteristics
            System.out.println(String.format("Adding Process Name: %-7s Process Id: %-5d Process Priority: %-5d " +
                            "Process Remaining Runtime: %-5d", process.getProcessName(), process.getProcessId(),
                            process.getProcessPriority(), process.getRemainingTime()));
        }

        // Set root
        processTree.setRoot(root);
        System.out.println();

        // For each process in processes
        for (ProcessInfo process : processes) {
            // Print process
            System.out.println(String.format("Process Name: %-7s Process Id: %-5d Process Priority: %-5d " +
                            "Process Remaining Runtime: %-5d", process.getProcessName(), process.getProcessId(),
                            process.getProcessPriority(), process.getRemainingTime()));
        }
        System.out.println();

        // Print level order traversal
        processTree.levelOrderTraversal(root);
        System.out.println();

        // Print process completion for each process
        int currentTime = 0;
        for (ProcessInfo process : processes) {
            currentTime += process.getRemainingTime();
            System.out.println("Process has completed: " + process.getProcessName() +
                    " - " + process.getProcessId());
        }

        // Print results
        System.out.println();
        System.out.println("Results >");
        currentTime = 0;
        for (ProcessInfo process : processes) {
            currentTime += process.getRemainingTime();
            System.out.println(String.format("Process Name: %-7s Process Priority: %-5d " +
                            "Completion Time: %-5d", process.getProcessName(), process.getProcessPriority(),
                    currentTime));
        }
    }
}
