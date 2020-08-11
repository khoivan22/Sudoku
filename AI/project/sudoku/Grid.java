package sudoku;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Grid extends JFrame {
	private JPanel root;
	private JButton startButton;
	private JPanel center;
	private JLabel runtime;
	ArrayList<JLabel> listLB;
	Map map;
	Genetic gen;

	Timer timerR;

	public Grid() {
		listLB = new ArrayList<>();
		for (int i = 0; i < 81; i++) {
			listLB.add(new JLabel());
		}
		center.setLayout(new GridLayout(9, 9));
		map = new Map();

		addLabel();
		gen = new Genetic(map.easy.get(0));
		gen.setNodeLabel(map.easy.get(0), listLB);
		center.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		add(root);
		setVisible(true);

		/*
		 * Handler event button dang nhap
		 */
		startButton.addActionListener(e -> {
			setTime();
			gen.run(listLB);
			timerR.cancel();
		});
	}

	private void setTime() {
		Date time1 = new Date();
		timerR = new Timer();
		timerR.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat sft = new SimpleDateFormat("mm:ss");
				runtime.setText(sft.format(new Date().getTime() - time1.getTime()) + "s");
			}
		}, 1000, 1000);
	}

	private void addLabel() {
		for (JLabel label : listLB) {
			center.add(label);
			label.setFont(new Font(label.getName(), Font.PLAIN, 30));
			label.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
		}
	}

	public static void main(String[] args) {
		new Grid();
	}
}
