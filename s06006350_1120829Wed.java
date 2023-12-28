import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 定義Calculator類別
public class s06006350_1120829Wed {
    private JFrame mainFrame;  // 主視窗
    private JTextField inputField;  // 文本輸入框
    private String currentOperator;  // 當前選擇的運算符
    private double operandOne, operandTwo, calcResult;  // 用於運算的數字和計算結果

    // 主函式，程序的入口點
    public static void main(String[] args) {
        // 啟動Swing界面
        SwingUtilities.invokeLater(() -> {
            try {
                s06006350_1120829Wed calcWindow = new s06006350_1120829Wed();  // 創建計算機實例
                calcWindow.mainFrame.setVisible(true);  // 設置主視窗為可見
            } catch (Exception e) {
                e.printStackTrace();  // 印出異常信息
            }
        });
    }

    // 建構子，初始化計算機
    public s06006350_1120829Wed() {
        initializeUI();  // 初始化用戶界面
    }

    // 初始化用戶界面
    private void initializeUI() {
        mainFrame = new JFrame();  // 創建主視窗
        mainFrame.setBounds(100, 100, 450, 700);  // 設置視窗位置和大小
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 設置關閉行為
        mainFrame.getContentPane().setLayout(null);  // 使用絕對布局

        // 創建文本輸入框並添加到主視窗
        inputField = new JTextField();
        inputField.setBounds(10, 11, 350, 50);  // 設置位置和大小
        mainFrame.getContentPane().add(inputField);

        // 定義按鈕的標籤
        String[] btnLabels = {
            "%", "CE", "C", "Delete",
            "1/x", "x^2", "x^(1/2)", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        // 初始化按鈕位置
        int posX = 10, posY = 60;

        // 循環創建按鈕
        for (String label : btnLabels) {
            JButton btn = new JButton(label);  // 創建按鈕
            btn.setBounds(posX, posY, 80, 80);  // 設置按鈕位置和大小
            btn.addActionListener(new CustomClickListener());  // 添加事件監聽
            mainFrame.getContentPane().add(btn);  // 將按鈕添加到主視窗
            posX += 90;  // 更新x座標
            if (posX > 300) {  // 檢查是否需要換行
                posX = 10;
                posY += 90;
            }
        }
    }

    // 定義按鈕點擊事件的內部類
    private class CustomClickListener implements ActionListener {
        // 從文本輸入框 (inputField) 獲取用戶輸入的數字並將其轉換為 double 類型。
        private double getInputAsDouble() {
            return Double.parseDouble(inputField.getText());
        }
        // 定義點擊事件的行為
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();  // 獲取點擊的按鈕的標籤（例如，"+" 或 "1/x"）

            // 使用 switch-case 結構來根據點擊的按鈕（cmd）執行不同的操作
            switch (cmd) {
                case "C":  // 清除當前輸入
                    inputField.setText("");  // 將文本輸入框清空
                    break;
                case "CE":  // 重置計算機狀態
                    operandOne = operandTwo = calcResult = 0;  // 將所有運算數和計算結果重置為0
                    currentOperator = "";  // 清除當前運算符
                    inputField.setText("");  // 將文本輸入框清空
                    break;
                case "Delete":  // 刪除最後一個字符
                    String tempStr = inputField.getText();  // 獲取當前文本輸入框的內容
                    inputField.setText(tempStr.substring(0, tempStr.length() - 1));  // 刪除最後一個字符
                    break;
                case "+":  // 設置加法運算符
                case "-":  // 設置減法運算符
                case "×":  // 設置乘法運算符
                case "÷":  // 設置除法運算符
                    operandOne = getInputAsDouble();  // 將文本輸入框的內容轉為數字，並設為第一運算數
                    currentOperator = cmd;  // 設置當前選擇的運算符
                    inputField.setText("");  // 清空文本輸入框以輸入第二運算數
                    break;
                case "=":  // 計算結果
                    operandTwo = getInputAsDouble();;  // 將文本輸入框的內容轉為數字，並設為第二運算數
                    // 根據當前選擇的運算符進行計算
                    switch (currentOperator) {
                        case "+":
                            calcResult = operandOne + operandTwo;  // 進行加法計算
                            break;
                        case "-":
                            calcResult = operandOne - operandTwo;  // 進行減法計算
                            break;
                        case "×":
                            calcResult = operandOne * operandTwo;  // 進行乘法計算
                            break;
                        case "÷":
                            if (operandTwo != 0) {  // 檢查第二運算數是否為0
                                calcResult = operandOne / operandTwo;  // 進行除法計算
                            } else {
                                JOptionPane.showMessageDialog(mainFrame, "不能除以零");  // 若第二運算數為0，則彈出錯誤對話框
                            }
                            break;
                    }
                    inputField.setText(String.valueOf(calcResult));  // 將計算結果顯示在文本輸入框中
                    break;
            
                // 求倒數
                case "1/x":
                    calcResult = 1 / getInputAsDouble();  // 將輸入框的數字轉換為 double 並求倒數
                    inputField.setText(String.valueOf(calcResult));  // 將計算結果轉換為字符串並顯示在輸入框中
                    break;

                // 求平方
                case "x^2":
                    calcResult = Math.pow(getInputAsDouble(), 2);  // 將輸入框的數字轉換為 double 並求平方
                    inputField.setText(String.valueOf(calcResult));  // 將計算結果轉換為字符串並顯示在輸入框中
                    break;

                // 求平方根
                case "x^(1/2)":
                    calcResult = Math.sqrt(getInputAsDouble());  // 將輸入框的數字轉換為 double 並求平方根
                    inputField.setText(String.valueOf(calcResult));  // 將計算結果轉換為字符串並顯示在輸入框中
                    break;

                // 求百分比（將數字除以100）
                case "%":
                    calcResult = getInputAsDouble() / 100;  // 將輸入框的數字轉換為 double 並除以100
                    inputField.setText(String.valueOf(calcResult));  // 將計算結果轉換為字符串並顯示在輸入框中
                    break;

                // 變換正負號（乘以 -1）
                case "+/-":
                    calcResult = -1 * getInputAsDouble();  // 將輸入框的數字轉換為 double 並乘以 -1
                    inputField.setText(String.valueOf(calcResult));  // 將計算結果轉換為字符串並顯示在輸入框中
                    break;

                // 添加小數點（檢查輸入框中是否已有小數點）
                case ".":
                    // 檢查輸入框中是否已包含小數點
                    if (!inputField.getText().contains(".")) {
                        inputField.setText(inputField.getText() + ".");  // 如果沒有，則在當前數字後添加小數點
                    }
                    break;
                
                default:  // 添加數字或其他字符到輸入框
                    inputField.setText(inputField.getText() + cmd);  //這一行做的事情是將當前inputField（文本輸入框）裡的文字和新按下的按鈕（這個按鈕的文字是存放在cmd變數中）結合起來，然後再設定回inputField。
                    break;
            }
        }
    }
}