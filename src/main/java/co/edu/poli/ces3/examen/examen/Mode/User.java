package co.edu.poli.ces3.examen.examen.Mode;

import co.edu.poli.ces3.examen.examen.dto.DtoUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Conexion implements CRUD{
        private int id;
        private String mail;
        private String name;
        private String pass;

        public User(int id, String mail, String name, String pass){
            this.id = id;
            this.mail = mail;
            this.name = name;
            this.pass = pass;
        }

        public User(String mail){
            this.mail = mail;
        }

        public User() {
        }

        public int getId(){
            return this.id;
        }

        private void setId(int id){
            this.id = id;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
        @Override
        public String toString() {
            return "El user se llama: " + this.name +
                    " su mail es: " + this.mail;
        }

        @Override
        public User create(DtoUser user) throws SQLException {
            Connection cnn = this.getConexion();
            if(cnn != null) {
                String sql1 = "SELECT * FROM user WHERE mail = ?";
                PreparedStatement stmt = cnn.prepareStatement(sql1);
                stmt.setString(1, user.getMail());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String mail = rs.getString("mail");
                        String name = rs.getString("name");
                        String pass = rs.getString("pass");
                        return null;
                    } else {
                        String sql2 = "INSERT INTO user(mail, name, pass) VALUES('"+user.getMail()+"', '"+user.getName()+"','"+user.getPass()+"')";
                        this.mail = user.getMail();
                        this.name = user.getName();
                        this.pass = user.getPass();


                            PreparedStatement stmt = cnn.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
                            stmt.executeUpdate();
                            ResultSet rs = stmt.getGeneratedKeys();
                            rs.next();
                            this.id = rs.getInt(1);

                        return this;
                        return null;
                    }
                }


            }
            return null;
        }

        @Override
        public ArrayList<User> all() {
            Connection cnn = this.getConexion();
            ArrayList<User> users = new ArrayList<>();

            if (cnn != null) {
                String sql = "SELECT id,mail,name,pass FROM user";
                try {
                    PreparedStatement stmt = cnn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String mail = rs.getString("mail");
                        String pass = rs.getString("pass");
                        User user = new User(id, mail, name,pass);
                        users.add(user);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (cnn != null) {
                            cnn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return users;
            }
            return null;
        }



        @Override
        public User findById(int userId) throws SQLException {
            Connection cnn = getConexion();

            if (cnn != null) {
                String sql = "SELECT id,mail,name,pass FROM user WHERE id = ?";
                try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                    stmt.setInt(1, userId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            int id = rs.getInt("id");
                            String mail = rs.getString("mail");
                            String name = rs.getString("name");
                            String pass = rs.getString("pass");
                            return new User(id, mail, name,pass);
                        } else {
                            return null;
                        }
                    }
                } finally {
                    if (cnn != null) {
                        cnn.close();
                    }
                }
            }
            return null;
        }

        @Override
        public User update(User user) throws SQLException {
            Connection conn = getConexion();

            if (conn != null) {
                String sql = "UPDATE user SET mail = ?, name = ?, pass = ? WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, user.getMail());
                    stmt.setString(2, user.getName());
                    stmt.setString(3, user.getPass());
                    stmt.setInt(4, user.getId());
                    stmt.executeUpdate();
                } finally {
                    if (conn != null) {
                        conn.close();
                    }
                }
            }
            return user;
        }

        @Override
        public int delete(int id) throws SQLException {
            String sql = "DELETE FROM user  WHERE id = ? ";
            Connection conn = this.getConexion();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,id);
                stmt.executeUpdate();
                return stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                conn.close();
            }

        }
}
