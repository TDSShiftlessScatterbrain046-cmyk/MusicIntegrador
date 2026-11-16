package gui;

import javax.swing.*;

import com.goxr3plus.streamplayer.stream.StreamPlayer;

import SoundBoard.PlayerPanel;

public class MainFrame {

	public static void main(String[] args) {
		
		StreamPlayer GOXR3 = new StreamPlayer();

		JFrame janela = new JFrame();
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    janela.setTitle("Boarding Por JH e LSS"); // Define o título
	   // Encerra o programa ao fechar
	    
	    //PlayerPanel MainPanel = new PlayerPanel(GOXR3, -20,"egg","https://docs.google.com/uc?export=download&id=1-P-B4D3A2nmrm5anmwq5EvYPORZ3mK52");
	    PlayerPanel MainPanel = new PlayerPanel(GOXR3, -20,"egg","C:/Users/Lxrong-Murchein/eclipse-workspace/com.soundboarding.maven.eclipse/src/main/resources/TempSounds/bingleboingo.mp3");
	    janela.add(MainPanel);
	    
	    janela.pack();
	    
	    janela.setLocationRelativeTo(null); // Centraliza a janela na tela
	    janela.setVisible(true); // Torna a janela visível
	    
		MainPanel.requestFocusInWindow();
					
	}

}
