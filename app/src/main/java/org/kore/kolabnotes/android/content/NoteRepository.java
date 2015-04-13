package org.kore.kolabnotes.android.content;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.kore.kolab.notes.Note;
import org.kore.kolab.notes.Note;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koni on 12.03.15.
 */
public class NoteRepository {

    // Database fields
    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_ACCOUNT,
            DatabaseHelper.COLUMN_ROOT_FOLDER,
            DatabaseHelper.COLUMN_UID,
            DatabaseHelper.COLUMN_PRODUCTID ,
            DatabaseHelper.COLUMN_CREATIONDATE ,
            DatabaseHelper.COLUMN_MODIFICATIONDATE ,
            DatabaseHelper.COLUMN_SUMMARY ,
            DatabaseHelper.COLUMN_DESCRIPTION ,
            DatabaseHelper.COLUMN_CLASSIFICATION,
            DatabaseHelper.COLUMN_DISCRIMINATOR };
    private ModificationRepository modificationRepository;

    public NoteRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
        this.context = context;
        this.modificationRepository = new ModificationRepository(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void openReadonly() {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String account, String rootFolder, Note note, String uidNotebook) {
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DISCRIMINATOR, DatabaseHelper.DESCRIMINATOR_NOTE);
        values.put(DatabaseHelper.COLUMN_ROOT_FOLDER, rootFolder);
        values.put(DatabaseHelper.COLUMN_ACCOUNT, account);
        values.put(DatabaseHelper.COLUMN_UID, note.getIdentification().getUid());
        values.put(DatabaseHelper.COLUMN_PRODUCTID, note.getIdentification().getProductId());
        values.put(DatabaseHelper.COLUMN_CREATIONDATE, note.getAuditInformation().getCreationDate().getTime());
        values.put(DatabaseHelper.COLUMN_MODIFICATIONDATE, note.getAuditInformation().getLastModificationDate().getTime());
        values.put(DatabaseHelper.COLUMN_SUMMARY, note.getSummary());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, note.getDescription());
        values.put(DatabaseHelper.COLUMN_UID_NOTEBOOK, uidNotebook);
        values.put(DatabaseHelper.COLUMN_CLASSIFICATION, note.getClassification().toString());

        database.insert(DatabaseHelper.TABLE_NOTES, null,values);

        Modification modification = modificationRepository.getUnique(account,rootFolder,note.getIdentification().getUid());

        if(modification == null){
            modificationRepository.insert(account,rootFolder,note.getIdentification().getUid(), ModificationRepository.ModificationType.INS);
        }
        close();
    }

    public void update(String account, String rootFolder,Note note){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_UID, note.getIdentification().getUid());
        values.put(DatabaseHelper.COLUMN_ROOT_FOLDER, rootFolder);
        values.put(DatabaseHelper.COLUMN_ACCOUNT, account);
        values.put(DatabaseHelper.COLUMN_PRODUCTID, note.getIdentification().getProductId());
        values.put(DatabaseHelper.COLUMN_CREATIONDATE, note.getAuditInformation().getCreationDate().getTime());
        values.put(DatabaseHelper.COLUMN_MODIFICATIONDATE, note.getAuditInformation().getCreationDate().getTime());
        values.put(DatabaseHelper.COLUMN_SUMMARY, note.getSummary());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, note.getDescription());
        values.put(DatabaseHelper.COLUMN_CLASSIFICATION, note.getClassification().toString());

        database.update(DatabaseHelper.TABLE_NOTES,
                values,
                DatabaseHelper.COLUMN_ACCOUNT + " = '" + account+"' AND "+
                DatabaseHelper.COLUMN_ROOT_FOLDER + " = '" + rootFolder+"' AND "+
                DatabaseHelper.COLUMN_UID + " = '" + note.getIdentification().getUid()+"' AND ",
                null);

        Modification modification = modificationRepository.getUnique(account,rootFolder,note.getIdentification().getUid());

        if(modification == null){
            modificationRepository.insert(account,rootFolder,note.getIdentification().getUid(), ModificationRepository.ModificationType.UPD);
        }
        close();
    }

    public void delete(String account, String rootFolder,Note note) {
        open();
       database.delete(DatabaseHelper.TABLE_NOTES,
               DatabaseHelper.COLUMN_ACCOUNT + " = '" + account+"' AND "+
               DatabaseHelper.COLUMN_ROOT_FOLDER + " = '" + rootFolder+"' AND "+
               DatabaseHelper.COLUMN_UID + " = '" + note.getIdentification().getUid()+"' ",
               null);

        Modification modification = modificationRepository.getUnique(account,rootFolder,note.getIdentification().getUid());

        if(modification == null){
            modificationRepository.insert(account,rootFolder,note.getIdentification().getUid(), ModificationRepository.ModificationType.DEL);
        }
        close();
    }


    public List<Note> getFromNotebookWithSummary(String account, String rootFolder,String uidNotebook,String summary) {
        openReadonly();
        List<Note> notes = new ArrayList<Note>();

        StringBuilder query = new StringBuilder(DatabaseHelper.COLUMN_ACCOUNT + " = '" + account+"' AND ");
        query.append(DatabaseHelper.COLUMN_ROOT_FOLDER + " = '" + rootFolder+"' AND ");
        query.append(DatabaseHelper.COLUMN_DISCRIMINATOR+" = '"+DatabaseHelper.DESCRIMINATOR_NOTE+"' AND ");
        query.append(" LOWER("+DatabaseHelper.COLUMN_SUMMARY+") like '%"+summary.toLowerCase()+"%' ");
        if(uidNotebook != null){
            query.append(" AND "+DatabaseHelper.COLUMN_UID_NOTEBOOK + " = '" + uidNotebook+"' ");
        }

        Cursor cursor = database.query(DatabaseHelper.TABLE_NOTES,
                allColumns,
                query.toString(),
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Note note = cursorToNote(account,rootFolder,cursor);
            notes.add(note);
        }
        cursor.close();
        close();
        return notes;
    }

    public List<Note> getFromNotebook(String account, String rootFolder,String uidNotebook) {
        openReadonly();
        List<Note> notes = new ArrayList<Note>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_NOTES,
                allColumns,
                DatabaseHelper.COLUMN_ACCOUNT + " = '" + account+"' AND "+
                DatabaseHelper.COLUMN_ROOT_FOLDER + " = '" + rootFolder+"' AND "+
                DatabaseHelper.COLUMN_UID_NOTEBOOK + " = '" + uidNotebook+"' AND "+
                DatabaseHelper.COLUMN_DISCRIMINATOR+" = '"+DatabaseHelper.DESCRIMINATOR_NOTE+"' ",
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Note note = cursorToNote(account,rootFolder,cursor);
            notes.add(note);
        }
        cursor.close();
        close();
        return notes;
    }

    public List<Note> getAll(String account, String rootFolder) {
        openReadonly();
        List<Note> notes = new ArrayList<Note>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_NOTES,
                allColumns,
                DatabaseHelper.COLUMN_ACCOUNT + " = '" + account+"' AND "+
                        DatabaseHelper.COLUMN_ROOT_FOLDER + " = '" + rootFolder+"' AND "+
                        DatabaseHelper.COLUMN_DISCRIMINATOR+" = '"+DatabaseHelper.DESCRIMINATOR_NOTE+"' ",
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Note note = cursorToNote(account,rootFolder,cursor);
            notes.add(note);
        }
        cursor.close();
        close();
        return notes;
    }

    public Note getByUID(String account, String rootFolder,String uid) {
        openReadonly();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NOTES,
                allColumns,
                DatabaseHelper.COLUMN_ACCOUNT + " = '" + account+"' AND "+
                DatabaseHelper.COLUMN_ROOT_FOLDER + " = '" + rootFolder+"' AND "+
                DatabaseHelper.COLUMN_UID + " = '" + uid+"' AND "+
                DatabaseHelper.COLUMN_DISCRIMINATOR+" = '"+DatabaseHelper.DESCRIMINATOR_NOTE+"' ",
                null,
                null,
                null,
                null);

        if (cursor.moveToNext()) {
            return cursorToNote(account,rootFolder,cursor);
        }
        cursor.close();
        close();
        return null;
    }

    private Note cursorToNote(String account, String rootFolder,Cursor cursor) {
        String uid = cursor.getString(3);
        String productId = cursor.getString(4);
        Long creationDate = cursor.getLong(5);
        Long modificationDate = cursor.getLong(6);
        String summary = cursor.getString(7);
        String description = cursor.getString(8);
        String classification = cursor.getString(9);

        Note.AuditInformation audit = new Note.AuditInformation(new Timestamp(creationDate),new Timestamp(modificationDate));
        Note.Identification ident = new Note.Identification(uid,productId);

        Note note = new Note(ident,audit, Note.Classification.valueOf(classification),description);
        note.setSummary(summary);

        List<String> tags = new NoteTagRepository(context).getTagsFor(account,rootFolder,uid);

        if(tags != null && tags.size() > 0) {
            note.addCategories(tags.toArray(new String[tags.size()]));
        }
        return note;
    }
}
