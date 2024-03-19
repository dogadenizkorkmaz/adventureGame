import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.tools.Tool;
import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);


    public Player(String name) {
        this.name = name;
    }

    public void selectchar() {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("------------------------");
        for(GameChar gameChar : charList) {
            System.out.println( "ID : " + gameChar.getId() +
                                "\tKarakter: " + gameChar.getName() +
                                "\t Hasar : "+gameChar.getDamage()+
                                " \t Sağlık : "+gameChar.getHealth()+
                                " \t Para : "+gameChar.getMoney());
        }
        System.out.println("------------------------");
        System.out.print("Lütfen bir karakter ID giriniz : ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
                break;
        }
        System.out.println("Karakter : " + this.getCharName() +
                            ", Hasar : " + this.getDamage() +
                            ", Sağlık : " +this.getHealth()+
                            ", Para : " + this.getMoney());
    }

    public void selectLoc(){

        Location location = null;
        System.out.println("Bölgeler");
        System.out.println("1-Güvenli Ev");
        System.out.println("2-Mağaza");
        System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
        int selectLoc = input.nextInt();
        switch (selectLoc){
            case 1:
                location = new SafeHouse(this);
                break;
            case 2:
                location = new ToolStore(this);
                break;
            default:
                location = new SafeHouse(this);

        }
        location.onLocation();
    }
    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }
}
