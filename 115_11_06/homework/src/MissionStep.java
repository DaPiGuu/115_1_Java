// MissionStep 類別：用於封裝單一行動的指令、剩餘能源與執行結果
public class MissionStep {
    private int command;   // 指令編號
    private int energy;    // 當時剩餘能源
    private String result; // 執行結果狀態

    // 【TODO 1】建構子：以 this 將傳入的三個參數保存到目前物件的私有欄位中
    public MissionStep(int command, int energy, String result) {
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    // 【TODO 2】三個 Getter 方法：讀取並回傳本物件對應欄位的值
    public int getCommand() {
        return this.command;
    }

    public int getEnergy() {
        return this.energy;
    }

    public String getResult() {
        return this.result;
    }

    // 【TODO 3】以內容比較判斷本物件的 result 是否等於 "OK"
    public boolean isSuccessful() {
        return "OK".equals(this.result);
    }
}