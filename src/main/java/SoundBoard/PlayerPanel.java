package SoundBoard;

import java.awt.*;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

import com.goxr3plus.streamplayer.enums.Status;
import com.goxr3plus.streamplayer.stream.*;
import gui.PlayerPanelGui;

public class PlayerPanel extends PlayerPanelGui implements StreamPlayerListener {
	
	private String Local = "https://docs.google.com/uc?export=download&id=1-P-B4D3A2nmrm5anmwq5EvYPORZ3mK52";
	StreamPlayer GOXR3;
	
	
	public PlayerPanel(StreamPlayer GOXR3, int id, String name, String local) {
		super(id, name);
		this.Local = local;
		this.GOXR3 = GOXR3;
	}

	public void LoadAudio() {
		try {
			
			/**
			 * @author GOXR3PLUS
			 * Thx for the Library
			 */
			
			// Register to the Listeners
			GOXR3.addStreamPlayerListener(this);
			
			//open(new AudioInputStream())
			//open(new File());
			//open(new URL()); // sometimes doesnt respect me

			
			if(this.Local.startsWith("htt")) {
				AudioInputStream in = AudioSystem.getAudioInputStream(new URL(this.Local));
				GOXR3.open(in);

			}
			else{
				AudioInputStream in = AudioSystem.getAudioInputStream(new File(this.Local));
				GOXR3.open(in);
			}
			
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	public PlayerPanel(int id, String name, StreamPlayer gOXR3) {
		super(id, name);
		GOXR3 = gOXR3;
	}

	@Override
	public void progress(final int nEncodedBytes, final long microsecondPosition, final byte[] pcmData,final Map<String, Object> properties) {

		//System.out.println("Encoded Bytes : " + nEncodedBytes);
		
		
		// ##########################################
		//This CAME WITH the EXAMPLE CODE
		String extension = getExtension(this.Local);
		extension = "mp3";//Look it doesnt always work URLs 
		//and assuming im the one checking it works i can check if its a .mp3 or an .wav manually
		//wish i could get ogg running here too but the library isnt garanteed to work with it
		
		long totalBytes = GOXR3.getTotalBytes();
		double progress = 0.0;
		if ("mp3".equals(extension) || "wav".equals(extension)) {
			// Calculate the progress until now
			progress = (nEncodedBytes > 0 && totalBytes > 0)
				? (nEncodedBytes * 1.0f / totalBytes * 1.0f)
				: -1.0f;
			setValueProgressBar((int)(progress*100));

			System.out.println("Seconds  : " + (int) (microsecondPosition / 1000000) + " s " + "Progress: [ " + progress * 100 + " ] %");			
			// .WHATEVER MUSIC FILE*
		}

		//This CAME WITH the EXAMPLE CODE
		//##########################################
		
		int[] Time = new int[4];
		Time[0] = (int)(microsecondPosition/10000);// .00 CentiSecond
		Time[1] = (int)(microsecondPosition/1000000);// second
		Time[2] = (int)(Time[1]/6000);// minute
		Time[3] = (int)(Time[1]/36000);// hour
		setTimeLabelText(Time[3]+":"+(Time[2]%60)+":"+(Time[1]%60)+"."+(Time[0]%100));

	}

	@Override
	public void opened(Object dataSource, Map<String, Object> properties) {
		// TODO Auto-generated method stub
	}
	

	@Override
	public void statusUpdated(StreamPlayerEvent event) {
        final Status status = event.getPlayerStatus();

        if (status == Status.OPENED) {
    		lockButtons(false);
        } else if (status == Status.OPENING) {
    		lockButtons(true);
        } else if (status == Status.RESUMED) {
    		lockButtons(false);
        } else if (status == Status.PLAYING) {
    		lockButtons(false);
        } else if (status == Status.STOPPED) {
    		lockButtons(true);
        } else if (status == Status.SEEKING) {
    		lockButtons(true);
        } else if (status == Status.SEEKED) {
    		lockButtons(false);
        }
	}
	@Override
	public void StopButton() {
		GOXR3.stop();
	}
	@Override
	public void PlayButton() {
		try {
			if(GOXR3.getStatus() != Status.OPENED) {LoadAudio();}
			GOXR3.play();
			
		} catch (StreamPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	public void LoadButton() {
		LoadAudio();
	}
	@Override
	public void MoveToButton(int seconds) {
		try {
			GOXR3.seekTo(seconds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getExtension(String audioFileName) {
		return audioFileName.split("\\.(?=[^.]+$)")[1];
	}
}
