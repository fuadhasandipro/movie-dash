package entities;
import java.time.LocalDate;

public class Director {
    private String directorId, name, gender, biography, image;
    private LocalDate birthdate;

    public Director() {}

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String toStringDirector() {
        return directorId + "," + name + "," + birthdate + "," + gender + "," + biography + "," + image + "\n";
    }

    public Director formDirector(String str) {
        String[] parts = str.split(",");
        Director director = new Director();
        director.setDirectorId(parts[0]);
        director.setName(parts[1]);
        director.setBirthdate(LocalDate.parse(parts[2]));
        director.setGender(parts[3]);
        director.setBiography(parts[4]);
        director.setImage(parts[5]);
        return director;
    }
}
