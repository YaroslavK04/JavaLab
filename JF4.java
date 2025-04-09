
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.io.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;



public class JF4 extends javax.swing.JFrame {
    DefaultTableModel model; 
    public JF4() {
        initComponents();
        
        
        model = (DefaultTableModel) jTable1.getModel();
    }

    @SuppressWarnings("unchecked")
    
    public class InvalidValueException extends Exception {
        public InvalidValueException(String message) {
            super(message);
        }
    }
     private void parseAndValidate(String DowbLimit,String UpLimit,String Step) throws InvalidValueException {
       
        try {
            double down = Double.parseDouble(DowbLimit);
            double up = Double.parseDouble(UpLimit);
            double step = Double.parseDouble(Step);
            if (down < 0.000001 || down > 1000000 || up < 0.000001 || up > 1000000 || step < 0.000001 || step > 1000000 ) {
                ResetField();
                throw new JF4.InvalidValueException("Число должно быть в диапазоне от 0.000001 до 1000000."); 
            }
            if ( down < 0 || up < 0 || step < 0){
                ResetField();
                throw new JF4.InvalidValueException("Введены неккоректные значения"); 
            }
            if (down >= up) {
                throw new JF4.InvalidValueException("Нижний предел должен быть меньше верхнего.");
            }   

            if (step <= 0) {
                throw new JF4.InvalidValueException("Шаг должен быть положительным числом.");
            }
            if(step>(up-down)){
                throw new JF4.InvalidValueException("Шаг не должен превышать интервал интегрирования.");
            }
        } catch (NumberFormatException e) {
            throw new JF4.InvalidValueException("Введено некорректное число" );
        }
    }
    

    private LinkedList<RecIntegral> SaveTable = new LinkedList<>();
    public void saveToTextFile(File file) throws IOException {
         
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (RecIntegral row : SaveTable) {
                writer.println(String.join(";", row.Ret()));
            }
        }
    }


    public void loadFromTextFile(File file) throws IOException {
        SaveTable.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loadFile = line.split(";");
                SaveTable.add(new RecIntegral(loadFile[0],loadFile[1],loadFile[2],loadFile[3]));

            }
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        StepTxt = new javax.swing.JTextPane();
        Delite = new javax.swing.JButton();
        Result = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DownLimit = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UpLimit = new javax.swing.JTextPane();
        Clear = new javax.swing.JButton();
        Rec = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        SaveInFile = new javax.swing.JMenu();
        SaveTxt = new javax.swing.JMenuItem();
        LoadTxt = new javax.swing.JMenuItem();
        SaveBin = new javax.swing.JMenuItem();
        LoadBin = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.setBackground(new java.awt.Color(45, 50, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(577, 400));

        jTable1.setBackground(new java.awt.Color(103, 111, 157));
        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "нижняя граница", "верхняя граница", "шаг", "результат"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setSelectionBackground(new java.awt.Color(249, 209, 122));
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable1);

        StepTxt.setBackground(new java.awt.Color(235, 235, 235));
        jScrollPane3.setViewportView(StepTxt);

        Delite.setBackground(new java.awt.Color(249, 209, 122));
        Delite.setText("удалить ");
        Delite.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Delite.setMaximumSize(new java.awt.Dimension(56, 22));
        Delite.setMinimumSize(new java.awt.Dimension(56, 22));
        Delite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeliteActionPerformed(evt);
            }
        });

        Result.setBackground(new java.awt.Color(249, 209, 122));
        Result.setText("вычислить");
        Result.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultActionPerformed(evt);
            }
        });

        Add.setBackground(new java.awt.Color(249, 209, 122));
        Add.setText("добавить");
        Add.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        DownLimit.setBackground(new java.awt.Color(235, 235, 235));
        jScrollPane1.setViewportView(DownLimit);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Верхняя граница");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Нижняя граница");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Шаг");

        UpLimit.setBackground(new java.awt.Color(235, 235, 235));
        jScrollPane2.setViewportView(UpLimit);

        Clear.setBackground(new java.awt.Color(249, 209, 122));
        Clear.setText("очистить");
        Clear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        Rec.setBackground(new java.awt.Color(249, 209, 122));
        Rec.setText("заполнить");
        Rec.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Rec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(42, 42, 42)
                                            .addComponent(jLabel3)))
                                    .addGap(23, 23, 23))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(87, 87, 87)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Delite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Result, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(Rec, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Delite, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Result)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        SaveInFile.setText("File");

        SaveTxt.setText("Сохранить txt");
        SaveTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTxtActionPerformed(evt);
            }
        });
        SaveInFile.add(SaveTxt);

        LoadTxt.setText("Загрузить txt");
        LoadTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadTxtActionPerformed(evt);
            }
        });
        SaveInFile.add(LoadTxt);

        SaveBin.setText("Сохранить bin");
        SaveBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBinActionPerformed(evt);
            }
        });
        SaveInFile.add(SaveBin);

        LoadBin.setText("Загрузить bin");
        LoadBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadBinActionPerformed(evt);
            }
        });
        SaveInFile.add(LoadBin);

        jMenuBar1.add(SaveInFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public void saveToBinaryFile(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(SaveTable);
        }
    }

    public void loadFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            SaveTable = (LinkedList<RecIntegral>) in.readObject();
        }
    }
    
    private void updateTable() {
        for ( RecIntegral count: SaveTable){
            model.addRow(count.Ret());
        }
    }
    private void ResetField(){
        StepTxt.setText("");
        DownLimit.setText("");
        UpLimit.setText("");
    }
    private void DeliteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeliteActionPerformed
       if (jTable1.getSelectedRow() > -1){
           int getRow = jTable1.getSelectedRow();
           SaveTable.remove(getRow);
           model.removeRow(jTable1.getSelectedRow());
       } else {
           JOptionPane.showMessageDialog(JF4.this, "Выбери строку для удаления");
       }
    }//GEN-LAST:event_DeliteActionPerformed

    private void ResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultActionPerformed
        if (jTable1.getSelectedRow() > -1){
            
            int getRow = jTable1.getSelectedRow();
            try {
                parseAndValidate(jTable1.getValueAt(getRow, 0).toString(),jTable1.getValueAt(getRow, 1).toString(),jTable1.getValueAt(getRow, 2).toString());   
            } catch (InvalidValueException e) {
                JOptionPane.showMessageDialog(JF4.this, e.getMessage(), "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            }
            double StepD = Double.parseDouble(jTable1.getValueAt(getRow, 2).toString());
            double DownLimitD = Double.parseDouble(jTable1.getValueAt(getRow, 0).toString());
            double UpLimitD = Double.parseDouble(jTable1.getValueAt(getRow, 1).toString());
            double Square = 0;
            int CountThread = 4;
            ExecutorService executor = Executors.newFixedThreadPool(CountThread);
            List<Future<Double>> ListFuture = new ArrayList<>();
            
            long start = System.nanoTime();
            double range = (UpLimitD - DownLimitD)/CountThread;
            double down = DownLimitD;
            
            for (int i = 0; i < CountThread; i++)
            { 
                double up = (i == CountThread - 1) ? UpLimitD : Math.floor((down + range) / StepD) * StepD;
                Callable<Double> task = new MyFuture(down, up, StepD);
                ListFuture.add(executor.submit(task));
                down = up;

            }
            for(var futures: ListFuture){
                try{
                    Square += futures.get();
                    
                }catch(InterruptedException e){
                    JOptionPane.showMessageDialog(JF4.this, e.getMessage(), "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
                } catch (ExecutionException ex) {
                    Logger.getLogger(JF4.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(Square);
            long end = System.nanoTime() - start ;
            System.out.println("Time work : " + end);

            SaveTable.set(getRow,new RecIntegral(jTable1.getValueAt(getRow, 0).toString(),jTable1.getValueAt(getRow, 1).toString(),jTable1.getValueAt(getRow, 2).toString(),String.format("%.5f", Square)));
            jTable1.setValueAt(Square, getRow, 3);

        } else {
           JOptionPane.showMessageDialog(JF4.this, "Выбери строку для вычисления");
        }
    }//GEN-LAST:event_ResultActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
            
        if (DownLimit.getText().isEmpty() || UpLimit.getText().isEmpty() || StepTxt.getText().isEmpty()  ) {
            JOptionPane.showMessageDialog(JF4.this, "Введены некорректные значения");
            return; 
        }
        try{
            parseAndValidate(DownLimit.getText(), UpLimit.getText(),StepTxt.getText());
            SaveTable.add(new RecIntegral(DownLimit.getText(), UpLimit.getText(),StepTxt.getText(),"0"));
            model.addRow(new Object[]{ DownLimit.getText(), UpLimit.getText(),StepTxt.getText(),0});

        }catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(JF4.this, e.getMessage(), "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
        }
        ResetField();
    }//GEN-LAST:event_AddActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
           model = (DefaultTableModel) jTable1.getModel();
           model.setRowCount(0);
           jTable1.setModel(model);
    }//GEN-LAST:event_ClearActionPerformed

    private void RecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecActionPerformed
        if (!SaveTable.isEmpty()){
            for ( RecIntegral count: SaveTable){
                model.addRow(count.Ret());
            }
        } else {
           JOptionPane.showMessageDialog(JF4.this, "Список пуст");
        }  
    }//GEN-LAST:event_RecActionPerformed

    private void SaveTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTxtActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(JF4.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                saveToTextFile(file);
                JOptionPane.showMessageDialog(JF4.this, "Данные успешно сохранены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(JF4.this, "Ошибка сохранения: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_SaveTxtActionPerformed

    private void LoadTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadTxtActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(JF4.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                loadFromTextFile(file);
                updateTable();
                JOptionPane.showMessageDialog(JF4.this, "Данные успешно загружены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(JF4.this, "Ошибка загрузки: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_LoadTxtActionPerformed

    private void SaveBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBinActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();            
            try {
                saveToBinaryFile(file);
                JOptionPane.showMessageDialog(null, "Данные успешно сохранены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ошибка сохранения: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_SaveBinActionPerformed

    private void LoadBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadBinActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(JF4.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                loadFromBinaryFile(file);
                updateTable();
                JOptionPane.showMessageDialog(JF4.this, "Данные успешно загружены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(JF4.this, "Ошибка загрузки: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_LoadBinActionPerformed

    public static void main(String args[]) {
  
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Clear;
    private javax.swing.JButton Delite;
    private javax.swing.JTextPane DownLimit;
    private javax.swing.JMenuItem LoadBin;
    private javax.swing.JMenuItem LoadTxt;
    private javax.swing.JButton Rec;
    private javax.swing.JButton Result;
    private javax.swing.JMenuItem SaveBin;
    private javax.swing.JMenu SaveInFile;
    private javax.swing.JMenuItem SaveTxt;
    private javax.swing.JTextPane StepTxt;
    private javax.swing.JTextPane UpLimit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
