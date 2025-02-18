import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.LinkedList;


public class NewJFrame extends javax.swing.JFrame {
    DefaultTableModel model; 
    public NewJFrame() {
        initComponents();
        model = (DefaultTableModel) jTable1.getModel();
    }

    @SuppressWarnings("unchecked")
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
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public class RecIntegral{
        private LinkedList<String[]> list = new LinkedList<>();

        public void RecTable(String DownL, String UpL, String Step,String Result ){
            list.add(new String[]{DownL,UpL,Step,Result});
        }
        
        public String[][] GetTable() {
            String[][] records = new String[list.size()][4];
            for (int i = 0; i < list.size(); i++) {
                records[i] = list.get(i);
            }
            return records;
        }
        public void DelElemList(int NumberElem){
            list.remove(NumberElem);
        }
        public void ChangeValue(int NumberElem,String DownL, String UpL, String Step,String Result ){
            list.set(NumberElem, new String[]{DownL,UpL,Step,Result});
        }
        public boolean hasRecords() {
            return !list.isEmpty();
        }
    }
    
    RecIntegral SaveTable = new RecIntegral();
    private void ResetField(){
        StepTxt.setText("");
        DownLimit.setText("");
        UpLimit.setText("");
    }
    private void DeliteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeliteActionPerformed
       if (jTable1.getSelectedRow() > -1){
           int getRow = jTable1.getSelectedRow();
           SaveTable.DelElemList(getRow);
           model.removeRow(jTable1.getSelectedRow());
       } else {
           JOptionPane.showMessageDialog(NewJFrame.this, "Выбери строку для удаления");
       }
    }//GEN-LAST:event_DeliteActionPerformed

    private void ResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultActionPerformed
        if (jTable1.getSelectedRow() > -1){
            
            int getRow = jTable1.getSelectedRow();
            double StepD = Double.parseDouble(jTable1.getValueAt(getRow, 2).toString());
            double DownLimitD = Double.parseDouble(jTable1.getValueAt(getRow, 0).toString());
            double UpLimitD = Double.parseDouble(jTable1.getValueAt(getRow, 1).toString());
            double Square = 0;
            if (StepD <= 0 || UpLimitD < 0 || DownLimitD < 0 || UpLimitD < DownLimitD  ) {
                JOptionPane.showMessageDialog(NewJFrame.this, "Введены некорректные значения");
                return; 
            }
            for (double i = DownLimitD; i < UpLimitD; i += StepD) {

               Square += i + StepD > UpLimitD ? (UpLimitD - i) * (Math.tan(i) + Math.tan(UpLimitD)) / 2 :  (StepD / 2) * (Math.tan(i) + Math.tan(i + StepD));
               
            }
            String SquareStr = String.format("%.5f", Square);
            SaveTable.ChangeValue(getRow,jTable1.getValueAt(getRow, 0).toString(),jTable1.getValueAt(getRow, 1).toString(),jTable1.getValueAt(getRow, 2).toString(),SquareStr);
            jTable1.setValueAt(Square, getRow, 3);

        } else {
           JOptionPane.showMessageDialog(NewJFrame.this, "Выбери строку для вычисления");
        }
    }//GEN-LAST:event_ResultActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
            
        if (DownLimit.getText().isEmpty() || UpLimit.getText().isEmpty() || StepTxt.getText().isEmpty()  ) {
            JOptionPane.showMessageDialog(NewJFrame.this, "Введены некорректные значения");
            return; 
        }
        model.addRow(new Object[]{ DownLimit.getText(), UpLimit.getText(),StepTxt.getText(),0});
   
        SaveTable.RecTable(DownLimit.getText(), UpLimit.getText(),StepTxt.getText(),"0");

        
        ResetField();
    }//GEN-LAST:event_AddActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
           model = (DefaultTableModel) jTable1.getModel();
           model.setRowCount(0);
           jTable1.setModel(model);
    }//GEN-LAST:event_ClearActionPerformed

    private void RecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecActionPerformed
        if (SaveTable.hasRecords()){
            String[][] GetTb = SaveTable.GetTable();
            for ( int i = 0; i < GetTb.length;i++){
                model.addRow(GetTb[i]);
            }
        } else {
           JOptionPane.showMessageDialog(NewJFrame.this, "Список пуст");
        }
        
    }//GEN-LAST:event_RecActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
  
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Clear;
    private javax.swing.JButton Delite;
    private javax.swing.JTextPane DownLimit;
    private javax.swing.JButton Rec;
    private javax.swing.JButton Result;
    private javax.swing.JTextPane StepTxt;
    private javax.swing.JTextPane UpLimit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
