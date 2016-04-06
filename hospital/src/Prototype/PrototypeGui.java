package Prototype;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Prototype.Patient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class PrototypeGui {

private JFrame frame;
private JTextField PPStextField;
private JTextField NOKTextField;
private JTextField AllergytextField;
private JTextField HospitalIDtextField;
private JTextField ReferanceTextField;
private JTextField RefferalTextField;

/**
* Launch the application.
*/
public static void main(String[] args) {
int menu_option;
  Scanner keyIn = new Scanner(System.in);
  Patient patient = new Patient();
  Ward ward = new Ward();

EventQueue.invokeLater(new Runnable() {
public void run() {
try {
PrototypeGui window = new PrototypeGui();
window.frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

/**
* Create the application.
*/
public PrototypeGui() {
initialize();
}

/**
* Initialize the contents of the frame.
*/
private void initialize() {
frame = new JFrame();
frame.setBounds(100, 100, 776, 445);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(null);
JLabel lblNewLabel = new JLabel("PPS");
lblNewLabel.setBounds(53, 56, 146, 28);
frame.getContentPane().add(lblNewLabel);
JLabel lblNewLabel_1 = new JLabel("Next Of Kin");
lblNewLabel_1.setBounds(52, 95, 147, 28);
frame.getContentPane().add(lblNewLabel_1);
PPStextField = new JTextField();
PPStextField.setBounds(282, 56, 209, 28);
frame.getContentPane().add(PPStextField);
PPStextField.setColumns(10);
NOKTextField = new JTextField();
NOKTextField.setBounds(282, 99, 209, 28);
frame.getContentPane().add(NOKTextField);
NOKTextField.setColumns(10);
JLabel lblNewLabel_2 = new JLabel("Allergy");
lblNewLabel_2.setBounds(53, 140, 146, 28);
frame.getContentPane().add(lblNewLabel_2);
AllergytextField = new JTextField();
AllergytextField.setBounds(282, 140, 209, 28);
frame.getContentPane().add(AllergytextField);
AllergytextField.setColumns(10);

JButton btnNewButton_1 = new JButton("Cancel");
btnNewButton_1.setBounds(610, 344, 101, 28);
frame.getContentPane().add(btnNewButton_1);
JLabel lblNewLabel_3 = new JLabel("Hospital ID");
lblNewLabel_3.setBounds(53, 11, 146, 28);
frame.getContentPane().add(lblNewLabel_3);
HospitalIDtextField = new JTextField();
HospitalIDtextField.setBounds(280, 11, 211, 34);
frame.getContentPane().add(HospitalIDtextField);
HospitalIDtextField.setColumns(10);
JLabel lblNewLabel_4 = new JLabel("History");
lblNewLabel_4.setBounds(53, 192, 146, 28);
frame.getContentPane().add(lblNewLabel_4);
JLabel lblNewLabel_5 = new JLabel("Referance");
lblNewLabel_5.setBounds(53, 231, 146, 28);
frame.getContentPane().add(lblNewLabel_5);
JTextArea HistorytextArea = new JTextArea();
HistorytextArea.setBounds(282, 194, 209, 22);
frame.getContentPane().add(HistorytextArea);
ReferanceTextField = new JTextField();
ReferanceTextField.setBounds(282, 235, 209, 20);
frame.getContentPane().add(ReferanceTextField);
ReferanceTextField.setColumns(10);
JLabel lblNewLabel_6 = new JLabel("Refferal");
lblNewLabel_6.setBounds(53, 270, 146, 22);
frame.getContentPane().add(lblNewLabel_6);
RefferalTextField = new JTextField();
RefferalTextField.setBounds(282, 271, 209, 20);
frame.getContentPane().add(RefferalTextField);
RefferalTextField.setColumns(10);
JButton Add = new JButton("Add");
Add.setBounds(479, 344, 101, 28);
frame.getContentPane().add(Add);
Add.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String Patientno=HospitalIDtextField.getText();
String patientPPS=PPStextField.getText();
String patientnok=NOKTextField.getText();
String allergy=AllergytextField.getText();
String history=HistorytextArea.getText();
String referance=ReferanceTextField.getText();
String referance_class=RefferalTextField.getText();
Patient addpatient = new Patient(0, patientPPS, patientnok, allergy, history, referance, referance_class, referance_class, referance_class, referance_class, referance_class, referance_class, referance_class);
       int addStatus = addpatient.add(addpatient);
       if(addStatus==1)
       {
        System.out.println("patient added to datbase");
       }
}
});
}
}