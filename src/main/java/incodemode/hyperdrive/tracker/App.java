package incodemode.hyperdrive.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.turn.ttorrent.tracker.Tracker;

import incodemode.hyperdrive.tracker.torrent.TorrentBucket;
import incodemode.hyperdrive.tracker.torrent.TrackerServer;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableWebMvc
public class App 
{
	public static Tracker tracker;
	public static TorrentBucket bucket;
	
    public static void main( String[] args )
    {
    	
    	bucket = new TorrentBucket();
    	TrackerServer trackerServer = new TrackerServer(); 
    	tracker = trackerServer.track();
        
		SpringApplication.run(App.class, args);
        
        
        
    }
}
