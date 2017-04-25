package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 25.4.17..
 */
@DatabaseTable(tableName = "voz")
public class Voz {

    //Staticki atributi
    public static final String POLJE_OZNAKA= "oznaka";
    public static final String POLJE_NAZIV= "naziv";

    //Atributi za bazu
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = POLJE_OZNAKA,canBeNull = false)
    private String oznaka;
    @DatabaseField(columnName = POLJE_NAZIV,canBeNull = false)
    private String naziv;

    //Atribut za povezivanje Voz-Vagon
    @ForeignCollectionField(foreignFieldName = "voz")
    private ForeignCollection<Vagon> vagon;

    //Konstruktori
    public Voz (){}
    public Voz (String oznaka, String naziv){
        this.oznaka = oznaka;
        this.naziv = naziv;
    }

    //Get/Set
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getOznaka(){return oznaka;}
    public void setOznaka(String oznaka){this.oznaka = oznaka;}

    public String getNaziv(){return naziv;}
    public void setNaziv(String naziv){this.naziv = naziv;}

    //Povezivanje sa klasom Vagon
    public ForeignCollection<Vagon> getVagon() {return vagon;}
    public void setVagon(ForeignCollection<Vagon> vagon){this.vagon = vagon;}

    //toString
    @Override
    public String toString(){
        return "Voz{" +
                "id=" + id +
                ", oznaka='" + oznaka + '\'' +
                ", naziv=" + naziv +
                '}';
    }

}
