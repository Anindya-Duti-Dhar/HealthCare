package reverie.corporation.com.bmi.exercise.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 12/22/2016.
 */

public class Exercise1ListItem {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    int imageUrl;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    String hint;

}
