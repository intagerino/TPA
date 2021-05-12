package lt.vu.services;

import javax.enterprise.inject.Model;

@Model
public class ProvideString {
    public String text(){
        return "Non-specialized string";
    }
}
