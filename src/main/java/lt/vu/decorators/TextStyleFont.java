package lt.vu.decorators;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Named;


@Model
public class TextStyleFont implements TextStyle{
    @Override
    public String style(){
        return "font-family:'Courier New'";
    }
}