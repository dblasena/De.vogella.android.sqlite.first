package dblasena.cis.devogellaandroidsqlitefirst;

/**
 * Created by dblasena on 3/31/2017.
 * Sets up the comments object and can display a comment when the id is called
 */

public class Comment {
    // Id for the comment when it is created
    private long id;
    // string value for the text of the comment
    private String comment;

    private String rating;

    /**
     * Gets the id for the comment
     * @return id
     */
    public long getId() {
        return id;
    }
    /**
     * Sets the id for the comment
     * @param id sets the id to this id
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * retrieve the comment attached to the id
     * @return comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * sets the comment to the retrieved comment
     * @param comment sets this.comment to the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * returns a string of the comments
     * @return comment
     */
    public void setRating(String rating) {this.rating = rating;}

    public String getRating() { return rating;}



    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment + " " + rating;
    }
}
