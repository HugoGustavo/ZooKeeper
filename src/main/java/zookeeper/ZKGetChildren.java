package zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetChildren {
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
				List<String> children = zooKeeper.getChildren(path, false);
				for(int i = 0; i < children.size(); i++)
					System.out.println(children.get(i));
			} else {
				System.out.println("Node does not exists");
			}
			zooKeeperConnection.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				zooKeeperConnection.close();
			} catch(Exception pass) {}
		}

	}

}
