package zadaci;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Vagon;
import model.Voz;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by androiddevelopment on 25.4.17..
 */
public class VagonNit extends  Thread{
    static Dao<Voz,Integer> vozDao;
    static Dao<Vagon,Integer> vagonDao;

    private Vagon vagon;
    private String oznaka;

    public VagonNit(){}
    public VagonNit(Vagon vagon, String oznaka){
        this.vagon = vagon;
        this.oznaka = oznaka;
    }

    public void run(){
        synchronized (vagonDao){
            do {
                System.out.println(oznaka + " krece utovar vagona " + vagon.getOznaka());
                System.out.print(oznaka + " tovari u vagon " + vagon.getOznaka());
                Random random = new Random();
                try {
                    this.sleep(random.nextInt(2000));
                    vagon.setTeret(vagon.getTeret()+1);
                    System.out.println("Tezina je sada " + vagon.getTeret());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        while(vagon.getTeret() != vagon.getNosivost());
        }
        System.out.println(oznaka + "Zavrsen utovar vagona " + vagon.getOznaka());
    }

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");

            //Instanciranje Dao objekata
            vozDao = DaoManager.createDao(connectionSource, Voz.class);
            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);

            List<Voz> vozovi = vozDao.queryForEq(Voz.POLJE_OZNAKA, "Voz1");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connectionSource != null) {
                try {
                    //Zatvaranje konekcije sa bazom
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
