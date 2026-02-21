public class Course { 
    private String courseCode; 
    private String courseName; 
    private String schedule; 
 
    public Course(String courseCode, String courseName) { 
        this.courseCode = courseCode; 
        this.courseName = courseName; 
        this.schedule = ""; 
    } 
 
    public String getCourseCode() { 
        return courseCode; 
    } 
 
    public String getCourseName() { 
        return courseName; 
    } 
    public String getSchedule() { 
        return schedule; 
    } 
    public void setSchedule(String schedule) { 
this.schedule = schedule; 
} 
@Override 
public String toString() { 
return courseCode + ": " + courseName+ " (" + schedule + ")"; 
} 
} 
