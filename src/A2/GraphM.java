package A2;

import java.util.*;

public class GraphM extends Components {
    public int vertices;
    public int matrix[][];

    public GraphM(int vertices){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }

    public GraphM() {

    }

    public List<String> getVertex(List<Commit> allCommits){
        Set<String> vertices = new LinkedHashSet<>();
        for(Commit currentC : allCommits){
            Iterator<String> files = currentC.commitFiles.iterator();
            while(files.hasNext()){
                vertices.add(files.next());
            }
        }
        List<String> vertexA = new ArrayList<>(vertices);
        return vertexA;
    }

    public Boolean Commit(String developer, int commitTime, String Task, Set<String> commitFiles, Character first, List<Commit> allC, Boolean res){
        if(!first.equals("B") || !first.equals("A") && res == true){
            Commit newCommit = new Commit(developer, commitTime, Task, commitFiles);
            allC.add(newCommit);
        }
        else{
            return false;
        }
        return true;
    }

    public  List<Integer> primeFactors(int num) {
        List<Integer> factors = new ArrayList<>();
        while (num % 2 == 0) {
            factors.add(2);
            num /= 2;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            while (num % i == 0) {
                factors.add(i);
                num /= i;
            }
        }
        if (num > 2) {
            factors.add(num);
        }
        return factors;
    }

}
