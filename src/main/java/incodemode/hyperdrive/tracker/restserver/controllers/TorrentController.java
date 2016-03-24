package incodemode.hyperdrive.tracker.restserver.controllers;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.tracker.TrackedTorrent;

import incodemode.hyperdrive.tracker.App;
import incodemode.hyperdrive.tracker.restserver.units.ExecutionResult;
import incodemode.hyperdrive.tracker.torrent.ClientCreator;
import incodemode.hyperdrive.tracker.torrent.TorrentHolder;

@RestController
public class TorrentController {
    
    
    @RequestMapping("/torrent/upload")
    public ExecutionResult<Object> upload(
    		@RequestParam(value="md5", defaultValue="") String md5,
    		@RequestParam(value="name", defaultValue="") String name,
    		@RequestParam(value="amazonPath", defaultValue="/") String amazonPath) {
    	String userName = "mercenario";
    	if(name != ""){
    		
    		
    		TorrentHolder torrentHolder = App.bucket.add(userName, md5, name, amazonPath);
    		if(torrentHolder == null){
    			ExecutionResult<Object> executionResult = (ExecutionResult<Object>) new ExecutionResult(true, "Ya se ha agregado antes el archivo " + name, false, null);
    			return executionResult;
    		}
    		File f = new File("/var/www/torrents/" + torrentHolder.fileName);
    		
    		try {
    			
				App.tracker.announce(TrackedTorrent.load(f));
			
				torrentHolder.client.download();
				
				
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				ExecutionResult<Object> executionResult = new ExecutionResult<Object>(false, null, true, e.getMessage());
				return executionResult;
			}
    	}
    	ExecutionResult<Object> executionResult = new ExecutionResult(true, null, false, null);
        return executionResult;
    }
    
    
}