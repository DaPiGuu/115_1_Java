public class RoverBattery {
    private int energy;

    public RoverBattery(int initialEnergy) {
        energy = initialEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean explore() {
        if (energy < 6) {
            return false;
        }
        energy -= 6;
        return true;
    }

    public boolean charge() {
        if (energy == 20) {
            return false;
        }
        energy += 5;
        if (energy > 20) {
            energy = 20;
        }
        return true;
    }
}