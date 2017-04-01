package radu.denis;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game gm;
        Scanner sc = new Scanner(System.in);
        String resp = "Y";
        Boolean startnewgame = true;
        while(startnewgame)
        {
            if (resp.equals("Y") || resp.equals("y"))
            {
                gm = new Game();
            }
            else if(resp.equals("N") || resp.equals("n"))
            {
                startnewgame = false;
                break;
            }
            System.out.println("\nStart a new game? (Y/N)");
            resp = sc.next();
        }
    }
}
