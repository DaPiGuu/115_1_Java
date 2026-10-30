請參考以下填寫好的 **AI 使用紀錄表**。你可以直接複製並貼到你的紀錄文件中（提交前記得將講義範例區段刪除）：

| 欄位 | 記錄 |
| --- | --- |
| **使用工具** | ChatGPT (GPT-4o) |
| **使用目的與提問摘要** | 詢問 `MissionLog.java` 中 `TODO 1` 至 `TODO 5` 的實作邏輯，並確認 ArrayList 走訪與 `Collections` 工具類別的使用方式。

 |
| **AI 建議與本人處理** | **完全採納**。AI 提供了標準且符合規範的實作 code：1. 在 `countCommands` 加入 `0..3` 邊界檢查；2. 在 `runMission` 迴圈內使用 `.add()` 寫入紀錄；3. 在 `printReplay` 使用索引 `i` 與 `.get(i)` 讀取；4. 在 `printSummary` 使用 `Collections.frequency` 算出的次數反推 `rejected` 與 `ignored`；5. 使用 `.isEmpty()` 與 `Collections.min` 處理最低能源。

 |
| **實際驗證結果** | **編譯並執行成功**。使用預設腳本 `{1, 1, 2}` 執行後：

<br>

<br>- `counts[0..3]=[0, 2, 1, 0]`

<br>

<br>- `steps=3, ok=3, rejected=0, ignored=0`

<br>

<br>- `final energy=6, samples=5, actionCount=3 -> SUCCESS`

<br>

<br>完整輸出符合講義規格，驗證邏輯正確。 |