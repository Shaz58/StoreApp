package com.apps.storeapp.Database;

import android.content.Context;
import android.content.SharedPreferences;

import com.apps.storeapp.Models.CartItemObject;
import com.apps.storeapp.Models.UserObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "Main";
    private static final String USER = "user";
    private static final String CART = "cart";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void setUsers(List<UserObject> users){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER,new Gson().toJson(users));
        editor.commit();
    }
    public List<UserObject> getUsers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        List<UserObject> users = new ArrayList<>();
        String json = sharedPreferences.getString(USER,"[]");
        UserObject[] arr = new Gson().fromJson(json,UserObject[].class);
        if (arr.length>0){
            for (UserObject u : arr){
                users.add(u);
            }
        }

        return users;
    }
    public int getLastUserId(){
        int id = 0;
        List<UserObject> users = getUsers();
        if (users.size()>0){
            id = users.get(users.size()-1).getId();
        }

        return id;
    }
    public void RegisterUser(String username,String password){
        List<UserObject> users = getUsers();
        UserObject user = new UserObject();
        user.setId(getLastUserId()+1);
        user.setUser_name(username);
        user.setPassword(password);
        users.add(user);
        setUsers(users);
    }
    public boolean checkUserNameDublication(String username){
        boolean found = false;
        List<UserObject> users = getUsers();
        if (users.size()>0){
            for (UserObject u : users){
                if (u.getUser_name().equals(username)){
                    found = true;
                    break;
                }
            }
        }

        return found;
    }
    public boolean loginUser(String username,String password){
        boolean found = false;
        List<UserObject> users = getUsers();
        if (users.size()>0){
            for (UserObject u : users){
                if (u.getUser_name().equals(username)&&u.getPassword().equals(password)){
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public void setCart(List<CartItemObject> items){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CART,new Gson().toJson(items));
        editor.commit();
    }
    public List<CartItemObject> getCart(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        List<CartItemObject> items = new ArrayList<>();
        String json = sharedPreferences.getString(CART,"[]");
        CartItemObject[] arr = new Gson().fromJson(json,CartItemObject[].class);
        if (arr.length>0){
            for (CartItemObject u : arr){
                items.add(u);
            }
        }

        return items;
    }
    public void AddToCart(CartItemObject item){
        List<CartItemObject> items = getCart();
        items.add(item);
        setCart(items);
    }
    public boolean checkIfItemExistsInCart(int id){
        boolean found = false;
        List<CartItemObject> items = getCart();
        if (items.size()>0){
            for (CartItemObject u : items){
                if (u.getId()==id){
                    found = true;
                    break;
                }
            }
        }

        return found;
    }
    public void clearCart(){
        List<CartItemObject> items = new ArrayList<>();
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CART,new Gson().toJson(items));
        editor.commit();
    }
}
