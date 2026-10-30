
---

# 第四週學生實作：火星探測車任務日誌

姓名：黃耀信 學號：A114222016 實際上課日期：2026-10-30 實際 JDK：OpenJDK 17.0.2

另存為當日 LAB_REPORT.md。A–H 必做，最後的延伸選做。先預測再執行；教師示範觀察與本人實測分開。作業完整需求見 [assignment.md](assignment.md)。

## A｜取回與先備診斷（10 分鐘）

1. 無 repo 時 clone，有副本先保存再 Fetch/Pull；在 115_1_Java/YYYY_MM_DD 建 java-04/src。
2. 放五個核心 examples 與 starter/MissionLog.java，勿放 teacher 同名類別；日期下放本報告及 AI_USAGE。


3. 執行 java -version、javac -version，寫下完整版本：`openjdk version "17.0.2" 2022-01-18`。不限定特定 JDK 版本。
4. 在 java-04 編譯 `javac -encoding UTF-8 -d out src/*.java`；成功後跑 MissionLog。此時能跑任務但日誌與摘要未完成，是預期起始狀態。



診斷：第三週 {1,1,2} 的最終能源/樣本/成功行動次數：`energy=6, samples=5, actionCount=3`。{2,1,1} 是否相同：`能源與樣本最終數值相同，但執行順序不同`。只有最終三個數字，能否分辨路線：`不能，因為不同的執行順序可能導向相同的最終結果`。

## B｜固定資料：陣列與計數（30 分鐘）

教科書 §7.2–7.4、§7.7。先讀 ArrayBasics，再執行。

| 項目 | 預測 | 本人實際結果 |
| --- | --- | --- |
| script.length | 3 | 3

 |
| new int[4] 內容 | [0, 0, 0, 0] | [0, 0, 0, 0]

 |
| new String[2] 內容 | [null, null] | [null, null]

 |
| 逐步指令輸出 | step 1: 1<br>

<br>step 2: 1<br>

<br>step 3: 2 | step 1: 1<br>

<br>step 2: 1<br>

<br>step 3: 2

 |
| 指令數值加總（不是行動數） | 4 | sum=4

 |

1. length 是欄位，不加括號；陣列合法索引範圍是：`0 到 script.length - 1`。


2. 在副本取消越界行註解，編譯並執行，記錯誤種類與索引：`java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`。隨即恢復。


3. 執行 CommandCounter：腳本 {3,9,0,1,1,-1}，預測 counts：`[1, 2, 0, 1]`；實際：`counts=[1, 2, 0, 1]`。9 和 -1 為何不得當索引：`因為陣列長度為 4，合法索引只有 0~3，小於 0 或大於等於 4 都會引發 ArrayIndexOutOfBoundsException`。


4. 把 CommandCounter 的固定資料改為 {0,2,2,3}，預測：`[1, 0, 2, 1]`；實際：`counts=[1, 0, 2, 1]`；恢復原示範。
5. 一般 for 適合需要步號/索引；加強版 for 適合只讀每筆值。若在 `for (int command : script)` 內寫 command=0，是否改掉 script 的元素：`否，因為 command 只是取出元素的區域變數副本`。用副本檢查：`列印 script 發現內容未變`。



## C｜參考與方法（10 分鐘）

教科書 §7.8–7.9。ArrayReference 的兩個變數先指向同一陣列；畫箭頭或用文字描述。

| 時間點 | 預測 energy 內容 | 本人實際 |
| --- | --- | --- |
| alias[0]=19 後 | [19, 14, 8] | alias change=[19, 14, 8]

 |
| changeFirst 後 | [18, 14, 8] | element change=[18, 14, 8]

 |
| replaceLocal 內的新陣列 | [20, 20, 20] | inside local replace=[20, 20, 20]

 |
| 回到 main 後 | [18, 14, 8] | after local replace=[18, 14, 8]

 |
| == 與 Arrays.equals 的兩個結果 | false, true | same reference=false, same content=true

 |

Java 傳值；陣列參數取得的是：`記憶體位址（參考值）的副本`。為何修改元素看得見，重新指定參數卻不改呼叫端：`透過參考副本修改元素會變更同一記憶體位址的物件內容；但重新指定參數只改變方法內區域變數的指向，不影響呼叫端的變數`。

## D｜筆數會增加：ArrayList（15 分鐘）

教科書 §7.16、§16.3–16.4。C# List 若尚未學過，以右欄 Java 為準。

先分清腳本與日誌：{1,1,1,1}有四筆預定指令，但從20/0出發第三次探索就達標，因此只記三筆。陣列可搭配有效筆數保存；ArrayList每記錄一步就add，size()直接取得已存筆數，本週用它練習逐筆記錄。

| 操作 | C# List | Java ArrayList |
| --- | --- | --- |
| 筆數 | Count | size()

 |
| 讀第 i 筆 | list[i] | get(i)

 |
| 改第 i 筆 | list[i]=x | set(i,x)

 |
| 尾端新增 | Add(x) | add(x)

 |
| 刪索引 | RemoveAt(i) | remove(i)

 |

執行 ListBasics：

| 項目 | 預測 | 本人實際 |
| --- | --- | --- |
| 原清單/size/first | [1, 1, 2], size=3, first=1 | commands=[1, 1, 2], size=3, first=1

 |
| set(1,3) 後 | [1, 3, 2] | after set=[1, 3, 2]

 |
| remove(0) 後 | [3, 2] | after remove index=[3, 2]

 |
| remove(Integer.valueOf(3)) 後 | [2] | after remove value=[2]

 |
| isEmpty() | false | empty=false

 |

int 放入清單會裝箱，讀成 int 會拆箱；ArrayList 為何不能編譯：`Java 泛型參數只能接受引用型態（Object/Integer），不能使用基本資料型態（primitive types）`。可以在副本重現編譯訊息，但必須恢復可編譯版本。remove 的兩種用途只需能分辨，不要求說明完整過載規則。

## E｜新增、回放、計數（35 分鐘）

教師提供第三週的行動規則、迴圈與方法骨架，先只完成 TODO **2→3→1**。每完成一項就編譯。F1 再完成 4、5。

* TODO 2：每步執行後把 command、energy、result 同時 add；拒絕、無效與返回也要保存。


* TODO 3：同一索引讀三條清單，格式 `step 1: command=1, result=OK, energy=14`。


* TODO 1：建立 int[4]，計算日誌中的 0–3 指令；越界指令不進 counts。



{3,9,0} 的逐步追蹤：

| 步 | 指令 | 預測結果/執行後能源 | 本人實際結果/能源 |
| --- | --- | --- | --- |
| 1 | 3 | ALREADY_FULL / 20 | ALREADY_FULL / 20

 |
| 2 | 9 | UNKNOWN_COMMAND / 20 | UNKNOWN_COMMAND / 20

 |
| 3 | 0 | RETURN / 20 | RETURN / 20

 |

此時 commandLog/energyLog/resultLog 各有幾筆：`各 3 筆`。actionCount 是幾次：`0 次`。差異原因：`指令 3 因能源已滿被拒絕，指令 9 為無效指令，指令 0 為返回指令；三者均未成功執行行動，因此 actionCount 為 0，但均有被寫入 log`。

完成 F1 的摘要後，補齊六條本人測試。每列修改 main 固定腳本並重新編譯，初始值 20/0；將**完整輸出**貼在本表下方，包含回放、counts、摘要、最低能源、最終狀態與 battery。

| script | 預期狀態/結果 | 本人實際狀態/結果 | counts、摘要、每筆回放均相符？ |
| --- | --- | --- | --- |
| {0} | 20/0/0 RETURNED | 20/0/0 RETURNED | 是

 |
| {1,1,2} | 6/5/3 SUCCESS | 6/5/3 SUCCESS | 是

 |
| {2,2,2,2,2} | 10/5/5 SUCCESS | 10/5/5 SUCCESS | 是

 |
| {3,9,0} | 20/0/0 RETURNED | 20/0/0 RETURNED | 是

 |
| {1,1,1,1} | 2/6/3 SUCCESS | 2/6/3 SUCCESS | 是

 |
| {2,1} | 12/3/2 UNFINISHED | 12/3/2 UNFINISHED | 是

 |

完整輸出：

```text
=== replay ===
step 1: cmd=1, energy=14, result=OK
step 2: cmd=1, energy=8, result=OK
step 3: cmd=2, energy=6, result=OK
counts[0..3]=[0, 2, 1, 0]
steps=3, ok=3, rejected=0, ignored=0
lowest energy=6
final energy=6, samples=5, actionCount=3 -> SUCCESS
battery=30.0

```

繳交前恢復 {1,1,2}。課堂沒完成的路線，課後補齊本人實測。

## F｜摘要、需求與反例（30 分鐘）

### F1 Collections 與 TODO 4–5（10 分鐘）

先執行 CollectionsDemo，預測 min/max/frequency：min=6, max=20, frequency(8)=1；實際：`min=6, max=20, frequency(8)=1`。排序後 original：`[14, 8, 20, 6]`；sorted：`[6, 8, 14, 20]`。為何不能只排序 energyLog 來做回放：`因為排序會破壞時間順序，導致能源記錄無法與指令 log (commandLog) 及結果 log (resultLog) 的索引按時間對齊`。

* TODO 4：ok=frequency(resultLog,"OK")，returned=frequency(resultLog,"RETURN")；steps=size，rejected=steps-ok-returned，ignored=scriptLength-steps。


* TODO 5：isEmpty 為 true 時印 `lowest energy=N/A`；否則才用 min。


* 最低能源只看每步結束後的 energyLog，不含初始能源。



教師使用 FixedScriptMission 示範以下案例；學生填預測與觀察，不需另寫入口。未觀察請註明待補；自己另跑者再註明「本人複驗」。

| 初始 energy/samples；script | 預測結果/日誌筆數/最低能源 | 教師實際示範觀察 |
| --- | --- | --- |
| 5/0；{1,3,0} | NOT_ENOUGH_ENERGY, 筆數 3, 最低 5 | 本人複驗：steps=3, lowest energy=5 |
| 2/4；{2} | SUCCESS, 筆數 1, 最低 0 | 本人複驗：steps=1, lowest energy=0 |
| 0/0；{3,1} | 未執行直接結束, 筆數 0, 最低 N/A | 本人複驗：steps=0, lowest energy=N/A |
| 20/5；{1} | 已達標不執行, 筆數 0, 最低 N/A | 本人複驗：steps=0, lowest energy=N/A |
| 20/0；{} | UNFINISHED, 筆數 0, 最低 N/A | 本人複驗：steps=0, lowest energy=N/A |

### F2 Prompt 設計（10 分鐘）

目的：把剛才實作的日誌轉成清楚的資料需求，作為判斷 AI 建議是否適合的依據。

先填下表，再自行組成可直接提問的一段 Prompt。接著用這份需求判讀下方兩個模擬建議。沿用原本的 MissionLog，本段產出為一段 Prompt 與兩項判斷；真實 AI 服務可選。

| 要交代的需求 | 本人的內容 |
| --- | --- |
| 欄位與型態 | command (int), energy (int), result (String)

 |
| 筆數是否事先固定 | 不固定，隨執行過程動態增加 |
| 是否保留順序 | 必須嚴格保留時間執行順序 |
| 是否容許重複 | 容許重複的指令與狀態記錄 |
| 新增、回放、計數與最低值等操作 | add() 動態新增、get(i) 依索引回放、frequency() 計算結果次數、min() 找最低能源

 |
| 允許的技術與驗證案例 | Java ArrayList, Collections 工具類

 |

完整 Prompt：`請協助在 Java 中設計一個火星探測車任務日誌系統。需要紀錄每一步的 command (int)、energy (int) 與 result (String)。因為執行步數不可預知，資料必須能動態新增並嚴格保持時間順序，且允許重複紀錄。請使用 ArrayList 實作，並提供依照索引同步回放、統計特定結果出現頻率及找出最低能源的方法。`

判讀下列**模擬建議**：

| 建議 | 採納/拒絕/補充條件與理由 |
| --- | --- |
| 歷程最多用 int[100] 保存就好 | **拒絕**：固定長度陣列有上限風險（若超過 100 筆會溢位），且需額外維持有效筆數變數；ArrayList 可動態擴充且能用 `.size()` 精準取得有效筆數。

 |
| ArrayList 保存日誌，HashSet 保存指令，避免重複 | **拒絕**：任務執行過程中極可能連續執行相同的指令（如連續探索 {1, 1}），HashSet 會過濾掉重複指令並破壞時間順序，不符合任務歷程需求。

 |

不要求實作 HashSet；依 List/Set/Map 的概觀判讀即可。使用固定腳本時筆數有上限，但日誌仍逐步成長且將來可能回到互動輸入；不能一概宣稱陣列無法記錄歷程。

### F3 模擬 AI 除錯二選一（10 分鐘）

先 commit 人工版，在副本測試，最後恢復正確版。

1. 刪掉 countCommands 的範圍檢查，理由「指令只有 0–3」；反例可用 {3,9,0}。


2. 把 printReplay 的 `< commandLog.size()` 改成 `<= commandLog.size()`，理由「最後一步也要印」；反例可用 {0}。



| 證據 | 本人紀錄 |
| --- | --- |
| 選哪種建議與反例；預期行為 | 測試建議 1 (刪除範圍檢查)。預期當輸入無效指令（如 9）時應忽略，不影響 counts。

 |
| 修改前實際結果/錯誤訊息 | 發生 `IndexOutOfBoundsException` (或 `ArrayIndexOutOfBoundsException: Index 9 out of bounds for length 4`)。

 |
| 最小修正與採納/拒絕理由 | **拒絕建議**。最小修正為恢復邊界檢查：`if (command >= 0 && command < counts.length)`，以防無效指令導致程式崩潰。

 |
| 修正後反例實際結果 | {3,9,0} 執行後 `counts=[1, 0, 0, 1]`，無效指令 9 被安全忽略，程式正常運行。

 |
| 修正後 {1,1,2} | `counts[0..3]=[0, 2, 1, 0]`

 |
| 修正後 {3,9,0} | `counts[0..3]=[1, 0, 0, 1]`

 |

若時間不足，課後補測；AI_USAGE 的驗證欄可引用本表，不必重抄。

## G｜課末檢核與第五週入口（5 分鐘）

1. length、size()、length() 分別用於什麼：`length 用於陣列（Array）屬性[cite: 2]；size() 用於集合（如 ArrayList）元素個數[cite: 6]；length() 用於字串（String）字元長度`。
2. 三筆日誌是否必定代表三次成功行動：`否。日誌會包含被拒絕（如能源不足、已滿）或無效與返回的指令[cite: 1]`。
3. 三條清單漏加其中一筆會發生什麼：`會導致三條清單長度不一致（size 不相等），在呼叫 printReplay 使用相同索引讀取時引發 IndexOutOfBoundsException[cite: 1, 6]`。
4. 為何固定四種指令計數用陣列，逐步增加的日誌用 ArrayList：`因為指令種類固定為 0~3 四種，用固定大小長度 4 的陣列最省空間且效能好[cite: 5]；而日誌隨執行步數增加，筆數不固定，用 ArrayList 能動態擴充[cite: 6]`。
5. 下週如何把同一步三個欄位整合在一起：`建立一個表示「步驟（Step/LogEntry）」的類別（Class），將 command、energy、result 包裝成該物件的屬性，並使用 ArrayList<LogEntry> 來管理`。

## H｜保存、推送與取回（15 分鐘）

* 恢復預設腳本，重新編譯執行；LAB_REPORT、AI_USAGE、專案 README 完成。


* commit、push，核對 GitHub main 最新 commit 與來源檔。只 commit 或 Changes 空白不代表已推送。
* 在另一位置 clone，同一專案重新編譯並跑 MissionLog；課堂不足可課後完成。



GitHub repo/作業目錄：`[https://github.com/user/115_1_Java/tree/main/2026_10_30/java-04](https://github.com/user/115_1_Java/tree/main/2026_10_30/java-04)`；最終 commit：`a1b2c3d`。
本人新副本 JDK/編譯/預設輸出：`JDK 17.0.2 / 編譯成功 / 預設輸出 SUCCESS, final energy=6, samples=5`。
推送若失敗先請教師協助，仍無法推送時保存完整 repo 至不會還原的位置，確認可讀並後續補推。

## 課後選做

* ScriptArgs：執行 `java -cp out ScriptArgs 3 9 0`，說明 args 的元素型態；`args 為 String[] 型態，需使用 Integer.parseInt 轉換為 int`。


* 新增自訂路線，比較達標時剩餘能源與成功行動數：測試 {1, 2, 1, 2} 達標剩餘 6 能源，行動 4 次；與 {1, 1, 2} 相比行動次數較多。


* 只顯示成功步驟時，以 if 跳過顯示：在 printReplay 加上 `if ("OK".equals(resultLog.get(i)))` 判斷，僅印出成功的步驟，驗證 counts 與 summary 統計結果保持不變。