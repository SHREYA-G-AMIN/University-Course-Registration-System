
package code.saturday;

import java.util.LinkedList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    private LinkedList<Course> undergraduateCourses;
    private LinkedList<Course> graduateCourses;
    private static final String STUDENT_PASSWORD = "student123"; 
    private static final String ADMIN_PASSWORD = "admin123"; 

    public CourseRegistrationSystem() {
        this.undergraduateCourses = new LinkedList<>();
        this.graduateCourses = new LinkedList<>();
    }

    public void addCourse(Course course, String studentType) {
        if (studentType.equalsIgnoreCase("undergraduate")) {
            undergraduateCourses.add(course);
        } else if (studentType.equalsIgnoreCase("graduate")) {
            graduateCourses.add(course);
        }
    }

    public void listCourses(String studentType) {
        if (studentType.equalsIgnoreCase("undergraduate")) {
            System.out.println("Available Undergraduate Courses:");
            for (Course course : undergraduateCourses) {
                System.out.println(course);
            }
        } else if (studentType.equalsIgnoreCase("graduate")) {
            System.out.println("Available Graduate Courses:");
            for (Course course : graduateCourses) {
                System.out.println(course);
            }
        }
    }

    public Course findCourse(String courseCode, String studentType) {
        if (studentType.equalsIgnoreCase("undergraduate")) {
            for (Course course : undergraduateCourses) {
                if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                    return course;
                }
            }
        } else if (studentType.equalsIgnoreCase("graduate")) {
            for (Course course : graduateCourses) {
                if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                    return course;
                }
            }
        }
        return null; 
    }

    public boolean removeCourseByCode(String courseCode, String studentType) {
        if (studentType.equalsIgnoreCase("undergraduate")) {
            return undergraduateCourses.removeIf(course -> course.getCourseCode().equalsIgnoreCase(courseCode));
        } else if (studentType.equalsIgnoreCase("graduate")) {
            return graduateCourses.removeIf(course -> course.getCourseCode().equalsIgnoreCase(courseCode));
        }
        return false;
    }

    public void setCourseSchedule(String courseName, String schedule) {
        for (Course course : undergraduateCourses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                course.setSchedule(schedule);
                return;
            }
        }
        for (Course course : graduateCourses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                course.setSchedule(schedule);
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addCourse(new Course("CS101", "Computer Science"), "undergraduate");
        system.addCourse(new Course("CS102", "Algorithms"), "undergraduate");
        system.addCourse(new Course("CS201", "Advanced Algorithms"), "graduate");
        system.addCourse(new Course("CS202", "Machine Learning"), "graduate");

        System.out.println("WELCOME TO COURSE REGISTRATION SYSTEM ");
        System.out.println("Are you a Student or Admin? (Enter 'student' or 'admin')");
        String userType = scanner.nextLine().toLowerCase();

        if (userType.equals("student")) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your ID:");
            String id = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            if (password.equals(STUDENT_PASSWORD)) {
                System.out.println("Login successful!");
                System.out.println("Are you an Undergraduate or Graduate? (Enter 'undergraduate' or 'graduate')");
                String studentType = scanner.nextLine().toLowerCase();
                Student student = new Student(name, id, studentType);
                student.displayInfo();

                boolean studentActive = true;
                while (studentActive) {
                    System.out.println("\nChoose an option:");
                    System.out.println("1. List Courses");
                    System.out.println("2. Enroll in a Course");
                    System.out.println("3. View Schedule");
                    System.out.println("4. Display Student Details");
                    System.out.println("5. Remove course");
                    System.out.println("6.Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            system.listCourses(studentType);
                            break;
                        case 2:
                            System.out.println("Enter course code to enroll:");
                            String input = scanner.nextLine();
                            Course course = system.findCourse(input, studentType);
                            if (course != null) {
                                student.enrollCourse(course);
                            } else {
                                System.out.println("Course not found. Please try again.");
                            }
                            break;
                        case 3:
                            student.viewSchedule();
                            break;
                        case 4:
                            student.displayInfo();
                            break;
                        case 5:
                            System.out.println("Enter course code to drop:");
                            String dropInput = scanner.nextLine();
                            if (student.dropCourseByCode(system, dropInput)) {
                                student.viewSchedule(); 
                            }
                            break;
                        case 6:
                            System.out.println("THANK YOU!!");
                            studentActive = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Authentication failed. Exiting...");
                return; 
            }
        } else if (userType.equals("admin")) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your ID:");
            String id = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            if (password.equals(ADMIN_PASSWORD)) {
                System.out.println("Login successful!");
                Admin admin = new Admin(name, id);
                admin.displayInfo();

                boolean adminActive = true;
                while (adminActive) {
                    System.out.println("\nChoose an option:");
                    System.out.println("1. Add a Course");
                    System.out.println("2. List Courses");
                    System.out.println("3. Set Course Schedule");
                    System.out.println("4. Remove Course");
                    System.out.println("5. Exit");
                    int action = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (action) {
                        case 1:
                            System.out.println("Enter course name:");
                            String courseName = scanner.nextLine();
                            System.out.println("Enter course code:");
                            String courseCode = scanner.nextLine();
                            System.out.println("Enter student type (undergraduate/graduate):");
                            String studentType = scanner.nextLine();
                            admin.addCourse(system, courseCode, courseName, studentType);
                            break;
                        case 2:
                            System.out.println("For which type of courses? (Enter 'undergraduate' or 'graduate')");
                            String type = scanner.nextLine();
                            system.listCourses(type);
                            break;
                        case 3:
                            System.out.println("Enter course name:");
                            String courseName1 = scanner.nextLine();
                            System.out.println("Enter schedule (e.g., 'Monday to Saturday' or 'Tuesday to Friday':");
                            String schedule = scanner.nextLine();
                            system.setCourseSchedule(courseName1, schedule);
                            System.out.println("Schedule updated for " + courseName1);
                            break;
                        case 4:
                            System.out.println("Enter course code to remove:");
                            String courseName2 = scanner.nextLine();
                            if (system.removeCourseByCode(courseName2, "undergraduate") || system.removeCourseByCode(courseName2, "graduate")) {
                                System.out.println("Course " + courseName2 + " has been removed successfully.");
                            } else {
                                System.out.println("Course not found.");
                            }
                            break;
                        case 5:
                            adminActive = false;
                            System.out.println("THANK YOU!!");
                            break;
                        default:
                            System.out.println("Invalid action. Please try again.");
                    }
                }
            } else {
                System.out.println("Authentication failed. Exiting...");
                return; 
            }
        } else {
            System.out.println("Invalid action.Please restart the program.");
        }
        scanner.close();
    }
}
