import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Main {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static long lx=0;
    public static double dx=0;
    public static boolean florint=true;
    public static int nrofdig=0;
    public static long seclx=0;
    public static double secdx=0;
    public static char opperation='0';
    public static DecimalFormat formatter = new DecimalFormat("0.#####E0");
    public static JFrame f = new JFrame();
    private static int mouseX, mouseY;

    public static void main(String[] args) {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setUndecorated(true);
        ImageIcon icon = new ImageIcon("icon.png");
        Image image = icon.getImage();
        f.setIconImage(image);
        f.setVisible(true);
        JButton[][] Buttons = new JButton[6][4];
        JPanel titleBar = new JPanel();
        titleBar.setPreferredSize(new Dimension(f.getWidth(), 32));
        titleBar.setBackground(Color.darkGray);
        JButton exitButton = new JButton("x");
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.white);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBackground(new Color(50,50,50));
        JButton minimizeButton = new JButton("-");
        minimizeButton.addActionListener(e -> f.setState(Frame.ICONIFIED));
        minimizeButton.setFocusable(false);
        minimizeButton.setForeground(Color.white);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setBackground(new Color(50,50,50));
        JButton calcButton=new JButton();
        calcButton.setIcon(new ImageIcon("1icon.png"));
        calcButton.setSize(16,16);
        calcButton.setBackground(titleBar.getBackground());
        calcButton.setFocusable(false);
        calcButton.setForeground(Color.white);
        calcButton.setBorderPainted(false);
        JButton graphButton=new JButton();
        graphButton.setIcon(new ImageIcon("graph.png"));
        graphButton.setSize(16,16);
        graphButton.setBackground(titleBar.getBackground());
        graphButton.setFocusable(false);
        graphButton.setForeground(Color.white);
        graphButton.setBorderPainted(false);
        titleBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        titleBar.add(calcButton);
        titleBar.add(graphButton);
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleBar.add(minimizeButton);
        titleBar.add(exitButton);
        f.setJMenuBar(new JMenuBar() {
            { add(titleBar); }
        });
        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Get the current mouse position
                mouseX = e.getX();
                mouseY = e.getY();
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Point p = f.getLocation();

                // Calculate the new position based on the mouse movement
                int x = p.x + evt.getX() - mouseX;
                int y = p.y + evt.getY() - mouseY;

                // Set the new position of the JFrame
                f.setLocation(x, y);
            }
            });
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++) {
                Buttons[i][j] = new JButton();
                Buttons[i][j].setPreferredSize(new Dimension(60, 60));
                Buttons[i][j].setFocusable(false);
                Buttons[i][j].setBackground(new Color(20,20,20));
                Buttons[i][j].setForeground(Color.white);
                Buttons[i][j].setRolloverEnabled(false);
            }
        Buttons[0][3].setText("DEL");
        Buttons[1][3].setText("+");
        Buttons[2][3].setText("-");
        Buttons[3][3].setText("*");
        Buttons[4][3].setText("/");
        Buttons[5][3].setText("=");
        Buttons[0][2].setText("C");
        Buttons[1][2].setText("sqrt(x)");
        Buttons[2][2].setText("9");
        Buttons[3][2].setText("6");
        Buttons[4][2].setText("3");
        Buttons[5][2].setText(".");
        Buttons[0][1].setText("CE");
        Buttons[1][1].setText("x^2");
        Buttons[2][1].setText("8");
        Buttons[3][1].setText("5");
        Buttons[4][1].setText("2");
        Buttons[5][1].setText("0");
        Buttons[0][0].setText("%");
        Buttons[1][0].setText("1/x");
        Buttons[2][0].setText("7");
        Buttons[3][0].setText("4");
        Buttons[4][0].setText("1");
        Buttons[5][0].setText("+/-");
        JPanel ButtonPanel = new JPanel();
        JPanel PrintPanel = new JPanel();
        JPanel PrintPanel2 = new JPanel();
        JLabel PrintLabel = new JLabel(String.valueOf(lx));
        PrintLabel.setPreferredSize(new Dimension(240, 30));
        PrintLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        PrintPanel.setBackground(Color.darkGray);
        PrintLabel.setForeground(Color.white);
        PrintPanel.setPreferredSize(new Dimension(240, 30));
        PrintPanel.add(PrintLabel);
        JLabel PrintLabel2 = new JLabel("");
        PrintPanel2.setBackground(Color.darkGray);
        PrintLabel2.setForeground(Color.lightGray);
        PrintLabel2.setPreferredSize(new Dimension(240, 30));
        PrintLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        PrintPanel2.add(PrintLabel2);
        PrintPanel2.setPreferredSize(new Dimension(240, 30));
        ButtonPanel.setLayout(new GridLayout(6, 4));
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++)
                ButtonPanel.add(Buttons[i][j]);
        GridBagLayout gridBagLayout = new GridBagLayout();
        Graph panel = new Graph();
        StringReader string=new StringReader();
        f.setSize(screenSize.width/7, screenSize.height/2);
        f.setLocation(screenSize.width/2-f.getWidth()/2,screenSize.height/2-f.getHeight()/2);
        f.setLayout(gridBagLayout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        PrintPanel2.setVisible(true);
        f.add(PrintPanel2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        PrintPanel.setVisible(true);
        f.add(PrintPanel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        ButtonPanel.setVisible(true);
        f.add(ButtonPanel, constraints);
        f.repaint();
        calcButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                f.remove(panel);
                f.remove(string);
                f.repaint();
                f.setSize(screenSize.width/7, screenSize.height/2);
                f.setLayout(gridBagLayout);
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.weightx = 1.0;
                constraints.weighty = 0.0;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                f.add(PrintPanel2, constraints);
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.weightx = 1.0;
                constraints.weighty = 0.0;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                f.add(PrintPanel, constraints);

                constraints.gridx = 0;
                constraints.gridy = 2;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.fill = GridBagConstraints.BOTH;
                f.add(ButtonPanel, constraints);
                f.repaint();
            }
        });
        graphButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                f.setSize(screenSize.width/2, screenSize.height/2);
                f.remove(PrintPanel);
                f.remove(PrintPanel2);
                f.remove(ButtonPanel);
                f.repaint();
                f.getContentPane().setLayout(new BorderLayout());
                f.getContentPane().add(string,BorderLayout.NORTH);
                f.getContentPane().add(panel, BorderLayout.CENTER);
                f.setVisible(true);
            }
        });
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++) {
                int finalI = i;
                int finalJ = j;
                Buttons[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        Buttons[finalI][finalJ].setBackground(Color.black);
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        Buttons[finalI][finalJ].setBackground(new Color(20,20,20));
                    }
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        Buttons[finalI][finalJ].setBackground(Color.black);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        Buttons[finalI][finalJ].setBackground(new Color(30,30,30));
                        if(secdx==0||seclx==0)
                            PrintLabel2.setText(" ");
                        if (BigDecimal.valueOf(lx).abs().compareTo(BigDecimal.valueOf(Long.MAX_VALUE).divide(BigDecimal.TEN)) > 0) {
                            PrintLabel.setText(BigDecimal.valueOf(lx).toEngineeringString());
                        }

                        if (BigDecimal.valueOf(seclx).abs().compareTo(BigDecimal.valueOf(Long.MAX_VALUE).divide(BigDecimal.TEN)) > 0) {
                            PrintLabel2.setText(BigDecimal.valueOf(seclx).toEngineeringString());
                        }

                        if (BigDecimal.valueOf(dx).abs().compareTo(BigDecimal.valueOf(Double.MAX_VALUE).divide(BigDecimal.TEN)) > 0) {
                            PrintLabel2.setText(BigDecimal.valueOf(dx).toEngineeringString());
                        }

                        if (BigDecimal.valueOf(secdx).abs().compareTo(BigDecimal.valueOf(Double.MAX_VALUE).divide(BigDecimal.TEN)) > 0) {
                            PrintLabel2.setText(BigDecimal.valueOf(secdx).toEngineeringString());
                        }
                        switch (finalJ) {
                            case 0 -> {
                                switch (finalI) {
                                    case 0 -> {
                                        if (opperation == '0') {
                                            if (florint) {
                                                opperation = '%';
                                                seclx = lx;
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                opperation = '%';
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = dx;
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        } else {
                                            if (florint) {
                                                seclx = Long.parseLong(calc());
                                                opperation = '%';
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = Double.parseDouble(calc());
                                                opperation = '%';
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        }
                                    }

                                    case 1 -> {
                                        if (florint) {
                                            opperation='/';
                                            seclx=1;
                                            if(calc()!="Cannot divide by 0!"){
                                                opperation='0';
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation + lx);
                                                lx = Long.parseLong(calc());
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                            else{
                                                PrintLabel.setText(calc());
                                            }
                                        }
                                        else {
                                            if (nrofdig == 0)
                                                dx = (double) lx;
                                            if(calc()!="Cannot divide by 0!") {
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation + dx);
                                                dx = Double.parseDouble(calc());
                                                PrintLabel.setText(String.valueOf(dx));
                                            }
                                            else{
                                                PrintLabel.setText(calc());
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 7;
                                            else
                                                lx = lx * 10 - 7;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 7 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 3 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 4;
                                            else
                                                lx = lx * 10 - 4;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 4 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 4 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 1;
                                            else
                                                lx = lx * 10 - 1;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 1 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 5 -> {
                                        if (florint) {
                                            lx = -lx;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            dx = -dx;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                }
                            }
                            case 1 -> {
                                switch (finalI) {
                                    case 0 -> {
                                        dx = 0;
                                        lx = 0;
                                        if (!florint)
                                            florint = true;
                                        PrintLabel.setText(String.valueOf(lx));
                                        nrofdig = 0;
                                    }
                                    case 1 -> {
                                        if (florint) {
                                            lx = (long) Math.pow(lx, 2);
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx = Math.pow(dx, 2);
                                            String str = Double.toString(dx);
                                            for (int p = 0; p < str.length(); p++) {
                                                if (str.charAt(p) == '.')
                                                    nrofdig = str.length() - p - 1;
                                            }
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 2 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 8;
                                            else
                                                lx = lx * 10 - 8;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 8 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 3 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 5;
                                            else
                                                lx = lx * 10 - 5;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 5 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 4 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 2;
                                            else
                                                lx = lx * 10 - 2;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 2 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 5 -> {
                                        if (florint) {
                                            lx = lx * 10;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                }
                            }
                            case 2 -> {
                                switch (finalI) {
                                    case 0 -> {
                                        lx = 0;
                                        nrofdig = 0;
                                        dx = 0;
                                        if (!florint)
                                            florint = true;
                                        seclx = 0;
                                        secdx = 0;
                                        PrintLabel.setText(String.valueOf(lx));
                                        PrintLabel2.setText("");
                                    }
                                    case 1 -> {
                                        if (florint) {
                                            dx = Math.sqrt(lx);
                                            PrintLabel.setText(String.valueOf(dx));
                                        } else {
                                            dx = Math.sqrt(dx);
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 2 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 9;
                                            else
                                                lx = lx * 10 - 9;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx += 9 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 3 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 6;
                                            else
                                                lx = lx * 10 - 6;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0)
                                                dx = (double) lx;
                                            dx += 6 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 4 -> {
                                        if (florint) {
                                            if (lx == Math.abs(lx))
                                                lx = lx * 10 + 3;
                                            else
                                                lx = lx * 10 - 3;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0)
                                                dx = (double) lx;
                                            dx += 3 * Math.pow(10, -(nrofdig + 1));
                                            nrofdig++;
                                            PrintLabel.setText(String.valueOf(dx));
                                        }
                                    }
                                    case 5 -> florint = !florint;
                                }
                            }
                            case 3 -> {
                                switch (finalI) {
                                    case 0 -> {
                                        if (florint) {
                                            lx /= 10;
                                            PrintLabel.setText(String.valueOf(lx));
                                        } else {
                                            if (nrofdig == 0) {
                                                dx = (double) lx;
                                            }
                                            dx *= Math.pow(10, nrofdig);
                                            dx /= 10;
                                            nrofdig--;
                                            dx /= Math.pow(10, nrofdig);
                                            if (nrofdig == 0 || (dx * 10) % 10 == 0) {
                                                lx = (int) dx;
                                                florint = true;
                                                PrintLabel.setText(String.valueOf(lx));
                                            } else {
                                                PrintLabel.setText(String.valueOf(dx));
                                            }
                                        }
                                    }
                                    case 1 -> {
                                        if (opperation == '0') {
                                            if (florint) {
                                                opperation = '+';
                                                seclx = lx;
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                opperation = '+';
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = dx;
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        } else {
                                            if (florint) {
                                                seclx = Long.parseLong(calc());
                                                opperation = '+';
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = Double.parseDouble(calc());
                                                opperation = '+';
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        if (opperation == '0') {
                                            if (florint) {
                                                opperation = '-';
                                                seclx = lx;
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                opperation = '-';
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = dx;
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        } else {
                                            if (florint) {
                                                seclx = Long.parseLong(calc());
                                                opperation = '-';
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = Double.parseDouble(calc());
                                                opperation = '-';
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        }
                                    }
                                    case 3 -> {
                                        if (opperation == '0') {
                                            if (florint) {
                                                opperation = '*';
                                                seclx = lx;
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                opperation = '*';
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = dx;
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        } else {
                                            if (florint) {
                                                seclx = Long.parseLong(calc());
                                                opperation = '*';
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = Double.parseDouble(calc());
                                                opperation = '*';
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        }
                                    }
                                    case 4 -> {
                                        if (opperation == '0') {
                                            if (florint) {
                                                opperation = '/';
                                                seclx = lx;
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                opperation = '/';
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = dx;
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        } else {
                                            if (florint) {
                                                seclx = Long.parseLong(calc());
                                                opperation = '/';
                                                lx = 0;
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));

                                            } else {
                                                if (nrofdig == 0)
                                                    dx = (double) lx;
                                                secdx = Double.parseDouble(calc());
                                                opperation = '/';
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation);
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                        }
                                    }
                                    case 5 -> {
                                        if (florint) {
                                            if(calc()!="Cannot divide by 0!") {
                                                PrintLabel2.setText(String.valueOf(seclx) + opperation + lx);
                                                lx = Long.parseLong(calc());
                                                opperation = '0';
                                                PrintLabel.setText(String.valueOf(lx));
                                            }
                                            else {
                                                PrintLabel.setText(calc());
                                            }
                                        } else {
                                            if (nrofdig == 0)
                                                dx = (double) lx;
                                            if(calc()!="Cannot divide by 0!") {
                                                PrintLabel2.setText(String.valueOf(secdx) + opperation + dx);
                                                dx = Double.parseDouble(calc());
                                                PrintLabel.setText(String.valueOf(dx));
                                            }
                                            else{
                                                PrintLabel.setText(calc());
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                });
            }
    }
    public static String calc(){
        String result=null;
        switch (opperation) {
            case '+' -> {
                if (florint)
                    result = String.valueOf(seclx + lx);
                else
                    result = String.valueOf(secdx + dx);
            }
            case '-' -> {
                if (florint)
                    result = String.valueOf(seclx - lx);
                else
                    result = String.valueOf(secdx - dx);
            }
            case '*' -> {
                if (florint)
                    result = String.valueOf(seclx * lx);
                else
                    result = String.valueOf(secdx * dx);
            }
            case '/' -> {
                if (florint) {
                    if (lx != 0) {
                        result = String.valueOf(seclx / lx);
                    } else {
                        result = "Cannot divide by 0!";
                    }
                } else {
                    if (dx != 0) {
                        result = String.valueOf(secdx / dx);
                    } else {
                        result="Cannot divide by 0!";
                    }

                }
            }
            case '%'-> {
                if (florint) {
                    if (lx != 0) {
                        result = String.valueOf(seclx % lx);
                    } else {
                        result = "Cannot divide by 0!";
                    }
                } else {
                    if (dx != 0) {
                        result = String.valueOf(secdx % dx);
                    } else {
                        result="Cannot divide by 0!";
                    }

                }
            }
        }
        return result;
    }
}