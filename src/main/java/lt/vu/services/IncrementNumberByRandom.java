package lt.vu.services;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.util.Random;

@ApplicationScoped
@Alternative
public class IncrementNumberByRandom implements IncrementNumber{

    Random rand = new Random();
    private int rand_int = rand.nextInt(100);

    public int IncrementNumberBy(int number){
        return number + rand_int;
    }
}


