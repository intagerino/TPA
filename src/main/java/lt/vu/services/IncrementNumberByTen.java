package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@ApplicationScoped
public class IncrementNumberByTen implements IncrementNumber{
    public int IncrementNumberBy(int number){
        return number+10;
    }
}