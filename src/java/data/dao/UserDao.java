/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.common.DbHelper;
import data.dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangminhtien
 */
public class UserDao {

    public UserDto getUser(String userEmail, String userPassword) throws ClassNotFoundException, SQLException {
        try (Connection con = DbHelper.connect()) {
            PreparedStatement getUserStatement = con.prepareStatement("SELECT EMAIL, ISVERIFIED,SIGNINMETHOD, FULLNAME, PASSWORD, PHONENUMBER, ADDRESS, TYPE AS ROLE "
                    + "FROM USERS INNER JOIN USERROLE ON USERS.ROLEID=USERROLE.ID "
                    + "WHERE EMAIL=? AND PASSWORD=?");
            getUserStatement.setString(1, userEmail);
            getUserStatement.setString(2, userPassword);
            ResultSet result = getUserStatement.executeQuery();
            if (result.next()) {
                int isVerified = result.getInt("ISVERIFIED");
                String email = result.getString("EMAIL");
                String signInMethod = result.getString("SIGNINMETHOD");
                String fullName = result.getString("FULLNAME");
                String password = result.getString("PASSWORD");
                String address = result.getString("ADDRESS");
                String phoneNumber = result.getString("PHONENUMBER");
                String role = result.getString("ROLE");
                return new UserDto(email, isVerified == 1, signInMethod, fullName, password, phoneNumber, address, role);
            }
        }
        return null;
    }

    public UserDto getUser(String userEmail) throws ClassNotFoundException, SQLException {
        try (Connection con = DbHelper.connect()) {
            PreparedStatement getUserStatement = con.prepareStatement("SELECT EMAIL, ISVERIFIED,SIGNINMETHOD, FULLNAME, PASSWORD, PHONENUMBER, ADDRESS, TYPE AS ROLE "
                    + "FROM USERS INNER JOIN USERROLE ON USERS.ROLEID=USERROLE.ID "
                    + "WHERE EMAIL=?");
            getUserStatement.setString(1, userEmail);
            ResultSet result = getUserStatement.executeQuery();
            if (result.next()) {
                int isVerified = result.getInt("ISVERIFIED");
                String signInMethod = result.getString("SIGNINMETHOD");
                String email = result.getString("EMAIL");
                String fullName = result.getString("FULLNAME");
                String password = result.getString("PASSWORD");
                String address = result.getString("ADDRESS");
                String phoneNumber = result.getString("PHONENUMBER");
                String role = result.getString("ROLE");
                return new UserDto(email, isVerified == 1, signInMethod, fullName, password, phoneNumber, address, role);
            }
        }
        return null;
    }

    public boolean getVerifiedEmailState(String userEmail) throws ClassNotFoundException, SQLException {
        int isVerified = 0;
        try (Connection con = DbHelper.connect()) {
            PreparedStatement getUserStatement = con.prepareStatement("SELECT ISVERIFIED FROM USERS WHERE EMAIL=?");
            getUserStatement.setString(1, userEmail);
            ResultSet result = getUserStatement.executeQuery();
            if (result.next()) {
                isVerified = result.getInt("ISVERIFIED");
            }
        }
        return isVerified == 1;
    }

    public UserDto createUser(UserDto user) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;

        String roleId = "2";
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            roleId = "1";
        }
        int verified = 0;
        if (user.isIsVerified()) {
            verified = 1;
        }
        try (Connection con = DbHelper.connect()) {
            PreparedStatement createUserStatement = con.prepareStatement("INSERT INTO USERS (EMAIL, FULLNAME, PASSWORD, PHONENUMBER, ADDRESS, ROLEID, ISVERIFIED,SIGNINMETHOD) VALUES (?,?,?,?,?,?,?,?)");
            createUserStatement.setString(1, user.getEmail());
            createUserStatement.setString(2, user.getFullName());
            createUserStatement.setString(3, user.getPassword());
            createUserStatement.setString(4, user.getPhoneNumber());
            createUserStatement.setString(5, user.getAddress());
            createUserStatement.setString(6, roleId);
            createUserStatement.setInt(7, verified);
            createUserStatement.setString(8, user.getSignInMethod());
            effectedRow = createUserStatement.executeUpdate();
        }
        if (effectedRow > 0) {
            return new UserDto(user.getEmail(), verified == 1, user.getSignInMethod(), user.getFullName(), user.getPassword(), user.getPhoneNumber(), user.getAddress(), user.getRole());
        }
        return null;
    }

    public boolean updateMailVerifiedState(String userEmail, boolean isVerifed) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;
        int verified = 0;
        if (isVerifed) {
            verified = 1;
        }
        try (Connection con = DbHelper.connect()) {
            PreparedStatement updateVerifiedMailStatement = con.prepareStatement("UPDATE USERS "
                    + "SET ISVERIFIED = ? "
                    + "WHERE EMAIL = ?");
            updateVerifiedMailStatement.setInt(1, verified);
            updateVerifiedMailStatement.setString(2, userEmail);
            effectedRow = updateVerifiedMailStatement.executeUpdate();
        }
        return effectedRow > 0;
    }

    public List<UserDto> searchUser(String userName) throws ClassNotFoundException, SQLException {
        ArrayList<UserDto> result;
        try (Connection con = DbHelper.connect()) {
            Statement searchStatement = con.createStatement();
            ResultSet dbResults = searchStatement.executeQuery(
                    "SELECT EMAIL, ISVERIFIED,SIGNINMETHOD, FULLNAME, PASSWORD, PHONENUMBER, ADDRESS, TYPE AS ROLE "
                    + "FROM USERS INNER JOIN USERROLE ON USERS.ROLEID=USERROLE.ID "
                    + "WHERE FULLNAME like '%" + userName + "%'");
            result = new ArrayList();
            UserDto cached;
            while (dbResults.next()) {
                String email = dbResults.getString("EMAIL");
                String signInMethod = dbResults.getString("SIGNINMETHOD");
                String fullName = dbResults.getString("FULLNAME");
                String password = dbResults.getString("PASSWORD");
                String address = dbResults.getString("ADDRESS");
                String phoneNumber = dbResults.getString("PHONENUMBER");
                String role = dbResults.getString("ROLE");
                int isVerified = dbResults.getInt("ISVERIFIED");
                cached = new UserDto(email, isVerified == 1, signInMethod, fullName, password, phoneNumber, address, role);
                result.add(cached);
            }
        }
        return result;
    }

    public boolean deleteUser(String userEmail) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;
        try (Connection con = DbHelper.connect()) {
            PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM USERS WHERE EMAIL = ?");
            deleteStatement.setString(1, userEmail);
            effectedRow = deleteStatement.executeUpdate();
        }
        return effectedRow > 0;
    }

    public boolean updateFullName(String id, String fullName) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;
        try (Connection con = DbHelper.connect()) {
            PreparedStatement updateStatement = con.prepareStatement("UPDATE USERS SET FULLNAME = ? WHERE USERID = ?");
            updateStatement.setString(1, fullName);
            updateStatement.setString(2, id);
            effectedRow = updateStatement.executeUpdate();
        }
        return effectedRow > 0;
    }

    public UserDto updateUser(String userEmail, UserDto updateValue) throws ClassNotFoundException, SQLException {
        deleteUser(userEmail);
        return createUser(updateValue);
    }

    public boolean updateRole(String id, boolean isRole) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;

        int role = 0;
        if (isRole) {
            role = 1;
        }

        try (Connection con = DbHelper.connect()) {
            PreparedStatement updateStatement = con.prepareStatement("UPDATE USERS SET ROLE = ? WHERE USERID = ?");
            updateStatement.setInt(1, role);
            updateStatement.setString(2, id);
            effectedRow = updateStatement.executeUpdate();
        }
        return effectedRow > 0;
    }
}
