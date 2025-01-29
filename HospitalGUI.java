import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HospitalGUI extends JFrame {

    protected ArrayList<Appointment> appointments;
    protected JPanel mainPanel;
    protected CardLayout cardLayout;

    // Sample data for clinics and doctors
    protected Map<String, String[]> clinicsAndDoctors;

    public HospitalGUI() {
        appointments = new ArrayList<>();
        setTitle("Appointment Scheduling System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize clinics and doctors data
        initializeClinicsAndDoctors();

        mainPanel.add(createMainMenuPanel(), "Main Menu");
        mainPanel.add(new LoginGUI(this).createLoginPanel(), "Login");
        mainPanel.add(new PatientGUI(this).createSetAppointmentPanel(), "Set Appointment");
        mainPanel.add(new ClinicGUI(this).createViewAppointmentsPanel(), "Clinic View Appointments");

        add(mainPanel);
        cardLayout.show(mainPanel, "Main Menu");
    }

    private void initializeClinicsAndDoctors() {
        clinicsAndDoctors = new HashMap<>();
        clinicsAndDoctors.put("Clinic A", new String[]{"Dr. Ravi", "Dr. Sudheer", "Dr. Madhavi"});
        clinicsAndDoctors.put("Clinic B", new String[]{"Dr. Preethi", "Dr. Lokesh"});
        clinicsAndDoctors.put("Clinic C", new String[]{"Dr. Swathi", "Dr. Pradeep"});
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to the Appointment Scheduling System");
        welcomeLabel.setFont(new Font("sans serif", Font.BOLD, 28));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        welcomeLabel.setForeground(Color.DARK_GRAY);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JButton patientButton = createButton("Patient: Book an Appointment", new Color(0, 123, 255)); // Blue
        patientButton.addActionListener(e -> cardLayout.show(mainPanel, "Set Appointment"));
        buttonPanel.add(patientButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton clinicButton = createButton("   Clinic: View Appointments   ", new Color(40, 167, 69)); // Green
        clinicButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        buttonPanel.add(clinicButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton exitButton = createButton("                      Exit                     ", new Color(220, 53, 69)); // Red
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        panel.add(welcomeLabel);
        panel.add(buttonPanel);
        return panel;
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("sans serif", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(1000, 150));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalGUI gui = new HospitalGUI();
            gui.setVisible(true);
        });
    }
}
