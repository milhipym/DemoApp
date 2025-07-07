package com.db.demoapp.ui.etc.preparedstatement.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class StorageDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "storage.db";
    private static final int DB_VERSION = 1;

    public StorageDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE storage_data (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS storage_data");
        onCreate(db);
    }

    // 데이터 삽입
    public void insertStorageData(String content) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO storage_data (content) VALUES (?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, content);
        stmt.executeInsert();
        stmt.close();
        db.close();
    }

    // 모든 데이터 조회 (최신순)
    public List<String> getAllStorageData() {
        List<String> dataList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT content FROM storage_data ORDER BY id DESC", null);
        while (cursor.moveToNext()) {
            dataList.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return dataList;
    }

    // 데이터 전체 삭제
    public void deleteAllStorageData() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("storage_data", null, null);
        db.close();
    }
}
