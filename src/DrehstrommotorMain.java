import javax.swing.*;

public class DrehstrommotorMain {
    public static void main(String[] args) throws Exception {
        createGUI("Drehstrommotor", 525, 368);
    }

    public static void createGUI(String frameName,int frameX, int frameY){
        JFrame frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameX, frameY);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        DoubleTextField textfield1 = DrehzahlEingabe(panel);
        DoubleTextField textfield2 = DrehmomentEingabe(panel);
        JComboBox<Integer> comboBox1 = SpannungEingabe(panel);
        DoubleTextField textfield4 = StromstärkeEingabe(panel);
        DoubleTextField textfield5 = LeistungsfaktorEingabe(panel);
        DoubleTextField textfield6 = UbersetzungsverhaltnisEingabe(panel);
        JButton button = new JButton("Berechnen");
        button.setBounds(5,115,500,43);
        JLabel titleLabel1 = new JLabel("Leistungsaufnahme:");
        titleLabel1.setBounds(5,193,150,15);
        JLabel titleLabel2 = new JLabel("Abgegebene Leistung:");
        titleLabel2.setBounds(5,228,150,15);
        JLabel titleLabel3 = new JLabel("Verlustleistung:");
        titleLabel3.setBounds(5,263,150,15);
        JLabel titleLabel4 = new JLabel("Wirkungsgrad:");
        titleLabel4.setBounds(5,298,150,15);
        JLabel resultLabel1 = new JLabel();
        resultLabel1.setBounds(170,193,150,15);
        JLabel resultLabel2 = new JLabel();
        resultLabel2.setBounds(170,228,150,15);
        JLabel resultLabel3 = new JLabel();
        resultLabel3.setBounds(170,263,150,15);
        JLabel resultLabel4 = new JLabel();
        resultLabel4.setBounds(170,298,150,15);
        panel.add(button);
        panel.add(titleLabel1);
        panel.add(titleLabel2);
        panel.add(titleLabel3);
        panel.add(titleLabel4);
        panel.add(resultLabel1);
        panel.add(resultLabel2);
        panel.add(resultLabel3);
        panel.add(resultLabel4);
        Berechnen(panel, textfield1, textfield2, comboBox1, textfield4, textfield5, textfield6, titleLabel1, titleLabel2, titleLabel3, titleLabel4, resultLabel1, resultLabel2, resultLabel3, resultLabel4, button);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

            //Berechnung:
            public static void Berechnen(JPanel panel, DoubleTextField textfield1, DoubleTextField textfield2, JComboBox<Integer> comboBox1, DoubleTextField textfield4, DoubleTextField textfield5,  DoubleTextField textfield6,
            JLabel titleLabel1, JLabel titleLabel2, JLabel titleLabel3,JLabel titleLabel4, JLabel resultLabel1, JLabel resultLabel2, JLabel resultLabel3, JLabel resultLabel4, JButton button){
        
                button.addActionListener(e -> {
                    // Hole den eingegebenen Wert
                    String drehzahlString = textfield1.getText();
                    int drehzahl = drehzahlString.isEmpty() ? 0 : Integer.parseInt(drehzahlString);
                   
                    String drehmomentString = textfield2.getText();
                    drehmomentString = drehmomentString.replace(",",".");
                    double drehmoment = drehmomentString.isEmpty() ? 0 : Double.parseDouble(drehmomentString);
                    if (drehmoment < 1.0 || drehmoment > 6.0){
                        JOptionPane.showMessageDialog(null, "Das Drehmoment muss zwischen 1,0 und 6,0 liegen");
                        return;
                    }
    
                    int spannung = (int) comboBox1.getSelectedItem();

                    String stromString = textfield4.getText();
                    stromString = stromString.replace(",",".");
                    double strom = stromString.isEmpty() ? 0 : Double.parseDouble(stromString);
    
                    String leistungsfaktorString = textfield5.getText();
                    leistungsfaktorString = leistungsfaktorString.replace(",",".");
                    double leistungsfaktor = leistungsfaktorString.isEmpty() ? 0 : Double.parseDouble(leistungsfaktorString);
    
                    String ubersetzungsverhaltnisString = textfield6.getText();
                    ubersetzungsverhaltnisString = ubersetzungsverhaltnisString.replace(",",".");
                    double ubersetzungsverhaltnis = ubersetzungsverhaltnisString.isEmpty() ? 0 : Double.parseDouble(ubersetzungsverhaltnisString);
    
                    Drehstrommotor sewMotor = new Drehstrommotor(drehzahl, drehmoment, spannung, strom, leistungsfaktor, ubersetzungsverhaltnis);
                    double leistungsaufnahme = sewMotor.getLeistungsaufnahme();
                    double leistungsabgabe = sewMotor.getLeistungsabgabe();
                    double verlustleistung = sewMotor.getVerlustleistung();
                    double wirkungsgrad = sewMotor.getWirkungsgrad();

                    if(leistungsaufnahme < 0 || leistungsabgabe < 0 || verlustleistung  < 0 || wirkungsgrad < 0){
                        JOptionPane.showMessageDialog(null, "Überpfüfe die eingegebenen Werte, die Ergebnisse dürfen nicht negativ sein!");
                    }
    
                    resultLabel1.setText(leistungsaufnahme+" kW");
                    resultLabel2.setText(leistungsabgabe+" kW");
                    resultLabel3.setText(verlustleistung+" kW");
                    resultLabel4.setText(wirkungsgrad+" %");
                });
            }
    
    //Drehzahl Abfrage:
    public static DoubleTextField DrehzahlEingabe(JPanel panel){
        JLabel label1 = new JLabel("Drehzahl");
        label1.setBounds(5,10,80,15);
        DoubleTextField textField1 = new DoubleTextField(true);
        textField1.setBounds(100,10,100,17);
        panel.add(label1);
        panel.add(textField1);
        return textField1;
        }
    
        //Drehmoment Abfrage:
        public static DoubleTextField DrehmomentEingabe(JPanel panel){
            JLabel label2 = new JLabel("Drehmoment");
            label2.setBounds(5,45,80,15);
            DoubleTextField textfield2 = new DoubleTextField(false);
            textfield2.setBounds(100,45,100,17);
            panel.add(label2);
            panel.add(textfield2);
            return textfield2;
        }
    
        //Spannung Abfrage:
        public static JComboBox<Integer> SpannungEingabe(JPanel panel){
            JLabel label3 = new JLabel ("Spannung");
            label3.setBounds(5,80,80,15);
            JComboBox<Integer> comboBox1 = new JComboBox<>(new Integer[]{230, 400});
            comboBox1.setBounds(100, 80, 100,17);
            panel.add(label3);
            panel.add(comboBox1);
            return comboBox1;
        }
    
        //Stromstärke Abfragen:
        public static DoubleTextField StromstärkeEingabe(JPanel panel){
            JLabel label4 = new JLabel ("Stromstärke");
            label4.setBounds(250,10,80,15);
            DoubleTextField textfield4 = new DoubleTextField(false);
            textfield4.setBounds(405,10,100,17);
            panel.add(label4);
            panel.add(textfield4);
            return textfield4;
        }
    
        //Leistungsfaktor Abfragen:
        public static DoubleTextField LeistungsfaktorEingabe(JPanel panel){
            JLabel label5 = new JLabel ("cos φ");
            label5.setBounds(250,45,80,15);
            DoubleTextField textfield5 = new DoubleTextField(false);
            textfield5.setBounds(405,45,100,17);
            panel.add(label5);
            panel.add(textfield5);
            return textfield5;
        }
    
        //Übersetzungsverhältnis Abfrage
        public static DoubleTextField UbersetzungsverhaltnisEingabe(JPanel panel){
            JLabel label6 = new JLabel("Übersetzungsverhältnis");
            label6.setBounds(250,80,140,15);
            DoubleTextField textfield6 = new DoubleTextField(false);
            textfield6.setBounds(405,80,100,17);
            panel.add(label6);
            panel.add(textfield6);
            return textfield6;
        }
    
    }
    