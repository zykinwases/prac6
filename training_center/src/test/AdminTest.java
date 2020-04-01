package test;

import java.util.Collection;
import java.util.Iterator;

import org.testng.annotations.*;

import database.*;
import training_center.Training_center;

public class AdminTest {
	
    @Test(priority = 0)
    public void getById() throws Exception {   	
        assert(Training_center.getInstance().getAdminDAO().getAdminById((long) 100) == null);
        Admin admin = Training_center.getInstance().getAdminDAO().getAdminById((long) 1);
        assert(admin.getId() == 1);
        assert(admin.getLogin().equals("admin")); 
        assert(admin.getPswd_hash().equals("f189656226a53e50eae44f80d4befb6e"));
        assert(admin.getLast_name().equals("Araratova"));
        assert(admin.getFirst_name().equals("Diana"));
    }

    @Test(priority = 0)
    public void insert() throws Exception {
    	Training_center.getInstance().getAdminDAO().addAdmin(new Admin((long) 2, "betteradmin", "s189656226a53e50eae44f80d4befb6e", "Gorge", "Alex"));
    	Admin admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
    	assert(admin.getLogin().equals("betteradmin"));
    }
    
    @Test(priority = 0)
    public void getByLogin() throws Exception {   	
        assert(Training_center.getInstance().getAdminDAO().getAdminByLogin("noadmin") == null);
        Admin admin = Training_center.getInstance().getAdminDAO().getAdminByLogin("admin");
        assert(admin.getId() == 1);
        assert(admin.getLogin().equals("admin")); 
        assert(admin.getPswd_hash().equals("f189656226a53e50eae44f80d4befb6e"));
        assert(admin.getLast_name().equals("Araratova"));
        assert(admin.getFirst_name().equals("Diana"));
    }
    
    @Test(priority = 1)
    public void getall() throws Exception {
    	Long i = (long) 1;
        Collection<Admin> admins = Training_center.getInstance().getAdminDAO().getAllAdmins();
        Iterator<Admin> iter = admins.iterator();
        while (iter.hasNext()) {
        	Admin ad = iter.next();
        	assert(i == ad.getId());
        	i = i + 1;
        }
    }
    
    @Test(priority = 1)
    public void update() throws Exception {
    	Admin admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
		admin.setLogin("bestadmin");
		Training_center.getInstance().getAdminDAO().updateAdmin(admin);
		admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
    	assert(admin.getLogin().equals("bestadmin"));
    }
    
    @Test(priority = 2)
    public void delete() throws Exception {
    	Training_center.getInstance().getAdminDAO().deleteAdmin(
    			Training_center.getInstance().getAdminDAO().getAdminById((long) 2));
    	Admin admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
    	assert(admin == null);
    }
}
