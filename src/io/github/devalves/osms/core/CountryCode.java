package io.github.devalves.osms.core;

/**
 * <p>List the countries that have access to the Orange SMS API</p>
 *
 * @author Amani Christian Cyrille Alves
 */
public enum CountryCode {
    botswana("+267","Botswana"),
    cameroon("+237","Cameroon"),
    ivoryCoast("+225","Ivory Coast"),
    RDCongo("+243","RD Congo"),
    egypt("+20","Egypt"),
    guineaConakry("+224","Guinea Conakry"),
    mali("+223","Mali"),
    niger("+227","Niger"),
    senegal("+221","Senegal"),
    tunis("+216","Tunis");

    private String code;
    private String country;

    CountryCode(String code, String country) {
        this.code = code;
        this.country = country;
    }


    /**
     * <p>Get the country code</p>
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * <p>Get the country name</p>
     */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Country : "+country+";Code : "+code;
    }
}
