import java.util.Scanner;

public class Game {
    public Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoşgeldiniz.");
        System.out.println("Lütfen bir karakter seçiniz.");
        System.out.println("--------------------------------------------------------------------------------");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("########### BÖLGELER ###########");
            System.out.println();
            System.out.println("1-Güvenli Ev ----> Burası sizin için güvenli bir ev, düşman yoktur!");
            System.out.println("2-Eşya Dükkanı ----> Silah veya Zırh satın alabilirsiniz.");
            System.out.println("3-Mağara ----> Ödül <Yemek>, Dikkatli ol karşına zombi çıkabilir.");
            System.out.println("4-Orman ----> Ödül <Odun>, Dikkatli ol karşına vampir çıkabilir.");
            System.out.println("5-Nehir ----> Ödül <Su>, Dikkatli ol karşına ayı çıkabilir.");
            System.out.println("6-Maden ----> Ödül <Weapon, Armor, Money >, Dikkatli ol karşına ayı çıkabilir.");
            System.out.println("0-Çıkış Yap ----> Oyunu Sonlandır.");

            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen Geçerli Bir Bölge Giriniz.");

            }
            if (location == null) {
                System.out.println("Bu Karanlık ve Sisli Adadan Çabuk Vazgeçtin..");
                break;

            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER");
                break;
            }
        }
    }
}