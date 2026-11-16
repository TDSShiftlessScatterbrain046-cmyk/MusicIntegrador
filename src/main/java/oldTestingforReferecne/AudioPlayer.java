package oldTestingforReferecne;

import com.goxr3plus.streamplayer.enums.Status;
import com.goxr3plus.streamplayer.stream.*;

import java.io.File;
import java.net.URL;
import java.util.Map;

import javax.sound.sampled.*;

/**
 * @author GOXR3PLUS
 * Thx for the Library
 */
public class AudioPlayer implements StreamPlayerListener {

	private String AUDIO_FILE_NAME;
	private long microsecondPosition;
	private double UsableProgress;
	StreamPlayer SP = new StreamPlayer();

	public AudioPlayer(String local) {
		super();
		this.AUDIO_FILE_NAME = local;
	}
	
	
	public void LoadAudio() {
		try {
			
			// Register to the Listeners
			SP.addStreamPlayerListener(this);
			
			AudioInputStream in = AudioSystem.getAudioInputStream(new URL(AUDIO_FILE_NAME));
			SP.open(in);
			// Open a File
			// open(new File("...")) //..Here must be the file absolute path
			// open(INPUTSTREAM)
			// open(AUDIOURL)

			// Example
			

			//Seek by bytes
			//seekBytes(500000L);

			//Seek +x seconds starting from the current position
			//seekSeconds(15); // forward 15 seconds
			//seekSeconds(15); // forward 15 seconds again

			/* Seek starting from the begginning of the audio */
			//seekTo(200);

			// Play it
			//pause();

		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void opened(final Object dataSource, final Map<String, Object> properties) {

	}

	@Override
	public void progress(final int nEncodedBytes, final long microsecondPosition, final byte[] pcmData,final Map<String, Object> properties) {

		//System.out.println("Encoded Bytes : " + nEncodedBytes);
		// Current time position in seconds:) by GOXR3PLUS STUDIO
		// This is not the more precise way ...
		// in XR3Player i am using different techniques .
		//https://github.com/goxr3plus/XR3Player
		// Just for demostration purposes :)
		// I will add more advanced techniques with milliseconds , microseconds , hours
		// and minutes soon
		// .MP3 OR .WAV
		//THE SAMPLE Audio i am using is .MP3 SO ... :)
		String extension = getExtension(AUDIO_FILE_NAME);
		

		long totalBytes = SP.getTotalBytes();
		if ("mp3".equals(extension) || "wav".equals(extension)) {

			// Calculate the progress until now
			double progress = (nEncodedBytes > 0 && totalBytes > 0)
				? (nEncodedBytes * 1.0f / totalBytes * 1.0f)
				: -1.0f;

			System.out.println("Seconds  : " + (int) (microsecondPosition / 1000000) + " s " + "Progress: [ " + progress * 100 + " ] %");
			this.microsecondPosition = microsecondPosition;
			this.UsableProgress = progress;
			
			
			// .WHATEVER MUSIC FILE*
		}


	}

	public long getMicroSecondPosition() {
		return this.microsecondPosition;
	}


	private String getExtension(String audioFileName) {
		return audioFileName.split("\\.(?=[^.]+$)")[1];
	}

	@Override
	public void statusUpdated(final StreamPlayerEvent streamPlayerEvent) {

		// Player status
		final Status status = streamPlayerEvent.getPlayerStatus();
		
		// Do different things depending on the status.
		// See XR3PLAYER https://github.com/goxr3plus/XR3Player for advanced examples
	}


	public double getUsableProgress() {
		return UsableProgress;
	}


	public void play() {
		try {
			if(SP.getStatus() == Status.OPENED) {LoadAudio();}
			SP.play();
			
		} catch (StreamPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Status getStatus() {
		SP.getStatus();
		return null;
	}


	public void stop() {
		SP.stop();
	}


	public void pause() {
		SP.pause();
	}

	

}