package entities;
import java.time.LocalDate;

public class Actor {
    private String actorId, name, gender, biography, image;
    private LocalDate birthdate;

    public Actor() {}

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
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

    public String toStringActor() {
        return actorId + "," + name + "," + birthdate + "," + gender + "," + biography + "," + image + "\n";
    }

    public Actor formActor(String str) {
        String[] parts = str.split(",");
        Actor actor = new Actor();
        actor.setActorId(parts[0]);
        actor.setName(parts[1]);
        actor.setBirthdate(LocalDate.parse(parts[2]));
        actor.setGender(parts[3]);
        actor.setBiography(parts[4]);
        actor.setImage(parts[5]);
        return actor;
    }
}
