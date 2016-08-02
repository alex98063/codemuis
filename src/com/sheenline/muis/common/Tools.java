package com.sheenline.muis.common;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.util.Log;

public class Tools {
	// java 合并两个byte数组
	public static synchronized byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static synchronized String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}

		return stringBuilder.toString();
	}

	public static synchronized String[] bytesToStringArray(byte[] awavetemp) {
		String[] aWaveStringarry = new String[awavetemp.length / 2];

		if (awavetemp == null || awavetemp.length <= 0) {

			return null;
		}

		for (int i = 0; i < awavetemp.length / 2; i++) {

			byte[] bytevtemp = new byte[2];
			bytevtemp[0] = awavetemp[2 * i + 1];
			bytevtemp[1] = awavetemp[2 * i];

			String strv = bytesToHexString(bytevtemp);

			int v = Integer.parseInt(strv, 16);

			aWaveStringarry[i] = String.valueOf(v);

		}

		return aWaveStringarry;
	}

	public static synchronized String bytesToStringArray(byte[] awavetemp, int location) {
		String key = "";

		if (awavetemp == null || awavetemp.length <= 0) {
			return null;
		}

		byte[] byteawave = new byte[1];
		byteawave[0] = awavetemp[location];
		String strbyteawave = bytesToHexString(byteawave);
		int v = Integer.parseInt(strbyteawave, 16);

		key = String.valueOf(v);

		return key;
	}

	public static synchronized String[] bytesToStringArrayPercent(byte[] awavetemp, String key) {
		long timer3 = System.currentTimeMillis();
		String[] aWaveStringarry = new String[awavetemp.length / 2];

		if (awavetemp == null || awavetemp.length <= 0) {

			return null;
		}

		byte[] bytevtemp = new byte[2];

		for (int i = 0; i < awavetemp.length / 2; i++) {
			bytevtemp[0] = awavetemp[2 * i + 1];
			bytevtemp[1] = awavetemp[2 * i];

			String strv = bytesToHexString(bytevtemp);

			int v = Integer.parseInt(strv, 16);

			switch (key) {
				case "0":

				case "512":

				case "256":
					aWaveStringarry[i] = Integer.toString((int) Math.round(v / 512.00 * 100));
					break;

				case "4097":

				case "4098":

				case "4099":

				case "4353":

				case "4354":

				case "4355":

					aWaveStringarry[i] = Integer.toString((int) Math.round(v / 8196.00 * 100));

					break;
				default:
					break;
			}

		}

		Log.d("calctime3", String.valueOf(System.currentTimeMillis() - timer3) + " " + String.valueOf(awavetemp.length)
				+ " " + key);
		return aWaveStringarry;
	}

	public static synchronized short byteToShort(byte[] b) {
		short s = 0;
		short s0 = (short) (b[0] & 0xff);// 最低位
		short s1 = (short) (b[1] & 0xff);
		s1 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	/**
	 * Convert char to byte
	 *
	 * @param c
	 *            char
	 * @return byte
	 */
	private static synchronized byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static int[] getMax(String[] arr) {
		int[] intmax = new int[2];
		int max = Integer.MIN_VALUE;
		int index = 0;

		for (int i = 0; i < arr.length; i++) {
			if (Integer.valueOf(arr[i]) > max) {
				max = Integer.valueOf(arr[i]);

				index = i;
			}
		}

		intmax[0] = max;
		intmax[1] = index;

		return intmax;
	}

	/**
	 * Convert hex string to byte[]
	 *
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	@SuppressLint("DefaultLocale")
	public static synchronized byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	// 转换为Java格式的字节数组
	public static synchronized byte[] int2bytes2(int n) {
		byte[] result = new byte[4];
		result[3] = (byte) ((n & 0xFF000000) >> 24);
		result[2] = (byte) ((n & 0x00FF0000) >> 16);
		result[1] = (byte) ((n & 0x0000FF00) >> 8);
		result[0] = (byte) ((n & 0x000000FF));
		return result;
	}

	// // 转换为Java格式的字节数组
	// public static byte[] int2bytes(int n)
	// {
	// byte[] result = new byte[4];
	// result[0] = (byte) ((n & 0xFF000000) >> 24);
	// result[1] = (byte) ((n & 0x00FF0000) >> 16);
	// result[2] = (byte) ((n & 0x0000FF00) >> 8);
	// result[3] = (byte) ((n & 0x000000FF));
	// return result;
	// }

	public static synchronized String[] intStringArrayPercent(int[] awavetemp, String key) {
		String[] aWaveStringarry = new String[awavetemp.length];

		for (int i = 0; i < awavetemp.length; i++) {
			int v = awavetemp[i];
			switch (key) {
				case "0":

				case "512":

				case "256":
					aWaveStringarry[i] = Integer.toString((int) Math.round(v / 512.00 * 512));

					// Log.d("testjni", aWaveStringarry[i]);
					// aWaveStringarry[i] = Integer.toString((int)
					// Math.round(v));
					break;

				case "4097":

				case "4098":

				case "4099":

				case "4353":

				case "4354":

				case "4355":

					aWaveStringarry[i] = Integer.toString((int) Math.round(v / 8196.00 * 100));
					// aWaveStringarry[i] = Integer.toString((int)
					// Math.round(v));
					break;
				default:
					break;
			}
		}
		return aWaveStringarry;
	}

	// 计算最大的几个值
	public static List<Integer> selectSortK(String[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] newArr = new int[k];
		List<Integer> list = new ArrayList<Integer>();// 记录每次最大数的下标
		for (int i = 0; i < k; i++) {
			int maxValue = Integer.MIN_VALUE; // 最大值
			int maxIndex = i;
			for (int j = 0; j < arr.length; j++) {
				if (Integer.valueOf(arr[j]) > maxValue && !list.contains(j)) {
					maxValue = Integer.valueOf(arr[j]);
					maxIndex = j;
				}
			}
			if (!list.contains(maxIndex)) {// 如果不存在，就加入
				list.add(maxIndex);
				newArr[i] = maxValue;
			}
		}
		return list;
	}

	@SuppressLint("UseValueOf")
	public static synchronized byte[] shortToByte(short number) {
		int temp = number;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	@SuppressLint("UseValueOf")
	public static synchronized byte[] shortToByte2(short number) {
		int temp = number;
		byte[] b = new byte[2];
		byte[] a = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}

		a[0] = b[1];
		a[1] = b[0];

		return a;
	}

	@SuppressLint("UseValueOf")
	public static synchronized byte[] shortToByte2stern(short number) {
		int temp = number;
		byte[] b = new byte[2];
		byte[] a = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}

		a[0] = b[1];
		a[1] = b[0];

		return a;
	}
}
