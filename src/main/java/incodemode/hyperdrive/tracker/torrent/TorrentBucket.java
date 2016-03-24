package incodemode.hyperdrive.tracker.torrent;


import java.util.Vector;

public class TorrentBucket {
	
	Vector<TorrentHolder> holders = new Vector<TorrentHolder>();
	public TorrentBucket(){
		
	}
	
	public TorrentHolder add(String userName, String md5, String name, String amazonPath){
		
			
		String fileName = userName + "-" + md5 + "-" + name + ".torrent";
		boolean fileNameExisted = exists(fileName, amazonPath);
		
		if(fileNameExisted){
			System.out.println("================================ Ya existe este torrent " + name);
			return null;
		}
		
		TorrentHolder holder = new TorrentHolder(userName, md5, name, amazonPath);
		
		addTorrentHolder(holder);
		if(fileNameExisted){
			return null;
		}else{
			return holder;
		}
	}
	
	public void addTorrentHolder(TorrentHolder torrentHolder){
		
		holders.add(torrentHolder);
		
	}
	
	public boolean exists(String fileName, String amazonPath){
		
		for(TorrentHolder holder : holders){
			if(holder.fileName.equals(fileName)){
				return true;
			}
		}
		return false;
		
	}

	
}
