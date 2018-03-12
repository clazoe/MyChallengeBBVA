package com.challenge.bbva.challengebbva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.challenge.bbva.challengebbva.rest.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clazoe
 */

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "bbvaDB";
    // Contacts table name
    private static final String TABLE_PRODUCTS = "Productos";
    // Shops Table Columns names
    private static final String CODIGO_PRODUCTO = "CODIGO_PRODUCTO";
    private static final String NOMBRE_PRODUCTO = "NOMBRE_PRODUCTO";
    private static final String DESCRIPCION = "DESCRIPCION";
    private static final String CANTIDAD = "CANTIDAD";



   // private static final String JSON_DATA = "Jason_Data";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      /*  String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + JSON_DATA + " TEXT)"  ;
        db.execSQL(CREATE_PRODUCTS_TABLE);*/

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + CODIGO_PRODUCTO + " INTEGER PRIMARY KEY," + NOMBRE_PRODUCTO + " TEXT," + DESCRIPCION + " TEXT," + CANTIDAD + " TEXT)"  ;
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
     // Creating tables again
        onCreate(db);
    }
    // Adding new shop
    public void addJsonData (String jsonData) {
        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JSON_DATA, jsonData); // Shop Name
         // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection*/
    }


    public void grabarProductos (List<Producto> producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        for (Producto p : producto) {
            Producto table = p;
            db.insert(TABLE_PRODUCTS, null,
                    this.getContentValues(p));

            db.setTransactionSuccessful();
            db.endTransaction();
        }


    }


    public ContentValues getContentValues(Object object) {
        Producto table = (Producto) object;
        ContentValues contentValues = new ContentValues();
        contentValues.put(CODIGO_PRODUCTO, table.getCodProd() );
        contentValues.put(NOMBRE_PRODUCTO, table.getNombreProd());
        contentValues.put(DESCRIPCION, table.getDescripcion() );
        contentValues.put(CANTIDAD, table.getCantidad());

        return contentValues;

    }



    public int getRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM " + TABLE_PRODUCTS, null);
        db.close();
        return numRows;
    }


    public ArrayList<Producto> getAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor  cursor = null;
        ArrayList<Producto> productosList = null;

        try {

            cursor= db.rawQuery("select * from " + TABLE_PRODUCTS,null);
            productosList = new ArrayList<Producto>();

            while (cursor.moveToNext()) {
                Producto producto = new Producto();
                producto.setCodProd ((   new Integer( cursor.getInt(cursor.getColumnIndex(CODIGO_PRODUCTO))) ).toString())   ;
                producto.setNombreProd(cursor.getString(cursor.getColumnIndex(NOMBRE_PRODUCTO)));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex(DESCRIPCION)));
                producto.setCantidad(cursor.getString(cursor.getColumnIndex(CANTIDAD)));

                productosList.add(producto);
            }
        }
        catch (Exception e) {
            Log.e("SqlLite", "::getAllProducts", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return productosList;
    }

}
