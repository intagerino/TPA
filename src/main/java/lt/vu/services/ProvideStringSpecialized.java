package lt.vu.services;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;

@Model
@Specializes
public class ProvideStringSpecialized extends ProvideString{
    public String text(){
        return "Specialized string";
    }
}
