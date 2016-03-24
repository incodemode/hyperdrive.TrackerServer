package incodemode.hyperdrive.tracker.amazon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import incodemode.hyperdrive.tracker.torrent.TorrentHolder;

public class AmazonUploader {
	TorrentHolder torrentHolder;
	public void upload(TorrentHolder torrentHolder){
		Runtime rt = Runtime.getRuntime();
		try {
			System.out.println("sudo -u mercenario -i acd_cli upload -f -x 1 '"+torrentHolder.parentLocationFile+"/" + torrentHolder.name + "' '" + torrentHolder.amazonPath+"'");
			String[] cmd = new String[]{"sudo", "-u", "mercenario", "-i", "acd_cli", "upload", "-f", "-x", "1", torrentHolder.parentLocationFile + "/" + torrentHolder.name, torrentHolder.amazonPath};
			Process proc = rt.exec(cmd);
			
			
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(proc.getInputStream()));
			
				BufferedReader stdError = new BufferedReader(new 
				     InputStreamReader(proc.getErrorStream()));
				
				// read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String s = null;
				while ((s = stdInput.readLine()) != null) {
				    System.out.println(s);
				}
				System.out.println("======================== Mau - Se termino de leer stdIn" + torrentHolder.fileName + " a " + torrentHolder.amazonPath);
				// read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
				    System.out.println(s);
				}
				System.out.println("======================== Mau - Se termino de leer stdErr" + torrentHolder.fileName + " a " + torrentHolder.amazonPath);
			// 16-03-09 21:35:34.093 [ERROR] [acd_cli] - Path "/var/www/client-downloads/mercenario-D41D8CD98F00B204E9800998ECF8427E-testFile.torrent/*" does not exist.

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
