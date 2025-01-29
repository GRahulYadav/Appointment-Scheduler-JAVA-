import javax.swing.*;
import java.awt.*;

public class PatientGUI {

    private HospitalGUI hospitalGUI;

    public PatientGUI(HospitalGUI hospitalGUI) {
        this.hospitalGUI = hospitalGUI;
    }

    public JPanel createSetAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245)); // Light Grey Background

        JLabel titleLabel = new JLabel("Book an Appointment", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titleLabel.setForeground(new Color(33, 37, 41)); // Dark Grey Text
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        formPanel.setBackground(new Color(245, 245, 245)); // Light Grey Background

        formPanel.add(createLabel("Time (AM or PM):"));
        JTextField timeField = createTextField();
        formPanel.add(timeField);

        formPanel.add(createLabel("New Patient (Yes/No):"));
        JTextField newPatientField = createTextField();
        formPanel.add(newPatientField);

        formPanel.add(createLabel("Sick (Yes/No):"));
        JTextField sickField = createTextField();
        formPanel.add(sickField);

        formPanel.add(createLabel("Clinic:"));
        JComboBox<String> clinicComboBox = new JComboBox<>(hospitalGUI.clinicsAndDoctors.keySet().toArray(new String[0]));
        formPanel.add(clinicComboBox);

        formPanel.add(createLabel("Doctor:"));
        JComboBox<String> doctorComboBox = new JComboBox<>();
        formPanel.add(doctorComboBox);

        clinicComboBox.addActionListener(e -> {
            String selectedClinic = (String) clinicComboBox.getSelectedItem();
            doctorComboBox.removeAllItems();
            for (String doctor : hospitalGUI.clinicsAndDoctors.get(selectedClinic)) {
                doctorComboBox.addItem(doctor);
            }
        });

        formPanel.add(createLabel("Full Name:"));
        JTextField fullNameField = createTextField();
        formPanel.add(fullNameField);

        formPanel.add(createLabel("Phone Number:"));
        JTextField phoneNumberField = createTextField();
        formPanel.add(phoneNumberField);

        formPanel.add(createLabel("Reason:"));
        JTextField reasonField = createTextField();
        formPanel.add(reasonField);

        formPanel.add(createLabel("Insurance Plan:"));
        JTextField insurancePlanField = createTextField();
        formPanel.add(insurancePlanField);

        formPanel.add(createLabel("Medical Records:"));
        JTextField medicalRecordsField = createTextField();
        formPanel.add(medicalRecordsField);

        formPanel.add(createLabel("Medication:"));
        JTextField medicationField = createTextField();
        formPanel.add(medicationField);

        JButton bookButton = createButton("Book Appointment", new Color(40, 167, 69)); // Green Button
        bookButton.addActionListener(e -> {
            String time = timeField.getText();
            String newPatient = newPatientField.getText();
            String sick = sickField.getText();
            String clinic = (String) clinicComboBox.getSelectedItem();
            String doctor = (String) doctorComboBox.getSelectedItem();
            String fullName = fullNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String reason = reasonField.getText();
            String insurancePlan = insurancePlanField.getText();
            String medicalRecords = medicalRecordsField.getText();
            String medication = medicationField.getText();

            Appointment appointment = new Appointment(time, newPatient, sick, clinic, doctor, fullName, phoneNumber, reason, insurancePlan, medicalRecords, medication);
            hospitalGUI.appointments.add(appointment);

            // Show confirmation message
            JOptionPane.showMessageDialog(panel, "Appointment booked successfully!");

            // Clear fields after booking
            timeField.setText("");
            newPatientField.setText("");
            sickField.setText("");
            clinicComboBox.setSelectedIndex(0);
            fullNameField.setText("");
            phoneNumberField.setText("");
            reasonField.setText("");
            insurancePlanField.setText("");
            medicalRecordsField.setText("");
            medicationField.setText("");
        });
        formPanel.add(bookButton);

        JButton backButton = createButton("Back", new Color(220, 53, 69)); // Red Button
        backButton.addActionListener(e -> hospitalGUI.cardLayout.show(hospitalGUI.mainPanel, "Main Menu"));
        formPanel.add(backButton);

        panel.add(formPanel, BorderLayout.CENTER);
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(new Color(33, 37, 41)); // Dark Grey Text
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(new Color(108, 117, 125))); // Grey Border
        return textField;
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }
}
