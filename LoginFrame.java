/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pos;

/**
 *
 * @author fatimabintetariq
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(UserAuthentication userAuth) {
        setTitle("Pharmacy POS Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = userAuth.authenticateUser(username, password);
                if (user != null) {
                    String role = user.getRole();
                    // Based on the role, open the appropriate main application window.
                    if (role.equals("Manager")) {
                        // Open Manager's window
                    } else if (role.equals("Sales Assistant")) {
                        // Open Sales Assistant's window
                    }
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login failed. Please check your credentials.");
                }
            }
        });
    }

    private void placeComponents(JPanel panel)
    {
        panel.setLayout(new GridLayout(3, 1));

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserAuthentication userAuth = new UserAuthentication();
            LoginFrame loginFrame = new LoginFrame(userAuth);
            loginFrame.setVisible(true);
         //   ProductDatabase productDB = new ProductDatabase();
            
            ProductGUI productGUI = new ProductGUI();
            productGUI.setVisible(true);
        });
    }
}
