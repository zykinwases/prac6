package test;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import database.StdLess;
import database.StdLessHyst;
import training_center.Training_center;

public class StdLessTest extends Assert {
	private long ids[] = {4, 1, 3, 0};
	
	@Test
	public void getByStudent() throws Exception{
		int i = 0;
		Collection<StdLess> courses = Training_center.getInstance().getStdLessDAO().getCoursesByStudent((long) 1);
		Iterator<StdLess> iter = courses.iterator();
		while (iter.hasNext()) {
			StdLess c = iter.next();
			assertEquals((long) c.getCourse_id().getId(), ids[i]);
			i = i + 1;
		}
	}
	
	@Test
	public void testHystory() throws Exception{
		Training_center.getInstance().getStdLessDAO().deleteStdLess(new StdLess(
				Training_center.getInstance().getStudentDAO().getStudentById((long) 5),
				Training_center.getInstance().getCourseDAO().getCourseById((long) 3)));
		Collection<StdLessHyst> h = Training_center.getInstance().getStdLessHystDAO().getStudentsByCourse((long) 3);
		Iterator<StdLessHyst> iter = h.iterator();
		StdLessHyst s = iter.next();
		assertEquals((long) s.getStudent_id().getId(), 5);
		assertFalse(iter.hasNext());
	}
}
