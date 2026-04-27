import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm extends JFrame implements ActionListener {

    JLabel lblTitle, lblName, lblEmail, lblGender, lblCourse, lblCity, lblOutput;
    JTextField txtName, txtEmail;
    JRadioButton rbMale, rbFemale;
    JComboBox<String> cmbCourse;
    JCheckBox chkPune, chkMumbai, chkDelhi;
    JButton btnSubmit, btnClear;
    JTextArea txtArea;

    public RegistrationForm() {
        setTitle("User Registration Form");
        setSize(400, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("Registration Form");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 20, 250, 30);
        add(lblTitle);

        lblName = new JLabel("Name:");
        lblName.setBounds(50, 80, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 80, 150, 25);
        add(txtName);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 120, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 120, 150, 25);
        add(txtEmail);

        lblGender = new JLabel("Gender:");
        lblGender.setBounds(50, 160, 100, 25);
        add(lblGender);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbMale.setBounds(150, 160, 70, 25);
        rbFemale.setBounds(230, 160, 80, 25);

        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        add(rbMale);
        add(rbFemale);

        lblCourse = new JLabel("Course:");
        lblCourse.setBounds(50, 200, 100, 25);
        add(lblCourse);

        String courses[] = {"B.Tech", "B.Sc", "B.Com", "BCA"};
        cmbCourse = new JComboBox<>(courses);
        cmbCourse.setBounds(150, 200, 150, 25);
        add(cmbCourse);

        lblCity = new JLabel("Preferred City:");
        lblCity.setBounds(50, 240, 100, 25);
        add(lblCity);

        chkPune = new JCheckBox("Pune");
        chkMumbai = new JCheckBox("Mumbai");
        chkDelhi = new JCheckBox("Delhi");

        chkPune.setBounds(150, 240, 70, 25);
        chkMumbai.setBounds(220, 240, 80, 25);
        chkDelhi.setBounds(300, 240, 80, 25);

        add(chkPune);
        add(chkMumbai);
        add(chkDelhi);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(80, 300, 100, 30);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnClear = new JButton("Clear");
        btnClear.setBounds(200, 300, 100, 30);
        btnClear.addActionListener(this);
        add(btnClear);

        txtArea = new JTextArea();
        txtArea.setBounds(50, 350, 280, 100);
        txtArea.setEditable(false);
        add(txtArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "Not Selected";
            String course = (String) cmbCourse.getSelectedItem();

            String city = "";
            if (chkPune.isSelected()) city += "Pune ";
            if (chkMumbai.isSelected()) city += "Mumbai ";
            if (chkDelhi.isSelected()) city += "Delhi ";
            if (city.equals("")) city = "None";

            txtArea.setText("Registration Details:\n"
                    + "Name: " + name + "\n"
                    + "Email: " + email + "\n"
                    + "Gender: " + gender + "\n"
                    + "Course: " + course + "\n"
                    + "Preferred City: " + city);
        } 
        else if (e.getSource() == btnClear) {
            txtName.setText("");
            txtEmail.setText("");
            rbMale.setSelected(false);
            rbFemale.setSelected(false);
            cmbCourse.setSelectedIndex(0);
            chkPune.setSelected(false);
            chkMumbai.setSelected(false);
            chkDelhi.setSelected(false);
            txtArea.setText("");
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
