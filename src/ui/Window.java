package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JPanel{
    private static final JPanel cards = new JPanel(new CardLayout());
    private static final JComboBox combo = new JComboBox();
    private final String name;
	
	public Window(String name){
        this.name = name;
        this.setPreferredSize(new Dimension(500, 500));
		this.add(new JLabel(name));
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });
    }
	
	private static void create() {
		JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 1; i < 9; i++) {
        	Window p = new Window("Panel " + String.valueOf(i));
            combo.addItem(p);
            cards.add(p, p.toString());
        }
        JPanel control = new JPanel();
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, jcb.getSelectedItem().toString());
            }
        });
        control.add(combo);
        f.add(cards, BorderLayout.CENTER);
        f.add(control, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
