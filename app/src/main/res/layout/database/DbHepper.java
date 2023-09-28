package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHepper extends SQLiteOpenHelper {
    public  DbHepper(Context context){
        super(context,"sinhvien",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sinhvien="CREATE TABLE sinhvien (  ma   INTEGER PRIMARY KEY AUTOINCREMENT,  ten  TEXT    NOT NULL,  tuoi INTEGER NOT NULL);";
    sqLiteDatabase.execSQL(sinhvien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
