public class Inventory {

    private boolean food;
    private int foodCount;
    private boolean water;
    private int waterCount;
    private boolean fireWood;
    private int fireWoodCount;
    private Weapon weapon;
    private Armor armor;

    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0, 0);
        this.armor = new Armor(-1, "Paçavra", 0, 0);
        this.food=false;
        this.water=false;
        this.fireWood=false;
        this.foodCount=0;
        this.waterCount=0;
        this.fireWoodCount=0;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }

    public boolean isFireWood() {
        return fireWood;
    }

    public void setFireWood(boolean fireWood) {
        this.fireWood = fireWood;
    }

    public int getFireWoodCount() {
        return fireWoodCount;
    }

    public void setFireWoodCount(int fireWoodCount) {
        this.fireWoodCount = fireWoodCount;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}