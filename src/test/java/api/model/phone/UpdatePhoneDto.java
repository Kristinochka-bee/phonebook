package api.model.phone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneDto {
    int id;
    String countryCode;
    String phoneNumber;
    int contactId;
}
