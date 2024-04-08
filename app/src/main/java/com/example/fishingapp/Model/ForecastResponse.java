package com.example.fishingapp.Model;

public class ForecastResponse {
    private Forecast forecast;
    private Astro astro;
    private Condition condition;

    public Astro getAstro() {
        return astro;
    }
    public void setAstro(Astro astro) {
        this.astro = astro;
    }
    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public Forecast getForecast() {
        return forecast;
    }
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
