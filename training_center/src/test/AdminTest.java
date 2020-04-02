package test;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.*;

import database.*;
import training_center.Training_center;

public class AdminTest extends Assert {
	
    @Test(priority = 0)
    public void getById() throws Exception {   	
        assertNull(Training_center.getInstance().getAdminDAO().getAdminById((long) 100));
        Admin admin = Training_center.getInstance().getAdminDAO().getAdminById((long) 1);
        assertEquals((long) admin.getId(), 1);
        assertEquals(admin.getLogin(), "admin"); 
        assertEquals(admin.getPswd_hash(), "f189656226a53e50eae44f80d4befb6e");
        assertEquals(admin.getLast_name(), "Araratova");
        assertEquals(admin.getFirst_name(), "Diana");
    }

    @Test(priority = 0)
    public void insert() throws Exception {
    	Training_center.getInstance().getAdminDAO().addAdmin(new Admin((long) 2, "betteradmin", "s189656226a53e50eae44f80d4befb6e", "Gorge", "Alex"));
    	Admin admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
    	assertEquals(admin.getLogin(), "betteradmin");
    }
    
    @Test(priority = 0)
    public void getByLogin() throws Exception {   	
        assertNull(Training_center.getInstance().getAdminDAO().getAdminByLogin("noadmin"));
        Admin admin = Training_center.getInstance().getAdminDAO().getAdminByLogin("admin");
        assertEquals((long) admin.getId(), 1);
        assertEquals(admin.getLogin(), "admin"); 
        assertEquals(admin.getPswd_hash(), "f189656226a53e50eae44f80d4befb6e");
        assertEquals(admin.getLast_name(), "Araratova");
        assertEquals(admin.getFirst_name(), "Diana");
    }
    
    @Test(priority = 1)
    public void getall() throws Exception {
    	Long i = (long) 1;
        Collection<Admin> admins = Training_center.getInstance().getAdminDAO().getAllAdmins();
        Iterator<Admin> iter = admins.iterator();
        while (iter.hasNext()) {
        	Admin ad = iter.next();
        	assertEquals(i, ad.getId());
        	i = i + 1;
        }
    }
    
    @Test(priority = 1)
    public void update() throws Exception {
    	Admin admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
		admin.setLogin("bestadmin");
		Training_center.getInstance().getAdminDAO().updateAdmin(admin);
		admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
    	assertEquals(admin.getLogin(), "bestadmin");
    }
    
    @Test(priority = 2)
    public void delete() throws Exception {
    	Training_center.getInstance().getAdminDAO().deleteAdmin(
    			Training_center.getInstance().getAdminDAO().getAdminById((long) 2));
    	Admin admin = Training_center.getInstance().getAdminDAO().getAdminById(((long) 2));
    	assertNull(admin);
    }
}
