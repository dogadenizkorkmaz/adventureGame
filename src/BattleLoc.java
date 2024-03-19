import java.util.Random;


public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("Şuan buradasınız: " + this.getName());
        System.out.println("Dikkatli ol! Burada: " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor!");
        System.out.println("<S>avaş veya <K>aç :");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            //fight
            System.out.println(this.getName() + " tüm düşmanları yendiniz.");
            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) { //Creating as many loops as I need to fight with monsters

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);

            boolean playerFirst = Math.random() > 0.5; //50% starting status of the player
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase();
                if (playerFirst) {
                    if (selectCombat.equals("V")) {
                        System.out.println("Siz Vurdunuz");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();

                        } else {
                            System.out.println("Canavar size vurdu");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                            if (this.getPlayer().getHealth() > 0) {
                                System.out.println("Siz Vurdunuz");
                                this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                                afterHit();
                            }
                        }

                    } else {
                        return false;
                    }
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                int count = 0;
                System.out.println("Düşmanı yendiniz.");
                System.out.println(this.getObstacle().getAward() + " para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + getObstacle().getAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());

                if (i == obsNumber && this.getObstacle().getName().equals("Zombie")) {
                    this.getPlayer().getInventory().setFood(true);
                    count++;
                    System.out.println("Tebrikler, yemek kazandın");
                    this.getPlayer().getInventory().setFoodCount(count);
                } else if (i == obsNumber && this.getObstacle().getName().equals("Vampire")) {
                    this.getPlayer().getInventory().setFireWood(true);
                    count++;
                    System.out.println("Tebrikler, odun kazandın");
                    this.getPlayer().getInventory().setFireWoodCount(count);
                } else if (i == obsNumber && this.getObstacle().getName().equals("Bear")) {
                    this.getPlayer().getInventory().setWater(true);
                    count++;
                    System.out.println("Tebrikler, su kazandın");
                    this.getPlayer().getInventory().setWaterCount(count);
                } else if (i == obsNumber && this.getObstacle().getName().equals("Snake")) {
                    getSnake();

                }

            } else {
                return false;
            }
        }

        return false;
    }

    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "Canı : " + this.getObstacle().getHealth());
        System.out.println("-------------");
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("-----------------------------------");
        System.out.println("Sağlık :" + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar :" + this.getPlayer().getTotalDamage());
        System.out.println("Para :" + this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri");
        System.out.println("-------------------------");
        System.out.println("Sağlık :" + this.getObstacle().getHealth());
        System.out.println("Hasar :" + this.getObstacle().getDamage());
        System.out.println("Ödül :" + this.getObstacle().getAward());

    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public void getSnake() {
        Random r = new Random();
        int randomNumber = r.nextInt(100);
        if (randomNumber <= 15) {
            weaponRate();
        } else if (randomNumber <= 30) {
            armorRate();
        } else if (randomNumber <= 55) {
            moneyRate();
        } else {
            System.out.println("Hiçbir şey kazanamadınız.");
        }
    }

    public void weaponRate() {
        Random r = new Random();
        int randomNumber = r.nextInt(101) +1;
        if (randomNumber <= 50) {
            System.out.println("Tabanca kazandınız");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
        } else if (randomNumber <= 80) {
            System.out.println("Kılıç kazandınız");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
        } else if (randomNumber <= 100) {
            System.out.println("Tüfek Kazandınız");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
        }
    }

    public void armorRate() {
        Random r = new Random();
        int randomNumber = r.nextInt(101)+1;
        if (randomNumber <= 50) {
            System.out.println("Hafif Zırh kazandınız");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
        } else if (randomNumber <= 80) {
            System.out.println("Orta Zırh Kazandınız.");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
        } else if (randomNumber <= 100) {
            System.out.println("Ağır Zırh Kazandınız.");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
        }
    }

    public void moneyRate() {
        Random r = new Random();
        int randomNumber = r.nextInt(101)+1;
        int money = 0;
        if (randomNumber <= 50) {
            money = 1;
            System.out.println("1 Para Kazandnız");
            this.getPlayer().setMoney(getPlayer().getMoney() + money);
        } else if (randomNumber <= 80) {
            money = 5;
            System.out.println("5 Para Kazandınız");
            this.getPlayer().setMoney((getPlayer().getMoney() + money));
        } else if (randomNumber <= 100) {
            money = 10;
            System.out.println("10 Para Kazandınız");
            this.getPlayer().setMoney(getPlayer().getMoney() + money);
        }
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}