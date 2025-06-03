package com.optimum.AvicaStaff.Utils;

import android.content.SharedPreferences;

import com.optimum.AvicaStaff.AvicaStaff;
import com.optimum.AvicaStaff.HttpUtils.GsonUtils;
import com.optimum.AvicaStaff.Models.User;

import org.json.JSONObject;

public class UserPrefs {
    private static String Type = "doc";
    //    private static SharedPreferences.Editor preferences = TeleAfya.getContext().getSharedPreferences("USER", 0).edit();
    private static SharedPreferences.Editor docPrefs = AvicaStaff.getContext().getSharedPreferences(Type + "Prefs", 0).edit();
    private static UserPrefs manager;

    public static UserPrefs getInstance() {
        if (manager == null)
            manager = new UserPrefs();
        return manager;
    }

    public static User getGetUser() {
        SharedPreferences sharedPreferences = AvicaStaff.getContext().getSharedPreferences(Type + "Prefs", 0);
        return GsonUtils.fromJSON(sharedPreferences.getString(Constants.USER, null), User.class);
    }

    public void saveUser(JSONObject user) {
        docPrefs.putString(Constants.USER, user.toString());
        docPrefs.commit();
    }

    public void saveUser(User user) {
        docPrefs.putString(Constants.USER, user.toString());
        docPrefs.commit();
    }

    public void clearDoctorUser() {
        docPrefs.remove(Constants.USER);
        docPrefs.commit();
    }

    private static final class Constants {
        static final String USER = Type,
                id = "id",
                first_name = "first_name",
                middle_name = "middle_name",
                email = "email",
                phone_number = "phone_number",
                username = "username",
                role = "role",
                uri = "uri",
                active = "active",
                organization_id = "organization_id",
                isPincode = "isPincode",
                token = "token";
    }


}
