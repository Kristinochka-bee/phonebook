package api.model.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//описывает тело нашего запроса
public class ContactDto {

    String firstName;
    String lastName;
    String description;


}
