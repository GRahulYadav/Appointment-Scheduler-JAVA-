import javax.swing.*;
import java.awt.*;

class LoginGUI {

    private HospitalGUI hospitalGUI;

    public LoginGUI(HospitalGUI hospitalGUI) {
        this.hospitalGUI = hospitalGUI;
    }

    public JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light Grey

        JLabel titleLabel = new JLabel("Clinic Staff Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20));
        titleLabel.setForeground(Color.BLACK);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        formPanel.setBackground(new Color(240, 240, 240)); // Light Grey
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font formFont = new Font("Arial", Font.PLAIN, 18);

        JLabel usernameLabel = new JLabel("Username:", JLabel.LEFT);
        usernameLabel.setFont(formFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField();
        usernameField.setFont(formFont);
        usernameField.setPreferredSize(new Dimension(200, 30)); // Set preferred size
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:", JLabel.LEFT);
        passwordLabel.setFont(formFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(formFont);
        passwordField.setPreferredSize(new Dimension(200, 30)); // Set preferred size
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(0, 123, 255)); // Light Blue
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Simple login validation for demonstration purposes
            if ("clinic".equals(username) && "password".equals(password)) {
                hospitalGUI.cardLayout.show(hospitalGUI.mainPanel, "Clinic View Appointments");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid credentials, please try again.");
                passwordField.setText("");
            }
        });
        buttonPanel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(220, 53, 69)); // Salmon
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> hospitalGUI.cardLayout.show(hospitalGUI.mainPanel, "Main Menu"));
        buttonPanel.add(backButton);

        panel.add(formPanel, BorderLayout.CENTER);
        return panel;
    }
}
