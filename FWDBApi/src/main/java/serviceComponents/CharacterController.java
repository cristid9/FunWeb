package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceResources.CharacterDAO;

@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @Autowired
    DBConnector dbConnector;
    CharacterDAO characterDAO;

}
