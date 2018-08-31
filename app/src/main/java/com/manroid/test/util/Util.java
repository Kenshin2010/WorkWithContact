package com.manroid.test.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class Util {

    public static void scanContact(Context context, Boolean isConvert) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cur = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        int phoneType = pCur.getInt(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.TYPE));
                        String phoneNumber = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        switch (phoneType) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                Logger.d(phoneNumber);
                                if (isConvert)
                                    convert11To10(phoneNumber, contentResolver, id);
                                else
                                    convert10To11(phoneNumber, contentResolver, id);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                Logger.d(phoneNumber);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                Logger.d(phoneNumber);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                                Logger.d(phoneNumber);
                                break;
                            default:
                                break;
                        }
                    }
                    pCur.close();
                }
            }
        }
    }

    private static void updatePhoneNumber(ContentResolver contentResolver, long rawContactId, int phoneType, String newPhoneNumber) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, newPhoneNumber);
        StringBuffer whereClauseBuf = new StringBuffer();
        whereClauseBuf.append(ContactsContract.Data.RAW_CONTACT_ID);
        whereClauseBuf.append("=");
        whereClauseBuf.append(rawContactId);
        whereClauseBuf.append(" and ");
        whereClauseBuf.append(ContactsContract.Data.MIMETYPE);
        whereClauseBuf.append(" = '");
        String mimetype = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
        whereClauseBuf.append(mimetype);
        whereClauseBuf.append("'");
        whereClauseBuf.append(" and ");
        whereClauseBuf.append(ContactsContract.CommonDataKinds.Phone.TYPE);
        whereClauseBuf.append(" = ");
        whereClauseBuf.append(phoneType);
        Uri dataUri = ContactsContract.Data.CONTENT_URI;
        int updateCount = contentResolver.update(dataUri, contentValues, whereClauseBuf.toString(), null);
    }

    private static void convert11To10(String phoneNumber, ContentResolver contentResolver, String id) {
        String beginNumber = phoneNumber.replace(" ", "").trim();
        String cutString = beginNumber.substring(0, 4).trim();

        switch (cutString) {

            case "0120":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "070 "));
                break;
            case "0121":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "079 "));
                break;
            case "0122":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "077 "));
                break;
            case "0126":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "076 "));

                break;
            case "0128":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "078 "));
                break;


            case "0123":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "083 "));
                break;
            case "0124":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "084 "));
                break;
            case "0125":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "085 "));
                break;
            case "0127":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "081 "));
                break;
            case "0129":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "082 "));
                break;


            case "0162":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "032 "));
                break;
            case "0163":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "033 "));
                break;
            case "0164":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "034 "));
                break;
            case "0165":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "035 "));
                break;
            case "0166":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "036 "));
                break;
            case "0167":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "037 "));
                break;
            case "0168":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "038 "));
                break;
            case "0169":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "039 "));
                break;


            case "0186":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "056 "));
                break;
            case "0188":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "058 "));
                break;


            case "0199":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "059 "));
                break;


        }

    }


    private static void convert10To11(String phoneNumber, ContentResolver contentResolver, String id) {
        String beginNumber = phoneNumber.replace(" ", "").trim();
        String cutString = beginNumber.substring(0, 3).trim();

        switch (cutString) {

            case "070":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0120 "));
                break;
            case "079":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0121 "));
                break;
            case "077":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0122 "));
                break;
            case "076":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0126 "));
                break;
            case "078":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0128 "));
                break;


            case "083":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0123 "));
                break;
            case "084":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0124 "));
                break;
            case "085":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0125 "));
                break;
            case "081":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0127 "));
                break;
            case "082":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0129 "));
                break;


            case "032":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0162 "));
                break;
            case "033":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0163 "));
                break;
            case "034":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0164 "));
                break;
            case "035":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0165 "));
                break;
            case "036":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0166 "));
                break;
            case "037":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0167 "));
                break;
            case "038":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0168 "));
                break;
            case "039":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0169 "));
                break;


            case "056":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0186 "));
                break;
            case "058":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0188 "));
                break;


            case "059":
                updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, StringUtil.replaceString(phoneNumber, "0199 "));
                break;


        }

//        if (beginNumber.startsWith("0120")) {
//            String newNumberPhone = beginNumber.repla()
//            updatePhoneNumber(contentResolver, Long.parseLong(id), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, newNumberPhone);
//        }


    }



}
