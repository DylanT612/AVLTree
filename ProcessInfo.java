/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 10/5/2024

Author: Dylan Theis
Date: Fall 2024
Class: CSC420
Project: ProcessScheduler
Description: ProcessInfo class for the ProcessScheduler. Class holding the process's information.
*/


// Process Info class for each new process
public class ProcessInfo {
    // Variables for name, id, Priority, and Time
    private String processName;
    private int processId;
    private int processPriority;
    private int remainingTime;

    // Basic constructor
    public ProcessInfo(String processName, int processId, int processPriority, int remainingTime) {
        this.processName = processName;
        this.processId = processId;
        this.processPriority = processPriority;
        this.remainingTime = remainingTime;
    }

    // Setters and Getters for variables
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getProcessPriority() {
        return processPriority;
    }

    public void setProcessPriority(int processPriority) {
        this.processPriority = processPriority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

}
