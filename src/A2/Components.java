package A2;
public class Components {

    public boolean componentMinimum(int threshold) {
        return true;
    }

    public boolean isPrime(int num){
        if(num <= 1){
            return false;
        }
        for(int i=2; i*i<=num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

}
