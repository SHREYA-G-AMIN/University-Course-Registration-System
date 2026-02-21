package code.saturday; 
import java.util.ArrayList; 
public class Student { 
private String name; 
private String id; 
private String studentType; // "undergraduate" or "graduate" 
private ArrayList<Course> enrolledCourses; 
public Student(String name, String id, String studentType) { 
        this.name = name; 
        this.id = id; 
        this.studentType = studentType; 
        this.enrolledCourses = new ArrayList<>(); 
    } 
 
    public void enrollCourse(Course course) { 
        enrolledCourses.add(course); 
        if (course.getCourseCode().equalsIgnoreCase("CS101")) { 
            course.setSchedule("Monday to Saturday"); 
        } else if (course.getCourseCode().equalsIgnoreCase("CS102")) { 
            course.setSchedule("Tuesday to Friday"); 
        } else if (course.getCourseCode().equalsIgnoreCase("CS201")) { 
            course.setSchedule("Monday to Friday"); 
        } else if (course.getCourseCode().equalsIgnoreCase("CS202")) { 
            course.setSchedule("Thursday to Saturday"); 
        } 
        //System.out.println(name + " enrolled in " + 
course.getCourseName()); 
        System.out.println(name + " enrolled in " + 
course.getCourseName()); 
    } 
 
    public void viewSchedule() { 
        System.out.println("\n" + name + "'s Schedule:"); 
        if (enrolledCourses.isEmpty()) { 
            System.out.println("No courses enrolled."); 
        } else { 
            System.out.println("Enrolled Courses (" + 
enrolledCourses.size() + "):"); 
            for (Course course : enrolledCourses) { 
                System.out.println(" - " + course.getCourseCode() + ": " + 
                                    course.getCourseName() + " (" + 
course.getSchedule() + ")"); 
            } 
        } 
        System.out.println(); 
    } 
 
    public void displayInfo() { 
System.out.println("STUDENT DETAILS :"); 
System.out.println("Name: " + name); 
System.out.println("ID: " + id); 
System.out.println("Type: " + studentType); 
} 
public boolean dropCourseByCode(CourseRegistrationSystem system, 
String courseCode) { 
if (enrolledCourses.removeIf(course -> 
course.getCourseCode().equalsIgnoreCase(courseCode))) { 
System.out.println("Course " + courseCode + " has been dropped 
successfully."); 
return true; 
} 
System.out.println("Course not found in your enrolled courses."); 
return false; 
} 
} 
