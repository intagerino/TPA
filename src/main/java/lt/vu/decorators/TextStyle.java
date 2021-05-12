package lt.vu.decorators;

import javax.enterprise.inject.Model;
import javax.inject.Named;

@Model
public interface TextStyle {
    String style();
}
