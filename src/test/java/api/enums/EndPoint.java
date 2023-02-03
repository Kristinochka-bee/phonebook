package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EndPoint {
    
    ADD_NEW_CONTACT("/api/contact"),
    DELETE_CONTACT("/api/contact/{id}");

    private final String value;
}
