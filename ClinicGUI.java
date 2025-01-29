import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClinicGUI {

    private HospitalGUI hospitalGUI;
    private DefaultListModel<Appointment> appointmentListModel;
    private JTextArea appointmentDetailsArea;

    public ClinicGUI(HospitalGUI hospitalGUI) {
        this.hospitalGUI = hospitalGUI;
    }

    public JPanel createViewAppointmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.GRAY); // Change the background color to a darker grey

        JLabel titleLabel = new JLabel("View Appointments", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titleLabel.setForeground(Color.WHITE); // Change the text color to white for contrast
        panel.add(titleLabel, BorderLayout.NORTH);

        appointmentListModel = new DefaultListModel<>();
        JList<Appointment> appointmentList = new JList<>(appointmentListModel);
        appointmentList.setFont(new Font("Arial", Font.PLAIN, 16));
        appointmentList.setForeground(Color.BLACK);
        appointmentList.setBackground(Color.LIGHT_GRAY);
        JScrollPane listScrollPane = new JScrollPane(appointmentList);
        panel.add(listScrollPane, BorderLayout.WEST);

        appointmentDetailsArea = new JTextArea();
        appointmentDetailsArea.setEditable(false);
        appointmentDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        appointmentDetailsArea.setForeground(Color.BLACK);
        appointmentDetailsArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane detailsScrollPane = new JScrollPane(appointmentDetailsArea);
        panel.add(detailsScrollPane, BorderLayout.CENTER);

        appointmentList.addListSelectionListener(e -> {
            Appointment selectedAppointment = appointmentList.getSelectedValue();
            if (selectedAppointment != null) {
                displayAppointmentDetails(selectedAppointment);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.GRAY); // Change the background color to match the panel

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 18));
        refreshButton.setForeground(Color.WHITE); // Set button text color to white
        refreshButton.setBackground(Color.DARK_GRAY); // Set button background color to dark grey
        refreshButton.setOpaque(true); // Make the background color visible
        refreshButton.setBorderPainted(false); // Remove the button border
        refreshButton.addActionListener(e -> loadAppointments());
        buttonPanel.add(refreshButton);

        JButton approveButton = new JButton("Approve Appointment");
        approveButton.setFont(new Font("Arial", Font.PLAIN, 18));
        approveButton.setForeground(Color.WHITE);
        approveButton.setBackground(Color.GREEN); // Set button background color to green for approve
        approveButton.setOpaque(true);
        approveButton.setBorderPainted(false);
        approveButton.addActionListener(e -> {
            Appointment selectedAppointment = appointmentList.getSelectedValue();
            if (selectedAppointment != null) {
                JOptionPane.showMessageDialog(panel, "Appointment approved successfully.\nMessage sent to: " + selectedAppointment.phoneNumber);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select an appointment to approve.");
            }
        });
        buttonPanel.add(approveButton);

        JButton cancelButton = new JButton("Cancel Appointment");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.RED); // Set button background color to red for cancel
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);
        cancelButton.addActionListener(e -> {
            Appointment selectedAppointment = appointmentList.getSelectedValue();
            if (selectedAppointment != null) {
                hospitalGUI.appointments.remove(selectedAppointment);
                loadAppointments();
                appointmentDetailsArea.setText("");
                JOptionPane.showMessageDialog(panel, "Appointment canceled successfully.\nMessage sent to: " + selectedAppointment.phoneNumber + "\nReason: Doctor not available.");
            } else {
                JOptionPane.showMessageDialog(panel, "Please select an appointment to cancel.");
            }
        });
        buttonPanel.add(cancelButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE); // Set button text color to white
        backButton.setBackground(Color.DARK_GRAY); // Set button background color to dark grey
        backButton.setOpaque(true); // Make the background color visible
        backButton.setBorderPainted(false); // Remove the button border
        backButton.addActionListener(e -> hospitalGUI.cardLayout.show(hospitalGUI.mainPanel, "Main Menu"));
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void loadAppointments() {
        appointmentListModel.clear();
        List<Appointment> appointments = hospitalGUI.appointments;
        for (Appointment appointment : appointments) {
            appointmentListModel.addElement(appointment);
        }
    }

    private void displayAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: ").append(appointment.time).append("\n")
                .append("New Patient: ").append(appointment.newPatient).append("\n")
                .append("Sick: ").append(appointment.sick).append("\n")
                .append("Clinic: ").append(appointment.clinic).append("\n")
                .append("Doctor: ").append(appointment.doctor).append("\n")
                .append("Full Name: ").append(appointment.fullName).append("\n")
                .append("Phone Number: ").append(appointment.phoneNumber).append("\n")
                .append("Reason: ").append(appointment.reason).append("\n")
                .append("Insurance Plan: ").append(appointment.insurancePlan).append("\n")
                .append("Medical Records: ").append(appointment.medicalRecords).append("\n")
                .append("Medication: ").append(appointment.medication).append("\n")
                .append("-----------\n");

        appointmentDetailsArea.setText(sb.toString());
    }
}
