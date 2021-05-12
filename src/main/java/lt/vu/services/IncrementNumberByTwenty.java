package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class IncrementNumberByTwenty implements IncrementNumber{
    public int IncrementNumberBy(int number){
        return number+20;
    }
}