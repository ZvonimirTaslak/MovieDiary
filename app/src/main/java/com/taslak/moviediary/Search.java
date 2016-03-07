package com.taslak.moviediary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Year")
    @Expose
    private String Year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String Type;
    @SerializedName("Poster")
    @Expose
    private String Poster;

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The Year
     */
    public String getYear() {
        return Year;
    }

    /**
     *
     * @param Year
     * The Year
     */
    public void setYear(String Year) {
        this.Year = Year;
    }

    /**
     *
     * @return
     * The imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     *
     * @param imdbID
     * The imdbID
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     *
     * @return
     * The Type
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     *
     * @return
     * The Poster
     */
    public String getPoster() {
        return Poster;
    }

    /**
     *
     * @param Poster
     * The Poster
     */
    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    @Override
    public String toString() {
        return this.Title+"\n"+this.Year;
    }
}

