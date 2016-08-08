package com.sheenline.muis.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Register extends SQLiteOpenHelper {
	private final static String	DATABASE_NAME		= "USERS.db";
	private final static int	DATABASE_VERSION	= 1;
	private final static String	TABLE_NAME			= "users_table";
	public final static String	USER_ID				= "user_id";
	public final static String	USER_NAME			= "user_name";
	public final static String	USER_PASSWORD		= "user_password";

	public Register(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	// 删除操作
	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = USER_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	public String find(String aString) {
		String bString = null;
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "muisselect * from " + TABLE_NAME + " where " + "user_name = '" + aString + "'";

		try {
			Cursor cursor = db.rawQuery(sql, null);

			while (cursor.moveToNext()) {
				bString = cursor.getString(cursor.getColumnIndex("user_password"));
			}
			if (null != cursor) {
				cursor.close();
			}

			return bString;
		}
		catch (Exception exception) {
			return null;
		}
	}

	// 增加操作
	public Boolean insert(String username, String password) {

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			String sql = "muisselect * from " + TABLE_NAME + " where " + "user_name = '" + username + "'";

			Cursor cursor = db.rawQuery(sql, null);

			if (cursor.moveToFirst() == true) {
				return false;
			}
			else
				if (null != cursor) {
					cursor.close();

					SQLiteDatabase db1 = this.getWritableDatabase();
					/* ContentValues */
					ContentValues cv = new ContentValues();
					cv.put(USER_NAME, username);
					cv.put(USER_PASSWORD, password);
					db1.insert(TABLE_NAME, null, cv);
					return true;
				}
				else {
					return false;
				}

		}
		catch (Exception exception) {
			return false;
		}

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + USER_ID
				+ " INTEGER primary key autoincrement, " + USER_NAME + " text, " + USER_PASSWORD + " text);";

		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);

	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}

	// 修改操作
	public void update(int id, String username, String password) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = USER_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };

		ContentValues cv = new ContentValues();
		cv.put(USER_NAME, username);
		cv.put(USER_PASSWORD, password);
		db.update(TABLE_NAME, cv, where, whereValue);
	}

}
