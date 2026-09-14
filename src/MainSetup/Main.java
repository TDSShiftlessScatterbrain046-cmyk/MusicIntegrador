package MainSetup;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import Renderer.MAINpaneling;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(TestGUI()) {System.out.print("CannotRunJFRAMEGUIisHeadless");}
		
		
		else {
			JFrame janela = new JFrame();
			janela.setResizable(false);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    janela.setTitle("TROPSAS"); // Define o título
		   // Encerra o programa ao fechar
		    
		    MAINpaneling MainPanel = new MAINpaneling();
		    janela.add(MainPanel);
		    
		    janela.pack();
		    
		    janela.setLocationRelativeTo(null); // Centraliza a janela na tela
		    janela.setVisible(true); // Torna a janela visível
		    
		};
	}

	
	
	
	
    public static boolean TestGUI() {
        boolean isHeadless = GraphicsEnvironment.isHeadless();
        System.out.println("Is headless: " + isHeadless);
        return isHeadless;
	}

}
