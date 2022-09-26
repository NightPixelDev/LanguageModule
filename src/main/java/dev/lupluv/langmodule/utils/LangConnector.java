package dev.lupluv.langmodule.utils;

import dev.lupluv.langmodule.Lang;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LangConnector {

    public static boolean isUserExists(UUID uuid){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isUserExists(String name){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE NAME = ?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void updateName(UUID uuid, String name){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("UPDATE lang_connector SET NAME = ? WHERE UUID = ?");
                ps.setString(1, name);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void updateName(Player p){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("UPDATE lang_connector SET NAME = ? WHERE UUID = ?");
                ps.setString(1, p.getName());
                ps.setString(2, p.getUniqueId().toString());
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void createUser(UUID uuid, String name, String lang){
        if(Lang.mySQL.isConnected()){
            try {
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("INSERT INTO lang_connector (UUID,NAME,LANG) VALUES (?,?,?)");
                ps.setString(1, uuid.toString());
                ps.setString(2, name);
                ps.setString(3, lang);
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void createUser(Player p, String lang){
        if(Lang.mySQL.isConnected()){
            try {
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("INSERT INTO lang_connector (UUID,NAME,LANG) VALUES (?,?,?)");
                ps.setString(1, p.getUniqueId().toString());
                ps.setString(2, p.getName());
                ps.setString(3, lang);
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void setLang(UUID uuid, String lang){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("UPDATE lang_connector SET LANG = ? WHERE UUID = ?");
                ps.setString(1, lang);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void setLang(String name, String lang){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("UPDATE lang_connector SET LANG = ? WHERE NAME = ?");
                ps.setString(1, lang);
                ps.setString(2, name);
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void setLang(Player p, String lang){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("UPDATE lang_connector SET LANG = ? WHERE UUID = ?");
                ps.setString(1, lang);
                ps.setString(2, p.getUniqueId().toString());
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static String getLangTag(Player p){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE UUID = ?");
                ps.setString(1, p.getUniqueId().toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return rs.getString("LANG");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return FileManager.getInstance().getConfig().getString("DefaultLanguage");
    }

    public static String getLangTag(UUID uuid){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return rs.getString("LANG");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return FileManager.getInstance().getConfig().getString("DefaultLanguage");
    }

    public static String getLangTag(String player){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE NAME = ?");
                ps.setString(1, player);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return rs.getString("LANG");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return FileManager.getInstance().getConfig().getString("DefaultLanguage");
    }

    public static String getNameByUuid(UUID uuid){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return rs.getString("NAME");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static UUID getUuidByName(String name){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("SELECT * FROM lang_connector WHERE NAME = ?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return UUID.fromString(rs.getString("UUID"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getLangName(Player p){
        String tag = getLangTag(p);
        return FileManager.getInstance().getConfig().getString(tag + ".Name");
    }

    public static void deleteUser(UUID uuid){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("DELETE FROM lang_connector WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void deleteUser(String name){
        if(Lang.mySQL.isConnected()){
            try{
                PreparedStatement ps = Lang.mySQL.getCon().prepareStatement("DELETE FROM lang_connector WHERE NAME = ?");
                ps.setString(1, name);
                ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
