package incodemode.hyperdrive.tracker.torrent;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.Client.ClientState;
import com.turn.ttorrent.client.SharedTorrent;

public class ClientCreator {
	
	public static Client createClient(String fileName){
	  
		
		
		try {
			
	        
	        System.out.println("connection established");
			Client client;
			//System.out.println(getRouterAddress());
			//InetAddress ia = InetAddress.getLocalHost();
			//ia = getInetAddress();
			//System.out.println(ia.toString());
			//InetSocketAddress ia2 = new InetSocketAddress("localhost", 9999);
			
			InetSocketAddress ia3 = new InetSocketAddress("localhost", 6868);
			File parentLocationFile = new File("/var/www/client-downloads/" + fileName );
			parentLocationFile.mkdir();
			String torrentFilePath = "/var/www/client-torrents/" + fileName;
			File torrentFile = new File(torrentFilePath); 
			SharedTorrent torrent = SharedTorrent.fromFile(torrentFile, parentLocationFile);
			
			client = new Client( ia3.getAddress() , torrent );
			
			client.setMaxDownloadRate(0.0);
			client.setMaxUploadRate(0.0);
		
			
			System.out.println("UploadClient - Uploading - " + fileName);
			// Downloading and seeding is done in background threads.
			// To wait for this process to finish, call:
			ClientState state = client.getState();
			System.out.println(state);
			
			return client;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
