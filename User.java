package code.saturday; 
import java.util.LinkedList; 
public abstract class User { 
protected String name; 
protected String id; 
protected LinkedList<Course> enrolledCourses; 
public User(String name, String id) { 
this.name = name; 
this.id = id; 
this.enrolledCourses = new LinkedList<>(); 
} 
public abstract void displayInfo(); 
public void enrollCourse(Course course) { 
enrolledCourses.add(course); 
System.out.println("Enrolled in: " + course); 
} 
public void viewSchedule() { 
System.out.println("Enrolled Courses for " + name + ":"); 
for (Course course : enrolledCourses) { 
System.out.println(course); 
} 
} 
} 
