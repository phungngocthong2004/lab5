package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import DTO.Sinhvien_DTO;
import database.DbHepper;

public class SinhvienDao {
    DbHepper mDbHepper;
    SQLiteDatabase db;
    public SinhvienDao(Context context){
        mDbHepper=new DbHepper(context);
        db=mDbHepper.getWritableDatabase();
    }
    public  long add(Sinhvien_DTO dto){
        ContentValues values=new ContentValues();
        values.put("ten",dto.getTen());
        values.put("tuoi",dto.getTuoi());
        return db.insert("sinhvien",null,values);

    }
    public  int update(Sinhvien_DTO dto){
        ContentValues values=new ContentValues();
        values.put("ten",dto.getTen());
        values.put("tuoi",dto.getTuoi());
        String[] dk=new String[]{String.valueOf(dto.getId())};
        return  db.update("sinhvien",values,"id=?",dk);
    }
    public int delete(Sinhvien_DTO dto){
        String[] dk=new String[]{String.valueOf(dto.getId())};
        return  db.delete("sinhvien","id=?",dk);
    }
    public ArrayList<Sinhvien_DTO> gettAll(){
        ArrayList<Sinhvien_DTO>list=new ArrayList<>();
        Cursor cursor= db.rawQuery("Select * from sinhvien ",null);
        if (cursor!=null|cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new Sinhvien_DTO(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }else{
            Log.d("zzzzzzzzz","khong lau dc gia tri");
        }
        return list;
    }
}
