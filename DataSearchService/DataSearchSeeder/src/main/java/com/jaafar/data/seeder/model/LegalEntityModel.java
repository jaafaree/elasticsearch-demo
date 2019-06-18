package com.jaafar.data.seeder.model;

/**
 * @author jaafaree
 * @create 2019/6/18 21:55
 */
public class LegalEntityModel extends BaseLegalEntityModel {

    private Long id;
    private String name;
    private Long registrationDay;
    private String character;
    private String legalRepresentative;
    private Double capital;
    private String businessScope;
    private String province;
    private String city;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegistrationDay() {
        return registrationDay;
    }

    public void setRegistrationDay(Long registrationDay) {
        this.registrationDay = registrationDay;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
