package A2;

import java.util.*;

public class Bugs extends CommitManager{

    public String description;
    public int severity;
    public boolean isFixed;

    public CommitManager c1 = new CommitManager();
    public Set<String> repetitionInBugs(int threshold) {
        try{
            List<Commit> currentWindow=null;
            if(t1.endTime!=0){
                for(Commit commitWindow: c1.allC){
                    if(commitWindow.commitTime>=t1.startTime && commitWindow.commitTime<=t1.endTime){
                        currentWindow.add(commitWindow);
                    }
                }
            } else if (t1.endTime == 0) {
                currentWindow = c1.allC;
            }
            Set<String> bugFiles = new LinkedHashSet<>();
            Map<String, Integer> fileFrequency = new LinkedHashMap<>();
            Map<String, Map<String, Integer>> bugFile = new LinkedHashMap<>();
            //iterating through commits
            for (Commit currentC : currentWindow) {
                //storing the first task
                String TaskInitial = String.valueOf(currentC.Task.charAt(0));
                //checking if it is bug
                if(TaskInitial.equals("B")){
                    //Iterating through the commitFiles
                    Iterator<String> it1 = currentC.commitFiles.iterator();
                    while (it1.hasNext()) {
                        //Storing the current file
                        String currentFile = it1.next();
                        //If the task already exists just adding the set of files associated with the task.
                        if(bugFile.containsKey(currentC.Task)){
                            fileFrequency = new LinkedHashMap<>();
                            //storing the occurence of file
                            fileFrequency = bugFile.get(currentC.Task);
                            if(fileFrequency.containsKey(currentFile)){
                                //Incrementing the value with each file and storing it
                                int frequency = fileFrequency.get(currentFile);
                                fileFrequency.put(currentFile,frequency +1);
                            }
                            else{
                                //The file does not exist in the map
                                fileFrequency.put(currentFile,1);
                            }
                        }
                        else{
                            //Add the bug with its frequency table
                            fileFrequency = new LinkedHashMap<>();
                            fileFrequency.put(currentFile,1);
                            bugFile.put(currentC.Task, fileFrequency);
                        }
                    }
                }
                //Storing the keys from the map into the set which will be returned.
                for(String bug: bugFile.keySet()){
                    Map<String, Integer> currentFiles = bugFile.get(bug);
                    int maxF = 0;
                    for(Integer fileF: currentFiles.values()){
                        if(maxF<fileF){
                            maxF = fileF;
                        }
                    }
                    //Return for only those bugs which have threshold greater.
                    if(maxF>=threshold){
                        bugFiles.add(bug);
                    }
                }
            }
            return bugFiles;
        }
        catch(Exception e){
            return null;
        }
    }

    public int gh(GraphM g1){
        return 0;
    }

    public int check(Time t2) throws Exception {
        throw new Exception();
    }

    public void ver(GraphM g1){
        
    }

    
}


