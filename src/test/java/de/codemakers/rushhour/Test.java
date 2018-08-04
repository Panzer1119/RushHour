package de.codemakers.rushhour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
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
        int x = 0;
        int y = 0;
        final boolean test = true;
        final Random random = new Random();
        for (Vehicle vehicle : Vehicle.values()) {
            if (vehicle == Vehicle.EMPTY) {
                continue;
            }
            if (test) {
                if (random.nextBoolean()) {
                    final int x_1 = random.nextInt(BOARD.length - 1);
                    final int y_1 = random.nextInt(BOARD[0].length - 1);
                    int x_2 = x_1;
                    int y_2 = y_1;
                    if (random.nextBoolean()) { //vertical
                        y_2 = y_1 + ((vehicle.isCar() ? 1 : 2) * (y_1 < (BOARD[0].length - (vehicle.isCar() ? 1 : 2)) ? 1 : -1));
                    } else { // horizontal
                        x_2 = x_1 + ((vehicle.isCar() ? 1 : 2) * (x_1 < (BOARD.length - (vehicle.isCar() ? 1 : 2)) ? 1 : -1));
                    }
                    putVehicle(vehicle, x_1, y_1, x_2, y_2);
                }
            } else {
                if (x >= BOARD.length) {
                    x = 0;
                    y ++;
                }
                System.out.println(vehicle);
                putVehicle(vehicle, x, y, x + (vehicle.isCar() ? 1 : 2), y);
                x += (vehicle.isCar() ? 2 : 3);
            }
        }
        System.out.println("Finished static init");
    }
    
    public static final void main(String[] args) {
        System.out.println("Test");
        initFrame();
    }
    
    public static final void initFrame() {
        FRAME.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        FRAME.setLayout(new BorderLayout());
        FRAME.setPreferredSize(new Dimension(600, 600));
        PANEL_BOARD.setLayout(new GridLayout(6, 6));
        for (int y = 0; y < BUTTONS_BOARD.length; y++) {
            for (int x = 0; x < BUTTONS_BOARD[y].length; x++) {
                BUTTONS_BOARD[x][y] = new JButton();
                PANEL_BOARD.add(BUTTONS_BOARD[x][y]);
            }
        }
        initListeners();
        FRAME.add(PANEL_BOARD, BorderLayout.CENTER);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);
        FRAME.setVisible(true);
        System.out.println("BOARD: " + Arrays.deepToString(BOARD));
        updateBoard();
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
                //System.out.println("COLOR: " + COLORS.get(BOARD[x][y]));
                BUTTONS_BOARD[x][y].setBackground(COLORS.get(BOARD[x][y]));
                BUTTONS_BOARD[x][y].setOpaque(true);
                BUTTONS_BOARD[x][y].setForeground(COLORS.get(BOARD[x][y]));
                BUTTONS_BOARD[x][y].setText(String.format("%s;X:%d;Y:%d", Vehicle.ofId(BOARD[x][y]).name(), x, y));
            }
        }
        PANEL_BOARD.revalidate();
        PANEL_BOARD.repaint();
    }
    
    public static final void action(ActionEvent event, JButton button, int x, int y) {
        System.out.println("Clicked: " + x + "|" + y);
    }
    
    public static final void putVehicle(Vehicle vehicle, int x_1, int y_1, int x_2, int y_2) {
        if (!checkBounds(x_1, y_1, x_2, y_2)) {
            throw new IllegalArgumentException("Wrong coordinates");
        }
        final int x_min = Math.min(x_1, x_2);
        final int x_max = Math.max(x_1, x_2);
        final int y_min = Math.min(y_1, y_2);
        final int y_max = Math.max(y_1, y_2);
        for (int x = x_min; x <= x_max; x++) {
            for (int y = y_min; y <= y_max; y++) {
                if (BOARD[x][y] != Vehicle.EMPTY.getId()) {
                    System.err.println(String.format("(%d:%d)-(%d:%d) is already occupied", x_min, y_min, x_max, y_max));
                    return;
                }
            }
        }
        for (int x = x_min; x <= x_max; x++) {
            for (int y = y_min; y <= y_max; y++) {
                BOARD[x][y] = vehicle.getId();
            }
        }
    }
    
    public static final boolean checkBounds(int x_1, int y_1, int x_2, int y_2) {
        return !(Math.min(x_1, x_2) < 0 || Math.min(y_1, y_2) < 0 || Math.max(x_1, x_2) >= BOARD.length || Math.max(y_1, y_2) >= BOARD[0].length);
    }
    
}
