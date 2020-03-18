package com.practice.weatherapp.model;

public class CurrentWeather {

    private String mainWeather;
    private String mainWeatherDescription;
    private double temperature;
    private double feelsLikeTemperature;
    private double windSpeed;
    private int windDirectionDeg;
    private int cloudinessPercentage;

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public String getMainWeatherDescription() {
        return mainWeatherDescription;
    }

    public void setMainWeatherDescription(String mainWeatherDescription) {
        this.mainWeatherDescription = mainWeatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirectionDeg() {
        return windDirectionDeg;
    }

    public void setWindDirectionDeg(int windDirectionDeg) {
        this.windDirectionDeg = windDirectionDeg;
    }

    public int getCloudinessPercentage() {
        return cloudinessPercentage;
    }

    public void setCloudinessPercentage(int cloudinessPercentage) {
        this.cloudinessPercentage = cloudinessPercentage;
    }

    public static class Builder {

        private String mainWeather;
        private String mainWeatherDescription;
        private double temperature;
        private double feelsLikeTemperature;
        private double windSpeed;
        private int windDirectionDeg;
        private int cloudinessPercentage;

        public Builder mainWeather(String mainWeather) {
            this.mainWeather = mainWeather;
            return this;
        }

        public Builder mainWeatherDescription(String mainWeatherDescription) {
            this.mainWeatherDescription = mainWeatherDescription;
            return this;
        }

        public Builder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder feelsLikeTemperature(double feelsLikeTemperature) {
            this.feelsLikeTemperature = feelsLikeTemperature;
            return this;
        }

        public Builder windSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public Builder windDirectionDeg(int windDirectionDeg) {
            this.windDirectionDeg = windDirectionDeg;
            return this;
        }

        public Builder cloudinessPercentage(int cloudinessPercentage) {
            this.cloudinessPercentage = cloudinessPercentage;
            return this;
        }

        public CurrentWeather build() {
            CurrentWeather currentWeather = new CurrentWeather();
            currentWeather.mainWeather = this.mainWeather;
            currentWeather.mainWeatherDescription = this.mainWeatherDescription;
            currentWeather.temperature = this.temperature;
            currentWeather.feelsLikeTemperature = this.feelsLikeTemperature;
            currentWeather.windSpeed = this.windSpeed;
            currentWeather.windDirectionDeg = this.windDirectionDeg;
            currentWeather.cloudinessPercentage = this.cloudinessPercentage;

            return currentWeather;
        }
    }
}
