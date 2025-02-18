package edu.missouriwestern.wwuerfele.app;

import java.time.LocalDate;
import java.util.Objects;

public class State {
    private int id;
    private int population;
    private LocalDate admission_date;
    private String state;
    private String slug;
    private String code;
    private String nickname;
    private String website;
    private String admission_number;
    private String capital_city;
    private String capital_url;
    private String population_rank;
    private String constitution_url;
    private String state_flag_url;
    private String state_seal_url;
    private String map_image_url;
    private String landscape_background_url;
    private String skyline_background_url;

    //constructors
    public State(int id, String state, String slug, String code, String nickname, String website, LocalDate admission_date,
                 String admission_number, String capital_city, String capital_url, int population, String population_rank,
                 String constitution_url, String state_flag_url, String state_seal_url, String map_image_url,
                 String landscape_background_url, String skyline_background_url){
        setId(id);
        setState(state);
        setSlug(slug);
        setCode(code);
        setNickname(nickname);
        setWebsite(website);
        setAdmission_date(admission_date);
        setAdmission_number(admission_number);
        setCapital_city(capital_city);
        setCapital_url(capital_url);
        setPopulation(population);
        setPopulation_rank(population_rank);
        setConstitution_url(constitution_url);
        setState_flag_url(state_flag_url);
        setState_seal_url(state_seal_url);
        setMap_image_url(map_image_url);
        setLandscape_background_url(landscape_background_url);
        setSkyline_background_url(skyline_background_url);
    }

    public State(){
        this(-99, "???", "???","???","???","???", LocalDate.parse("0000-01-01"),"???","???","???",-99,"???","???",
                "???","???","???", "???","???");
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAdmission_number() {
        return admission_number;
    }

    public void setAdmission_number(String admission_number) {
        this.admission_number = admission_number;
    }

    public LocalDate getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(LocalDate admission_date) {
        this.admission_date = admission_date;
    }

    public String getCapital_city() {
        return capital_city;
    }

    public void setCapital_city(String capital_city) {
        this.capital_city = capital_city;
    }

    public String getCapital_url() {
        return capital_url;
    }

    public void setCapital_url(String capital_url) {
        this.capital_url = capital_url;
    }

    public String getPopulation_rank() {
        return population_rank;
    }

    public void setPopulation_rank(String population_rank) {
        this.population_rank = population_rank;
    }

    public String getConstitution_url() {
        return constitution_url;
    }

    public void setConstitution_url(String constitution_url) {
        this.constitution_url = constitution_url;
    }

    public String getState_flag_url() {
        return state_flag_url;
    }

    public void setState_flag_url(String state_flag_url) {
        this.state_flag_url = state_flag_url;
    }

    public String getState_seal_url() {
        return state_seal_url;
    }

    public void setState_seal_url(String state_seal_url) {
        this.state_seal_url = state_seal_url;
    }

    public String getMap_image_url() {
        return map_image_url;
    }

    public void setMap_image_url(String map_image_url) {
        this.map_image_url = map_image_url;
    }

    public String getLandscape_background_url() {
        return landscape_background_url;
    }

    public void setLandscape_background_url(String landscape_background_url) {
        this.landscape_background_url = landscape_background_url;
    }

    public String getSkyline_background_url() {
        return skyline_background_url;
    }

    public void setSkyline_background_url(String skyline_background_url) {
        this.skyline_background_url = skyline_background_url;
    }

    @Override
    public String toString(){
        return "State{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", slug='" + slug + '\'' +
                ", code='" + code + '\'' +
                ", nickname='" + nickname + '\'' +
                ", website='" + website + '\'' +
                ", admission_date='" + admission_date + '\'' +
                ", admission_number='" + admission_number + '\'' +
                ", capital_city='" + capital_city + '\'' +
                ", capital_url='" + capital_url + '\'' +
                ", population='" + population + '\'' +
                ", population_rank='" + population_rank + '\'' +
                ", constitution_url='" + constitution_url + '\'' +
                ", state_flag_url='" + state_flag_url + '\'' +
                ", state_seal_url='" + state_seal_url + '\'' +
                ", map_image_url'" + map_image_url + '\'' +
                ", landscape_background_url='" + landscape_background_url + '\'' +
                ", skyline_background_url'" + skyline_background_url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id == state.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int compareTo(State other) {
        int result = this.slug.compareTo(other.slug);
        return result;
    }
}
