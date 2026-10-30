public class RoverDashboard {
    public static void main(String[] args) {
        String roverName = "古代靈異雙頭戰象號"; // 可自訂名稱
        char zone = 'A';
        int energy = 7;
        boolean locked = false;

        double batteryPercent = calculateBatteryPercent(energy);
        int explorationCount = energy / 6; // TODO 1：以整數除法計算可進行幾次 6 格能源的探索
        int remainingEnergy = energy % 6; // TODO 2：以取餘數計算探索後的剩餘能源
        boolean validEnergy = energy >= 0 && energy <= 20; // TODO 3：檢查能源是否在 0 至 20 之間（包含端點）
        boolean canExplore = energy >= 6 && !locked; // TODO 4：能源至少 6 格且未鎖定

        System.out.println("探測車：" + roverName + "；區域：" + zone);
        System.out.println("能源：" + energy + "；電量百分比：" + batteryPercent);
        System.out.println("完整探索次數：" + explorationCount + "；剩餘能源：" + remainingEnergy);
        System.out.println("有效能源：" + validEnergy + "；能否探索：" + canExplore);
    }

    static double calculateBatteryPercent(int energy) {
        // TODO 5：除以 20.0 確保進行浮點數除法，再乘以 100 得到百分比
        return (energy / 20.0) * 100;
    }
}
