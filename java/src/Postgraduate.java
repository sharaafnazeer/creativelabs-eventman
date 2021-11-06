public class Postgraduate extends Student implements Exam{

    int workExperience;
    String workPlace;

    public Postgraduate(String firstName, String lastName, String address, int age) {
        super(firstName, lastName, address, age);
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public void doExam() {
        System.out.println("Postgraduate Exam");
    }
}
