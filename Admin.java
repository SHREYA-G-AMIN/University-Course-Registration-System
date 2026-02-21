package code.saturday; 
 
public class Admin { 
    private String name; 
    private String id; 
 
    public Admin(String name, String id) { 
        this.name = name; 
        this.id = id; 
    } 
      
    
 
    public void addCourse(CourseRegistrationSystem system, String 
courseCode, String courseName, String studentType) { 
        Course newCourse = new Course(courseCode, courseName); 
        system.addCourse(newCourse, studentType); 
        System.out.println("Course " + courseName + " added for " + 
studentType + " students."); 
    } 
 
    public void displayInfo() { 
        System.out.println("Admin Name: " + name); 
        System.out.println("Admin ID: " + id); 
    } 
}
