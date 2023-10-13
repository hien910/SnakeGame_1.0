public class User {
    private String name;
    private Integer score;

    @Override
    public String toString() {
        return
                name + " " +
                 score
                ;
    }

    public User(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public User(){
        if ()
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
