import java.util.ArrayList;
import java.util.Arrays;

// 第五週專案：使用 MissionStep 物件來紀錄日誌
public class MissionLog {
    public static void main(String[] args) {
        // 預設測試腳本：指令 1, 1, 2
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // 教師測試入口：傳入腳本陣列、初始能源與初始樣本數
    static void runMission(int[] script, int energy, int samples) {
        int actionCount = 0;       // 成功執行的行動次數
        boolean running = true;    // 任務是否繼續進行
        ArrayList<MissionStep> history = new ArrayList<>(); // 儲存每一步快照的清單

        // 當能源大於 0、樣本小於 5 且未結束時，依序讀取腳本指令
        for (int i = 0; i < script.length && running && energy > 0 && samples < 5; i++) {
            int command = script[i];
            String result;
            
            // 判斷當前指令
            switch (command) {
                case 1: // 指令 1：探索（消耗 6 格能源，獲得 2 份樣本）
                    if (energy >= 6) {
                        energy -= 6;
                        samples += 2;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 2: // 指令 2：掃描（消耗 2 格能源，獲得 1 份樣本）
                    if (energy >= 2) {
                        energy -= 2;
                        samples++;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 3: // 指令 3：充電（增加 5 格能源，上限 20 格）
                    if (energy < 20) {
                        energy += 5;
                        if (energy > 20) {
                            energy = 20;
                        }
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "ALREADY_FULL";
                    }
                    break;
                case 0: // 指令 0：返回（停止任務）
                    running = false;
                    result = "RETURN";
                    break;
                default: // 未知指令
                    result = "UNKNOWN_COMMAND";
                    break;
            }
            // 【TODO 4】每執行完一筆指令，就建立一個 MissionStep 物件並加入歷史紀錄清單
            history.add(new MissionStep(command, energy, result));
        }

        // 判定最終任務結果
        String outcome;
        if (samples >= 5) {
            outcome = "SUCCESS";           // 樣本達標
        } else if (energy == 0) {
            outcome = "EXHAUSTED";         // 能源耗盡
        } else if (!running) {
            outcome = "RETURNED";          // 主動返回
        } else {
            outcome = "UNFINISHED";        // 腳本用完但未完成任務
        }

        // 印出歷史回放與各項統計
        printReplay(history);
        System.out.println("counts[0..3]=" + Arrays.toString(countCommands(history)));
        printSummary(script.length, history);
        System.out.println("final energy=" + energy + ", samples=" + samples
                + ", actionCount=" + actionCount + " -> " + outcome);
        System.out.println("battery=" + calculateBatteryPercent(energy));
    }

    // 統計各指令（0..3）出現的次數
    static int[] countCommands(ArrayList<MissionStep> history) {
        int[] counts = new int[4];
        for (MissionStep step : history) {
            int command = step.getCommand();
            if (command >= 0 && command < counts.length) {
                counts[command]++;
            }
        }
        return counts;
    }

    // 【TODO 5】走訪 history 清單，使用 Getter 印出每一步的詳細回放資訊
    static void printReplay(ArrayList<MissionStep> history) {
        System.out.println("=== replay ===");
        for (int i = 0; i < history.size(); i++) {
            MissionStep step = history.get(i);
            System.out.println("step " + i + ": command=" + step.getCommand()
                    + ", energy=" + step.getEnergy() + ", result=" + step.getResult());
        }
    }

    // 【TODO 6】走訪 history 清單，計算成功（OK）、返回（RETURN）數量與最低能源紀錄
    static void printSummary(int scriptLength, ArrayList<MissionStep> history) {
        int ok = 0;
        int returned = 0;
        
        // 統計 OK 與 RETURN 次數
        for (MissionStep step : history) {
            if ("OK".equals(step.getResult())) {
                ok++;
            } else if ("RETURN".equals(step.getResult())) {
                returned++;
            }
        }

        int steps = history.size();
        int rejected = steps - ok - returned;       // 遭拒絕或無效的指令筆數
        int ignored = scriptLength - steps;         // 未執行的剩餘指令筆數

        System.out.println("steps=" + steps + ", ok=" + ok
                + ", rejected=" + rejected + ", ignored=" + ignored);

        // 計算過程中的最低能源值
        if (history.isEmpty()) {
            System.out.println("lowest energy=N/A");
        } else {
            int lowest = history.get(0).getEnergy();
            for (MissionStep step : history) {
                if (step.getEnergy() < lowest) {
                    lowest = step.getEnergy();
                }
            }
            System.out.println("lowest energy=" + lowest);
        }
    }

    // 計算剩餘電量百分比
    static double calculateBatteryPercent(int energy) {
        return energy / 20.0 * 100;
    }
}