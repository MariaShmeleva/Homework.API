package tinkoff.qa.models;

import java.util.Objects;

public class PetDelete {
    private int code;
    private String type;
    private  String message;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetDelete)) return false;
        PetDelete petDelete = (PetDelete) o;
        return code == petDelete.code && Objects.equals(type, petDelete.type) && Objects.equals(message, petDelete.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, message);
    }

}


