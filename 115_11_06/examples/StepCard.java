public class StepCard {
    private int command;
    private int energy;

    public StepCard(int command, int energy) {
        this.command = command;
        this.energy = energy;
    }

    public int getCommand() {
        return command;
    }

    public int getEnergy() {
        return energy;
    }
}