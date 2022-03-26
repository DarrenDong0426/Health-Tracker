package com.example.healthtracker;

public class Meal {
    String title;
    String image;
    int calories;
    int proteins;
    int fats;
    int carbs;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getCarbs() {
        return carbs;
    }

    public Meal(String title, String image, int calories, int proteins, int fats, int carbs){
        this.title = title;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }


}
