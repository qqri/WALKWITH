package dev.ceos.caloringmvp.test;
//import lombok.Data;

import javax.persistence.*;

@Entity
//@lombok.Data
public class Caloring {
    private Caloring() {
    }

    public Caloring(String name, Integer exercising, Integer goal, Integer caloring, Integer fat) {
        this.name = name;
        this.exercising = exercising;
        this.goal = goal;
        this.caloring = caloring;
        this.fat = fat;
    }

    public void calcuateCalor() {
        caloring = (exercising / goal) * 100;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    @Lob
    private String name;

    @Column
    private Integer exercising;

    @Column
    private Integer goal;

    @Column
    private Integer caloring;


    @Column
    private Integer fat;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCaloring() {
        return caloring;
    }

    public Integer getExercising() {
        return exercising;
    }

    public Integer getGoal() {
        return goal;
    }

    public Integer getFat() {
        return fat;
    }

    public void setCaloring(Integer caloring) {
        this.caloring = caloring;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Caloring{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", exercising=" + exercising +
                ", goal=" + goal +
                ", caloring=" + caloring +
                ", fat=" + fat +
                '}';
    }

}
