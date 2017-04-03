package dblasena.cis.devogellaandroidsqlitefirst;

/**
 * Created by dblasena on 3/31/2017.
 * This class handles all of the comments and will insert them into the database
 * It also has the delete comment function that when clicked will remove the comment from the database
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    /**
     * Creates an object to open the database but does not open it
     * @param context Data needed to create object
     */
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }
    /**
     * opens database connection using object previously created
     *
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    /**
     * closes database connection
     *
     */
    public void close() {
        dbHelper.close();
    }

    /**INSERT INTO comments(comment) VALUES(text);
     * creates a new comment object. It then inserts it into the database and then moves it to the front of the database table.
     * @param comment the comment text to be stored
     * @return newComment Object that was stored into the table
     */
    public Comment createComment(String comment, String rating) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        values.put(MySQLiteHelper.COLUMN_RATING, rating);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }
    /**
     * DELETE FROM comment WHERE commentid = id;
     * Removes the comment from the database when given a used id
     * @param comment Comment object that will be removed from the database
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * SELECT * FROM comments;
     * creates an array list to show all of the current comments that are being stored in the database
     * connection is closed when the command is finished
     * @return comments the array list of all of the comments
     *
     */

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }
    /**SELECT * FROM comments WHERE id=0;
     * Sets the cursor to a specific comment that is to be displayed
     * @param cursor The cursor object to display the selected comment
     * @return comment The comment that is selected to be displayed
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        comment.setRating(cursor.getString( cursor.getColumnIndex( MySQLiteHelper.COLUMN_RATING ) ));
        return comment;
    }
}

