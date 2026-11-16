package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public abstract class PlayerPanelGui extends JPanel{
	
	int Id;
	String Name = "Default";
//	String Local = "https://docs.google.com/uc?export=download&id=1-P-B4D3A2nmrm5anmwq5EvYPORZ3mK52";
//	AudioPlayer application;
	
	
	public PlayerPanelGui(int id, String name) {
		super();
		this.Id = id;
		this.Name = name;
		//application = new AudioPlayer(this.Local);
		LoadPluginMagicPanel();
	}
	
	
	public abstract void LoadAudio();
	public abstract void StopButton();
	public abstract void PlayButton();
	public abstract void LoadButton();
	public abstract void MoveToButton(int seconds);
	
	Label TimeLabel;
	Button loadButton;
	JProgressBar progressBar;
	private JTextField textField;
	private Button pauseButton;
	private Button stopButton;
	private Button moveToButton;

	
	public void setTimeLabelText(String Text) {
		TimeLabel.setText(Text);
	}


	public void setValueProgressBar(int INTEGE) {
		this.progressBar.setValue(INTEGE);
	}
	
	
	public void lockButtons(boolean lock) {
		pauseButton.setEnabled(!lock);
		stopButton.setEnabled(!lock);
		moveToButton.setEnabled(!lock);
	}

	
	//Thank you window builder current
	private void LoadPluginMagicPanel(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 38, 63, 0};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("ID:"+this.Id);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(this.Name);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		loadButton = new Button("Load");
		GridBagConstraints gbc_loadButton = new GridBagConstraints();
		gbc_loadButton.insets = new Insets(0, 0, 5, 5);
		gbc_loadButton.gridx = 0;
		gbc_loadButton.gridy = 1;
		add(loadButton, gbc_loadButton);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadButton();
			}
		});
		
		Button playButton = new Button("Play");
		GridBagConstraints gbc_playButton = new GridBagConstraints();
		gbc_playButton.insets = new Insets(0, 0, 5, 5);
		gbc_playButton.gridx = 1;
		gbc_playButton.gridy = 1;
		add(playButton, gbc_playButton);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayButton();
			}
		});
		
		pauseButton = new Button("Pause");
		pauseButton.setEnabled(false);
		GridBagConstraints gbc_pauseButton = new GridBagConstraints();
		gbc_pauseButton.insets = new Insets(0, 0, 5, 5);
		gbc_pauseButton.gridx = 2;
		gbc_pauseButton.gridy = 1;
		add(pauseButton, gbc_pauseButton);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayButton();
			}
		});
		
		stopButton = new Button("Stop");
		stopButton.setEnabled(false);
		GridBagConstraints gbc_stopButton = new GridBagConstraints();
		gbc_stopButton.insets = new Insets(0, 0, 5, 5);
		gbc_stopButton.gridx = 3;
		gbc_stopButton.gridy = 1;
		add(stopButton, gbc_stopButton);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StopButton();
			}
		});
		
		textField = new JTextField(5);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		
		moveToButton = new Button("MoveTo");
		moveToButton.setEnabled(false);
		GridBagConstraints gbc_MoveTo = new GridBagConstraints();
		gbc_MoveTo.insets = new Insets(0, 0, 5, 0);
		gbc_MoveTo.gridx = 5;
		gbc_MoveTo.gridy = 1;
		add(moveToButton, gbc_MoveTo);
		moveToButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int TempTime;
				try {
					TempTime = Integer.parseInt(textField.getText());
					MoveToButton(TempTime);
				} catch (NumberFormatException iamthewalrus) {
					System.out.println("NOT A NUMBER IN TEXT FIELD");
				}
			}
		});
		
		this.TimeLabel = new Label("???");
		GridBagConstraints gbc_timeLabel = new GridBagConstraints();
		gbc_timeLabel.gridwidth = 2;
		gbc_timeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_timeLabel.gridx = 0;
		gbc_timeLabel.gridy = 2;
		add(TimeLabel, gbc_timeLabel);
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 4;
		gbc_progressBar.gridx = 2;
		gbc_progressBar.gridy = 2;
		add(progressBar, gbc_progressBar);
	}


	






}
