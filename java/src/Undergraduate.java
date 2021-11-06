public class Undergraduate extends Student implements Exam{

    int courseCode;
    int year;
    float gpa;
    String qualification;

    public Undergraduate(String firstName, String lastName, String address, int age) {
        super(firstName, lastName, address, age);
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public void doExam() {
        System.out.println("Undergraduate Exam");
    }
}
