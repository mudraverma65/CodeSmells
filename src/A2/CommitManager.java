package A2;

import java.util.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import Sort.BubbleSort;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class CommitManager extends Components {

    public List<Commit> allC = new ArrayList<Commit>();

    //int matrix[][];
    public GraphM g1 = new GraphM();

    public Set<Set<String>> components = new LinkedHashSet<>();

    public Time t1;

    public CommitManager(){

    }

    public void addCommit( String developer, int commitTime, String Task, Set<String> commitFiles) throws IllegalArgumentException{
        Character first = Task.charAt(0);

        //long parameter list
        if(g1.Commit(developer, commitTime, Task, commitFiles, first, allC, true) == true){
            System.out.println("A2.Commit created");
        }
        else if(g1.Commit(developer, commitTime, Task, commitFiles, first, allC, true) == false){
            throw new IllegalArgumentException();
        }
    }

    public boolean componentMinimum(int threshold){
        try{
            if(threshold>0){
                t1.threshold = threshold;
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    public Set<Set<String>> softwareComponents(){
        try{
            List<Commit> currentWindow = new ArrayList<>();
            if(t1.endTime!=0){
                for(Commit commitWindow: allC){
                    if(commitWindow.commitTime>=t1.startTime && commitWindow.commitTime<=t1.endTime){
                        currentWindow.add(commitWindow);
                    }
                }
            } else if (t1.endTime == 0) {
                currentWindow = allC;
            }
            Set<String> vertices = new LinkedHashSet<>();
            for(Commit currentC : currentWindow){
                Iterator<String> files = currentC.commitFiles.iterator();
                while(files.hasNext()){
                    vertices.add(files.next());
                }
            }
            List<String> allvertices = new ArrayList<>(vertices);
            Set<String> individualSet = new LinkedHashSet<>();
            Set<String> vertexA = new LinkedHashSet<>();
            Set<String> vertices1 = new LinkedHashSet<>();
            for(Commit currentC : currentWindow){
                Iterator<String> files = currentC.commitFiles.iterator();
                while(files.hasNext()){
                    vertices.add(files.next());
                }
            }
            List<String> vertexAll = new ArrayList<>(vertices1);
            int verticesAll = vertexAll.size();
            int matrix[][] = new int[verticesAll][verticesAll];
            for(int i =0; i<verticesAll; i++) {
                for (int j = 0; j < verticesAll; j++) {
                    for (Commit current : currentWindow) {
                        if (current.commitFiles.contains(vertexAll.get(i)) && current.commitFiles.contains(vertexAll.get(j))) {
                            matrix[i][j] = matrix[i][j]+ 1;
                        }
                    }
                }
            }
            for(int i = 0; i<matrix.length; i++){
                for(int j = 0; j<matrix.length; j++){
                    if(matrix[i][j] >= t1.threshold){
                        if(individualSet.contains(allvertices.get(i))) {
                            individualSet.add(allvertices.get(j));
                        }
                        else {
                            if(individualSet.isEmpty() == true){
                                individualSet.add(allvertices.get(i));
                            }
                            else{
                                components.add(individualSet);
                                individualSet = new LinkedHashSet<>();
                                individualSet.add(allvertices.get(i));
                            }
                        }
                    }
                    else if(j == matrix.length-1 && individualSet.contains(allvertices.get(i))==false){
                        components.add(individualSet);
                        individualSet = new LinkedHashSet<>();
                        individualSet.add(allvertices.get(i));
                    }
                    else if(i ==matrix.length-1 && j == matrix.length-1){
                        components.add(individualSet);
                        individualSet = new LinkedHashSet<>();
                        individualSet.add(allvertices.get(i));
                    }
                }
            }
            if(!individualSet.isEmpty()){
                components.add((individualSet));
            }
            return components;
        }
        catch (Exception e){
            return null;
        }
    }

    public Set<String> repetitionInBugs(int threshold) {

       List<Commit> currentWindow = null;
       if (t1.endTime != 0) {
           for (Commit commitWindow : allC) {
               if (commitWindow.commitTime >= t1.startTime && commitWindow.commitTime <= t1.endTime) {
                   currentWindow.add(commitWindow);
               }
           }
       } else if (t1.endTime == 0) {
           currentWindow = allC;
       }
       Set<String> bugFiles = new LinkedHashSet<>();
       Map<String, Integer> fileFrequency = new LinkedHashMap<>();
       Map<String, Map<String, Integer>> bugFile = new LinkedHashMap<>();
       //iterating through commits
       for (Commit currentC : currentWindow) {
           //storing the first task
           String TaskInitial = String.valueOf(currentC.Task.charAt(0));
           //checking if it is bug
           if (TaskInitial.equals("B")) {
               //Iterating through the commitFiles
               Iterator<String> it1 = currentC.commitFiles.iterator();
               while (it1.hasNext()) {
                   //Storing the current file
                   String currentFile = it1.next();
                   //If the task already exists just adding the set of files associated with the task.
                   if (bugFile.containsKey(currentC.Task)) {
                       fileFrequency = new LinkedHashMap<>();
                       //storing the occurence of file
                       fileFrequency = bugFile.get(currentC.Task);
                       if (fileFrequency.containsKey(currentFile)) {
                           //Incrementing the value with each file and storing it
                           int frequency = fileFrequency.get(currentFile);
                           fileFrequency.put(currentFile, frequency + 1);
                       } else {
                           //The file does not exist in the map
                           fileFrequency.put(currentFile, 1);
                       }
                   } else {
                       //Add the bug with its frequency table
                       fileFrequency = new LinkedHashMap<>();
                       fileFrequency.put(currentFile, 1);
                       bugFile.put(currentC.Task, fileFrequency);
                   }
               }
           }
           //Storing the keys from the map into the set which will be returned.
           for (String bug : bugFile.keySet()) {
               Map<String, Integer> currentFiles = bugFile.get(bug);
               int maxF = 0;
               for (Integer fileF : currentFiles.values()) {
                   if (maxF < fileF) {
                       maxF = fileF;
                   }
               }
               //Return for only those bugs which have threshold greater.
               if (maxF >= threshold) {
                   bugFiles.add(bug);
               }
           }
       }
       return bugFiles;
   }

    public Set<String> broadFeatures(int threshold){
        //storing the current set of commit files which will be valid in the given time frame.
        List<Commit> currentWindow = new ArrayList<>();
        if(t1.endTime!=0){
            for(Commit commitWindow: allC){
                if(commitWindow.commitTime>=t1.startTime && commitWindow.commitTime<=t1.endTime){
                    currentWindow.add(commitWindow);
                }
            }
        } else if (t1.endTime == 0) {
            currentWindow = allC;
        }
        Set<String> broadFeatures = new LinkedHashSet<>();
        Map<String, Set<String>> file = new LinkedHashMap<>();
        for (Commit currentC : currentWindow) {
            String TaskInitial = String.valueOf(currentC.Task.charAt(0));
            if(TaskInitial.equals("F")){
                if(file.containsKey(currentC.Task)){
                    Set<String> currentFile = file.get(currentC.Task);
                    currentFile.addAll(currentC.commitFiles);
                    file.put(currentC.Task, currentFile);
                }
                else{
                    file.put(currentC.Task, currentC.commitFiles);
                }
            }
        }
        Set<Set<String>> currentC = components;;
        for( Map.Entry<String, Set<String>> currentMap : file.entrySet()){
            String currentKey = currentMap.getKey();
            Set<String> currentValue = currentMap.getValue();
            int count=0;
            for(Set<String> currentComponent: currentC){
                currentValue.retainAll(currentComponent);
                if(currentValue.size()>0){
                    count++;
                }
            }
            if(count==threshold){
                broadFeatures.add(currentMap.getKey());
            }
        }
        return broadFeatures;
    }

    public Set<String>  experts (int threshold){
        List<Commit> currentWindow = new ArrayList<>();
        if(t1.endTime!=0){
            for(Commit commitWindow: allC){
                if(commitWindow.commitTime>=t1.startTime && commitWindow.commitTime<=t1.endTime){
                    currentWindow.add(commitWindow);
                }
            }
        } else if (t1.endTime == 0) {
            currentWindow = allC;
        }
        Set<String> experts = new LinkedHashSet<>();
        Map<String, Set<String>> file = new LinkedHashMap<>();
        for (Commit currentC : currentWindow) {
            if(file.containsKey(currentC.developer)){
                    Set<String> currentFile = file.get(currentC.developer);
                    currentFile.addAll(currentC.commitFiles);
                    file.put(currentC.developer, currentFile);
                }
                else{
                    file.put(currentC.developer, currentC.commitFiles);
                }
            }
        Set<Set<String>> currentC = components;

        for( Map.Entry<String, Set<String>> currentMap : file.entrySet()){
            String currentKey = currentMap.getKey();
            Set<String> currentValue = currentMap.getValue();
            int count=0;
            for(Set<String> currentComponent : currentC){
                //Set<String> currentFiles = currentValue;
                currentValue.retainAll(currentComponent);
                if(currentValue.size()>0){
                    count++;
                }
            }
            if(count==threshold){
                experts.add(currentMap.getKey());
            }
        }
        return experts;

    }

    public List<String> busyClasses (int limit){
        List<String> busyClasses = new ArrayList<>();
        List<Commit> currentWindow = new ArrayList<>();
        if(t1.endTime!=0){
            for(Commit commitWindow: allC){
                if(commitWindow.commitTime>=t1.startTime && commitWindow.commitTime<=t1.endTime){
                    currentWindow.add(commitWindow);
                }
            }
        } else if (t1.endTime == 0) {
            currentWindow = allC;
        }
        Map<String, Integer> fileFrequency = new HashMap<>();
        for (Commit currentC : currentWindow) {
            Iterator<String> it1 = currentC.commitFiles.iterator();
            while (it1.hasNext()) {
                String currentFile = it1.next();
                if(fileFrequency.containsKey(currentFile)){
                    int freq = fileFrequency.get(currentFile);
                    fileFrequency.put(currentFile,freq+1);
                }
                else{
                    fileFrequency.put(currentFile,1);
                }
            }
        }
        Map<String,Integer>sortedFiles = fileFrequency
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        int count=0;
        List<String> valuesH = new ArrayList<>();

        Iterator<Map.Entry<String,Integer>> iterator = sortedFiles.entrySet().iterator();
        while (iterator.hasNext() && count<limit) {
           // Integer counter = iterator.next().getValue();
            String currentFile = iterator.next().getKey();
            valuesH.add(currentFile);
            busyClasses.add(currentFile);
            count++;
        }

        if(busyClasses.isEmpty()==false) {
            String lastFile = busyClasses.get(busyClasses.size() - 1);
            int frequency = sortedFiles.get(lastFile);
            for (Map.Entry<String, Integer> entry : sortedFiles.entrySet()) {
                if (entry.getValue() == frequency) {
                    if (!busyClasses.contains(entry.getKey())) {
                        busyClasses.add(entry.getKey());
                    }
                }
            }
        }
        return busyClasses;
    }

    public int[][] createGraph(List<Commit> allCommits1){
        Set<String> vertexA = new LinkedHashSet<>();
        List<String> vertexAll = g1.getVertex(allCommits1);
        int verticesAll = vertexAll.size();
        int matrix[][] = new int[verticesAll][verticesAll];
        for(int i =0; i<verticesAll; i++) {
            for (int j = 0; j < verticesAll; j++) {
                for (Commit current : allCommits1) {
                    if (current.commitFiles.contains(vertexAll.get(i)) && current.commitFiles.contains(vertexAll.get(j))) {
                        matrix[i][j] = matrix[i][j]+ 1;
                    }
                }
            }
        }
        return matrix;
    }

    public List<Commit> getcommit(List<Commit> allCommits,int startTime, int endTime){
        List<Commit> currentCommits = new ArrayList<>();
        if(endTime!=0){
            for(Commit commitWindow: allCommits){
                if(commitWindow.commitTime>=startTime && commitWindow.commitTime<=endTime){
                    currentCommits.add(commitWindow);
                }
            }
        } else if (endTime == 0) {
            return allCommits;
        }
        return currentCommits;
    }

    public boolean setTimeWindow(int startTime, int endTime) {
        boolean val = true;
        try{
            if(endTime>startTime){
                t1.startTime = startTime;
                t1.endTime = endTime;
            }
        } catch (Exception e) {
            val = false;
        }
        return val;
    }

    public boolean resetCommitTime(Commit c2){
        c2.commitTime = 0;
        return true;
    }

    public boolean checkBugs(Bugs b){
        Set<String> bugFiles = new LinkedHashSet<>();
        bugFiles = b.repetitionInBugs(2);
        return true;
    }

    public boolean setT(Time t1){
        t1.startTime = 0;
        t1.endTime = 0;
        return true;
    }

    public boolean com(Components c1){
        c1.componentMinimum(6);
        return true;
    }

    public int gh(GraphM g1){
        if(g1.hashCode()==0){
            return 1;
        }
        return 0;
    }

    public int check(Time t2) throws Exception{
        if(t2.endTime < 0 && t2.startTime <0){
            return 0;
        }
        return 1;
    }

    public void ver(GraphM g1){
        List<String> vertex = g1.getVertex(allC);
    }

    public boolean bug(Bugs b1){
        Set<String> s1 = b1.repetitionInBugs(20);
        if(s1.isEmpty()){
            return false;
        }
        return true;
    }

    public Bugs create(String description, int severity) {
        Bugs b1 = new Bugs();
        b1.description = description;
        b1.severity = severity;
        b1.isFixed = false;
        return b1;
    }

    public String getDescription(Bugs b1) {
        return b1.description;
    }

    public int getSeverity(Bugs b1) {
        return b1.severity;
    }

    public boolean isFixed(Bugs b1) {
        return b1.isFixed;
    }

    public void markFixed(Bugs b1) {
        b1.isFixed = true;
    }

    public void printDetails(Bugs b1) {
        System.out.println("Bug description: " + b1.description);
        System.out.println("Bug severity: " + b1.severity);
        System.out.println("Bug status: " + (b1.isFixed ? "fixed" : "unfixed"));
    }

    public int getTimeWindowDuration(Time t1) {
        return t1.endTime - t1.startTime;
    }

    public boolean iWTW(int time, Time t1) {
        return (time >= t1.startTime && time <= t1.endTime);
    }

    public void setT(int threshold,Time t1) {
        t1.threshold = threshold;
    }

    public int getT(Time t1) {
        return t1.threshold;
    }

    public boolean isAT(int value,Time t1) {
        return (value > t1.threshold);
    }

    public void pTWindow(Time t1) {
        System.out.println("Time window start time: " + t1.startTime);
        System.out.println("Time window end time: " + t1.endTime);
        System.out.println("Time window duration: " + getTimeWindowDuration(t1));
    }

    public boolean removeC(Commit commit, List<Commit> allCommits){
        return allCommits.remove(commit);
    }

    public int getNuE(GraphM g1){
        int numEdges = 0;
        for(int i=0; i<g1.vertices; i++){
            for(int j=0; j<g1.vertices; j++){
                if(g1.matrix[i][j] == 1){
                    numEdges++;
                }
            }
        }
        return numEdges;
    }

    public int getDV(int vertex,GraphM g1){
        int degree = 0;
        for(int i=0; i<g1.vertices; i++){
            if(g1.matrix[vertex][i] == 1){
                degree++;
            }
        }
        return degree;
    }

    public boolean hasE(int vertex1, int vertex2,GraphM g1){
        return (g1.matrix[vertex1][vertex2] == 1);
    }

    public static int sA(int[] arr) {
        int sum = 0;
        int[] tempArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            tempArr[i] = arr[i] * arr[i];
        }
        for (int i = 0; i < tempArr.length; i++) {
            for (int j = i; j < tempArr.length; j++) {
                if (tempArr[j] < tempArr[i]) {
                    int temp = tempArr[i];
                    tempArr[i] = tempArr[j];
                    tempArr[j] = temp;
                }
            }
        }
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] > 10) {
                sum += tempArr[i];
            }
        }
        return sum;
    }
    public double avgS() {
        double totalSeverity = 0.0;
        int numBugs = 0;
        for (Commit commit : allC) {
            if (commit.Task.charAt(0) == 'B') {
                Bugs bug = new Bugs();
                totalSeverity += bug.severity;
                numBugs++;
            }
        }
        return (numBugs > 0) ? (totalSeverity / numBugs) : 0.0;
    }
    public int mFSeverity() {
        Map<Integer, Integer> freq = new HashMap<>();
        for (Commit commit : allC) {
            if (commit.Task.charAt(0) == 'B') {
                Bugs bug = new Bugs();
                freq.put(bug.severity, freq.getOrDefault(bug.severity, 0) + 1);
            }
        }
        int maxFreq = 0, mostFreqSeverity = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFreqSeverity = entry.getKey();
            }
        }
        return mostFreqSeverity;
    }

    public int[] getSortedArr(){
        BubbleSort sorter = new BubbleSort();
        int[] arr = {41, 12, 456, 28, 3};
        int[] sortedArr = sorter.bubbleS(arr);
        return sortedArr;
    }

}
