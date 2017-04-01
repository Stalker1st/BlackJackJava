package radu.denis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Denis on 31-Mar-17.
 */

class Card
{
    private int nr;
    private int val;
    private String type = new String();

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    Card()
    {

    }


    Card(int numar, String type) {
        this.nr = numar;
        if(numar > 10 && numar !=11)
            this.val = 10;
        else if(numar == 11)
            this.val = 11;
        else this.val = numar;
        this.type = type;

    }
    Card(int numar, int typenr)
    {
        this.nr = numar;
        if(numar > 10 && numar !=11)
            this.val = 10;
        else if(numar == 11)
            this.val = 11;
        else this.val = numar;
        switch(typenr)
        {
            case 1:
                this.type = "Heart";
                break;
            case 2:
                this.type = "Spade";
                break;
            case 3:
                this.type = "Club";
                break;
            case 4:
                this.type = "Diamond";
                break;

        }

    }

    public String showCard()
    {
        String nrDen = new String();
        if(nr>10) {
            switch (nr) {
                case 11:
                    nrDen = "A";
                    break;
                case 12:
                    nrDen = "J";
                    break;
                case 13:
                    nrDen = "Q";
                    break;
                case 14:
                    nrDen = "K";
                    break;

            }
        }
        else nrDen = "" + nr;
        return "" + nrDen + " " + type;
    }


}

class Dealer{
    private ArrayList<Card> cards = new ArrayList<>();
    private int sum, cardsOnTable, draws;

    public int getSum() {
        return sum;
    }

    public void increaseSum(int x) {
        this.sum += x;
    }

    public int getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(int cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }



    Dealer()
    {
        sum = 0;
        cardsOnTable = 0;
        draws = 0;
    }
    public void add(Card c)
    {
        cards.add(c);
        cardsOnTable++;
    }

}

class Player{

    private ArrayList<Card> cards = new ArrayList<>();
    private int sum;
    private int cardsOnTable;
    private int draws;

    public int getSum() {
        return sum;
    }

    public void increaseSum(int x) {
        this.sum += x;
    }

    public int getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(int cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }


    Player()
    {
        sum = 0;
        cardsOnTable = 0;
        draws = 0;
    }

    public void add(Card c)
    {
        cards.add(c);
        cardsOnTable++;
    }
}

public class Game{

    private ArrayList<Card> cards= new ArrayList<>();
    private int turn,randCard, randType;
    private Player p;
    private Dealer d;
    private Boolean showedHiddenCard = false;

    public Boolean getGameover() {
        return gameover;
    }

    public void setGameover(Boolean gameover) {
        this.gameover = gameover;
    }

    private Boolean gameover = false;

    Game()
    {
        for(int i=2; i<=14; i++)
        {
            Card cr1 = new Card(i,"Heart");
            Card cr2 = new Card(i,"Spade");
            Card cr3 = new Card(i,"Club");
            Card cr4 = new Card(i,"Diamond");
            cards.add(cr1);
            cards.add(cr2);
            cards.add(cr3);
            cards.add(cr4);
        }
        gameover = false;
        p = new Player();
        d = new Dealer();
        turn = 0;
        startCards();
        action();

    }
    private void startCards()
    {
        Boolean ok = false;
        System.out.println("Initial Draw");
        //First draw for Dealer
        System.out.print("Dealer draw: ");
        randCard = ThreadLocalRandom.current().nextInt(2, 15);
        randType = ThreadLocalRandom.current().nextInt(1, 5);
        Card cr;
        cr = new Card(randCard, randType);
        d.add(cr);
        for(Card c: cards)
            if(c.getNr() == cr.getNr() && c.getType().compareTo(cr.getType()) == 0)
            {
                cards.remove(c);
                System.out.print(cr.showCard() + ", ");
                break;
            }
        while(!ok) {
            randCard = ThreadLocalRandom.current().nextInt(2, 15);
            randType = ThreadLocalRandom.current().nextInt(1, 5);
            cr = new Card(randCard, randType);
            for(Card c : cards)
                if(c.getNr() == cr.getNr() && c.getType().compareTo(cr.getType())==0)
                {
                    d.add(c);
                    cards.remove(c);
                    System.out.print("Hidden" + "\n");
                    ok = true;
                    break;
                }
        }

        // Player first draw
        ok = false;
        System.out.print("Player draw(1): ");
        while(!ok) {
            randCard = ThreadLocalRandom.current().nextInt(2, 15);
            randType = ThreadLocalRandom.current().nextInt(1, 5);
            cr = new Card(randCard, randType);
            for(Card c : cards)
                if(c.getNr() == cr.getNr() && c.getType().compareTo(cr.getType())==0)
                 {
                     p.add(c);
                     cards.remove(c);
                     System.out.print(c.showCard()+ ", ");
                     ok = true;
                     break;
                 }
        }
        ok = false;
        while(!ok) {
            randCard = ThreadLocalRandom.current().nextInt(2, 15);
            randType = ThreadLocalRandom.current().nextInt(1, 5);
            cr = new Card(randCard, randType);
            for(Card c : cards)
                if(c.getNr() == cr.getNr() && c.getType().compareTo(cr.getType())==0)
                {
                    p.add(c);
                    cards.remove(c);
                    System.out.print(c.showCard());
                    ok = true;
                    break;
                }
        }
        turn = 1;
        p.setDraws(2);
        d.setDraws(1);
        for(Card c : d.getCards())
        {
            d.increaseSum(c.getVal());
        }

        if(d.getSum() > 21)
        {
            d.getCards().get(0).setVal(1);
            d.increaseSum(-10);
        }
        else if(d.getSum() >=17 && d.getSum()!=21 && d.getCards().get(0).getNr() == 11)
        {
            d.getCards().get(0).setVal(1);
            d.increaseSum(-10);
        }
        else if(d.getSum() >=17 && d.getSum()!=21 && d.getCards().get(1).getNr() == 11)
        {
            d.getCards().get(1).setVal(1);
            d.increaseSum(-10);
        }

        for(Card c : p.getCards())
        {
            p.increaseSum(c.getVal());

        }
        if(p.getSum() == 21 && d.getSum() == 21)
        {
            System.out.println("Dealer Hidden Card Was: " + d.getCards().get(1).getNr() + " " + d.getCards().get(1).getType());
            System.out.println("\nNo Winner, player and dealer have equal!!!");
            gameover = true;
        }
        else if(p.getSum() > 21)
        {
            p.getCards().get(0).setVal(1);
            p.increaseSum(-10);
        }
        else if(p.getSum() == 21)
        {
            turn = 0;

        }




    }

    private void action()
    {
        if(turn == 1 && !gameover) // Player turn
        {
            System.out.println(". Do you want to draw another card? (Y/N)");
            Scanner sc = new Scanner(System.in);
            String resp = sc.next();
            if((resp.equals("Y") || resp.equals("y")) && p.getCardsOnTable() < 5)
            {
                System.out.print("\nPlayer Draw(" + p.getDraws() + "): ");
                Boolean ok = false;
                Card cr = new Card();
                while(!ok)
                {
                    randCard = ThreadLocalRandom.current().nextInt(2, 15);
                    randType = ThreadLocalRandom.current().nextInt(1, 5);
                    cr = new Card(randCard, randType);
                    for(Card c : cards)
                        if(c.getNr() == cr.getNr() && c.getType().compareTo(cr.getType())==0)
                        {
                            p.add(c);
                            cards.remove(c);
                            System.out.print(c.showCard());
                            ok = true;
                            break;
                        }
                }
                if(cr.getNr() == 11)
                {
                    if(p.getSum() + cr.getVal() > 21)
                        p.getCards().get(p.getCards().size() - 1).setVal(1);
                    p.increaseSum(p.getCards().get(p.getCards().size() - 1).getVal());

                    if(p.getSum() > 21)
                    {
                        System.out.print("\nDealer Hidden Card Was: " + d.getCards().get(1).showCard());
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nDealer WINS!!!");
                        gameover = true;
                    }
                    else if(p.getSum() == 21)
                    {
                        turn = 0;
                        action();
                    }
                    else
                    {
                        int draws = p.getDraws();
                        p.setDraws(draws + 1);
                        action();
                    }
                }
                else
                {
                    p.increaseSum(cr.getVal());
                    if(p.getSum() > 21)
                    {
                        for(Card c : p.getCards())
                            if(c.getNr() == 11 && c.getVal() == 11)
                            {
                                c.setVal(1);
                                p.increaseSum(-10);
                            }

                         if(p.getSum() > 21)
                        {
                            System.out.print("\nDealer Hidden Card Was: " + d.getCards().get(1).showCard());
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nDealer WINS!!!");
                            gameover = true;
                        }
                        else if(p.getSum() == 21)
                         {
                             turn = 0;
                             action();
                         }
                        else
                        {
                            int draws = p.getDraws();
                            p.setDraws(draws+1);
                            action();
                        }

                    }
                    else if(p.getSum() == 21)
                    {
                        turn = 0;
                        action();
                    }
                    else
                    {
                        int draws = p.getDraws();
                        p.setDraws(draws+1);
                        action();
                    }
                }
            }
            else if(p.getCardsOnTable() == 5)
            {
                turn = 0;
                action();
            }
            else if(resp.equals("N") || resp.equals("n"))
            {
                turn = 0;
                action();
            }
        }
        else if(turn ==0 && !gameover) // Dealer turn
        {
            if(d.getSum() == 21 && !showedHiddenCard)
            {
                System.out.print("\nDealer Hidden Card Was: " + d.getCards().get(1).showCard());
                System.out.print(". Dealer hand is " + d.getSum());
                if(d.getSum() > p.getSum())
                {
                    System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                    System.out.println("Dealer WINS!!!");
                    gameover = true;
                }
                showedHiddenCard = true;

            }
            else if(d.getSum() >=17 && d.getSum()<=21)
            {
                if(!showedHiddenCard)
                {
                    System.out.print("\nDealer Hidden Card Was: " + d.getCards().get(1).showCard());
                    showedHiddenCard = true;
                }
                System.out.print(". Dealer hand is " + d.getSum() + ", dealer stops.");
                if(d.getSum() > p.getSum())
                {
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("Dealer WINS!!!");
                        gameover = true;
                }
                else if(d.getSum() == p.getSum())
                {
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nNo Winner, player and dealer have equal!!!");
                        gameover = true;
                }
                else
                {
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("Player WINS!!!");
                        gameover = true;
                }


            }
            else if(d.getSum() < 17 && d.getCardsOnTable() < 5)
            {
                if(!showedHiddenCard)
                {
                    System.out.print("\nDealer Hidden Card Was: " + d.getCards().get(1).showCard());
                    showedHiddenCard = true;
                }
                System.out.println(". Dealer hand is " + d.getSum() + ", dealer draws another hand.");
                System.out.print("\nDealer Draw(" + d.getDraws() + "): ");
                Boolean ok = false;
                Card cr = new Card();
                while(!ok)
                {
                    randCard = ThreadLocalRandom.current().nextInt(2, 15);
                    randType = ThreadLocalRandom.current().nextInt(1, 5);
                    cr = new Card(randCard, randType);
                    for(Card c : cards)
                        if(c.getNr() == cr.getNr() && c.getType().compareTo(cr.getType())==0)
                        {
                            d.add(c);
                            cards.remove(c);
                            System.out.print(c.showCard());
                            ok = true;
                            break;
                        }
                }
                if(cr.getNr() == 11)
                {
                    if(d.getSum() + cr.getVal() > 21)
                        d.getCards().get(d.getCards().size() - 1).setVal(1);
                    else if(d.getSum() + cr.getVal() >=17 && d.getSum() + cr.getVal() < 21 && d.getSum() + cr.getVal() <p.getSum())
                        d.getCards().get(d.getCards().size() - 1).setVal(1);
                    d.increaseSum(d.getCards().get(d.getCards().size() - 1).getVal());

                    if(d.getSum() == 21 && p.getSum() < 21)
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nDealer WINS!!!");
                        gameover = true;
                    }
                    else if(d.getSum() == p.getSum())
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nNo Winner, player and dealer have equal!!!");
                        gameover = true;
                    }
                    else if(d.getSum() < p.getSum() && d.getSum() >=17)
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nPlayer WINS!!!");
                        gameover = true;
                    }
                    else
                    {
                        int draws = d.getDraws();
                        d.setDraws(draws + 1);
                        action();
                    }
                }
                else
                {
                    d.increaseSum(cr.getVal());
                    if(d.getSum() > 21)
                    {
                        for(Card c : d.getCards())
                            if(c.getNr() == 11 && c.getVal() == 11)
                            {
                                c.setVal(1);
                                d.increaseSum(-10);
                            }

                        if(d.getSum() == 21 && p.getSum() < 21)
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nDealer WINS!!!");
                            gameover = true;
                        }
                        else if(d.getSum() == p.getSum())
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nNo Winner, player and dealer have equal!!!");
                            gameover = true;
                        }
                        else if(d.getSum() < p.getSum() && d.getSum() >=17 )
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nPlayer WINS!!!");
                            gameover = true;
                        }
                        else if(d.getSum() > 21)
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nPlayer WINS!!!");
                            gameover = true;
                        }
                        else
                        {
                            int draws = d.getDraws();
                            d.setDraws(draws+1);
                            action();
                        }

                    }
                    else if(d.getSum() + cr.getVal() >=17 && d.getSum() + cr.getVal() < 21 && d.getSum() + cr.getVal() <p.getSum())
                    {
                        for(Card c : d.getCards())
                            if(c.getNr() == 11 && c.getVal() == 11 && d.getSum() >=17)
                            {
                                c.setVal(1);
                                d.increaseSum(-10);
                            }
                        if(d.getSum() == 21 && p.getSum() < 21)
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nDealer WINS!!!");
                            gameover = true;
                        }
                        else if(d.getSum() == p.getSum())
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nNo Winner, player and dealer have equal!!!");
                            gameover = true;
                        }
                        else if(d.getSum() < p.getSum() && d.getSum() >=17)
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nPlayer WINS!!!");
                            gameover = true;
                        }
                        else if(d.getSum() > 21)
                        {
                            System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                            System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                            System.out.println("\nPlayer WINS!!!");
                            gameover = true;
                        }
                        else
                        {
                            int draws = d.getDraws();
                            d.setDraws(draws+1);
                            action();
                        }
                    }
                    else if(d.getSum() == 21 && p.getSum() < 21)
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nDealer WINS!!!");
                        gameover = true;
                    }
                    else if(d.getSum() == p.getSum())
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nNo Winner, player and dealer have equal!!!");
                        gameover = true;
                    }
                    else if(d.getSum() < p.getSum() && d.getSum() >=17)
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nPlayer WINS!!!");
                        gameover = true;
                    }
                    else if(d.getSum() > 21)
                    {
                        System.out.println(". Dealer hand is " + d.getSum() + ", dealer stops.");
                        System.out.println("\nPlayer Hand values " + p.getSum() + ", Dealer Hand values " + d.getSum() + ".");
                        System.out.println("\nPlayer WINS!!!");
                        gameover = true;
                    }
                    else
                    {
                        int draws = d.getDraws();
                        d.setDraws(draws+1);
                        action();
                    }
                }
            }
            else if(d.getCardsOnTable() == 5)
            {
                turn = -1;

            }

        }

    }




}
