package incodemode.hyperdrive.tracker.torrent;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.client.Client.ClientState;

public class TorrentHolder extends Thread{
	
	
	boolean stopper = false;
	public String fileName;
	public String torrentFilePath;
	public File torrentFile;
	public File parentLocationFile; 
	public SharedTorrent torrent;
	public Client client;
	public String amazonPath;
	public String userName;
	public String md5;
	public String name;
	
	public TorrentHolder(String userName, String md5, String name, String amazonPath){
		
		this.userName = userName;
		this.md5 = md5;
		this.name = name;
		
		String fileName = userName + "-" + md5 + "-" + name + ".torrent";
		this.fileName = fileName;
		
		try {
			
			InetSocketAddress ia3 = new InetSocketAddress("localhost",6868);
			parentLocationFile = new File("/var/www/client-downloads/" + fileName );
			parentLocationFile.mkdir();
			torrentFilePath = "/var/www/client-torrents/" + fileName;
			torrentFile = new File(torrentFilePath); 
			torrent = SharedTorrent.fromFile(torrentFile, parentLocationFile);
			
			client = new Client( ia3.getAddress() , torrent );
			
			client.setMaxDownloadRate(0.0);
			client.setMaxUploadRate(0.0);
		
			
			System.out.println("UploadClient - Uploading - " + fileName);
			// Downloading and seeding is done in background threads.
			// To wait for this process to finish, call:
			
			
			client.addObserver(new TorrentObserver(this));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.amazonPath = amazonPath;
		
	}

	public void run(){
		
		while(!stopper){
			
			long downloaded = client.getTorrent().getDownloaded();
			ClientState state = client.getState();
			System.out.println("Mau " + fileName + " " + state.toString() + " " + downloaded);
			
		}
		
	}
	
	public String getUploadedFilePath(){
		return parentLocationFile +"/" + name;
	}
	public File getUploadedFile(){
		return new File(getUploadedFilePath());
	}
	
}
