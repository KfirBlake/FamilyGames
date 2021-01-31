package com.blake.kids.util;

import com.blake.kids.model.BirdInfo;

import java.util.ArrayList;

public class BirdsUtil
{

    private BirdsUtil()
    {
        if (null == allBirds)
        {
            allBirds = new ArrayList<>();
            initData();
        }
    }

    private static BirdsUtil instance;

    public static BirdsUtil getInstance()
    {
        if (null == instance)
            instance = new BirdsUtil();
        return instance;
    }

    private static ArrayList<BirdInfo> allBirds;

    private void initData()
    {
        allBirds.add(new BirdInfo("בולבול", "bulbul", "ציפור שיר עם ראש שחור וגוף צהוב", "bulbul", "זרעים", "Description for Bulbul"));
        allBirds.add(new BirdInfo("דררה", "drara", "תוקי מרעיש ומצחיק", "drara", "עלים", "Description for Drara"));
        allBirds.add(new BirdInfo("דרור", "dror", "הגן של מעיין", "dror", "דבש", "Description for Dror"));
        allBirds.add(new BirdInfo("דוכיפת", "ducifat", "דוכיפת זאת הציפור הלאומית של ישראל", "ducifat", "חיטה", "Description for Ducifat"));
        allBirds.add(new BirdInfo("חוחית", "hohit", "חוחית יפה וחמודה", "hohit", "סעורה", "Description for Hohot"));
        allBirds.add(new BirdInfo("מינה", "myna", "ציפור פולשת ומרעישה", "myna", "גפן", "Description for Myna"));
        allBirds.add(new BirdInfo("נחליאלי", "nahlieli", "ציפור המבשרת את בוא הסתיו", "nahlieli", "תאנה", "Description for nahlieli"));

    }

    public ArrayList<BirdInfo> getAllBirds()
    {
        return allBirds;
    }

    public BirdInfo getBirdByName(String name)
    {
        for (BirdInfo birdInfo : allBirds)
        {
            if (name.equals(birdInfo.getNameOfFilesInEnglish()))
                return birdInfo;
        }
        return null;
    }
}
