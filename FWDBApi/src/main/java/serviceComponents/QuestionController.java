package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceResources.QuestionDAO;

@RestController
@RequestMapping("/v1/question")
public class QuestionController {

    @Autowired
    DBConnector dbConnector;
    QuestionDAO questionDAO;


}
