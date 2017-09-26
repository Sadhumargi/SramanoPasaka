package com.sramanopasaka.sipanionline.sadhumargi;

import java.util.Comparator;

public class GridItem implements Comparator<GridItem> {
    private String image;
    private String name;

    public String getLogoid() {
        return logoid;
    }

    public void setLogoid(String logoid) {
        this.logoid = logoid;
    }

    private String logoid;
    private String karyakarni_id;
    public String getKaryakarni_id() {
        return karyakarni_id;
    }

    public void setKaryakarni_id(String karyakarni_id) {
        this.karyakarni_id = karyakarni_id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String city;

    public GridItem() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int compare(GridItem lhs, GridItem rhs) {

        return 0;
    }
    /*public int compareTo(GridItem compareFruit) {

        int compareQuantity = ((GridItem) compareFruit).getKaryakarni_id();

        //ascending order
        return this.getKaryakarni_id() - compareQuantity;

        //descending order
        //return compareQuantity - this.quantity;

    }*/


  /*  public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/
}
