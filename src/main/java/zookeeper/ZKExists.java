package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKExists {
	private static ZooKeeper zooKeeper;
	private static ZooKeeperConnection zooKeeperConnection;
	
	
	public static Stat znodeExists(String path) throws KeeperException, InterruptedException {
		return zooKeeper.exists(path, true);
	}
	public static void main(String[] args) {
		String path = "/MyFirstZnode";
		
		try {
			zooKeeperConnection = new ZooKeeperConnection();
			zooKeeper = zooKeeperConnection.connect("localhost");
			Stat stat = znodeExists(path);
			if ( stat != null ) {
				System.out.println("Node exists and the node version is " + stat.getVersion());
			} else {
				System.out.println("Node does not exists");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
