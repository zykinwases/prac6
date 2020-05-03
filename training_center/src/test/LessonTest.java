package test;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import database.Lesson;
import database.Training_center;

public class LessonTest extends Assert{
	private long ids[] = {2, 0};
	
	@Test
	public void getByCourse() throws Exception {
		int i = 0;
		Collection<Lesson> lessons = Training_center.getInstance().getLessonDAO().getLessonsByCourse(
				Training_center.getInstance().getCourseDAO().getCourseById((long) 1));
		Iterator<Lesson> iter = lessons.iterator();
		while (iter.hasNext()) {
			Lesson l = iter.next();
			assertEquals((long) l.getLesson_id(), ids[i]);
			i = i + 1;
		}
	}
	
//	@Test
//	public void getByTime() throws Exception {
//		long i = 2;
//		Collection<Lesson> lessons = Training_center.getInstance().getLessonDAO().getLessonByTime(
//				toTimestamp(2020,3,2,0,0), toTimestamp(2020,3,6,0,0));
//		Iterator<Lesson> iter = lessons.iterator();
//		while (iter.hasNext()) {
//			Lesson l = iter.next();
//			assertEquals((long) l.getId(), i);
//			i = i + 1;
//		}
//	}
}
