package incodemode.hyperdrive.tracker.torrent;

import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.Client.ClientState;

import incodemode.hyperdrive.tracker.amazon.AmazonUploader;

public class TorrentObserver implements Observer{
	
	public TorrentHolder torrentHolder;
	
	public TorrentObserver(TorrentHolder torrentHolder){
		this.torrentHolder = torrentHolder;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		long downloaded = torrentHolder.client.getTorrent().getDownloaded();
		ClientState state = torrentHolder.client.getState();
		if(state == ClientState.DONE){
			System.out.println("Mau ===================== DONE DONE DONE " + torrentHolder.fileName + " " + state.toString() + " " + downloaded);
			new AmazonUploader().upload(torrentHolder);
		}
		System.out.println("Mau ===================== " + torrentHolder.fileName + " " + state.toString() + " " + downloaded);
	}
	
}
