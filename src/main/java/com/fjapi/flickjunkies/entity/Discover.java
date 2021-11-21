package com.fjapi.flickjunkies.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Discover
{
    public String actorId = null;
    public String title = null;
    public String genre = null;
    public String page = "1";
    public String dateMIN = null;
    public String dateMAX = null;
    public String ratingMIN = null;
    public String ratingMAX = null;
    public String language = "en";


    public String getActorId()
    {
        return actorId;
    }

    public void setActorId(String actorId)
    {
        this.actorId = actorId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getDateMIN()
    {
        return dateMIN;
    }

    public void setDateMIN(String dateMIN)
    {
        this.dateMIN = dateMIN;
    }

    public String getDateMAX()
    {
        return dateMAX;
    }

    public void setDateMAX(String dateMAX)
    {
        this.dateMAX = dateMAX;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }


    public String getRatingMIN()
    {
        return ratingMIN;
    }

    public void setRatingMIN(String ratingMIN)
    {
        this.ratingMIN = ratingMIN;
    }

    public String getRatingMAX()
    {
        return ratingMAX;
    }

    public void setRatingMAX(String ratingMAX)
    {
        this.ratingMAX = ratingMAX;
    }
}

