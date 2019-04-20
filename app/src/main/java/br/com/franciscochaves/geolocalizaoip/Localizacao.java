package br.com.franciscochaves.geolocalizaoip;

public class Localizacao {

    //{"as":"AS53057 RedeHost Internet Ltda.","city":"Gravataí","country":"Brazil","countryCode":"BR","isp":"RedeHost Internet Ltda","lat":-29.9423,"lon":-50.9908,"org":"RedeHost Internet Ltda","query":"177.55.104.82","region":"RS","regionName":"Rio Grande do Sul","status":"success","timezone":"America/Sao_Paulo","zip":"94000-000"}


    private String countryCode;
    private String country;
    private String city;
    private String regionName;
    private String zip;

    public Localizacao(String countryCode, String country, String city, String regionName, String zip) {
        this.countryCode = countryCode;
        this.country = country;
        this.city = city;
        this.regionName = regionName;
        this.zip = zip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
