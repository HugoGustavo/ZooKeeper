package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZKDelete {
	private static ZooKeeper zooKeeper;
	private static ZooKeeperConnection zooKeeperConnection;
	
	
	public static void delete(String path) throws InterruptedException, KeeperException {
		zooKeeper.delete(path, zooKeeper.exists(path, true).getVersion());
	}
	public static void main(String[] args) {
		String path = "/MyFirstZnode/MyFirstChildNode";
		try {
			zooKeeperConnection = new ZooKeeperConnection();
			zooKeeper = zooKeeperConnection.connect("localhost");
			delete(path);
			zooKeeperConnection.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				zooKeeperConnection.close();
			} catch(Exception pass) {}
		}

	}

}
