package com.blake.kids.model;

public class BirdInfo
{
    private String name;
    private String nameOfFilesInEnglish;
    private String info;
    private String imageName;
    private String food;
    private String description;
    private boolean isExpanded;

    public BirdInfo(String name, String nameOfFilesInEnglish, String info, String imageName, String food, String description)
    {
        this.name = name;
        this.nameOfFilesInEnglish = nameOfFilesInEnglish;
        this.info = info;
        this.imageName = imageName;
        this.food = food;
        this.description = description;
        isExpanded = false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNameOfFilesInEnglish()
    {
        return nameOfFilesInEnglish;
    }

    public void setNameOfFilesInEnglish(String nameOfFilesInEnglish)
    {
        this.nameOfFilesInEnglish = nameOfFilesInEnglish;
    }


    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public String getFood()
    {
        return food;
    }

    public void setFood(String food)
    {
        this.food = food;
    }

    public boolean isExpanded()
    {
        return isExpanded;
    }

    public void setExpanded(boolean expanded)
    {
        isExpanded = expanded;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "BirdInfo{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
