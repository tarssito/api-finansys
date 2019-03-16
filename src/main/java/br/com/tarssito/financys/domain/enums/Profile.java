package br.com.tarssito.financys.domain.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int code;
    private String description;

    private Profile(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (Profile t : Profile.values()) {
            if (code.equals(t.getCode())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
