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
public class Zadatak4BrisanjeVrednosti {
    static Dao<Voz,Integer> vozDao;
    static Dao<Vagon,Integer> vagonDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:vozVagon.db");
            //Instanciranje Dao objekata
            vozDao = DaoManager.createDao(connectionSource, Voz.class);
            vagonDao = DaoManager.createDao(connectionSource, Vagon.class);

            //Prikaz svih vrednosti tabela Vagon
            List<Vagon> vagon = vagonDao.queryForAll();
            for (Vagon vgn:vagon)
                System.out.println("Vagon = " + vgn);

            //Brisemo atribut nosivost 10 za objekat Vagon
            List<Vagon> pronadjenVagon = vagonDao.queryForEq(Vagon.POLJE_NOSIVOST, 10);
            Vagon vagonZaIzmenu = pronadjenVagon.get(0);
            vagonDao.delete(vagonZaIzmenu);

            //Prikaz svih vrednosti tabela Vagon
            vagon = vagonDao.queryForAll();
            for (Vagon vgn:vagon)
                System.out.println("Vagon = " + vgn);

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
