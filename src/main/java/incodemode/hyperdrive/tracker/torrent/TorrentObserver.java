package incodemode.hyperdrive.tracker.torrent;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.Client.ClientState;

import incodemode.hyperdrive.checker.Md5;
import incodemode.hyperdrive.tracker.amazon.AmazonUploader;

public class TorrentObserver implements Observer{
	
	public TorrentHolder torrentHolder;
	public boolean checked = false;
	public TorrentObserver(TorrentHolder torrentHolder){
		this.torrentHolder = torrentHolder;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		long downloaded = torrentHolder.client.getTorrent().getDownloaded();
		ClientState state = torrentHolder.client.getState();
		doneCheck(state);
		
		System.out.println("Mau ===================== " + torrentHolder.fileName + " " + state.toString() + " " + downloaded);
	}
	public void doneCheck(ClientState clientState){
		if(clientState != ClientState.DONE){
			return;
		}
		
		long downloaded = torrentHolder.client.getTorrent().getDownloaded();
		if(!checked){
			
			Md5 md5 = new Md5();
			File uploadedFile = torrentHolder.getUploadedFile(); 
			String md5String = md5.md5(uploadedFile);
			if(md5String.equals(torrentHolder.md5)){
				new AmazonUploader().upload(torrentHolder);
				System.out.println("Mau ===================== DONE DONE DONE " + torrentHolder.fileName + " " + clientState.toString() + " " + downloaded);
			}else{
				System.out.println("Mau ===================== Md5 failed! Do something! " + torrentHolder.fileName + " " + clientState.toString() + " " + downloaded);
			}
			
		}
		
		
		
	}
	
}
