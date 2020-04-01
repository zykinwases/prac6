package test;

import java.util.Collection;
import java.util.Iterator;
import org.testng.annotations.Test;

import database.Lesson;
import training_center.Training_center;
import training_center.Main;

public class LessonTest {
	private int ids[] = {2, 0};
	
	@Test
	public void getByCourse() throws Exception {
		int i = 0;
		Collection<Lesson> lessons = Training_center.getInstance().getLessonDAO().getLessonsByCourse(
				Training_center.getInstance().getCourseDAO().getCourseById((long) 1));
		Iterator<Lesson> iter = lessons.iterator();
		while (iter.hasNext()) {
			Lesson l = iter.next();
			assert(l.getId() == ids[i]);
			i = i + 1;
		}
	}
	
	@Test
	public void getByTime() throws Exception {
		int i = 2;
		Collection<Lesson> lessons = Training_center.getInstance().getLessonDAO().getLessonByTime(
				Main.toTimestamp(2020,3,2,0,0), Main.toTimestamp(2020,3,6,0,0));
		Iterator<Lesson> iter = lessons.iterator();
		while (iter.hasNext()) {
			Lesson l = iter.next();
			assert(l.getId() == i);
			i = i + 1;
		}
	}
}
