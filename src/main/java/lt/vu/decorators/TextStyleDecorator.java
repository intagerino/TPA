package lt.vu.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class TextStyleDecorator implements TextStyle{

    @Inject
    @Delegate
    @Any
    TextStyle textStyle;

    @Override
    public String style(){
        return textStyle.style() + ";color:blue";
    }
}
