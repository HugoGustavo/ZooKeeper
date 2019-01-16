package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZKSetData {
	private static ZooKeeper zooKeeper;
	private static ZooKeeperConnection zooKeeperConnection;
	
	
	public static void update(String path, byte[] data) throws KeeperException, InterruptedException {
		zooKeeper.setData(path, data, zooKeeper.exists(path, true).getVersion());
	}
	
	public static void main(String[] args) throws InterruptedException {
		String path = "/MyFirstZnode";
		byte[] data = "Sucess".getBytes();
		try {
			zooKeeperConnection = new ZooKeeperConnection();
			zooKeeper = zooKeeperConnection.connect("localhost");
			update(path, data);
			zooKeeperConnection.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				zooKeeperConnection.close();
			} catch(Exception e1) {}
				
		}

	}

}
