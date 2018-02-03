package studentadmingui;

import studentadmin.StudentAdmin;
import studentadmin.StudentAdminException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StudentAdminFrame extends JFrame {

    private StudentAdmin studentenAdmin = null;

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JScrollPane jScrollPane = null;
    private JTextArea uitvoerGebied = null;
    private JButton toonAlleKnop = null;
    private JTabbedPane mijnTabbladenPanel = null;
    private JPanel voegStudenttoePanel = null;
    private JPanel voegScholertoePanel = null;
    private JPanel studentPanel = null;
    private JPanel alleStudentenPanel = null;
    private JLabel bestaandenaamLabel = null;
    private JTextField bestaandeNaamVeld = null;
    private JLabel infoLabel = null;
    private JTextField studentInfoVeld = null;
    private JLabel nieuwepuntenLabel = null;
    private JTextField puntenVeld = null;
    private JLabel uitlegLabel = null;
    private JButton moduleKnop = null;
    private JLabel opleidingLabel = null;
    private JLabel nstudentLabel = null;
    private JComboBox opleidingComboBox = null;
    private JLabel studentLabel = null;
    private JTextField studentTextField = null;
    private JButton studentButton = null;
    private JComboBox scholingComboBox = null;
    private JLabel scholerLabel = null;
    private JTextField scholerTextField = null;
    private JButton scholerButton = null;

    /**
     * This is the default constructor
     */
    public StudentAdminFrame() {
        super();
        initialize();
        mijnInitialize();
    }

    /**
     * This method initializes this gui
     */
    private void initialize() {
        this.setSize(466, 347);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("Studenten Administratie");
    }

    /**
     * Verbindt de gui met de domeinlaag en initialiseert veld-waardes van de gui.
     */
    private void mijnInitialize() {

        studentenAdmin = new StudentAdmin();

    /*
    toevoegen van items aan keuzes voor reguliere studenten
     */
        List<String> opleidingNamen = studentenAdmin.getAlleOpleidingNamen();

        for (String opleidingNaam : opleidingNamen){

            opleidingComboBox.addItem(opleidingNaam);

        }

        opleidingComboBox.setSelectedIndex(-1);

    /*
    toevoegen van items aan keuzes voor scholers
     */
        List<String> cppNamen = studentenAdmin.getAlleCppNamen();

        for (String cppNaam : cppNamen){

            scholingComboBox.addItem(cppNaam);

        }

        scholingComboBox.setSelectedIndex(-1);

    }

    /**
     * Action handler voor het drukken van de knop "Voeg student toe" op tab "nieuwe student"
     */
    private void studentButtonAction(){

        try{
            String studentNaam = studentTextField.getText();

            String opleidingNaam = opleidingComboBox.getSelectedItem().toString();

            studentenAdmin.voegRegulierToe(studentNaam, opleidingNaam);

            infoLabel.setText("Reguliere student met naam " + studentNaam + " is succesvol toegevoegd." );

        } catch (StudentAdminException adminEx) {

            infoLabel.setText(adminEx.getMessage());

        } catch (NullPointerException nullEx) {

            infoLabel.setText("Opleiding veld is niet geselecteed");

        } finally{

            studentTextField.setText("");
        }
    }

    /**
     * Action handler voor het drukken van de knop "Voeg scholer toe" op tab "nieuwe scholer"
     */
    private void scholerButtonAction(){


        try{

            String scholerNaam = scholerTextField.getText();

            String cppNaam = scholingComboBox.getSelectedItem().toString();

            studentenAdmin.voegScholerToe(scholerNaam, cppNaam);

            infoLabel.setText("Scholer met naam " + scholerNaam + " is succesvol toegevoegd." );

        } catch (StudentAdminException studadminEx) {

            infoLabel.setText(studadminEx.getMessage());

        } catch (NullPointerException nullEx) {

            infoLabel.setText("CPP veld is niet geselecteed");


        } finally {

            scholerTextField.setText("");
        }


    }


    /**
     * Action handler voor het invoeren van een String in het textveld "Studentnaam" op tab "info student"
     */
    private void bestaandeNaamVeldAction(){

        String naam = bestaandeNaamVeld.getText();

        try{
            String info = studentenAdmin.getStudentInfo(naam);
            studentInfoVeld.setText(info);

        } catch (StudentAdminException studadminEx){

            infoLabel.setText(studadminEx.getMessage());

        }


    }

    /**
     * Action handler voor het invoeren van een String in het textveld "Punten behaald" op tab "info student"
     */
    private void puntenVeldAction(){

        String naam = bestaandeNaamVeld.getText();

        try {

            double d1 = Double.parseDouble(puntenVeld.getText());

            studentenAdmin.verhoogAantalStudiepunten(naam, d1);

            String info = studentenAdmin.getStudentInfo(naam);

            studentInfoVeld.setText(info);

        } catch (StudentAdminException studentAdminException){

            infoLabel.setText(studentAdminException.getMessage());

        } catch (NumberFormatException numFormEx){

            infoLabel.setText("Input kan niet naar double overgezet worden");
        }

    }

    /**
     * Action handler voor het drukken van de knop "Module behaald" op tab "info student"
     */
    private void moduleKnopAction(){

        String naam = bestaandeNaamVeld.getText();

        try {
            studentenAdmin.verhoogAantalModulesMetEen(naam);
            String info = studentenAdmin.getStudentInfo(naam);
            studentInfoVeld.setText(info);

        } catch (StudentAdminException studAdminEx){

            infoLabel.setText(studAdminEx.getMessage());
        }

    }

    /**
     * Action handler voor het drukken van de knop "Module behaald" op tab "info student"
     */
    private void toonAlleKnopAction(){
        String text = studentenAdmin.getAlleStudentenInfo();

        uitvoerGebied.setText(text);
        infoLabel.setText("");

    }


    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            infoLabel = new JLabel();
            infoLabel.setBounds(new Rectangle(14, 278, 424, 33));
            infoLabel.setText("");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getMijnTabbladenPanel(), null);
            jContentPane.add(infoLabel, null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBounds(new Rectangle(6, 65, 382, 121));
            jScrollPane.setViewportView(getUitvoerGebied());
        }
        return jScrollPane;
    }

    /**
     * This method initializes uitvoerGebied
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getUitvoerGebied() {
        if (uitvoerGebied == null) {
            uitvoerGebied = new JTextArea();
            uitvoerGebied.setEditable(false);
        }
        return uitvoerGebied;
    }

    /**
     * This method initializes toonAlleKnop
     *
     * @return javax.swing.JButton
     */
    private JButton getToonAlleKnop() {
        if (toonAlleKnop == null) {
            toonAlleKnop = new JButton();
            toonAlleKnop.setText("Toon alle studenten");
            toonAlleKnop.setBounds(new Rectangle(11, 8, 147, 20));
            toonAlleKnop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toonAlleKnopAction();
                }
            });
        }
        return toonAlleKnop;
    }

    /**
     * This method initializes mijnTabbladenPanel
     *
     * @return javax.swing.JTabbedPane
     */
    private JTabbedPane getMijnTabbladenPanel() {
        if (mijnTabbladenPanel == null) {
            mijnTabbladenPanel = new JTabbedPane();
            mijnTabbladenPanel.setBounds(new Rectangle(13, 19, 423, 224));
            mijnTabbladenPanel.addTab("nieuwe student", null,
                    getVoegStudenttoePanel(), null);
            mijnTabbladenPanel.addTab("nieuwe scholer", null,
                    getVoegScholertoePanel(), null);
            mijnTabbladenPanel.addTab("studentinfo", null, getStudentPanel(), null);
            mijnTabbladenPanel.addTab("alle studenten", null,
                    getAlleStudentenPanel(), null);
            mijnTabbladenPanel.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    infoLabel.setText("");
                    infoLabel.setText("");
                    studentInfoVeld.setText("");
                    puntenVeld.setText("");
                    opleidingComboBox.setSelectedIndex(-1);
                    scholingComboBox.setSelectedIndex(-1);
                    studentTextField.setText("");
                    scholerTextField.setText("");
                    studentInfoVeld.setText("");
                    bestaandeNaamVeld.setText("");
                    uitvoerGebied.setText("");
                }
            });
        }
        return mijnTabbladenPanel;
    }

    /**
     * This method initializes voegStudenttoePanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getVoegStudenttoePanel() {
        if (voegStudenttoePanel == null) {
            studentLabel = new JLabel();
            studentLabel.setBounds(new Rectangle(15, 55, 91, 24));
            studentLabel.setText("Naam student");
            nstudentLabel = new JLabel();
            nstudentLabel.setBounds(new Rectangle(16, 16, 146, 24));
            nstudentLabel.setText("Selecteer een opleiding");
            voegStudenttoePanel = new JPanel();
            voegStudenttoePanel.setLayout(null);
            voegStudenttoePanel.add(nstudentLabel, null);
            voegStudenttoePanel.add(getOpleidingComboBox(), null);
            voegStudenttoePanel.add(studentLabel, null);
            voegStudenttoePanel.add(getStudentTextField(), null);
            voegStudenttoePanel.add(getStudentButton(), null);

        }
        return voegStudenttoePanel;
    }

    private JPanel getVoegScholertoePanel() {
        if (voegScholertoePanel == null) {
            scholerLabel = new JLabel();
            scholerLabel.setBounds(new Rectangle(16, 54, 116, 25));
            scholerLabel.setText("Naam scholer");
            opleidingLabel = new JLabel();
            opleidingLabel.setBounds(new Rectangle(16, 16, 173, 25));
            opleidingLabel.setText("Selecteer een CPP-Opleiding");
            voegScholertoePanel = new JPanel();
            voegScholertoePanel.setLayout(null);
            voegScholertoePanel.add(opleidingLabel, null);
            voegScholertoePanel.add(getScholingComboBox(), null);
            voegScholertoePanel.add(scholerLabel, null);
            voegScholertoePanel.add(getScholerTextField(), null);
            voegScholertoePanel.add(getScholerButton(), null);

        }
        return voegScholertoePanel;
    }

    /**
     * This method initializes studentPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getStudentPanel() {
        if (studentPanel == null) {
            uitlegLabel = new JLabel();
            uitlegLabel.setBounds(new Rectangle(16, 8, 334, 19));
            uitlegLabel.setText("Geef enter om invoer te bevestigen");
            nieuwepuntenLabel = new JLabel();
            nieuwepuntenLabel.setBounds(new Rectangle(14, 63, 256, 20));
            nieuwepuntenLabel.setText("Punten behaald (alleen reguliere opleiding) ");
            bestaandenaamLabel = new JLabel();
            bestaandenaamLabel.setBounds(new Rectangle(14, 35, 86, 20));
            bestaandenaamLabel.setText("Studentnaam");
            studentPanel = new JPanel();
            studentPanel.setLayout(null);
            studentPanel.add(bestaandenaamLabel, null);
            studentPanel.add(getBestaandeNaamVeld(), null);
            studentPanel.add(getStudentInfoVeld(), null);
            studentPanel.add(nieuwepuntenLabel, null);
            studentPanel.add(getPuntenVeld(), null);
            studentPanel.add(uitlegLabel, null);
            studentPanel.add(getModuleKnop(), null);
        }
        return studentPanel;
    }

    /**
     * This method initializes alleStudentenPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getAlleStudentenPanel() {
        if (alleStudentenPanel == null) {
            alleStudentenPanel = new JPanel();
            alleStudentenPanel.setLayout(null);
            alleStudentenPanel.add(getToonAlleKnop(), null);
            alleStudentenPanel.add(getJScrollPane(), null);
        }
        return alleStudentenPanel;
    }

    /**
     * This method initializes bestaandeNaamVeld
     *
     * @return javax.swing.JTextField
     */
    private JTextField getBestaandeNaamVeld() {
        if (bestaandeNaamVeld == null) {
            bestaandeNaamVeld = new JTextField();
            bestaandeNaamVeld.setBounds(new Rectangle(114, 36, 151, 20));
            bestaandeNaamVeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bestaandeNaamVeldAction();
                }
            });
        }
        return bestaandeNaamVeld;
    }

    /**
     * This method initializes studentInfoVeld
     *
     * @return javax.swing.JTextField
     */
    private JTextField getStudentInfoVeld() {
        if (studentInfoVeld == null) {
            studentInfoVeld = new JTextField();
            studentInfoVeld.setBounds(new Rectangle(10, 152, 392, 27));
            studentInfoVeld.setEditable(false);
        }
        return studentInfoVeld;
    }

    /**
     * This method initializes puntenVeld
     *
     * @return javax.swing.JTextField
     */
    private JTextField getPuntenVeld() {
        if (puntenVeld == null) {
            puntenVeld = new JTextField();
            puntenVeld.setBounds(new Rectangle(284, 63, 47, 20));
            puntenVeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    puntenVeldAction();
                }
            });
        }
        return puntenVeld;
    }

    /**
     * This method initializes moduleKnop
     *
     * @return javax.swing.JButton
     */
    private JButton getModuleKnop() {
        if (moduleKnop == null) {
            moduleKnop = new JButton();
            moduleKnop.setBounds(new Rectangle(14, 91, 328, 21));
            moduleKnop.setText("Module behaald (alleen CPP)");
            moduleKnop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    moduleKnopAction();
                }
            });
        }
        return moduleKnop;
    }

    /**
     * This method initializes opleidingComboBox
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getOpleidingComboBox() {
        if (opleidingComboBox == null) {
            opleidingComboBox = new JComboBox();
            opleidingComboBox.setBounds(new Rectangle(195, 16, 200, 24));
        }
        return opleidingComboBox;
    }

    /**
     * This method initializes studentTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getStudentTextField() {
        if (studentTextField == null) {
            studentTextField = new JTextField();
            studentTextField.setBounds(new Rectangle(195, 55, 197, 24));
        }
        return studentTextField;
    }

    /**
     * This method initializes studentButton
     *
     * @return javax.swing.JButton
     */
    private JButton getStudentButton() {
        if (studentButton == null) {
            studentButton = new JButton();
            studentButton.setBounds(new Rectangle(15, 106, 153, 24));
            studentButton.setText("Voeg student toe");
            studentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    studentButtonAction();
                }
            });
        }
        return studentButton;
    }

    /**
     * This method initializes scholingComboBox
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getScholingComboBox() {
        if (scholingComboBox == null) {
            scholingComboBox = new JComboBox();
            scholingComboBox.setBounds(new Rectangle(210, 16, 167, 25));
        }
        return scholingComboBox;
    }

    /**
     * This method initializes scholerTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getScholerTextField() {
        if (scholerTextField == null) {
            scholerTextField = new JTextField();
            scholerTextField.setBounds(new Rectangle(210, 54, 166, 25));
        }
        return scholerTextField;
    }

    /**
     * This method initializes scholerButton
     *
     * @return javax.swing.JButton
     */
    private JButton getScholerButton() {
        if (scholerButton == null) {
            scholerButton = new JButton();
            scholerButton.setBounds(new Rectangle(14, 103, 163, 25));
            scholerButton.setText("Voeg scholer toe");
            scholerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    scholerButtonAction();
                }
            });
        }
        return scholerButton;
    }

    public static void main(String[] args) {
        StudentAdminFrame fr = new StudentAdminFrame();
        fr.setVisible(true);
    }

} // @jve:decl-index=0:visual-constraint="10,10"
