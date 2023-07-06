import java.util.Scanner;
public class numbergame{
    public static void num(){
        Scanner sc=new Scanner(System.in);
        int number=1+(int)(100*Math.random());
        int k=10;
        int i,guess;
        System.out.println(
            "A number is chosen by this game" + " between 1 to 100." + "Guess the number" + " within 10 trials.");
            for(i=0;i<k;i++){
                guess=sc.nextInt();
                if(number==guess){
                    System.out.println("Kudos!!!"+"You guessed the number genius");
                    break;
                }
                else if(number>guess && i!=k-1){
                    System.out.println("The number is "+"greater than"+guess);
                }
                else if(number<guess && i!=k-1){
                    System.out.println("The number is"+"less than"+guess);
                }
            }
            if(i==k){
                System.out.println("Alas!! You have exhausted"+"k trails");
                System.out.println("The number is "+number);
            }
    }
    public static void main(String args[]){
        num();
    }
}
