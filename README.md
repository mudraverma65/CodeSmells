Code Smells

This is a project I developed for CSCI 3901. The project has been modified to introdude smells accordingly.

Smells Covered.

Implementation Smells:
    1. Complex method - Implemented in class A2.CommitManager(methods: softwareComponents, repetitionInBugs, broadFeatured, experts and busyClasses) and A2.Bugs (methods: repetitionInBugs). The methods contain multiple loops with little clarity without documentation as to what each loop does. The cyclomatic complexity of each of these methods is also high.
    2. Complex conditional - Implemented in class A2.GraphM(methods: A2.Commit). The expression to create A2.Commit is complex and creates too many conditions. 
    3. Long parameter list - Implemented in class A2.GraphM(methods: A2.Commit). The method contains 7 parameters which is more than the number a standard method should have.
    4. Long method - Implemented in class A2.CommitManager(methods: softwareComponents). The method is 79 lines long more than the standard lines for a code to have. The method could have been abstracted to multiple methods to introduce modularization.

Design Smells:
    1. Deficient Encapsulation - Implemented in class A2.CommitManager, A2.Bugs, A2.Time and A2.GraphM. These classes contain methods and variables which are public.
    2. Multifaceted Abstraction - Implemented in class A2.CommitManager. The class has more responsibilties assigned rather than just managing commits which.
    3. Insufficient Modularization - Implemented in class CommitMananger. The class is not decomposed properly. The methods clearTimeWindow, getCommit, createGraph and addCommit could have further decomposition.
    4. Rebellious Hierarchy - Implemented in class A2.CommitManager(methods: componentMinimum) and A2.Bugs(repetitionInBugs). The method componentMinimum from sub class A2.Components is overridden in super class A2.CommitManager. The method repetitionInBugs from subclass A2.CommitManager is overridden in super class A2.Bugs.

Arcitecture Smells:
    1. Feature concentration - Implemented in class CommitManger. The class is working on more components than necessary. Further modularization may prove helpful in reducing the methods in the class.
