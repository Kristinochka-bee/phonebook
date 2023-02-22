package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter


public enum EndPoint {
    ADD_NEW_CONTACT("/api/contact"),
    DELETE_CONTACT("/api/contact/{id}"),
    GET_LIST_OF_CONTACTS("api/contact"),
    GET_CONTACT_BY_CONTACT_ID("/api/contact/{id}"),
    UPDATE_CONTACT("/api/contact"),
    ADD_NEW_EMAIL("/api/email"),
    GET_LIST_OF_EMAILS_BY_CONTACT_ID("/api/email/{id}/all"),
    GET_EMAIL_BY_EMAIL_ID("/api/email/{id}"),
    UPDATE_EMAIL("/api/email"),
    ADD_NEW_ADDRESS("/api/address"),
    ADD_NEW_PHONE("/api/phone"),
    UPDATE_PHONE("/api/phone"),
    GET_LIST_OF_PHONES_BY_CONTACT_ID("/api/phone/{id}/all"),

    GET_PHONE_BY_PHONE_ID("/api/phone/{id}");

    private final String value;
}
