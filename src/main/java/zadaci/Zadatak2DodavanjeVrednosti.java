package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Vagon;
import model.Voz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak2DodavanjeVrednosti {

    static Dao<Voz,Integer> vozDao;
    static Dao<Vagon,Integer> vagonDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");
            //Instanciranje Dao objekata
            vozDao = DaoManager.createDao(connectionSource, Voz.class);
            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);

            //Objekti klase VOZ
            Voz v1 = new Voz("Voz1", "Dizel");
            vozDao.create(v1);

            Voz v2 = new Voz("Voz2", "Elektricni");
            vozDao.create(v2);

            //Objekti klase VAGON
            Vagon vag1 = new Vagon("Vagon1", "Za prenos goriva", 10);
            vag1.setVoz(v1);
            vagonDao.create(vag1);

            Vagon vag2 = new Vagon("Vagon2", "Za prenos toksicnih materija", 5);
            vag2.setVoz(v1);
            vagonDao.create(vag2);

            Vagon vag3 = new Vagon("Vagon3", "Za prenos psenice", 20);
            vag3.setVoz(v1);
            vagonDao.create(vag3);

            Vagon vag4 = new Vagon("Vagon4", "Za spavanje", 5);
            vag4.setVoz(v2);
            vagonDao.create(vag4);

            Vagon vag5 = new Vagon("Vagon5", "Restoran", 3);
            vag5.setVoz(v2);
            vagonDao.create(vag5);

            //Prikaz svih vrednosti tabela VAGON
            List<Vagon> vagon = vagonDao.queryForAll();
            for (Vagon vgn:vagon)
                System.out.println("Vagon = " + vgn);

            //Prikaz svih vrednosti tabela VOZ
            List<Voz> voz = vozDao.queryForAll();
            for (Voz v:voz)
                System.out.println("Voz = " + v);

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
