package de.codemakers.rushhour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    
    public static final Map<Byte, Color> COLORS = new ConcurrentHashMap<>();
    
    public static final byte[][] BOARD = new byte[6][6];
    
    public static final JFrame FRAME = new JFrame(RushHour.class.getSimpleName());
    public static final JPanel PANEL_BOARD = new JPanel();
    public static final JButton[][] BUTTONS_BOARD = new JButton[BOARD.length][BOARD[0].length];
    
    static {
        for (byte[] b_ : BOARD) {
            Arrays.fill(b_, Vehicle.EMPTY.getId());
        }
        for (Vehicle vehicle : Vehicle.values()) {
            COLORS.put(vehicle.getId(), vehicle.getColor());
        }
    }
    
    public static final void main(String[] args) {
        System.out.println("Test");
        initFrame();
    }
    
    public static final void initFrame() {
        FRAME.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        FRAME.setLayout(new BorderLayout());
        FRAME.setPreferredSize(new Dimension(600, 600));
        for (int x = 0; x < BUTTONS_BOARD.length; x++) {
            for (int y = 0; y < BUTTONS_BOARD[x].length; y++) {
                BUTTONS_BOARD[x][y] = new JButton();
            }
        }
        initListeners();
        PANEL_BOARD.setLayout(new GridLayout(6, 6));
        FRAME.add(PANEL_BOARD, BorderLayout.CENTER);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);
        FRAME.setVisible(true);
    }
    
    public static final void initListeners() {
        for (int x = 0; x < BUTTONS_BOARD.length; x++) {
            for (int y = 0; y < BUTTONS_BOARD[x].length; y++) {
                final int x_ = x;
                final int y_ = y;
                BUTTONS_BOARD[x][y].addActionListener((event) -> action(event, BUTTONS_BOARD[x_][y_], x_, y_));
            }
        }
        FRAME.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
            }
            
            @Override
            public void windowIconified(WindowEvent e) {
            }
            
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            
            @Override
            public void windowActivated(WindowEvent e) {
            }
            
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }
    
    public static final void exit() {
        System.out.println("Exit!");
        System.exit(0);
    }
    
    public static final void updateBoard() {
        for (int x = 0; x < BOARD.length; x++) {
            for (int y = 0; y < BOARD[x].length; y++) {
                BUTTONS_BOARD[x][y].setBackground(COLORS.get(BOARD[x][y]));
            }
        }
    }
    
    public static final void action(ActionEvent event, JButton button, int x, int y) {
        System.out.println("Clicked: " + x + "|" + y);
    }
    
}
