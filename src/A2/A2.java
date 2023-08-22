package A2;

import java.util.*;

public class A2 {
    public static void main(String[] args){
        CommitManager commitM = new CommitManager();
        Bugs b1 = new Bugs();
        Time t1= new Time();
        t1.setTimeWindow(0,11);
        commitM.addCommit("mudra", 10, "B-190",new LinkedHashSet<>(Arrays.asList("A","B","C","D")));
        commitM.addCommit("pallavi", 15, "B-192",new LinkedHashSet<>(Arrays.asList("A","B","C")));
        commitM.addCommit("alen", 10, "B-190",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("dhruv", 10, "B-190",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("rishi", 10, "B-199",new LinkedHashSet<>(Arrays.asList("C","B")));
        commitM.addCommit("muskan", 10, "B-190",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("mudra", 10, "B-192",new LinkedHashSet<>(Arrays.asList("B")));
        commitM.addCommit("ashwati", 10, "B-192",new LinkedHashSet<>(Arrays.asList("E","A")));
        commitM.addCommit("mudra", 10, "B-193",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("dhruv", 10, "B-192",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("mudra", 10, "B-192",new LinkedHashSet<>(Arrays.asList("B","C","D")));
        commitM.addCommit("muskan", 10, "B-195",new LinkedHashSet<>(Arrays.asList("E","A")));
        commitM.addCommit("rishi", 10, "B-192",new LinkedHashSet<>(Arrays.asList("A","B","E")));
        commitM.addCommit("pallavi", 10, "B-192",new LinkedHashSet<>(Arrays.asList("D","E")));
        commitM.addCommit("milind", 10, "B-194",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("limysh", 10, "B-192",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("mudra", 10, "B-199",new LinkedHashSet<>(Arrays.asList("B","D")));

        commitM.addCommit("alen", 12, "F-192",new LinkedHashSet<>(Arrays.asList("A","B","C")));
        commitM.addCommit("mudra", 12, "F-190",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("alen", 12, "F-190",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("kaushal", 12, "F-199",new LinkedHashSet<>(Arrays.asList("C","B")));
        commitM.addCommit("anirudh", 12, "F-190",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("milind", 12, "F-192",new LinkedHashSet<>(Arrays.asList("B")));
        commitM.addCommit("limysh", 12, "F-192",new LinkedHashSet<>(Arrays.asList("E","A"))); // AE
        commitM.addCommit("ashwati", 12, "F-193",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("pallavi", 12, "F-192",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("mudra", 12, "F-192",new LinkedHashSet<>(Arrays.asList("B","C","D")));
        commitM.addCommit("kaushal", 12, "F-195",new LinkedHashSet<>(Arrays.asList("E","A"))); // AE
        commitM.addCommit("mudra", 12, "F-192",new LinkedHashSet<>(Arrays.asList("D","E")));
        commitM.addCommit("kaushal", 12, "F-194",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("mudra", 12, "F-192",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        t1.setTimeWindow(0,20);
        t1.clearTimeWindow();
        commitM.componentMinimum(9);
        Set<Set<String>> c= commitM.softwareComponents();
        Set<String> stringSet = b1.repetitionInBugs(4);
        Set<String> stringSet1 = commitM.broadFeatures(1);
        Set<String> stringSet3 = commitM.experts(2);
        List<String> stringSet4 = commitM.busyClasses(3);
    }
}
