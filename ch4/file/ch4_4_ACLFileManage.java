package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclFileAttributeView;
import java.util.List;
import java.util.Set;

public class ch4_4_ACLFileManage {
	private static String pathname = "C:\\log\\test";

	public static void main(String[] args) throws IOException {
		mngACLP();
	}

	/**
	 * Managing ACL file permissions
	 * @throws IOException 
	 */
	public static void mngACLP() throws IOException {
		Path path = Paths.get(pathname);
		AclFileAttributeView view = Files.getFileAttributeView(path,
				AclFileAttributeView.class);
		List<AclEntry> aclEntryList = view.getAcl();
		displayAclEntries(aclEntryList);
	}

	private static void displayAclEntries(List<AclEntry> aclEntryList) {
		System.out.println("ACL Entry List size: " + aclEntryList.size());
		for (AclEntry entry : aclEntryList) {
			System.out.println("User Principal Name: "
					+ entry.principal().getName());
			System.out.println("ACL Entry Type: " + entry.type());
			displayEntryFlags(entry.flags());
			displayPermissions(entry.permissions());
			System.out.println();
		}
	}

	private static void displayPermissions(Set<AclEntryPermission> permissionSet) {
		if (permissionSet.isEmpty()) {
			System.out.println("No Permissions present");
		} else {
			System.out.println("Permissions");
			for (AclEntryPermission permission : permissionSet) {
				System.out.print(permission.name() + " ");
			}
			System.out.println();
		}
	}

	private static void displayEntryFlags(Set<AclEntryFlag> flagSet) {
		if (flagSet.isEmpty()) {
			System.out.println("No ACL Entry Flags present");
		} else {
			System.out.println("ACL Entry Flags");
			for (AclEntryFlag flag : flagSet) {
				System.out.print(flag.name() + " ");
			}
			System.out.println();
		}
	}

}
