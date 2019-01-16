package zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetData {
	private static ZooKeeper zooKeeper;
	private static ZooKeeperConnection zooKeeperConnection;
	
	private static Stat znodeExists(String path) throws KeeperException, InterruptedException {
		return zooKeeper.exists(path, true);
	}
	public static void main(String[] args) {
		String path = "/MyFirstZnode";
		final CountDownLatch connectedSignal = new CountDownLatch(1);
		
		
		try {
			zooKeeperConnection = new ZooKeeperConnection();
			zooKeeper = zooKeeperConnection.connect("localhost");
			Stat stat = znodeExists(path);
			if ( stat != null ) {
				byte[] b = zooKeeper.getData(path, new Watcher() {

					public void process(WatchedEvent event) {
						if ( event.getType() == Event.EventType.None && event.getState() == KeeperState.Expired) {
							connectedSignal.countDown();
						} else {
							String path = "/MyFirstZnode";
							try {
								byte[] bn = zooKeeper.getData(path, false, null);
								String data = new String(bn, "UTF-8");
								System.out.println(data);
								connectedSignal.countDown();
							} catch(Exception ex) {
								System.out.println(ex.getMessage());
							}
						}
					}
					
				}, null);
				String data = new String(b, "UTF-8");
				System.out.println(data);
				connectedSignal.await();
			} else {
				System.out.println("Node does not exists");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
