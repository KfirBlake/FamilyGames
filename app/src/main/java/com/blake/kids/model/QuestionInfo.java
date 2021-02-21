package com.blake.kids.model;

/**
 * Created by Kfir Blake on 19/02/2021.
 */
public class QuestionInfo
{
    String id;
    String assetsName;
    String fileName;
    String answer;
    String question;
    //String questionAnswer;


    public QuestionInfo()
    {
    }

    public QuestionInfo(String assetsName, String fileName, String answer, String question)
    {
        this.assetsName = assetsName;
        this.fileName = fileName;
        this.answer = answer;
        this.question = question;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAssetsName()
    {
        return assetsName;
    }

    public void setAssetsName(String assetsName)
    {
        this.assetsName = assetsName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    @Override
    public String toString()
    {
        return "QuestionInfo{" +
                "id='" + id + '\'' +
                ", assetsName='" + assetsName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileNameAnswer='" + answer + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
