package edu.lab11;

import edu.lab11.generic.CMyLinkedList;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CMainForm extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JButton strButton;
    private JTextField strTextField;
    private JList strList;
    private JTextField strIdField;
    private JButton StrClear;
    private JTextField strFind;
    private JButton strFindId;
    private JButton strFindIndex;
    private JTextField intTextField;
    private JButton IntButton;
    private JList intList;
    private JTextField intIDField;
	private JTextField personIdField;
    private JTextField personTextFname;
    private JComboBox personYear;
    private JButton personButton;
    private JList personList;
    private JTextField personTextName;
    private JButton imgButton;
    private JList imgList;
    private JTextField imgIdField;
    private final DefaultListModel<Object> modelStr;
    private final DefaultListModel<Object> modelInt;
    private final CMyLinkedList<Integer, String> myListStr;
    private final CMyLinkedList<Byte, Integer> myListInt;

    private final DefaultListModel<Object> modelPerson;
    private final DefaultListModel<Object> modelImg;
    private final CMyLinkedList<Long,CPerson>myListPerson;
    private final CMyLinkedList<Integer,ImageIcon> myListImg;


    public CMainForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        myListStr=new CMyLinkedList<>();
        myListInt=new CMyLinkedList<>();
        modelStr=new DefaultListModel<>();
        modelInt=new DefaultListModel<>();
        modelPerson = new DefaultListModel<>();
        modelImg = new DefaultListModel<>();
        myListPerson = new CMyLinkedList<>();
        myListImg = new CMyLinkedList<>();

        personList.setModel(modelPerson);
        imgList.setModel(modelImg);
        imgList.setCellRenderer(new CImgListRenderer(this.myListImg));

        strList.setModel(modelStr);
        intList.setModel(modelInt);
        strButton.addActionListener(e -> StrButtonClick());
        StrClear.addActionListener(e -> StrButtonClearClick());
        strFindIndex.addActionListener(e -> strGetByIndexClick());
        strFindId.addActionListener(e -> strGetByIdClick());
        IntButton.addActionListener(e -> intButtonClick());
        personButton.addActionListener(e ->personButtonClick());
        imgButton.addActionListener(e->imageButtonClick());


    }

    private void StrButtonClick() {
        try {
            Integer n = Integer.parseInt(strIdField.getText());
            String text = strTextField.getText().trim();
            if (text.compareTo("") != 0) {
                myListStr.add(n, text);
            }
            myListStr.printToList(modelStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Niepoprawna wartość. Komumikat: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void StrButtonClearClick() {
        myListStr.clear();
        myListStr.printToList(modelStr);
    }

    private void strGetByIndexClick() {
        int idx = Integer.parseInt(strFind.getText());
        String s = myListStr.getByIndex(idx);
        JOptionPane.showMessageDialog(this, "Zwrócona wartość: " + s);
    }

    private void strGetByIdClick() {
        int id = Integer.parseInt(strFind.getText());
        String s = myListStr.getById(id);
        if (s != null) {
            JOptionPane.showMessageDialog(this, "Zwrócona wartość: " + s);
        } else {
            JOptionPane.showMessageDialog(this, "Brak elementu o id =" + id);
        }
    }

    private void intButtonClick() {
        Byte d = Byte.parseByte(intIDField.getText());
        Integer v = Integer.parseInt(intTextField.getText());
        myListInt.add(d, v);
        myListInt.printToList(modelInt);
    }

    private void personButtonClick()
    {
        Long n = Long.parseLong(personIdField.getText());
        CPerson person = new CPerson(
                personTextFname.getText(),
                personTextName.getText(),
                Integer.parseInt(personYear.getItemAt(
                        personYear.getSelectedIndex()).toString())
        );
    myListPerson.add(n,person);
    myListPerson.printToList(modelPerson);
    }
    private void imageButtonClick(){
        Integer n = Integer.parseInt(imgIdField.getText());
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            myListImg.add(n,
                    new ImageIcon(fc.getSelectedFile().getAbsolutePath()));
            myListImg.printToList(modelImg);
        }
    }

}
