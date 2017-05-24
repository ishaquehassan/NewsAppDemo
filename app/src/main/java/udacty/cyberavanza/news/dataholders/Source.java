package udacty.cyberavanza.news.dataholders;

import android.view.View;

/**
 * Created by Ishaq Hassan on 12/3/2016.
 */

public class Source {
    String id;
    String name;
    String description;
    Images urlsToLogos;
    View.OnClickListener clickListener;

    public Source(String id, String name, String description, Images urlsToLogos, View.OnClickListener clickListener) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlsToLogos = urlsToLogos;
        this.clickListener = clickListener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Images getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(Images urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class Images{
        String small;
        String medium;
        String large;

        public Images(String small, String medium, String large) {
            this.small = small;
            this.medium = medium;
            this.large = large;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }
    }
}
