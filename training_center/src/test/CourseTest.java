package test;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import database.Course;
import database.Training_center;

public class CourseTest extends Assert {
	private String names[] = {"Basics of banking", "Mathematical statistics in banking", "not going here"};
	
	@Test
	public void getByProf() throws Exception {
		int i = 0;
		Collection<Course> courses = Training_center.getInstance().getCourseDAO().getCoursesByProf(
				Training_center.getInstance().getProfessorDAO().getProfessorById((long) 1));
		Iterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
			Course c = iter.next();
			assertEquals(c.getName(), names[i]);
			i = i + 1;
		}
	}
}
