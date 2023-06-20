package dcy.mobile.weatherapp;

import java.io.Serializable;
public class Weather implements Serializable {

    public int codeCondition;
    public String date;
    public String weather;
    public int img;

    public Weather(){

    }

    public Weather(int codeCondition, String date){
        this.codeCondition = codeCondition;
        this.date = date;
    }

    public String  getDate() {
        return date;
    }

    public int getCodeCondition() {
        return codeCondition;
    }

    public void setCodeCondition(int codeCondition) {
        this.codeCondition = codeCondition;
    }
    public String getWeather(){
        switch (codeCondition) {
            case 0:
                this.weather = "Clear sky";
                break;
            case 1:
                this.weather = "Mainly clear";
                break;
            case 2:
                this.weather = "Partly cloudy";
                break;
            case 3:
                this.weather = "Overcast";
                break;
            case 45:
                this.weather = "Fog";
                break;
            case 48:
                this.weather = "Depositing rime fog";
                break;
            case 51:
                this.weather = "Drizzle light";
                break;
            case 53:
                this.weather = "Drizzle moderate";
                break;
            case 55:
                this.weather = "Drizzle dense intensity";
                break;
            case 61:
                this.weather = "Rain slight";
                break;
            case 63:
                this.weather = "Rain moderate";
                break;
            case 65:
                this.weather = "Rain heavy intensity";
                break;
            case 80:
                this.weather = "Rain shower slight";
                break;
            case 81:
                this.weather = "Rain shower moderate";
                break;
            case 82:
                this.weather = "Rain shower violent";
                break;
        }
        return weather;
    }
    public int getImg() {
        if (codeCondition == 0){
            this.img = R.drawable.clear_sky;
        } else if (codeCondition == 1 || codeCondition == 2){
            this.img = R.drawable.partly_cloudy;
        } else if (codeCondition == 3) {
            this.img = R.drawable.fog;
        } else if (codeCondition == 45 || codeCondition == 48) {
            this.img = R.drawable.day_fog;
        } else if (codeCondition == 51 || codeCondition == 53 || codeCondition == 55) {
            this.img = R.drawable.rain_drizzle;
        } else if (codeCondition == 61 || codeCondition == 63 || codeCondition == 80 || codeCondition == 81) {
            this.img = R.drawable.rain_shower;
        }
        return img;
    }
}