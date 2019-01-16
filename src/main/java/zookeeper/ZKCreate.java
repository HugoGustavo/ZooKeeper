package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreate {
	private static ZooKeeper zooKeeper;
	private static ZooKeeperConnection zooKeeperConnection;
	
	public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
		zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public static void main(String[] args) {
		String path = "/MyFirstZnode";
		
		byte[] data = "My first zookeper app".getBytes();
		
		try {
			zooKeeperConnection = new ZooKeeperConnection();
			zooKeeper = zooKeeperConnection.connect("localhost");
			create(path, data);
			zooKeeperConnection.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
