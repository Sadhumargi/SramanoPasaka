package com.sramanopasaka.sipanionline.sadhumargi.model;

/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 */

public class Achievements {

    private String achievement_sector = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id = null;

    public Achievements(String achievement_sector, String achievement_level, String achievement_type, String achievement_detail, String achievement_year) {
        this.achievement_sector = achievement_sector;
        this.achievement_level = achievement_level;
        this.achievement_type = achievement_type;
        this.achievement_detail = achievement_detail;
        this.achievement_year = achievement_year;
    }

    private String achievement_level = null;

    private String achievement_type = null;

    private String achievement_detail = null;

    private String achievement_year = null;

    public String getAchievement_sector() {
        return achievement_sector;
    }

    public void setAchievement_sector(String achievement_sector) {
        this.achievement_sector = achievement_sector;
    }

    public String getAchievement_level() {
        return achievement_level;
    }

    public void setAchievement_level(String achievement_level) {
        this.achievement_level = achievement_level;
    }

    public String getAchievement_type() {
        return achievement_type;
    }

    public void setAchievement_type(String achievement_type) {
        this.achievement_type = achievement_type;
    }

    public String getAchievement_detail() {
        return achievement_detail;
    }

    public void setAchievement_detail(String achievement_detail) {
        this.achievement_detail = achievement_detail;
    }

    public String getAchievement_year() {
        return achievement_year;
    }

    public void setAchievement_year(String achievement_year) {
        this.achievement_year = achievement_year;
    }
}
